package kf.plt.tas.adminserver.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.tas.adminserver.biz.RoleMenuBiz;
import kf.plt.tas.adminserver.components.aop.SystemLog;
import kf.plt.tas.adminserver.entity.dataentity.KxRoleMenu;
import kf.plt.tas.adminserver.entity.voentity.RoleMenuInfo;

@RequestMapping("roleMenu")
@RestController
public class RoleMenuController  extends BusinessController<RoleMenuBiz, KxRoleMenu>{	
	
	
	/**
	 * 查询角色列表 - 用户只能查看自己创建的角色(超级管理员可以查看所有)
	 * @return
	 */
	@SystemLog
    @RequestMapping(value = "addRoleMenus", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> addRoleMenus(@RequestBody RoleMenuInfo roleMenuInfo) {
    	return baseBiz.addRoleMenus(roleMenuInfo);
    }
}
