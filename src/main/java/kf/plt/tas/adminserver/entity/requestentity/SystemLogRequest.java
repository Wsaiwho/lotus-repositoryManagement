package kf.plt.tas.adminserver.entity.requestentity;

import lombok.Data;

/**
 * 系统日志查询请求实体类
 * @author wangs
 *
 */
@Data
public class SystemLogRequest extends DatePageRequest {
	/**
	 * 修改用户 id
	 */
	private String userId;
	/**
	 * 操作类型
	 */
	private String operationType;
}
