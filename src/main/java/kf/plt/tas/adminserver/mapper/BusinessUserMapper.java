package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.BusinessUser;

public interface BusinessUserMapper extends CommonMapper<BusinessUser> {
	
	List<BusinessUser> selectUserByCreator(@Param("userId") String userId);
}