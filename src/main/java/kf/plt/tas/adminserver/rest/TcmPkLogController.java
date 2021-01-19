package kf.plt.tas.adminserver.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.biz.TcmPkLogBiz;
import kf.plt.tas.adminserver.entity.dataentity.TcmPkLog;

/**
 * 公钥日志管理接口
 * @author wangs
 *
 */
@RequestMapping("tcmPkLog")
@RestController
public class TcmPkLogController extends BusinessController<TcmPkLogBiz, TcmPkLog> {
	
	/**
     * 查询公钥日志信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/queryTcmPkLog", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<TcmPkLog> queryTcmPkLog(@RequestBody Map<String, Object> params) {
    	Query query = new Query(params);
        return baseBiz.queryTcmPKLog(query);
    }

}
