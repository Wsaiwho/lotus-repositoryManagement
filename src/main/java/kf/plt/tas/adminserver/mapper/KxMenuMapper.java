package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.KxMenu;

public interface KxMenuMapper extends CommonMapper<KxMenu> {
	
	/**
	 * 根据用户Id查询菜单
	 * @param userId
	 * @return
	 */
	public List<KxMenu> selectMenuByUserId(@Param("userId")String userId);
	
	/**
	 * 根据角色Id查询菜单
	 * @param userId
	 * @return
	 */
	public List<KxMenu> selectMenuByRoleId(@Param("roleId")String roleId);
	
}