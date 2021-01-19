package kf.plt.tas.adminserver.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.tas.adminserver.biz.UserRoleBiz;
import kf.plt.tas.adminserver.components.aop.SystemLog;
import kf.plt.tas.adminserver.entity.dataentity.KxUserRole;
import kf.plt.tas.adminserver.entity.voentity.UserRoleInfo;
/**
 * 
 * @author wangs
 *
 */
@RequestMapping("userRole")
@RestController
public class UserRoleController extends BusinessController<UserRoleBiz, KxUserRole>{	
		
		/**
		 * 查询角色列表 - 用户只能查看自己创建的角色(超级管理员可以查看所有)
		 * @return
		 */
		@SystemLog
	    @RequestMapping(value = "addUserRoles", method = RequestMethod.POST)
	    @ResponseBody
	    public ObjectRestResponse<String> addUserRoles(@RequestBody UserRoleInfo userRoleInfo) {
	    	return baseBiz.addUserRoles(userRoleInfo);
	    }
}
