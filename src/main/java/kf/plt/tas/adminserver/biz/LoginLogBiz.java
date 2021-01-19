package kf.plt.tas.adminserver.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.entity.dataentity.LoginLog;
import kf.plt.tas.adminserver.mapper.LoginLogMapper;

/**
 * 登录日志业务层
 * @author wangs
 *
 */
@Service
public class LoginLogBiz extends KxBusinessBiz<LoginLogMapper, LoginLog> {

	
	/**
     * 根据用户Id查询登录日志
     * @param query
     * @return
     */
    public TableResultResponse<LoginLog> queryLoginLogByUserId(Query query) {	
    	 String userId = BaseContextHandler.getUserID();
		 Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
		 List<LoginLog> loginLogs = mapper.queryLogByUserId(userId);
	     return new TableResultResponse<LoginLog>(result.getTotal(), loginLogs);
    }
	
}
