package kf.plt.tas.adminserver.biz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.admin.jwt.core.util.jwt.JWTInfo;
import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.service.common.util.UUIDUtils;
import kf.plt.tas.adminserver.components.Cache.RedisCache;
import kf.plt.tas.adminserver.components.jwt.JwtTokenUtil;
import kf.plt.tas.adminserver.components.jwt.UserSecretUtils;
import kf.plt.tas.adminserver.entity.constant.UserConstant;
import kf.plt.tas.adminserver.entity.dataentity.KxUserRole;
import kf.plt.tas.adminserver.entity.dataentity.KxXtyh;
import kf.plt.tas.adminserver.entity.dataentity.LoginLog;
import kf.plt.tas.adminserver.entity.voentity.UserInfo;
import kf.plt.tas.adminserver.mapper.AuditMapper;
import kf.plt.tas.adminserver.mapper.KxUserRoleMapper;
import kf.plt.tas.adminserver.mapper.KxXtyhMapper;
import kf.plt.tas.adminserver.mapper.LoginLogMapper;
import kf.plt.tas.adminserver.utils.Base64;
import kf.plt.tas.adminserver.utils.IdUtils;
import kf.plt.tas.adminserver.utils.RemoteIPAddress;
import kf.plt.tas.adminserver.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 * 用户业务层
 * 
 * @author wangs
 * @date 2019/10/11
 * 
 */
@Slf4j
@Service
public class UserBiz extends KxBusinessBiz<KxXtyhMapper, KxXtyh> {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	@Autowired
	private KxXtyhMapper kxXtyhMapper;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserSecretUtils userSecretUtils;

	@Autowired
	private LoginLogMapper loginLogMapper;

	@Autowired
	private AuditBiz auditBiz;
	@Autowired
	private AuditMapper auditMapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private KxUserRoleMapper kxUserRoleMapper;
	

	/**
	 * 登录
	 * 
	 * @param userId
	 *            账户 ID
	 * @param password
	 *            密码
	 * @param uuid
	 *            验证码 uuid
	 * @param verCode
	 *            验证码
	 * @return
	 * @throws Exception
	 */
	public ObjectRestResponse<String> login(String userId, String password, String uuid, String verCode)
			throws Exception {

		// 验证 验证码是否正确
		String codeRes = redisCache.getCacheObject(UserConstant.CAPTCHA_CODE_KEY + uuid);
		ObjectRestResponse<String> result = new ObjectRestResponse<String>();
		if (codeRes == null) {
			result.setMessage("验证码过期,请重新获取验证码");
			result.setStatus("30107");
			return result;
		} else {
			if (codeRes.equals(verCode)) {
				return VerificationUser(userId, password);
			} else {
				result.setMessage("验证码输入错误");
				result.setStatus("30108");
				return result;
			}
		}
	}

	/**
	 * 用户登录接口(提供给busserver项目的)
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public ObjectRestResponse<String> BusserverLogin(String userId, String password) throws Exception {
		ObjectRestResponse<String> result = new ObjectRestResponse<String>();
		// 根据用户Id查询用户信息
		KxXtyh kxXtyh = kxXtyhMapper.queryUserByUserId(userId);
		// 用户是否存在
		if (kxXtyh != null) {
			// 密码是否正确
			if (encoder.matches(password, kxXtyh.getPassword())) {
				// 登录成功 的情况
				// 生成公钥密钥对
				userSecretUtils.initializeUserSecret(userId);
				// 返回登录token
				String token = "";
				if (!StringUtils.isEmpty(kxXtyh.getUserId())) {
					Map<String, String> otherInfo = new HashMap<>();
					otherInfo.put("isSuperAdmin", kxXtyh.getIsSuperAdmin());
					JWTInfo jwtInfo = new JWTInfo(kxXtyh.getUserId(), kxXtyh.getId(), kxXtyh.getUserName(), new Date(),
							otherInfo);
					token = jwtTokenUtil.generateToken(jwtInfo, kxXtyh.getUserId());
				}
				result.data(token);
			} else {
				// 返回提示
				result.setStatus("30103");
				result.setMessage("填写的密码错误");
			}
		} else {
			result.setStatus("30104");
			result.setMessage("用户不存在");
		}
		return result;
	}

	/**
	 * 验证用户是否合法
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private ObjectRestResponse<String> VerificationUser(String userId, String password) throws Exception {
		// 获取请求
		ServletRequestAttributes ServletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = ServletRequestAttributes.getRequest();
		// 获取用户登录的ip地址
		RemoteIPAddress remoteIPAddress = new RemoteIPAddress();
		String ipAddr = remoteIPAddress.getRemoteIPAddr(request);

		ObjectRestResponse<String> result = new ObjectRestResponse<String>();
		// 根据用户Id查询用户信息
		KxXtyh kxXtyh = kxXtyhMapper.queryUserByUserId(userId);
		// 用户是否存在
		if (kxXtyh != null) {
			// 用户被禁用的情况
			if (!"1".equals(kxXtyh.getIsDisabled())) {
				result.setStatus("30101");
				result.setMessage("该用户无效,请联系管理员");
				// 审计记录
				// auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "被禁用，登录失败"));
				return result;
			}
			// 用户被锁定的情况
			if (!"1".equals(kxXtyh.getIsLock())) {
				// 判断其最后登录时间到现在是否超过24小时
				Date time = new Date(System.currentTimeMillis());
				long day = (time.getTime() - kxXtyh.getLoginLastTime().getTime()) / (24 * 3600 * 1000);
				if (day < 1) {
					// 密码是否正确
					if (!encoder.matches(password, kxXtyh.getPassword())) {
						// 更新用户登录次数和时间
						kxXtyhMapper.updateUserFailLogin(setKxXtyh("0", 5, userId));
					}
					result.setStatus("30102");
					result.setMessage("登录失败，账号被锁定(请联系管理员)");
					// 审计记录
					// auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId +
					// "因失败次数超过上限被锁定"));
					return result;
				}
				// 更新用户登录次数和时间
				kxXtyh.setLoginFailCount(0);
				kxXtyhMapper.updateUserFailLogin(setKxXtyh("1", 0, userId));

				// 审计记录
				auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "由于因24小时前失败次数超过上限被锁定，现在解锁"));
			}

			// 密码是否正确
			if (encoder.matches(password, kxXtyh.getPassword())) {
				// 登录成功 的情况
				// 生成公钥密钥对
				userSecretUtils.initializeUserSecret(kxXtyh.getUserId());
				// 返回登录token
				String token = "";
				if (!StringUtils.isEmpty(kxXtyh.getUserId())) {
					Map<String, String> otherInfo = new HashMap<>();
					otherInfo.put("isSuperAdmin", kxXtyh.getIsSuperAdmin());
					JWTInfo jwtInfo = new JWTInfo(kxXtyh.getUserId(), kxXtyh.getId(), kxXtyh.getUserName(), new Date(),
							otherInfo);
					token = jwtTokenUtil.generateToken(jwtInfo,kxXtyh.getUserId());
				}
				result.data(token);

				// 记录登录日志
				// 查询该用户最近一次登录日志
				LoginLog loginLog = loginLogMapper.queryNewLogByUserId(userId);
				// 把登录次数
				int loginCount = 1;
				if (loginLog != null) {
					loginCount = loginLog.getLoginCount() + 1;
				}

				// 插入登录日志表
				LoginLog logInfo = new LoginLog();
				logInfo.setId(UUIDUtils.generateUuid());
				logInfo.setLoginCount(loginCount);
				logInfo.setUserId(userId);
				logInfo.setLoginTime(new Date());
				logInfo.setLoginIp(ipAddr);
				loginLogMapper.insert(logInfo);

				// 插入审计记录
				// auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "登录"));

				// 更新用户登录次数和时间
				kxXtyhMapper.updateUserFailLogin(setKxXtyh("1", 0, userId));

			} else {
				// 记录该用户是否被禁用 -- 默认：启用
				String isLock = "1";
				// 记录用户登录失败的次数
				int failCount = kxXtyh.getLoginFailCount() + 1;
				// 如果失败的次数大于等于5，将用户设为禁用状态
				if (failCount >= 5) {
					isLock = "0";
				}
				// 更新用户登录次数和时间
				kxXtyhMapper.updateUserFailLogin(setKxXtyh(isLock, failCount, userId));

				// 返回提示
				result.setStatus("30103");
				result.setMessage("填写的密码错误，还有" + (5 - failCount) + "次尝试的机会");
				// 审计记录
				// auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId +
				// "密码错误，请重新输入密码"));
			}
		} else {
			result.setStatus("30104");
			result.setMessage("用户不存在");
			// 审计记录
			// auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "不存在"));
		}
		return result;
	}

	/**
	 * 新增用户
	 * 
	 * @param kxXtyh
	 * @return
	 */
	public ObjectRestResponse<String> addUser(KxXtyh kxXtyh) {
		ObjectRestResponse<String> result = new ObjectRestResponse<>();
		// 根据用户Id查询用户信息
		KxXtyh entity = kxXtyhMapper.queryUserByUserId(kxXtyh.getUserId());
		if (entity != null) {
			result.setStatus("30105");
			result.setMessage("该用户已存在");
		} else {
			// 加密password
			String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(kxXtyh.getPassword());
			kxXtyh.setPassword(password);
			String id = UUIDUtils.generateUuid();
			kxXtyh.setId(id);
			// 设定用户登录失败次数为0
			kxXtyh.setLoginFailCount(0);
			// 设定用户是否启用 - 默认启用
			kxXtyh.setIsDisabled(kxXtyh.getIsDisabled() != null ? kxXtyh.getIsDisabled().toString() : "1");
			// 设定用户是否锁定 - 默认解锁
			kxXtyh.setIsLock(kxXtyh.getIsLock() != null ? kxXtyh.getIsLock().toString() : "1");
			kxXtyh.setCreateTime(new Date());
			kxXtyh.setCreator(BaseContextHandler.getUserID());
			kxXtyhMapper.insert(kxXtyh);
			result.setMessage("添加用户成功");
		}
		return result;
	}

	/**
	 * 更新用户登录失败次数以及是否可用
	 * 
	 * @param isDisabled
	 *            用户是否被禁用
	 * @param failCount
	 *            用户累计登录失败次数
	 * @param userId
	 *            用户Id
	 * @return
	 */
	public KxXtyh setKxXtyh(String isLock, int failCount, String userId) {
		KxXtyh kxXtyh = new KxXtyh();
		kxXtyh.setIsLock(isLock);
		kxXtyh.setLoginFailCount(failCount);
		kxXtyh.setLoginLastTime(new Date());
		kxXtyh.setUserId(userId);
		return kxXtyh;
	}

	/**
	 * 获取验证码
	 * 
	 * @return 返回验证码信息
	 * @throws IOException
	 */
	public Map<String, String> getVerificationCode() {
		Map<String, String> map = new HashMap<String, String>();
		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 唯一标识
		String uuid = IdUtils.simpleUUID();
		String verifyKey = UserConstant.CAPTCHA_CODE_KEY + uuid;

		redisCache.setCacheObject(verifyKey, verifyCode, UserConstant.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
		// 生成图片
		int w = 111, h = 36;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("生成验证码图片失败", e);
		}
		map.put("uuid", uuid);
		map.put("img", Base64.encode(stream.toByteArray()));
		return map;
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public UserInfo getUserInfo() {
		KxXtyh user = mapper.queryUserByUserId(BaseContextHandler.getUserID());
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(user, userInfo);
		return userInfo;
	}

	/**
	 * 修改用户
	 * 
	 * @param kxXtyh
	 * @return
	 */
	public ObjectRestResponse<String> updateUser(KxXtyh kxXtyh) {
		ObjectRestResponse<String> result = new ObjectRestResponse<>();
		// 根据用户Id查询用户信息
		KxXtyh entity = kxXtyhMapper.queryUserByUserId(kxXtyh.getUserId());
		if (entity != null) {
			kxXtyh.setUpdateTime(new Date());
			kxXtyh.setUpdater(BaseContextHandler.getUserID());
			kxXtyhMapper.updateByPrimaryKey(kxXtyh);
			// 判断用户锁定字段是否被修改
			if (!entity.getIsLock().equals(kxXtyh.getIsLock())) {
				if (kxXtyh.getIsLock() != null && "1".equals(kxXtyh.getIsLock().toString())) {
					// 审计记录
					auditMapper.insert(auditBiz.setAudit(kxXtyh.getUserId(), "用户" + kxXtyh.getUserId() + "被管理员解锁"));
				} else {
					// 审计记录
					auditMapper.insert(auditBiz.setAudit(kxXtyh.getUserId(), "用户" + kxXtyh.getUserId() + "被管理员锁定"));
				}
			}
			// 判断用户禁用字段是否被修改
			if (!entity.getIsDisabled().equals(kxXtyh.getIsDisabled())) {
				if (kxXtyh.getIsDisabled() != null && "1".equals(kxXtyh.getIsDisabled().toString())) {
					// 审计记录
					auditMapper.insert(auditBiz.setAudit(kxXtyh.getUserId(), "用户" + kxXtyh.getUserId() + "被管理员启用"));
				} else {
					// 审计记录
					auditMapper.insert(auditBiz.setAudit(kxXtyh.getUserId(), "用户" + kxXtyh.getUserId() + "被管理员禁用"));
				}
			}
			result.setMessage("修改用户成功");
		} else {
			result.setStatus("30106");
			result.setMessage("修改用户异常，用户不存在");
		}
		return result;
	}

	/**
	 * 查询用户创建的用户信息，超级管理员可以查询所有用户的信息
	 * 
	 * @param kxXtyh
	 * @return
	 */
	public TableResultResponse<KxXtyh> queryUser(Query query) {
		String userId = BaseContextHandler.getUserID();
		// 获取用户是否为超级管理员权限
		String isSuperAdmin = BaseContextHandler.get("isSuperAdmin") != null
				? BaseContextHandler.get("isSuperAdmin").toString()
				: "0";
		Page<Object> result = null;
		List<KxXtyh> kxXtyhs = null;
		// 判断用户是否为超级管理员
		if ("1".equals(isSuperAdmin)) {
			result = PageHelper.startPage(query.getPage(), query.getLimit());
			kxXtyhs = kxXtyhMapper.selectAll();
		} else {
			query.put("userId", userId);
			result = PageHelper.startPage(query.getPage(), query.getLimit());
			kxXtyhs = kxXtyhMapper.selectUserByCreator(userId);
		}
		return new TableResultResponse<KxXtyh>(result.getTotal(), kxXtyhs);
	}

	/**
	 * 删除用户，绑定了角色以及存在登录日志的用户不可删
	 * 
	 * @param kxXtyh
	 * @return
	 */
	public ObjectRestResponse<String> deleteUser(KxXtyh kxXtyh) {
		ObjectRestResponse<String> res = new ObjectRestResponse<>();
		// 判断该用户是否存在登录日志
		LoginLog loginLog = loginLogMapper.queryNewLogByUserId(kxXtyh.getUserId());
		if (loginLog != null) {
			res.setStatus("30111");
			res.setMessage("用户存在登录日志，用户不可删除");
			return res;
		}
		// 判断该用户是否绑定了角色
		List<KxUserRole> userRoles = kxUserRoleMapper.selectUserRoleByUserId(kxXtyh.getId());
		if (userRoles != null && userRoles.size() > 0) {
			res.setStatus("30112");
			res.setMessage("用户绑定了角色，用户不可删除");
			return res;
		}
		kxXtyhMapper.deleteByPrimaryKey(kxXtyh);
		res.setMessage("删除用户成功");
		return res;
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPw
	 * @param newPw
	 * @return
	 */
	public ObjectRestResponse<String> updatePassword(String oldPw, String newPw) {
		ObjectRestResponse<String> res = new ObjectRestResponse<>();
		if (oldPw == null || newPw == null) {
			res.setData("");
			res.setMessage("修改密码失败，请检查密码是否填写");
			return res;
		}
		String userId = BaseContextHandler.getUserID();
		KxXtyh kxXtyh = kxXtyhMapper.queryUserByUserId(userId);
		// 判断密码是否正确
		if (!encoder.matches(oldPw, kxXtyh.getPassword())) {
			res.setStatus("");
			res.setMessage("旧密码输入错误");
			return res;
		}
		// 加密password
		String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(newPw);
		kxXtyh.setPassword(password);
		// 更新用户
		kxXtyhMapper.updateByPrimaryKey(kxXtyh);
		res.setMessage("修改成功");
		return res;
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	public ObjectRestResponse<String> resetPassword(KxXtyh kxXtyh) {
		ObjectRestResponse<String> res = new ObjectRestResponse<>();
		String userId = BaseContextHandler.getUserID();
		String password = VerifyCodeUtils.generateVerifyCode(8, UserConstant.PASSWORD);
		// 加密password
		String encodePassword = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(password);
		kxXtyh.setPassword(encodePassword);
		kxXtyh.setUpdater(userId);
		kxXtyh.setUpdateTime(new Date());
		// 更新用户
		kxXtyhMapper.updateByPrimaryKey(kxXtyh);
		res.setMessage("重置成功");
		res.setData(password);
		return res;
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	public ObjectRestResponse<String> loginOut() {
		String userId = BaseContextHandler.getUserID();
		// 清除掉用户公钥密钥对redis
		userSecretUtils.clearUserSecretRedis(userId);
		// 审计记录
		auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "登出"));
		return new ObjectRestResponse<String>().data("");
	}

	/**
	 * 根据帐号进行查询
	 * 
	 * @param terminalCode
	 * @return
	 */
	public TableResultResponse<KxXtyh> queryByUserName(Query query) {
		Example example = new Example(KxXtyh.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andLike("userId", "%" + query.get("userId") + "%");
		Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
		List<KxXtyh> terminalList = mapper.selectByExample(example);
		return new TableResultResponse<KxXtyh>(result.getTotal(), terminalList);
	}

}
