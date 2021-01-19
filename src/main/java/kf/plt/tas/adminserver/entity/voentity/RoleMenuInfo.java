package kf.plt.tas.adminserver.entity.voentity;

/**
 * 角色菜单授予表
 * @author wangs
 * @date 2019/10/15
 *
 */
public class RoleMenuInfo {
	
	private String roleId;
	
	private String[] menuIds;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}

}
