package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.KxXtyh;

public interface KxXtyhMapper extends CommonMapper<KxXtyh> {
	
	 /**
	 * 根据userId获取用户信息
	 * @param userId
	 * @return
	 */
	public KxXtyh queryUserByUserId(@Param("userId")String userId);
	
	
	/**
	 * 更新用户的登录失败次数以及是否被禁用
	 * @param kxXtyh
	 * @return
	 */
	public int updateUserFailLogin(KxXtyh kxXtyh);
	
	/**
	 * 根据用户创建者获取用户信息
	 * @param userId
	 * @return
	 */
	public List<KxXtyh> selectUserByCreator(@Param("userId")String userId);
}