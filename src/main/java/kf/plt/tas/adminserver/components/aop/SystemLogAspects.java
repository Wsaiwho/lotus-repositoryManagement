package kf.plt.tas.adminserver.components.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.util.UUIDUtils;
import kf.plt.tas.adminserver.entity.dataentity.KxXtyh;
import kf.plt.tas.adminserver.entity.dataentity.XyLog;
import kf.plt.tas.adminserver.entity.dataentity.XyUri;
import kf.plt.tas.adminserver.mapper.XyLogMapper;
import kf.plt.tas.adminserver.mapper.XyUriMapper;
import kf.plt.tas.adminserver.utils.RemoteIPAddress;

/**
 * 插入系统日志界面
 * @author wangs
 *
 */
@Aspect
@Component
public class SystemLogAspects {
	@Pointcut("@annotation(kf.plt.tas.adminserver.components.aop.SystemLog)")
	public void SystemLog() {}
	
	@Autowired
	private XyUriMapper xyUriMapper;
	@Autowired
	private XyLogMapper xyLogMapper;
	
	/**
	 * 请求成功后
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(value = "SystemLog()", returning = "result")
	public void doAfter(JoinPoint joinPoint, Object result){
		// 获取请求的返回值
		ObjectRestResponse<Object> objectRestResponse = (ObjectRestResponse<Object>)result;
		// 操作成功后，再进行日志的插入
		if ("200".equals(objectRestResponse.getStatus())) {
			// 获取请求
	        ServletRequestAttributes ServletRequestAttributes =
	            (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	        HttpServletRequest request = ServletRequestAttributes.getRequest();
	        
	        // 获取请求中的uri
	        String uri = request.getRequestURI();
	        // 查询uri对应的操作类型和描述
	        XyUri xyUri = xyUriMapper.queryXyUriByUri(uri);
	        if (xyUri != null) {
	        	// 获取用户的ip地址
	            RemoteIPAddress remoteIPAddress = new RemoteIPAddress();
	            String ipAddr = remoteIPAddress.getRemoteIPAddr(request);
	            // 获取用户Id
	            String userId = BaseContextHandler.getUserID();
	            if (userId == null) {
	    			// 获取请求的参数
	    			Object[] params = joinPoint.getArgs();
	    			// 把请求的参数转换为用户实体
	    			KxXtyh kxtyh = (KxXtyh)params[0];
	    			userId = kxtyh.getUserId();
	    		}
	        	XyLog xyLog = new XyLog();
	        	// 主键
	        	xyLog.setId(UUIDUtils.generateUuid());
	        	// ip地址
	        	xyLog.setIpAddress(ipAddr);
	        	// 操作类型
	        	xyLog.setOperationType(xyUri.getOperationType());
	        	// 描述
	        	xyLog.setUpdateMessage(xyUri.getDescription());
	        	// 用户Id
	        	xyLog.setUpdateUser(userId);
	        	// 更新时间
	        	xyLog.setUpdateTime(new Date());
	        	// 请求的uri
	        	xyLog.setUri(uri);
	        	// 插入系统日志
	        	xyLogMapper.insert(xyLog);
			}
		}
		
	}
        
}
