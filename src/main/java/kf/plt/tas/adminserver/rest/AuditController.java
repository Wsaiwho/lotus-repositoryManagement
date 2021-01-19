package kf.plt.tas.adminserver.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.tas.adminserver.biz.AuditBiz;
import kf.plt.tas.adminserver.entity.dataentity.Audit;
import kf.plt.tas.adminserver.entity.requestentity.AuditRequest;

/**
 * 登录日志接口
 * 
 * @author wangs
 *
 */
@RequestMapping("audit")
@RestController
public class AuditController  extends BusinessController<AuditBiz, Audit> {
	
	/**
	 *  查询登录日志
	 * @param auditRequest
	 * @return
	 */
	@RequestMapping(value= "/queryAuditByCondition",method = RequestMethod.POST)
	@ResponseBody
	public TableResultResponse<Audit> queryAuditByCondition(@RequestBody AuditRequest auditRequest){
		return baseBiz.queryAudit(auditRequest);
	}
	

}
