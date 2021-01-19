package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.XyLog;
import kf.plt.tas.adminserver.entity.requestentity.SystemLogRequest;

public interface XyLogMapper extends CommonMapper<XyLog> {
	
	//查询系统数据
	public List<XyLog> queryXyLogByCondition(@Param("systemLogRequest")SystemLogRequest systemLogRequest);
}