package kf.plt.tas.adminserver.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.biz.RzZdjBiz;
import kf.plt.tas.adminserver.components.aop.SystemLog;
import kf.plt.tas.adminserver.entity.dataentity.RzZdj;

/**
 * 终端管理Controller
 * 
 * @author wangs
 *
 */
@RequestMapping("terminal")
@RestController
public class TerminalController extends BusinessController<RzZdjBiz, RzZdj> {
	
	@SystemLog
    @RequestMapping(value = "/insertRzZdj", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> insertRzZdj(@RequestBody RzZdj rzZdj) {
        return baseBiz.insertRzZdj(rzZdj);
    }

	@SystemLog
    @RequestMapping(value = "/deleteRzZdj", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> deleteRzZdj(@RequestBody RzZdj rzZdj) {
        baseBiz.deleteRzZdj(rzZdj);
        return new ObjectRestResponse<>().data("");
    }
	
	@SystemLog
    @RequestMapping(value = "/updateRzZdj", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> updateRzZdj(@RequestBody RzZdj rzZdj) {
		return baseBiz.updateRzZdj(rzZdj);
    }

    /**
     * 根据终端编号查询
     * 
     * @param terminalCode
     * @return
     */
    @RequestMapping(value = "/queryByTerminalCode", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<RzZdj> queryByTerminalCode(@RequestBody Map<String,Object> params) {
    	Query query = new Query(params);
        return baseBiz.queryByTerminalCode(query);
    }
}
