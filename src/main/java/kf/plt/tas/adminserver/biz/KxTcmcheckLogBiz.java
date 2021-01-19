package kf.plt.tas.adminserver.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.UUIDUtils;
import kf.plt.tas.adminserver.entity.dataentity.KxTcmcheckLog;
import kf.plt.tas.adminserver.mapper.KxTcmcheckLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 可信日志文件管理service层
 * @author wangs
 *
 */
@Service
@Slf4j
public class KxTcmcheckLogBiz extends KxBusinessBiz<KxTcmcheckLogMapper, KxTcmcheckLog>{
	
	/**
	 * 根据公钥和时间区间查询数据
	 * @param publicKey 公钥
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public TableResultResponse<KxTcmcheckLog> queryByCondition(String limit,String page,String publicKey,String startTime,String endTime){
		Page<Object> result = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
		List<KxTcmcheckLog> kxTcmcheckLogList = mapper.queryByCondition(publicKey, startTime, endTime);
		return new TableResultResponse<KxTcmcheckLog>(result.getTotal(),kxTcmcheckLogList);
	}
	
	
	/**
	 * 插入认证日志表
	 * @param tcmcheckLog
	 * @return
	 */
	public ObjectRestResponse<Boolean> insertTcmCheckLog(KxTcmcheckLog tcmcheckLog){
		log.info("调用插入认证日志表");
		tcmcheckLog.setId(UUIDUtils.generateUuid().toString());
		mapper.insert(tcmcheckLog);
		return new ObjectRestResponse<>().data(true);
	}
}
