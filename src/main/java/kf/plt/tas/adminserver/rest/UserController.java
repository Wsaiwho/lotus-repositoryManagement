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
import kf.plt.tas.adminserver.biz.UserBiz;
import kf.plt.tas.adminserver.components.aop.AuditLog;
import kf.plt.tas.adminserver.components.aop.SystemLog;
import kf.plt.tas.adminserver.entity.dataentity.KxXtyh;
import kf.plt.tas.adminserver.entity.voentity.UserInfo;

/**
 * @author wangs
 * @date 2019/10/11 用户接口模块
 */
@RequestMapping("user")
@RestController
public class UserController extends BusinessController<UserBiz, KxXtyh> {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
        binder.registerCustomEditor(String[].class, new StringEscapeEditor());
    }

    /**
     * 用户登录接口
     * 
     * @param kxXtyh
     * @return
     * @throws Exception
     */
    @SystemLog
    @AuditLog
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> invalid(@RequestBody KxXtyh kxXtyh) throws Exception {
        return baseBiz.login(kxXtyh.getUserId(), kxXtyh.getPassword(), kxXtyh.getUuid(), kxXtyh.getVerificationCode());
    }
    
    /**
     * 用户登录接口(提供给busserver项目的)
     * 
     * @param kxXtyh
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "busserverLogin", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> busserverLogin(@RequestBody KxXtyh kxXtyh) throws Exception {
        return baseBiz.BusserverLogin(kxXtyh.getUserId(), kxXtyh.getPassword());
    }

    /**
     * 用户注册接口
     * 
     * @param kxXtyh
     * @return
     * @throws Exception
     */
    @SystemLog
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> addUser(@RequestBody KxXtyh kxXtyh) {
        return baseBiz.addUser(kxXtyh);
    }

    /**
     * 获取验证码
     * 
     * @return
     */
    @RequestMapping(value = "getcode", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, String>> getVerificationCode() {
        return new ObjectRestResponse<>().data(baseBiz.getVerificationCode());
    }

    @RequestMapping(value = "info", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<UserInfo> getUserInfo() {
        return new ObjectRestResponse<UserInfo>().data(baseBiz.getUserInfo());
    }

    /**
     * 用户修改接口
     * 
     * @param kxXtyh
     * @return
     */
    @SystemLog
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> updateUser(@RequestBody KxXtyh kxXtyh) {
        return baseBiz.updateUser(kxXtyh);
    }
    
    /**
     * 查询用户创建的用户信息，超级管理员可以查询所有用户的信息
     * 
     * @param kxXtyh
     * @return
     */
    @RequestMapping(value = "queryUser", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<KxXtyh> queryUser(@RequestBody Map<String, Object> params) {
    	Query query = new Query(params);
        return baseBiz.queryUser(query);
    }
    /**
     * 删除用户，绑定了角色以及存在登录日志的用户不可删
     * @param kxXtyh
     * @return
     */
    @SystemLog
    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> deleteUser(@RequestBody KxXtyh kxXtyh){
    	return baseBiz.deleteUser(kxXtyh);
    }
    
    /**
     * 修改用户密码
     * @param kxXtyh
     * @return
     */
    @SystemLog
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> updatePassword(@RequestBody Map<String, Object> params){
    	String oldPw = params.get("oldPw") != null ? params.get("oldPw").toString() : null;
    	String newPw = params.get("newPw") != null ? params.get("newPw").toString() : null;;
    	return baseBiz.updatePassword(oldPw, newPw);
    }
    
    /**
     * 重置密码
     * @param kxXtyh
     * @return
     */
    @SystemLog
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> resetPassword(@RequestBody KxXtyh kxXtyh){
    	return baseBiz.resetPassword(kxXtyh);
    }
    
    /**
     * 用户登出接口
     * 
     * @param kxXtyh
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "loginOut", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> loginOut() throws Exception {
        return baseBiz.loginOut();
    }
    
    /**
     * 根据帐号查询
     * 
     * @param terminalCode
     * @return
     */
    @RequestMapping(value = "/queryByUserName", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<KxXtyh> queryByUserName(@RequestBody Map<String,Object> params) {
    	Query query = new Query(params);
        return baseBiz.queryByUserName(query);
    }
}
