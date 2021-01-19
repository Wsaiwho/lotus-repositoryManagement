package kf.plt.tas.adminserver.entity.dataentity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_kx_xtyh")
public class KxXtyh {
    /**
     * 主键 id
     */
    @Id
    private String id;

    /**
     * 用户名
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 登录用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否为管理员
     */
    @Column(name = "is_super_admin")
    private String isSuperAdmin;

    /**
     * 是否禁用 0-禁止,1-启用
     */
    @Column(name = "is_disabled")
    private String isDisabled;

    /**
     * 是否锁定 0-锁定,1-解锁
     */
    @Column(name = "is_lock")
    private String isLock;

    /**
     * 登录失败次数
     */
    @Column(name = "login_fail_count")
    private Integer loginFailCount;

    /**
     * 本次登录时间
     */
    @Column(name = "login_last_time")
    private Date loginLastTime;

    /**
     * 最后登录失败的时间
     */
    @Column(name = "login_last_fail_time")
    private Date loginLastFailTime;

    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private String updater;

    @Column(name = "update_time")
    private Date updateTime;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;
    
    /**
     * 获取验证码的uuid
     */
    @Transient
    private String uuid;

    
    /**
     * 验证码的输入结果
     */
    @Transient
    private String verificationCode;


    /**
     * 获取主键 id
     *
     * @return id - 主键 id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键 id
     *
     * @param id 主键 id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return user_id - 用户名
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户名
     *
     * @param userId 用户名
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取登录用户名
     *
     * @return user_name - 登录用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置登录用户名
     *
     * @param userName 登录用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取是否为管理员
     *
     * @return is_super_admin - 是否为管理员
     */
    public String getIsSuperAdmin() {
        return isSuperAdmin;
    }

    /**
     * 设置是否为管理员
     *
     * @param isSuperAdmin 是否为管理员
     */
    public void setIsSuperAdmin(String isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    /**
     * 获取是否禁用 0-禁止,1-启用
     *
     * @return is_disabled - 是否禁用 0-禁止,1-启用
     */
    public String getIsDisabled() {
        return isDisabled;
    }

    /**
     * 设置是否禁用 0-禁止,1-启用
     *
     * @param isDisabled 是否禁用 0-禁止,1-启用
     */
    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    /**
     * 获取是否锁定 0-锁定,1-解锁
     *
     * @return is_lock - 是否锁定 0-锁定,1-解锁
     */
    public String getIsLock() {
        return isLock;
    }

    /**
     * 设置是否锁定 0-锁定,1-解锁
     *
     * @param isLock 是否锁定 0-锁定,1-解锁
     */
    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    /**
     * 获取登录失败次数
     *
     * @return login_fail_count - 登录失败次数
     */
    public Integer getLoginFailCount() {
        return loginFailCount;
    }

    /**
     * 设置登录失败次数
     *
     * @param loginFailCount 登录失败次数
     */
    public void setLoginFailCount(Integer loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    /**
     * 获取本次登录时间
     *
     * @return login_last_time - 本次登录时间
     */
    public Date getLoginLastTime() {
        return loginLastTime;
    }

    /**
     * 设置本次登录时间
     *
     * @param loginLastTime 本次登录时间
     */
    public void setLoginLastTime(Date loginLastTime) {
        this.loginLastTime = loginLastTime;
    }

    /**
     * 获取最后登录失败的时间
     *
     * @return login_last_fail_time - 最后登录失败的时间
     */
    public Date getLoginLastFailTime() {
        return loginLastFailTime;
    }

    /**
     * 设置最后登录失败的时间
     *
     * @param loginLastFailTime 最后登录失败的时间
     */
    public void setLoginLastFailTime(Date loginLastFailTime) {
        this.loginLastFailTime = loginLastFailTime;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return updater
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * @param updater
     */
    public void setUpdater(String updater) {
        this.updater = updater;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
    
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

}