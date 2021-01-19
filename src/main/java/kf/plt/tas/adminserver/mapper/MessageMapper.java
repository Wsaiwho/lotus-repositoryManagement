package kf.plt.tas.adminserver.mapper;

import java.util.List;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.Message;

public interface MessageMapper extends CommonMapper<Message> {
	/**
	 * 查询未读的消息
	 * @return
	 */
	public List<Message> selectMessageNoMark();
}