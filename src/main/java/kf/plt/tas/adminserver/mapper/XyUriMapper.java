package kf.plt.tas.adminserver.mapper;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.XyUri;

public interface XyUriMapper extends CommonMapper<XyUri> {
	
	//查询uri对应的操作类型
	public XyUri queryXyUriByUri(@Param("uri")String uri);
}