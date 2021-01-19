package kf.plt.tas.adminserver.mapper;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.KxRoleMenu;
import kf.plt.tas.adminserver.entity.voentity.RoleMenuInfo;

public interface KxRoleMenuMapper extends CommonMapper<KxRoleMenu> {
	
	/**
	 * 根据角色Id删除角色菜单表
	 * @param roleId
	 */
	public void deleteRoleMenuByRoleId(@Param("roleId")String roleId);
	
	/**
	 * 批量插入角色菜单授予
	 * @param roleMenuInfo
	 */
	public void insertRoleMenu(RoleMenuInfo roleMenuInfo);
}