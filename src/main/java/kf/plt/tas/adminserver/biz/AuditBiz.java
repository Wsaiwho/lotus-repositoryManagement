package kf.plt.tas.adminserver.biz;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.UUIDUtils;
import kf.plt.tas.adminserver.entity.dataentity.Audit;
import kf.plt.tas.adminserver.entity.requestentity.AuditRequest;
import kf.plt.tas.adminserver.mapper.AuditMapper;

/**
 * 日志记录业务层
 * 
 * @author wangs
 * @date 2019/10/11
 *
 */
@Service
public class AuditBiz extends KxBusinessBiz<AuditMapper, Audit>{
	
	/**
	 * 设置用户操作记录实体
	 * @param recorder
	 * @param description
	 * @return
	 */
	public Audit setAudit(String recorder,String description) {
		Audit audit  = new Audit();
		audit.setId(UUIDUtils.generateUuid());
		audit.setRecorder(recorder);
		audit.setCreateTime(new Date());
		audit.setDescription(description);
		return audit;
	}
	
	/**
	 * 查询用户登录日志
	 * @param auditRequest
	 * @return
	 */
	public TableResultResponse<Audit> queryAudit(AuditRequest auditRequest) {
		int page = auditRequest.getPage() != null ? Integer.parseInt(auditRequest.getPage()) : 1;
		int limit = auditRequest.getLimit() != null ? Integer.parseInt(auditRequest.getLimit()) : 10;
		Page<Object> result = PageHelper.startPage(page,limit);
		auditRequest.setStartTime(auditRequest.getStartTime() != null && !"".equals(auditRequest.getStartTime()) ? auditRequest.getStartTime() + " 00:00:00" : null);
		auditRequest.setEndTime(auditRequest.getEndTime() != null && !"".equals(auditRequest.getEndTime()) ? auditRequest.getEndTime() + " 23:59:59" : null);
		List<Audit> auditList = mapper.queryAuditByCondition(auditRequest);
		return new TableResultResponse<Audit>(result.getTotal(),auditList);
	}
	
	
}
