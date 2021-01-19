package kf.plt.tas.adminserver.entity.voentity;

import java.util.Date;

import lombok.Data;

/**
 * token第二段解析出来的实体
 * @author wangs
 *
 */
@Data
public class TokenEntity {
	private String sub;
	private String userId;
	private String userName;
	private Date expire;
	private String isSuperAdmin;
}
