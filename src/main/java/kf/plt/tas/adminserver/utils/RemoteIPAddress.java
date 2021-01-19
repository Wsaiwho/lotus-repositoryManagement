package kf.plt.tas.adminserver.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class RemoteIPAddress {

	/**
	 *  获取真实的IP地址
	 * @param request
	 * @return
	 */
	public String getRemoteIPAddr(HttpServletRequest request) {
		        String ip = request.getHeader("x-forwarded-for");  
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getHeader("Proxy-Client-IP");  
		        }  
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getHeader("WL-Proxy-Client-IP");  
		        }  
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getHeader("HTTP_CLIENT_IP");  
		        }  
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
		        }  
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getRemoteAddr();  
		        }  
		        return ip;  

	}  
	
}
