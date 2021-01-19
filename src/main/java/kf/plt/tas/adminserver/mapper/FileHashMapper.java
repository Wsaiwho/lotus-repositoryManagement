package kf.plt.tas.adminserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kf.plt.service.common.mapper.CommonMapper;
import kf.plt.tas.adminserver.entity.dataentity.FileHash;

public interface FileHashMapper extends CommonMapper<FileHash> {
	
	public void insertFileHash(@Param("fileName")String fileName,@Param("hash")String hash,@Param("state")String state);
	
	//批量插入FileHash对象
	public void insertFileHashBatch(@Param("list")List<FileHash> fileHashList);
	
	//批量更新state字段
	public void batchUpdate(@Param("list")List<FileHash> fileHashList,@Param("state")String state);
	
	//根据id批量删除
	public void deleteByIds(@Param("fileHashIds")List<String> fileHashIds);
	
	public List<FileHash> queryFileHashByUnbelievable(@Param("state")String state);
	
	// 删除所有不可信的文件
	public void deleteUnbelievable();
	
	
}