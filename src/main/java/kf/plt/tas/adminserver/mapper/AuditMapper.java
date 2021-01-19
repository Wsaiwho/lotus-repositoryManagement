package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.Audit;
import kf.plt.tas.adminserver.entity.dataentity.XyLog;
import kf.plt.tas.adminserver.entity.requestentity.AuditRequest;

public interface AuditMapper extends CommonMapper<Audit> {
	
	//查询登录日志数据
	public List<Audit> queryAuditByCondition(@Param("auditRequest")AuditRequest auditRequest);
}