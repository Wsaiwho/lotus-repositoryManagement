package kf.plt.tas.adminserver.entity.responentity;

import lombok.Data;
/**
 * 公钥管理失败原因实体类
 * @author wangs
 *
 */
@Data
public class PublicKeyManageResponse {
	/**
	 * 公钥
	 */
	private String publicKey;
	/**
	 * 终端编号
	 */
	private String terminalCode;
	/**
	 * 原因
	 */
	private String reason;
	
}
