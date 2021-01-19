package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.LoginLog;

public interface LoginLogMapper extends CommonMapper<LoginLog> {
	
	/**
	 * 查询userId最近一次登录日志
	 * @param userId
	 * @return
	 */
	public LoginLog queryNewLogByUserId(@Param("userId")String userId);
	
	
	/**
	 * 根据userId查询登录日志
	 * @param userId
	 * @return
	 */
	public List<LoginLog> queryLogByUserId(@Param("userId")String userId);
}