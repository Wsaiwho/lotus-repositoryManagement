package kf.plt.tas.adminserver.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.tas.adminserver.entity.dataentity.KxRoleMenu;
import kf.plt.tas.adminserver.entity.voentity.RoleMenuInfo;
import kf.plt.tas.adminserver.mapper.KxRoleMenuMapper;

/**
 * 
 * 角色菜单层
 * 
 * @author wangs
 * @date 2019/10/15
 *
 */
@Service
public class RoleMenuBiz  extends KxBusinessBiz<KxRoleMenuMapper, KxRoleMenu>{
	@Autowired
	private KxRoleMenuMapper kxRoleMenuMapper;
	
	
	 /**
	  * 角色菜单授予
	  * @param roleIds
	  * @return
	  */
	 public ObjectRestResponse<String> addRoleMenus(RoleMenuInfo roleMenuInfo){
		 ObjectRestResponse<String> res = new ObjectRestResponse<String>();
		 // 删除该角色的所有菜单
		 kxRoleMenuMapper.deleteRoleMenuByRoleId(roleMenuInfo.getRoleId());
		 // 新增赋予的菜单
		 if (roleMenuInfo.getMenuIds() != null && roleMenuInfo.getMenuIds().length > 0) {
			 kxRoleMenuMapper.insertRoleMenu(roleMenuInfo);
		 }
		 res.setMessage("角色 菜单授予 成功");
		 return res;
	 }
}
