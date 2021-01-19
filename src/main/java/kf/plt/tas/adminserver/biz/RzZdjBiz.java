package kf.plt.tas.adminserver.biz;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.entity.dataentity.KxRole;
import kf.plt.tas.adminserver.entity.dataentity.RzZdj;
import kf.plt.tas.adminserver.mapper.RzZdjMapper;
import kf.plt.tas.adminserver.mapper.TcmPkLogMapper;
import kf.plt.tas.adminserver.mapper.TcmPkMapper;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

/**
 * 终端管理业务层
 * 
 * @author wangs
 *
 */
@Service
@Slf4j
public class RzZdjBiz extends KxBusinessBiz<RzZdjMapper,RzZdj>{

	@Autowired
	private TcmPkMapper tcmPkMapper;
	
	@Autowired
	private TcmPkLogMapper tcmPkLogMapper;
	
    /**
     * 插入单条数据
     * @param filehash
     */
    public ObjectRestResponse<String> insertRzZdj(RzZdj rzZdj) {
    	ObjectRestResponse<String> res = new ObjectRestResponse<>();
    	RzZdj terminal = mapper.selectByTerminalCode(rzZdj.getTerminalCode());
    	if (terminal != null) {
    		res.setStatus("34001");
			res.setMessage("添加失败");
			return res;
		}
    	mapper.insertRzZdj(rzZdj.getTerminalCode(),rzZdj.getTerminalName(),rzZdj.getMachineType());
    	res.setMessage("添加成功");
		return res;
    }
    
    /**
     * 更新单条数据
     * @param filehash
     */
    public ObjectRestResponse<String>  updateRzZdj(RzZdj rzZdj) {
    	ObjectRestResponse<Boolean> response = new ObjectRestResponse<>();
		boolean result = false;
		// 查询该角色是否以被该管理员创建过
		RzZdj terminal = mapper.selectIsSameByTerminalCode(rzZdj.getTerminalCode(), rzZdj.getId());
		if (terminal != null) {
			response.setStatus("34002");
			response.setMessage("修改失败");
			return response.data(result);
		}
		rzZdj.setUpdateUser(BaseContextHandler.getUserID());
		rzZdj.setUpdateTime(new Date());
		mapper.updateByPrimaryKey(rzZdj);
		response.setMessage("修改成功");
		return response.data(result);
    }
    
    /**
     * 根据终端编号删除数据
     * @param filehash
     */
    public void deleteRzZdj(RzZdj rzZdj) {
    	mapper.deleteByPrimaryKey(rzZdj);
//    	mapper.delete(rzZdj);
    	tcmPkMapper.deleteByTerminalCode(rzZdj.getTerminalCode());
    	tcmPkLogMapper.deleteByTerminalCode(rzZdj.getTerminalCode());
    	log.info("根据终端编号删除数据");
    }
    
    /**
     * 根据终端编号进行查询
     * @param terminalCode
     * @return
     */
	public TableResultResponse<RzZdj> queryByTerminalCode(Query query) {
		Example example = new Example(RzZdj.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andLike("terminalCode", "%" + query.get("terminalCode") + "%");
		Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
		List<RzZdj> terminalList = mapper.selectByExample(example);
		log.info("根据终端编号进行查询");
		return new TableResultResponse<RzZdj>(result.getTotal(), terminalList);
	}
}
