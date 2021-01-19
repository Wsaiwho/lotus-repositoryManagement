package kf.plt.tas.adminserver.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.service.common.util.UUIDUtils;
import kf.plt.tas.adminserver.entity.dataentity.KxRole;
import kf.plt.tas.adminserver.entity.dataentity.KxUserRole;
import kf.plt.tas.adminserver.entity.dataentity.KxXtyh;
import kf.plt.tas.adminserver.mapper.KxRoleMapper;
import kf.plt.tas.adminserver.mapper.KxRoleMenuMapper;
import kf.plt.tas.adminserver.mapper.KxUserRoleMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 * 角色业务层
 * 
 * @author wangs
 * @date 2019/10/14
 *
 */
@Service
public class RoleBiz extends KxBusinessBiz<KxRoleMapper, KxRole> {
	
	@Autowired
	private KxRoleMapper kxRoleMapper;
	
	@Autowired
	private KxUserRoleMapper kxUserRoleMapper;
	
	@Autowired
	private KxRoleMenuMapper kxRoleMenuMapper;
	
	/**
	 * 查询角色列表 - 用户只能查看自己创建的角色(超级管理员可以查看所有)
	 * @return
	 */
	public TableResultResponse<KxRole> queryRoles(Query query){
		// 获取用户Id
		String userId = BaseContextHandler.getUserID();
		// 获取是否为超级管理员
		String isSuperAdmin = BaseContextHandler.get("isSuperAdmin") != null ? BaseContextHandler.get("isSuperAdmin").toString() : "0";
		Example example = new Example(KxRole.class);
		// 拼接sql
        this.splicingCriteria(query, example,isSuperAdmin);
        // 分页
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        // 根据条件查询
        List<KxRole> roles = super.selectByExample(example);
    	return new TableResultResponse<KxRole>(result.getTotal(), roles);
    }
	
	/**
     * 拼接sql
     * 
     * @param query
     * @param example
     */
    public void splicingCriteria(Query query, Example example, String isSuperAdmin) {
        Example.Criteria criteria = example.createCriteria();
        if (query.entrySet().size() > 0) {
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                if (!StringUtils.isEmpty(entry.getValue().toString())) {
                    criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
                }
            }
        }
        // 判断用户是否为超级管理员，如果不是超级管理员，只能看其创建的角色
        if (!"1".equals(isSuperAdmin)) {
        	criteria.andEqualTo("creator", BaseContextHandler.getUserID());
		}
    }
	
    /**
	 * 查询角色列表 - 用户只能查看自己创建的角色(超级管理员可以查看所有)
	 * @return
	 */
	public ObjectRestResponse<List<KxRole>> queryRolesReturnList(){
		ObjectRestResponse<List<KxRole>> res = new ObjectRestResponse<>();
		// 获取用户Id
		String userId = BaseContextHandler.getUserID();
		// 获取是否为超级管理员
		String isSuperAdmin = BaseContextHandler.get("isSuperAdmin") != null ? BaseContextHandler.get("isSuperAdmin").toString() : "0";
		List<KxRole> roles = null;
		// 如果是超级管理员，全部都返回
		if("1".equals(isSuperAdmin)) {
			roles = kxRoleMapper.selectAll();
		}else {
			roles = kxRoleMapper.selectRoleByUserId(userId);
		}
		return res.data(roles);
    }
    
	
	/**
	 * 新增角色
	 * @return
	 */
	public ObjectRestResponse<String> addRole(KxRole kxRole){
		ObjectRestResponse<String> res = new ObjectRestResponse<>();
		// 查询该角色是否以被该管理员创建过
		KxRole role = kxRoleMapper.selectRoleByRoleName(kxRole.getRoleName(),BaseContextHandler.getUserID());
		if (role != null) {
			res.setStatus("33002");
			res.setMessage("新增角色失败");
			return res;
		}
		kxRole.setRoleId(UUIDUtils.generateUuid());
		kxRole.setCreator(BaseContextHandler.getUserID());
		kxRole.setCreateTime(new Date());
		kxRoleMapper.insert(kxRole);
		res.setMessage("新增角色成功");
		return res;
    }
	
	/**
	 * 更新角色
	 * @return
	 */
	public ObjectRestResponse<Boolean> updateRole(KxRole kxRole){
		ObjectRestResponse<Boolean> response = new ObjectRestResponse<>();
		boolean result = false;
		// 查询该角色是否以被该管理员创建过
		KxRole role = kxRoleMapper.selectIsSameRole(kxRole.getRoleName(),kxRole.getCreator(),kxRole.getRoleId());
		if (role != null) {
			response.setStatus("33002");
			response.setMessage("修改角色失败");
			return response.data(result);
		}
		kxRole.setUpdater(BaseContextHandler.getUserID());
		kxRole.setUpdateTime(new Date());
		int res = kxRoleMapper.updateByPrimaryKey(kxRole);
		if (res > 0) {
			result = true;
		}
		return response.data(result);
    }
	
	/**
	 * 删除角色，绑定了用户的角色不能删除
	 * 
	 * @param kxRole
	 * @return
	 */
	public ObjectRestResponse<String> deleteRole(KxRole kxRole){
		ObjectRestResponse<String> res = new ObjectRestResponse<>();
		// 查询用户角色表
		List<KxUserRole> userRoles = kxUserRoleMapper.selectUserRoleByRoleId(kxRole.getRoleId());
		// 判断是否存在用户绑定该角色
		if (userRoles != null && userRoles.size() > 0) {
			res.setStatus("30113");
			res.setMessage("该角色存在用户，不允许删除");
			return res;
		}
		// 删除角色菜单表
		kxRoleMenuMapper.deleteRoleMenuByRoleId(kxRole.getRoleId());
		// 删除角色表
		kxRoleMapper.deleteByPrimaryKey(kxRole);
		res.setMessage("删除角色成功");
		return res;
	}
	
	/**
	 * 查询该用户所授予的角色
	 * @return
	 */
	public ObjectRestResponse<List<KxRole>> queryRoleByUserRole(KxXtyh kxXtyh){
		List<KxRole> kxRoles= kxRoleMapper.selectRoleByUserRole(kxXtyh.getId());
		return new ObjectRestResponse<>().data(kxRoles);
    }
	

}
