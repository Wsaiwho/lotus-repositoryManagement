package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.KxUserRole;
import kf.plt.tas.adminserver.entity.voentity.UserRoleInfo;

public interface KxUserRoleMapper extends CommonMapper<KxUserRole> {
	
	/**
	 * 根据用户Id查询绑定的角色
	 * @param userId
	 * @return
	 */
	public List<KxUserRole> selectUserRoleByUserId(@Param("userId")String userId);
	
	/**
	 * 根据角色Id查询绑定的角色
	 * @param userId
	 * @return
	 */
	public List<KxUserRole> selectUserRoleByRoleId(@Param("roleId")String roleId);
	
	
	/**
	 * 根据用户Id删除绑定的角色
	 * @param userId
	 */
	public void deleteUserRoleByUserId(@Param("userId")String userId);
	
	/**
	 * 批量插入用户角色授予
	 * @param userRoleInfo
	 */
	public void insertUserRoles(UserRoleInfo userRoleInfo);
	
	
}