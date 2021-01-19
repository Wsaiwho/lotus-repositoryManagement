package kf.plt.tas.adminserver.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.tas.adminserver.entity.dataentity.KxUserRole;
import kf.plt.tas.adminserver.entity.voentity.UserRoleInfo;
import kf.plt.tas.adminserver.mapper.KxUserRoleMapper;

/**
 * 
 * 用户角色层
 * 
 * @author wangs
 * @date 2019/10/15
 *
 */
@Service
public class UserRoleBiz extends KxBusinessBiz<KxUserRoleMapper, KxUserRole>{
	
	@Autowired
	private KxUserRoleMapper kxUserRoleMapper;
	
	
	 /**
	  * 用户角色授予
	  * @param roleIds
	  * @return
	  */
	 public ObjectRestResponse<String> addUserRoles(UserRoleInfo userRoleInfo){
		 ObjectRestResponse<String> res = new ObjectRestResponse<String>();
		 // 删除该用户的所有角色
		 kxUserRoleMapper.deleteUserRoleByUserId(userRoleInfo.getUserId());
		 // 新增赋予的角色
		 if (userRoleInfo.getRoleIds() != null && userRoleInfo.getRoleIds().length > 0) {
			 kxUserRoleMapper.insertUserRoles(userRoleInfo);
		 }
		 res.setMessage("用户 角色授予 成功");
		 return res;
	 }
	 
	
}
