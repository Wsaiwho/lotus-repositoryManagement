package kf.plt.tas.adminserver.interceptors;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.admin.jwt.core.util.jwt.IJWTInfo;
import kf.plt.admin.jwt.core.util.jwt.JWTHelper;
import kf.plt.service.common.Interceptor.BaseInterceptor;
import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.tas.adminserver.components.Cache.RedisCache;
import kf.plt.tas.adminserver.entity.voentity.TokenEntity;
import sun.misc.BASE64Decoder;

/**
 * 用户token拦截认证
 */
@Component
public class TokenInterceptor extends BaseInterceptor {
	private Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	@Autowired
	private JWTHelper jwtHelper;
	@Autowired
	private RedisCache redisCache;

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
		
         System.err.println(request.getRequestURI());
        // 获取token
        String token = request.getHeader("Authorization");
        // 获取token的第二段解析出来，再从redis中获取公钥密钥对
        String temp[] = token.split("\\.");
        //解密token第二部分
        BASE64Decoder decoder = new BASE64Decoder();
        String token1 = new String(decoder.decodeBuffer(temp[1] + "="));
        //将json字符串转换为对象
        JSONObject jsonObject=JSONObject.parseObject(token1);
        TokenEntity tokenEntity=(TokenEntity)JSONObject.toJavaObject(jsonObject, TokenEntity.class);
        byte[] pubKey = redisCache.getCacheObject(tokenEntity.getSub() + "Pub");
        logger.info("pubKey是否为空：" + (pubKey == null));
        // 判断token是否为空 - 为空，不进行下面的操作； 不为空，判断token是否超时
        if (!StringUtils.isEmpty(token)) {
        	try {
        		IJWTInfo ijwtInfo = jwtHelper.getInfoFromToken(token, pubKey);
	        	Date now = new Date();
	            if (ijwtInfo.getExpireTime().after(now)) {
	            	logger.info("token有效");
	                BaseContextHandler.setUserID(ijwtInfo.getUniqueName());
	                BaseContextHandler.set("id", ijwtInfo.getId());
	                BaseContextHandler.set("isSuperAdmin", ijwtInfo.getOtherInfo().get("isSuperAdmin"));
	                return true;
	            } else {
	            	logger.info("token超时");
	                response.setCharacterEncoding("utf-8");
	                ObjectRestResponse<String> res = new ObjectRestResponse<>();
	                res.setStatus("30110");
	                res.setMessage("token超时");
	                response.getWriter().print(JSONObject.toJSONString(res));
	                return false;
	            }
        	}catch (Exception e) {
        		logger.info("获取token异常");
            	response.setCharacterEncoding("utf-8");
                ObjectRestResponse<String> res = new ObjectRestResponse<>();
                res.setStatus("30109");
                res.setMessage("获取token异常");
                response.getWriter().print(JSONObject.toJSONString(res));
                return false;
    		}
        } else {
        	logger.info("token为空");
            response.setCharacterEncoding("utf-8");
            ObjectRestResponse<String> res = new ObjectRestResponse<>();
            res.setStatus("30109");
            res.setMessage("获取token异常");
            response.getWriter().print(JSONObject.toJSONString(res));
            return false;
        }
    }

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		BaseContextHandler.remove();
		super.afterCompletion(request, response, handler, ex);
	}
}
