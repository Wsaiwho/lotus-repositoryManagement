package kf.plt.tas.adminserver.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.tas.adminserver.biz.FileHashBiz;
import kf.plt.tas.adminserver.components.aop.AuditLog;
import kf.plt.tas.adminserver.components.aop.SystemLog;
import kf.plt.tas.adminserver.entity.dataentity.FileHash;

/**
 * 可信文件管理controller
 * 
 * @author wangs
 *
 */
@RestController
@RequestMapping("fileHash")
public class FileHashCotroller extends BusinessController<FileHashBiz, FileHash> {
	
	@SystemLog
	@AuditLog
    @RequestMapping(value = "/insertFileHash", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> insertFileHash(@RequestBody FileHash fileHash) {
        baseBiz.insertFileHash(fileHash);
        return new ObjectRestResponse<>().data("");
    }

    /**
     * 解析Excel文件对象数据
     * 
     * @param request
     * @return
     * @throws Exception
     */
	@SystemLog
	@AuditLog
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> readExcelFile(HttpServletRequest request) throws Exception {
        return baseBiz.saveExcelFileHash(request);
    }

    /**
     * 解析zip以及其他文件对象数据
     * 
     * @param request
     * @return
     * @throws Exception
     */
	@SystemLog
	@AuditLog
    @RequestMapping(value = "/otherFile", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> readOtherFile(HttpServletRequest request) throws Exception {
        return baseBiz.saveFileHash(request);
    }

    /**
     * 批量更新(把不可信文件添加为可信)
     * 
     * @param fileHashList
     * @return
     */
	@SystemLog
	@AuditLog
    @RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> batchInsert(@RequestBody List<FileHash> fileHashList) {
        return baseBiz.batchUpdate(fileHashList);
    }

    /**
     * 批量删除
     * 
     * @param fileHashList
     * @return
     */
	@SystemLog
	@AuditLog
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> batchDelete(@RequestBody List<String> fileHashIds) {
        return baseBiz.batchDelete(fileHashIds);
    }

    /**
     * 根据条件查询
     * 
     * @param fileHashList
     * @return
     */
    @RequestMapping(value = "/queryFileHash", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<FileHash> queryFileHash(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        return baseBiz.queryFileHash(query);
    }

    /**
     * 查询不可信文件
     * 
     * @return
     */
    @RequestMapping(value = "/queryFileHashByUnbelievable", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<List<FileHash>> queryFileHashByUnbelievable() {

        return baseBiz.queryFileHashByUnbelievable();
    }
    
    /**
     * 根据文件list查询不可信文件
     * 
     * @param fileHashList
     * @return
     */
	@SystemLog
	@AuditLog
    @RequestMapping(value = "/getNoKxFile", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<List<FileHash>> getNoKxFile(@RequestBody List<FileHash> fileHashIds) {
        return baseBiz.getNoKxFile(fileHashIds);
    }
	
	/**
	 * 添加zip文件
	 * @param request
	 * @return
	 */
	@RequestMapping("/zipFile")
	@ResponseBody
	public ObjectRestResponse<String> zipFile(HttpServletRequest request) {
		return baseBiz.saveZipFileHash(request);
	}
}
