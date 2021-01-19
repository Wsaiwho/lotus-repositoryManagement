package kf.plt.tas.adminserver.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.tas.adminserver.biz.MenuBiz;
import kf.plt.tas.adminserver.entity.dataentity.KxMenu;
import kf.plt.tas.adminserver.entity.dataentity.KxRole;

/**
 * @author wangs
 * @date 2019/10/14 菜单接口模板
 */
@RequestMapping("menu")
@RestController
public class MenuController extends BusinessController<MenuBiz, KxMenu>  {
	 
	/**
	 * 根据用户Id查询菜单,返回树结构
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "queryMenuByUser", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> queryMenuByUser() throws Exception {
        return baseBiz.queryMenuByUser();
    }
    
    /**
	 * 根据用户查询菜单
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "queryMenuByRoleId", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> queryMenuByRoleId(@RequestBody KxRole kxRole) throws Exception {
        return baseBiz.queryMenuByRoleId(kxRole.getRoleId());
    }
    
    /**
	 * 根据用户Id查询菜单,返回列表
	 * @return
	 */
    @RequestMapping(value = "queryMenuByUserList", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> queryMenuByUserList() throws Exception {
        return new ObjectRestResponse<>().data(baseBiz.queryMenuByUserList());
    }
    
    /**
   	 * 根据用户Id查询菜单,返回列表
   	 * @return
   	 */
       @RequestMapping(value = "queryArrayMenuByRoleId", method = RequestMethod.POST)
       @ResponseBody
       public ObjectRestResponse<String> queryArrayMenuByRoleId(@RequestBody KxRole kxRole) throws Exception {
           return new ObjectRestResponse<>().data(baseBiz.queryArrayMenuByRoleId(kxRole.getRoleId()));
       }
    
    

}
