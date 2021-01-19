package kf.plt.tas.adminserver.entity.constant;

/**
 * @author wangs
 * @version 2018/1/15.
 */
public class UserConstant {
    /**
     * 密码腌制
     */
    public static int PW_ENCORDER_SALT = 15;

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;
    
    /**
     * 自动生成密码的组成
     */
 	public static final String PASSWORD = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz";
 	
 	/**
 	 * 自动生产userSecret值，用于token加密
 	 */
 	public static final String USERSECRET = "1234567890qwertyuiopasdfghjklmnbvcxzQWERTYUIOPLKJHGFDSAZXCVBNM!@#$%^&*()_+{}?><";

}
