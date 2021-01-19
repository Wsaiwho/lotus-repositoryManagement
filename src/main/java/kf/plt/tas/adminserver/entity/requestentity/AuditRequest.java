package kf.plt.tas.adminserver.entity.requestentity;

import lombok.Data;

/**
 * 登录日志查询请求实体类
 * 
 * @author wangs
 *
 */
@Data
public class AuditRequest extends DatePageRequest{
	
	/**
	 * 操作人
	 */
	private String recorder;
	
	/**
	 * 描述
	 */
	private String description;
}
