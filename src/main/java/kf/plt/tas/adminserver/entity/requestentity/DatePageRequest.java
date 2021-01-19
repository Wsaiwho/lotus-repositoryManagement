package kf.plt.tas.adminserver.entity.requestentity;

import lombok.Data;
/**
 * 日期分页实体类
 * 
 * @author wangs
 *
 */
@Data
public class DatePageRequest {
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 当前页码
	 */
	private String page;
	/**
	 * 每页条数
	 */
	private String limit;
	
}
