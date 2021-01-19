package kf.plt.tas.adminserver.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.tas.adminserver.entity.dataentity.XyLog;
import kf.plt.tas.adminserver.entity.requestentity.SystemLogRequest;
import kf.plt.tas.adminserver.mapper.XyLogMapper;

/**
 * 系统日志业务层
 * @author wangs
 *
 */
@Service
public class SystemLogBiz extends KxBusinessBiz<XyLogMapper, XyLog>{
		
	/**
	 * 查询系统日志
	 * @param xyLog
	 * @return
	 */
	public TableResultResponse<XyLog> queryXyLog(SystemLogRequest systemLogRequest) {
		int page = systemLogRequest.getPage() != null ? Integer.parseInt(systemLogRequest.getPage()) : 1;
		int limit = systemLogRequest.getLimit() != null ? Integer.parseInt(systemLogRequest.getLimit()) : 10;
		Page<Object> result = PageHelper.startPage(page,limit);
		systemLogRequest.setStartTime(systemLogRequest.getStartTime() != null && !"".equals(systemLogRequest.getStartTime()) ? systemLogRequest.getStartTime() + " 00:00:00" : null);
		systemLogRequest.setEndTime(systemLogRequest.getEndTime() != null && !"".equals(systemLogRequest.getEndTime()) ? systemLogRequest.getEndTime() + " 23:59:59" : null);
		List<XyLog> kxXyLogList = mapper.queryXyLogByCondition(systemLogRequest);
		return new TableResultResponse<XyLog>(result.getTotal(),kxXyLogList);
	}
	
}
