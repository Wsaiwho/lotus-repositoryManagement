package kf.plt.tas.adminserver.entity.responentity;

import lombok.Data;

/**
 * 公钥管理查询结果实体类
 * @author wangs
 *
 */
@Data
public class TcmPkResponse {
	
	/**
	 * 公钥
	 */
	private String publicKey;
	/**
	 * 终端编号
	 */
	private String terminalCode;
	/**
	 * 终端名称
	 */
	private String terminalName;
	/**
	 * 终端型号
	 */
	private String machineType;

}
