package kf.plt.tas.adminserver.components.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

/**
 * 读取Excel工具类
 * @author wangs
 *
 */
@Component
public class ExcelUtil {

	/**
	 * 获取workbook对象
	 * @param is
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
    public static Workbook getWorkbook(InputStream is,String fileName) throws Exception{
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(is);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(is);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }
    
	/**
	 * getRow(0)      返回第一行
	 * getLastCellNum 获取列数
	 * getCell(0)     返回第一行单元格的信息
	 * getColumnIndex 返回此单元格的列索引
	 * getDateCellValue 读取日期格式数据
	 * getStringCellValue  获取作为字符串的单元格值
	 * 
	 */
    /**
     * 根据表头名称获取该列index
     * @param sheet 表内容
     * @param name 表头名称
     * @return 下标
     */
    public static int getIndexForCell(Sheet sheet, String name) {
    	Row firstRow = sheet.getRow(0);
    	int index = -1;

    	for(int i = 0;i < firstRow.getLastCellNum();i++) {
    		if(firstRow.getCell(i).toString().equals(name)) {
    			index = firstRow.getCell(i).getColumnIndex();
    			break;
    		}
    	}
		return index;
    }
    
    
	public static byte[] inputStreamToByte(InputStream is) throws IOException{
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte[] data = bytestream.toByteArray();
		bytestream.close();
		return data;
	}
}
