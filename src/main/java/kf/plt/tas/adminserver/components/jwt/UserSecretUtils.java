package kf.plt.tas.adminserver.components.jwt;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kf.plt.admin.jwt.core.util.RsaKeyHelper;
import kf.plt.tas.adminserver.components.Cache.RedisCache;
import kf.plt.tas.adminserver.entity.constant.UserConstant;
import kf.plt.tas.adminserver.utils.RandomLengthUtils;
import kf.plt.tas.adminserver.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserSecretUtils {
	
	
	@Autowired
	private RsaKeyHelper rsaKeyHelper;
	
	@Autowired
	private RedisCache redisCache;
	
	/**
	 * 初始化
	 * @param userId
	 */
	public void initializeUserSecret(String userId) {
		try {
			// rsa-secret 的长度[16,20]
			int len = RandomLengthUtils.serectLength(16, 20);
			// 随机生成rsa-secret
			String userSecret = VerifyCodeUtils.generateVerifyCode(len, UserConstant.USERSECRET);
			// 生成公钥和密钥
			Map<String, byte[]> keyMap = rsaKeyHelper.generateKey(userSecret);
	        // 把值都存到redis中
	        redisCache.setCacheObject(userId + "UserSecret", userSecret);
	        redisCache.setCacheObject(userId + "Pri", keyMap.get("pri"));
	        redisCache.setCacheObject(userId + "Pub", keyMap.get("pub"));
		}catch (Exception e){
	        log.error("初始化公钥/密钥异常...",e);
	 	}
	}
	
	/**
	 * 清除redis中的密钥信息
	 * @param userId
	 */
	public void clearUserSecretRedis(String userId) {
		redisCache.deleteObject(userId + "UserSecret");
		redisCache.deleteObject(userId + "Pri");
		redisCache.deleteObject(userId + "Pub");
	}
	
}
