package kf.plt.tas.adminserver.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.tas.adminserver.biz.KxTcmcheckLogBiz;
import kf.plt.tas.adminserver.entity.dataentity.KxTcmcheckLog;

/**
 * 可信日志文件管理Controller
 * @author wangs
 *
 */
@RestController
@RequestMapping("/KxTcmcheckLog")
public class KxTcmcheckLogController extends BusinessController<KxTcmcheckLogBiz,KxTcmcheckLog>{
	
	/**
	 * 根据条件查询可信日志文件
	 * @return
	 */
	@RequestMapping(value= "/queryByCondition",method = RequestMethod.POST)
	@ResponseBody
	public TableResultResponse<KxTcmcheckLog> queryByCondition(@RequestParam(defaultValue = "10") String limit,@RequestParam(defaultValue = "1") String page,@RequestParam String publicKey,@RequestParam String startTime,@RequestParam  String endTime){
		return baseBiz.queryByCondition(limit,page,publicKey,startTime,endTime);
		
	}
	
	/**
	 * 插入可信日志文件
	 * @return
	 */
	@RequestMapping(value= "/insertTcmCheckLog",method = RequestMethod.POST)
	@ResponseBody
	public ObjectRestResponse<Boolean> insertTcmCheckLog(@RequestBody KxTcmcheckLog kxTcmcheckLog){
		return baseBiz.insertTcmCheckLog(kxTcmcheckLog);
		
	}
	
}
