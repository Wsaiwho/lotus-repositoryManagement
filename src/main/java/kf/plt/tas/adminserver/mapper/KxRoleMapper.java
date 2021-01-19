package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.KxRole;

public interface KxRoleMapper extends CommonMapper<KxRole> {
	
	/**
	 * 根据用户Id查询由该用户创建的角色
	 * @param userId
	 * @return
	 */
	public List<KxRole> selectRoleByUserId(@Param("userId")String userId);
	
	/**
	 * 根据角色名查询角色
	 * @param userId
	 * @return
	 */
	public KxRole selectRoleByRoleName(@Param("roleName")String roleName,@Param("creator")String creator);
	
	/**
	 * 查询是否存在重复的角色
	 * @param userId
	 * @return
	 */
	public KxRole selectIsSameRole(@Param("roleName")String roleName,@Param("creator")String creator,@Param("roleId")String roleId);
	
	/**
	 * 查询该用户所授予的角色
	 * @param userId
	 * @return
	 */
	public List<KxRole> selectRoleByUserRole(@Param("userId")String userId);
	
}