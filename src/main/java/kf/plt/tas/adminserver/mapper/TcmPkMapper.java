package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.TcmPk;

public interface TcmPkMapper extends CommonMapper<TcmPk> {
	
	//批量插入TcmPk对象
	public void insertTcmPk(@Param("list")List<TcmPk> tcmPkList);
	
	//批量更新TcmPk对象
	public void updateTcmPk(@Param("list")List<TcmPk> tcmPkList);
	
	//根据终端编号进行删除
	public void deleteByTerminalCode(@Param("terminalCode")String terminalCode);
}