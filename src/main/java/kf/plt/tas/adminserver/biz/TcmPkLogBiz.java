package kf.plt.tas.adminserver.biz;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.entity.dataentity.TcmPkLog;
import kf.plt.tas.adminserver.mapper.TcmPkLogMapper;
import tk.mybatis.mapper.entity.Example;
/**
 * 公钥日志管理业务层
 * 
 * @author wangs
 *
 */
@Service
public class TcmPkLogBiz extends KxBusinessBiz<TcmPkLogMapper, TcmPkLog> {
		
	
	/**
	 * 查询公钥日志信息
	 * @param query
	 * @return
	 */
	public TableResultResponse<TcmPkLog> queryTcmPKLog(Query query){
		
		Example example = new Example(TcmPkLog.class);
        this.splicingCriteria(query, example);
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
		List<TcmPkLog> tcmPks = super.selectByExample(example);
		return new TableResultResponse<TcmPkLog>(result.getTotal(), tcmPks);
	}
	
	/**
     * 拼接sql
     * 
     * @param query
     * @param example
     */
    public void splicingCriteria(Query query, Example example) {
        Example.Criteria criteria = example.createCriteria();
        if (query.entrySet().size() > 0) {
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                if (!StringUtils.isEmpty(entry.getValue().toString())) {
                    criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
                }
            }
        }
    }

}
