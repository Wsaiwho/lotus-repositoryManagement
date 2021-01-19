package kf.plt.tas.adminserver.rest;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.biz.RoleBiz;
import kf.plt.tas.adminserver.components.aop.SystemLog;
import kf.plt.tas.adminserver.entity.dataentity.KxRole;
import kf.plt.tas.adminserver.entity.dataentity.KxXtyh;

/**
 * @author wangs
 * @date 2019/10/14 角色接口模块
 */
@RequestMapping("role")
@RestController
public class RoleController  extends BusinessController<RoleBiz, KxRole> {
	
	/**
	 * 查询角色列表 - 用户只能查看自己创建的角色(超级管理员可以查看所有)
	 * @return
	 */
    @RequestMapping(value = "queryRoles", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<KxRole> queryRoles(@RequestBody Map<String, Object> params) {
    	Query query = new Query(params);
        return baseBiz.queryRoles(query);
    }
    
    /**
	 * 查询角色列表(返回列表) - 用户只能查看自己创建的角色(超级管理员可以查看所有)
	 * @return
	 */
    @RequestMapping(value = "queryRolesReturnList")
    @ResponseBody
    public ObjectRestResponse<List<KxRole>> queryRolesReturnList() {
        return baseBiz.queryRolesReturnList();
    }
    
    /**
	 * 新增角色
	 * @return
	 */
    @SystemLog
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> addRole(@RequestBody KxRole kxRole) {
        return baseBiz.addRole(kxRole);
    }
    
    /**
	 * 修改角色
	 * @return
	 */
    @SystemLog
    @RequestMapping(value = "updateRole", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Boolean> updateRole(@RequestBody KxRole kxRole) {
    	return baseBiz.updateRole(kxRole);
    }
    
    /**
	 * 删除角色
	 * @return
	 */
    @SystemLog
    @RequestMapping(value = "deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> deleteRole(@RequestBody KxRole kxRole) {
    	return baseBiz.deleteRole(kxRole);
    }
    
    /**
   	 * 查询该用户所授予的角色
   	 * @return
   	 */
       @SystemLog
       @RequestMapping(value = "queryRoleByUserRole", method = RequestMethod.POST)
       @ResponseBody
       public ObjectRestResponse<List<KxRole>> queryRoleByUserRole(@RequestBody KxXtyh kxXtyh) {
       	return baseBiz.queryRoleByUserRole(kxXtyh);
       }
    

}
