package kf.plt.tas.adminserver.components.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.tas.adminserver.biz.AuditBiz;
import kf.plt.tas.adminserver.entity.dataentity.KxXtyh;
import kf.plt.tas.adminserver.mapper.AuditMapper;

/**
 * 用户操作记录切面
 * @author wangs
 *
 */
@Aspect
@Component
public class AuditLogAspects {
	
	@Autowired
    private AuditMapper auditMapper;
	@Autowired
	private AuditBiz auditBiz;
	
	@Pointcut("@annotation(kf.plt.tas.adminserver.components.aop.AuditLog)")
	public void auditLog() {}
	
	/**
	 * 请求成功后
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(value = "auditLog()", returning = "result")
	public void doAfter(JoinPoint joinPoint, Object result){   
		// 获取请求
        ServletRequestAttributes ServletRequestAttributes =
            (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ServletRequestAttributes.getRequest();
        
        // 获取请求中的uri
        String uri = request.getRequestURI();
        
		// 获取用户Id
		String userId = BaseContextHandler.getUserID();
		// 如果获取不到token中的userId(登录功能)
		if (userId == null) {
			// 获取请求的参数
			Object[] params = joinPoint.getArgs();
			// 把请求的参数转换为用户实体
			KxXtyh kxtyh = (KxXtyh)params[0];
			userId = kxtyh.getUserId();
		}
		// 获取请求的返回值
		ObjectRestResponse<Object> objectRestResponse = (ObjectRestResponse<Object>)result;
		// 审计记录
		String status = objectRestResponse.getStatus();
		// 如果是登录请求
		if ("/api/user/login".equals(uri)) {
			switch (status) {
				case "30101":
					// 用户被禁用
					auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "被禁用，登录失败"));
					break;
				case "30102":
					// 用户被锁定
					auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "因失败次数超过上限被锁定"));
					break;
				case "30103":
					// 用户输入密码错误
					auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "密码错误，请重新输入密码"));
					break;
				case "30104":
					// 用户Id不存在
					auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "不存在"));
					break;
				case "200":
					// 用户登录成功
					auditMapper.insert(auditBiz.setAudit(userId, "用户" + userId + "登录"));
					break;
				default:
					break;
			}
		}
		// 如果是批量新增FileHash
		if ("/api/fileHash/excel".equals(uri)) {
			// 新增成功
			if ("200".equals(status)) {
				// fileHash批量新增成功
				auditMapper.insert(auditBiz.setAudit(userId, "管理员批量新增可信文件与hash"));
			}
		}
		// 如果是单条操作FileHash
		if ("/api/fileHash/otherFile".equals(uri) || "/api/fileHash/insertFileHash".equals(uri)) {
			// 新增成功
			if ("200".equals(status)) {
				// fileHash单挑操作新增成功
				auditMapper.insert(auditBiz.setAudit(userId, "管理员新增可信文件与hash"));
			}
		}
		
		// 如果是批量更新不可信文件为可信文件
		if ("/api/fileHash/batchUpdate".equals(uri)) {
			// 更新成功
			if ("200".equals(status)) {
				// fileHash批量更新成功
				auditMapper.insert(auditBiz.setAudit(userId, "管理员更新不可信文件为可信文件"));
			}
		}
		
		// 如果是批量删除可信文件
		if ("/api/fileHash/batchDelete".equals(uri)) {
			// 删除成功
			if ("200".equals(status)) {
				// fileHash批量删除成功
				auditMapper.insert(auditBiz.setAudit(userId, "管理员删除可信文件"));
			}
		}
		
	}

}
