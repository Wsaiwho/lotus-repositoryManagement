package kf.plt.tas.adminserver.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.biz.MessageBiz;
import kf.plt.tas.adminserver.entity.dataentity.Message;

/**
 * 消息接口
 * @author zhangng
 *
 */
@RestController
@RequestMapping("/message")
public class MessageController extends BusinessController<MessageBiz, Message> {
	
	/**
	 * 查询消息
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "queryMessage", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<Message> queryMessage(@RequestBody Map<String, Object> params) {
    	Query query = new Query(params);
        return baseBiz.queryMessage(query);
    }
}
