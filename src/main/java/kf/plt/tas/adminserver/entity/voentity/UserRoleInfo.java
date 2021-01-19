package kf.plt.tas.adminserver.entity.voentity;

/**
 * 用户角色授予表
 * @author wangs
 * @date 2019/10/15
 *
 */
public class UserRoleInfo {

	private String userId;
	
	private String[] roleIds;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	
}
