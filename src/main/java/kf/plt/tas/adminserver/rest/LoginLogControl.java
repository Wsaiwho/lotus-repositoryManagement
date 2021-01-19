package kf.plt.tas.adminserver.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.biz.LoginLogBiz;
import kf.plt.tas.adminserver.entity.dataentity.LoginLog;
import kf.plt.tas.adminserver.entity.dataentity.Message;

/**
 * 登录日志接口
 * 
 * @author wangs
 *
 */
@RestController
@RequestMapping("/loginLog")
public class LoginLogControl extends BusinessController<LoginLogBiz, LoginLog> {

	
	/**
	 * 根据用户Id查询登录日志
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "queryLoginLog", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<LoginLog> queryLoginLog(@RequestBody Map<String, Object> params) {
    	Query query = new Query(params);
        return baseBiz.queryLoginLogByUserId(query);
    }
}
