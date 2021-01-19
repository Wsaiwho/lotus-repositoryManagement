package kf.plt.tas.adminserver.biz;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.msg.TableResultResponse;
import kf.plt.service.common.util.Query;
import kf.plt.service.common.util.UUIDUtils;
import kf.plt.tas.adminserver.components.utils.ExcelUtil;
import kf.plt.tas.adminserver.components.utils.SM3;
import kf.plt.tas.adminserver.components.utils.ZipUtils;
import kf.plt.tas.adminserver.entity.dataentity.FileHash;
import kf.plt.tas.adminserver.mapper.FileHashMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 可信文件service层
 * 
 * @author wangs
 *
 */
@Service
public class FileHashBiz extends KxBusinessBiz<FileHashMapper, FileHash> {

    /**
     * 读取Excel文件中的数据
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public ObjectRestResponse<String> saveExcelFileHash(HttpServletRequest request) throws Exception {
        // 返回实体对象
        ObjectRestResponse<String> objectRestResponse = new ObjectRestResponse<String>();

        // 将请求转换成MultipartHttpServletRequest对象
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        // 获取请求内容
        MultipartFile excel = multipartRequest.getFile("file");
        // 获取Excel对象
        Workbook workbook = ExcelUtil.getWorkbook(excel.getInputStream(), excel.getOriginalFilename());
        // 获取sheet对象
        Sheet sheet = workbook.getSheetAt(0);
        // 获取文件名所在的index
        int fileNameIndex = ExcelUtil.getIndexForCell(sheet, "文件名");
        // 获取SM3值所在的index
        int SM3Index = ExcelUtil.getIndexForCell(sheet, "SM3值");

        List<FileHash> fileHashList = new ArrayList<>();
        // 循环获取对应值
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            // 获取sheet中第一行数据
            Row row = sheet.getRow(i);
            // 判断值是否存在，存在则保存数据库，不存在则返回错误信息
            if (StringUtils.isEmpty(row.getCell(fileNameIndex).getStringCellValue())
                || StringUtils.isEmpty(row.getCell(SM3Index).getStringCellValue())) {
                objectRestResponse.setStatus("30110");
                objectRestResponse.setData("上传失败");
                return objectRestResponse;
            } else {
                // fileHash对象
                FileHash fileHash = new FileHash();
                fileHash.setId(UUIDUtils.generateUuid().toString());
                fileHash.setFileName(row.getCell(fileNameIndex).getStringCellValue());
                fileHash.setHash(row.getCell(SM3Index).getStringCellValue());
                fileHash.setState("1");
                fileHashList.add(fileHash);
            }
        }
        mapper.insertFileHashBatch(fileHashList);
        objectRestResponse.setData("上传成功");
        return objectRestResponse;
    }

    /**
     * 读取保存文件数据
     * 
     * @param request
     * @return
     * @throws Exception
     * @throws IOException
     */
    public ObjectRestResponse<String> saveFileHash(HttpServletRequest request) throws IOException, Exception {
        ObjectRestResponse<String> objectRestResponse = new ObjectRestResponse<String>();
        try {
            // 获取指定算法的MessageDigest对象
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 请求转换
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            // 获取请求中的内容
            MultipartFile file = multipartRequest.getFile("file");
            // 通过流获取byte数组
            byte[] fileByte = ExcelUtil.inputStreamToByte(file.getInputStream());
            // 获取计算hash值对象
            SM3 sm = new SM3();
            // 初始化sm对象
            sm.initT();
            byte[] xx = sm.doHash(md.digest(fileByte));
            System.out.println();
            // 计算hash值
            String hash = sm.parseCStyleString(xx);
            // 将数据保存在数据库中
            FileHash fileHash = new FileHash();
            fileHash.setId(UUIDUtils.generateUuid().toString());
            fileHash.setFileName(file.getOriginalFilename());
            fileHash.setHash(hash);
            fileHash.setState("1");
            super.insert(fileHash);
            objectRestResponse.setData("上传成功");
            return objectRestResponse;
        } catch (Exception ex) {
            objectRestResponse.setStatus("30110");
            objectRestResponse.setData("上传失败");
            return objectRestResponse;
        }
    }
    
    /**
     * 读取zip保存文件数据
     * 
     * @param request
     * @return
     * @throws Exception
     * @throws IOException
     */
    public ObjectRestResponse<String> saveZipFileHash(HttpServletRequest request) {
        ObjectRestResponse<String> objectRestResponse = new ObjectRestResponse<String>();
        try {
            // 获取指定算法的MessageDigest对象
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 请求转换
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            // 获取请求中的内容
            MultipartFile filea = multipartRequest.getFile("file");
            // 转换
            ZipUtils zipUtil = new ZipUtils();
            File toFile = null;
            if(filea.equals("")||filea.getSize()<=0){
            	filea = null;
            }else {
                    InputStream ins = null;
                    ins = filea.getInputStream();
                    toFile = new File(filea.getOriginalFilename());
                    zipUtil.inputStreamToFile(ins, toFile);
                    ins.close();
            }
			ZipFile zipFile = new ZipFile(toFile,"GBK");
			Enumeration<ZipEntry> zipEntries = zipFile.getEntries();
			while(zipEntries.hasMoreElements()){
				ZipEntry zipEntry = zipEntries.nextElement();
				if(zipEntry.isDirectory()){
					continue;
				}
				SM3 sm=new SM3();
				sm.initT();
		    	byte[] xx= sm.doHash(md.digest(zipUtil.inputStreamToByte(zipFile.getInputStream(zipEntry))));//先sha1后sm3
		    	String fileName = zipEntry.getName();
		    	String hash = sm.parseCStyleString(xx);
		    	 // 将数据保存在数据库中
	            FileHash fileHash = new FileHash();
	            fileHash.setId(UUIDUtils.generateUuid().toString());
	            fileHash.setFileName(fileName);
	            fileHash.setHash(hash);
	            fileHash.setState("1");
			}
			
            objectRestResponse.setData("上传成功");
            return objectRestResponse;
        } catch (Exception ex) {
            objectRestResponse.setStatus("30110");
            objectRestResponse.setData("上传失败");
            return objectRestResponse;
        }
    }
    

    /**
     * 批量更新，将不可信文件更新为可信文件
     * 
     * @param fileHashList
     * @return
     */
    public ObjectRestResponse<String> batchUpdate(List<FileHash> fileHashList) {
        ObjectRestResponse<String> objectRestResponse = new ObjectRestResponse<>();
        try {
            // 批量更新
            mapper.batchUpdate(fileHashList, "1");
            objectRestResponse.setData("设置为可信文件成功");
            return objectRestResponse;
        } catch (Exception ex) {
            objectRestResponse.setStatus("30111");
            objectRestResponse.setData("设置为可信文件失败");
            return objectRestResponse;
        }
    }

    /**
     * 批量删除
     * 
     * @param fileHashIds
     * @return
     */
    public ObjectRestResponse<String> batchDelete(List<String> fileHashIds) {
        ObjectRestResponse<String> objectRestResponse = new ObjectRestResponse<>();
        try {
            // 批量删除
            mapper.deleteByIds(fileHashIds);
            objectRestResponse.setData("批量删除成功");
            return objectRestResponse;
        } catch (Exception ex) {
            objectRestResponse.setStatus("30112");
            objectRestResponse.setData("批量删除失败");
            return objectRestResponse;
        }
    }

    /**
     * 根据条件查询fileHash数据
     * 
     * @return
     */
    public TableResultResponse<FileHash> queryFileHash(Query query) {
        Example example = new Example(FileHash.class);
        this.splicingCriteria(query, example);
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<FileHash> fileHashList = super.selectByExample(example);
        return new TableResultResponse<FileHash>(result.getTotal(), fileHashList);
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
    
    /**
     * 插入单条数据
     * @param filehash
     */
    public void insertFileHash(FileHash filehash) {
    	mapper.insertFileHash(filehash.getFileName(), filehash.getHash(), "1");
    }
    
    /**
     * 查询不可信文件
     * @param filehash
     */
    @SuppressWarnings("unchecked")
	public ObjectRestResponse<List<FileHash>> queryFileHashByUnbelievable() {
    	List<FileHash> queryFileHashByUnbelievable = mapper.queryFileHashByUnbelievable("0");
		return new ObjectRestResponse<List<FileHash>>().data(queryFileHashByUnbelievable);
    }
    
    
	@SuppressWarnings("unchecked")
	public ObjectRestResponse<List<FileHash>> getNoKxFile(List<FileHash> fileHashs){   	
		List<FileHash> list = new ArrayList<FileHash>();
		// 获取数据库中的可信文件
    	List<FileHash> kxFileHashs = mapper.queryFileHashByUnbelievable("1");
    	if(kxFileHashs.size() <= 0) {
    		for(FileHash fileHash : fileHashs) {
    			fileHash.setId(UUIDUtils.generateUuid().toString());
    			fileHash.setState("0");
    		}
    		mapper.insertFileHashBatch(fileHashs);
    		return new ObjectRestResponse<List<FileHash>>().data(fileHashs);
    	}
    	for(FileHash fileHash : fileHashs) {
    		fileHash.setId(UUIDUtils.generateUuid().toString());
    		fileHash.setState("0");
    		boolean contained = false;
    		for(FileHash fileHash1 : kxFileHashs) {
    			if(fileHash1.getHash().equals(fileHash.getHash()) && getFileName(fileHash1.getFileName()).equals(getFileName(fileHash.getFileName()))) {
    				contained = true;
    				break;
    			}
    		}
    		if(!contained) {
    			list.add(fileHash);
    		}
    	}
    	// 删除所有的不可信文件
    	mapper.deleteUnbelievable();
    	if(list.size() > 0) {
    		mapper.insertFileHashBatch(list);
    	}
    	return new ObjectRestResponse<List<FileHash>>().data(list);
    }
	/**
	 * 获取文件名(不含路径)
	 * @param file
	 * @return
	 */
	private String getFileName(String str) {
		return str.substring(str.lastIndexOf("/")+1);
	}
}
