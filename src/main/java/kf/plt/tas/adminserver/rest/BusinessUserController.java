package kf.plt.tas.adminserver.rest;

import java.util.Map;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.service.common.util.StringEscapeEditor;
import kf.plt.tas.adminserver.biz.BusinessUserBiz;
import kf.plt.tas.adminserver.components.aop.SystemLog;
import kf.plt.tas.adminserver.entity.dataentity.BusinessUser;

/**
 * 商户管理
 * @author wangs
 *
 */
@RequestMapping("businessUser")
@RestController
public class BusinessUserController extends BusinessController<BusinessUserBiz, BusinessUser> {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
        binder.registerCustomEditor(String[].class, new StringEscapeEditor());
    }


    /**
     * 新增商户
     * 
     * @param kxXtyh
     * @return
     * @throws Exception
     */
    @SystemLog
    @RequestMapping(value = "addBusinessUser", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> addUser(@RequestBody BusinessUser businessUser) {
        return baseBiz.addBusinessUser(businessUser);
    }


    /**
     * 用户修改接口
     * 
     * @param kxXtyh
     * @return
     */
    @SystemLog
    @RequestMapping(value = "updateBusinessUser", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> updateUser(@RequestBody BusinessUser businessUser) {
        return baseBiz.updateBusinessUser(businessUser);
    }
    
    /**
     * 查询用户创建的用户信息，超级管理员可以查询所有用户的信息
     * 
     * @param kxXtyh
     * @return
     */
    @RequestMapping(value = "queryBusinessUser", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<BusinessUser> queryUser(@RequestBody Map<String, Object> params) {
    	Query query = new Query(params);
        return baseBiz.queryBusinessUser(query);
    }
    
    
    /**
     * 删除用户，绑定了角色以及存在登录日志的用户不可删
     * @param kxXtyh
     * @return
     */
    @SuppressWarnings("unchecked")
	@SystemLog
    @RequestMapping(value = "deleteBusinessUser", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> deleteBusinessUser(@RequestBody BusinessUser businessUser){
    	baseBiz.delete(businessUser);
    	return new ObjectRestResponse<String>().data("删除成功");
    }
    
}
