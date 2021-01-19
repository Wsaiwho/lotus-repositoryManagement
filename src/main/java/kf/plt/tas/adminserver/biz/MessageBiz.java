package kf.plt.tas.adminserver.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.entity.dataentity.Message;
import kf.plt.tas.adminserver.mapper.MessageMapper;

/**
 * 消息管理业务层
 * @author wangs
 *
 */
@Service
public class MessageBiz extends KxBusinessBiz<MessageMapper, Message>{
	
	/**
	 * 查询消息
	 * @param query
	 * @return
	 */
	public TableResultResponse<Message> queryMessage(Query query){
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
		List<Message> massage = mapper.selectMessageNoMark();
		return new TableResultResponse<Message>(result.getTotal(), massage);
	}
	
}
