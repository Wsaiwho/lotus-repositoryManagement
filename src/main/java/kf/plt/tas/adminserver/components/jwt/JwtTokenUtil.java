package kf.plt.tas.adminserver.components.jwt;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import kf.plt.admin.jwt.core.util.jwt.IJWTInfo;
import kf.plt.admin.jwt.core.util.jwt.JWTHelper;
import kf.plt.tas.adminserver.components.Cache.RedisCache;

/**
 * jwt加密token的公共类
 * 
 * @author wangs
 * @date 2019/10/11
 *
 */
@Component
public class JwtTokenUtil {
	   
	    @Value("${jwt.expire}")
	    private int expire;

	    @Autowired
		private RedisCache redisCache;

	    @Autowired
	    private JWTHelper jwtHelper;

	    public String generateToken(IJWTInfo jwtInfo,String userId) throws Exception {
	        Date expireTime = DateTime.now().plusSeconds(expire).toDate();
	        return jwtHelper.generateToken(jwtInfo, redisCache.getCacheObject(userId + "Pri"), expireTime, jwtInfo.getOtherInfo());
	    }

	    public IJWTInfo getInfoFromToken(String token,String userId) throws Exception {
	        IJWTInfo infoFromToken = jwtHelper.getInfoFromToken(token, redisCache.getCacheObject(userId + "Pub"));
	        return infoFromToken;
	    }
	    
	   
}
