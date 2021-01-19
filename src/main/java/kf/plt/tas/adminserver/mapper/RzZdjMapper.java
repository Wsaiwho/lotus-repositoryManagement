package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.RzZdj;
import kf.plt.tas.adminserver.entity.dataentity.TcmPk;

public interface RzZdjMapper extends CommonMapper<RzZdj> {
	
	public void insertRzZdj(@Param("terminalCode")String terminalCode,@Param("terminalName")String termianlName,@Param("machineType")String machineType);
	
	/**
	 * 根据终端编号查询终端信息
	 * @param TerminalCode
	 * @return
	 */
	public RzZdj selectByTerminalCode(@Param("terminalCode") String TerminalCode);
	
	/**
	 * 根据终端编号查询是否存在重复的终端信息
	 * @param TerminalCode
	 * @return
	 */
	public RzZdj selectIsSameByTerminalCode(@Param("terminalCode") String TerminalCode,@Param("id") String id);
	
	/**
	 * 根据终端编号查询终端信息
	 * @param tcmPks
	 * @return
	 */
	public List<RzZdj> selectRzZdjByTerminalCode(@Param("list") List<TcmPk> tcmPks);
}