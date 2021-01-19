package kf.plt.tas.adminserver.biz;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.service.common.biz.BusinessBiz;
import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.entity.dataentity.BusinessUser;
import kf.plt.tas.adminserver.mapper.BusinessUserMapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class BusinessUserBiz extends BusinessBiz<BusinessUserMapper,BusinessUser>{

	
	/**
	 * 新增用户
	 * 
	 * @param kxXtyh
	 * @return
	 */
	public ObjectRestResponse<String> addBusinessUser(BusinessUser businessUser) {
		ObjectRestResponse<String> result = new ObjectRestResponse<>();
		mapper.insert(businessUser);
		result.setMessage("添加用户成功");
		return result;
	}
	
	/**
	 * 修改用户
	 * 
	 * @param kxXtyh
	 * @return
	 */
	public ObjectRestResponse<String> updateBusinessUser(BusinessUser businessUser) {
		ObjectRestResponse<String> result = new ObjectRestResponse<>();
		businessUser.setUpdTime(new Date());
		businessUser.setUpdUserId(BaseContextHandler.getUserID());
		mapper.updateByPrimaryKey(businessUser);
		result.setMessage("修改用户成功");
		return result;
	}
	
	
	/**
	 * 查询用户创建的用户信息，超级管理员可以查询所有用户的信息
	 * 
	 * @param kxXtyh
	 * @return
	 */
	public TableResultResponse<BusinessUser> queryBusinessUser(Query query) {
		List<BusinessUser> selectByExample = null;
		String businessName = query.get("businessName").toString();
		if(StringUtils.isEmpty(businessName)) {
			selectByExample = mapper.selectAll();
		}else {
			Example example = new Example(BusinessUser.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andLike("userId", "%" + businessName + "%");
			selectByExample = mapper.selectByExample(example);
		}
		Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
		return new TableResultResponse<BusinessUser>(result.getTotal(), selectByExample);
	}
}
