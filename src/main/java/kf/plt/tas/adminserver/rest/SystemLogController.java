package kf.plt.tas.adminserver.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.tas.adminserver.biz.SystemLogBiz;
import kf.plt.tas.adminserver.entity.dataentity.XyLog;
import kf.plt.tas.adminserver.entity.requestentity.SystemLogRequest;
/**
 * 系统日志接口
 * 
 * @author wangs
 *
 */
@RequestMapping("systemLog")
@RestController
public class SystemLogController extends BusinessController<SystemLogBiz, XyLog> {
	
	/**
	 * 查询系统日志
	 * @param systemLogRequest
	 * @return
	 */
	@RequestMapping(value= "/queryXyLogByCondition",method = RequestMethod.POST)
	@ResponseBody
	public TableResultResponse<XyLog> queryXyLogByCondition(@RequestBody SystemLogRequest systemLogRequest){
		return baseBiz.queryXyLog(systemLogRequest);
	}
	
}
