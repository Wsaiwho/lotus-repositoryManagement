package kf.plt.tas.adminserver.entity.dataentity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tcm_pk_log")
public class TcmPkLog {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "myql")
    private String id;

    /**
     * 终端机编码
     */
    @Column(name = "terminal_code")
    private String terminalCode;

    /**
     * 旧的公钥
     */
    @Column(name = "old_public_key")
    private String oldPublicKey;

    /**
     * 更新人
     */
    @Column(name = "new_public_key")
    private String newPublicKey;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新用户 ID
     */
    @Column(name = "user_id")
    private String userId;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取终端机编码
     *
     * @return terminal_code - 终端机编码
     */
    public String getTerminalCode() {
        return terminalCode;
    }

    /**
     * 设置终端机编码
     *
     * @param terminalCode 终端机编码
     */
    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    /**
     * 获取旧的公钥
     *
     * @return old_public_key - 旧的公钥
     */
    public String getOldPublicKey() {
        return oldPublicKey;
    }

    /**
     * 设置旧的公钥
     *
     * @param oldPublicKey 旧的公钥
     */
    public void setOldPublicKey(String oldPublicKey) {
        this.oldPublicKey = oldPublicKey;
    }

    /**
     * 获取更新人
     *
     * @return new_public_key - 更新人
     */
    public String getNewPublicKey() {
        return newPublicKey;
    }

    /**
     * 设置更新人
     *
     * @param newPublicKey 更新人
     */
    public void setNewPublicKey(String newPublicKey) {
        this.newPublicKey = newPublicKey;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新用户 ID
     *
     * @return user_id - 更新用户 ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置更新用户 ID
     *
     * @param userId 更新用户 ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return attr1
     */
    public String getAttr1() {
        return attr1;
    }

    /**
     * @param attr1
     */
    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    /**
     * @return attr2
     */
    public String getAttr2() {
        return attr2;
    }

    /**
     * @param attr2
     */
    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    /**
     * @return attr3
     */
    public String getAttr3() {
        return attr3;
    }

    /**
     * @param attr3
     */
    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    /**
     * @return attr4
     */
    public String getAttr4() {
        return attr4;
    }

    /**
     * @param attr4
     */
    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    /**
     * @return attr5
     */
    public String getAttr5() {
        return attr5;
    }

    /**
     * @param attr5
     */
    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    /**
     * @return attr6
     */
    public String getAttr6() {
        return attr6;
    }

    /**
     * @param attr6
     */
    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }

    /**
     * @return attr7
     */
    public String getAttr7() {
        return attr7;
    }

    /**
     * @param attr7
     */
    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    /**
     * @return attr8
     */
    public String getAttr8() {
        return attr8;
    }

    /**
     * @param attr8
     */
    public void setAttr8(String attr8) {
        this.attr8 = attr8;
    }
}