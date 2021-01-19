package kf.plt.tas.adminserver.components.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Component;
/**
 * 导入zip的工具类
 * @author wangs
 *
 */
@Component
public class ZipUtils {
	/**
	 * 把MultipartFile转为File
	 * @param ins
	 * @param file
	 */
	public void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
	 * 把流转换为byte数组
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public byte[] inputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte data[] = bytestream.toByteArray();
		bytestream.close();
		return data;
	}
	
}
