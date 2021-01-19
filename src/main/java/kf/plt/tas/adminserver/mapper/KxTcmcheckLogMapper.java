package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.KxTcmcheckLog;

public interface KxTcmcheckLogMapper extends CommonMapper<KxTcmcheckLog> {
	
	//根据公钥以及时间区间查询数据
	public List<KxTcmcheckLog> queryByCondition(@Param("publicKey")String publicKey,@Param("startTime")String startTime,@Param("endTime")String endTime);
}