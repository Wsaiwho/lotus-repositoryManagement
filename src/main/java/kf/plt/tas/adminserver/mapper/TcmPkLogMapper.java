package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.TcmPk;
import kf.plt.tas.adminserver.entity.dataentity.TcmPkLog;

public interface TcmPkLogMapper extends CommonMapper<TcmPkLog> {
	
	//批量插入TcmPkLog对象
	public void insertTcmPkLog(@Param("list")List<TcmPkLog> tcmPkLogList);
	
	//根据终端编号查询对象
	public List<TcmPkLog> selectTcmPkLogByTerminalCode(@Param("list")List<TcmPk> tcmPkLogList);
	
	
	//批量删除TcmPkLog对象
	public void deleteTcmPkLog(@Param("list")List<TcmPkLog> tcmPkLogList);
	
	
	
	//根据终端编号进行删除
	public void deleteByTerminalCode(@Param("terminalCode")String terminalCode);
}