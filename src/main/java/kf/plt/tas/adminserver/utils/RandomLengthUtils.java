package kf.plt.tas.adminserver.utils;

import java.util.Random;

/**
 * 随机生成范围内的数值
 * @author wangs
 *
 */
public class RandomLengthUtils {

	/**
	 * 随机生产范围内的数值
	 * @param min
	 * @param max
	 * @return
	 */
	public static int serectLength(int min,int max) {
	     return new Random().nextInt(max-min+1)+min;
	}
	
}
