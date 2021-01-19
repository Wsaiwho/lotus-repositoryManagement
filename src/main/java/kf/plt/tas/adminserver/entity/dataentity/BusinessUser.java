package kf.plt.tas.adminserver.entity.dataentity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "gqh_business_user")
public class BusinessUser {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 商户名称
     */
    @Column(name = "business_name")
    private String businessName;

    /**
     * 联系方式
     */
    @Column(name = "business_phone")
    private String businessPhone;

    /**
     * 店铺名称
     */
    @Column(name = "shop_name")
    private String shopName;

    /**
     * 店铺地址
     */
    @Column(name = "shop_address")
    private String shopAddress;

    /**
     * 商户等级
     */
    @Column(name = "business_level")
    private String businessLevel;

    /**
     * 创建用户ID
     */
    @Column(name = "crt_user_id")
    private Date crtUserId;

    /**
     * 创建用户名
     */
    @Column(name = "crt_user_name")
    private Date crtUserName;

    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    private Date crtTime;

    /**
     * 更新用户ID
     */
    @Column(name = "upd_user_id")
    private String updUserId;

    /**
     * 更新用户名
     */
    @Column(name = "upd_user_name")
    private String updUserName;

    /**
     * 更新时间
     */
    @Column(name = "upd_time")
    private Date updTime;

    /**
     * 扩展字段
     */
    private String attr1;

    /**
     * 扩展字段
     */
    private String attr2;

    /**
     * 扩展字段
     */
    private String attr3;

    /**
     * 扩展字段
     */
    private String attr4;

    /**
     * 扩展字段
     */
    private String attr5;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取商户名称
     *
     * @return business_name - 商户名称
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * 设置商户名称
     *
     * @param businessName 商户名称
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * 获取联系方式
     *
     * @return business_phone - 联系方式
     */
    public String getBusinessPhone() {
        return businessPhone;
    }

    /**
     * 设置联系方式
     *
     * @param businessPhone 联系方式
     */
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    /**
     * 获取店铺名称
     *
     * @return shop_name - 店铺名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置店铺名称
     *
     * @param shopName 店铺名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取店铺地址
     *
     * @return shop_address - 店铺地址
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * 设置店铺地址
     *
     * @param shopAddress 店铺地址
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    /**
     * 获取商户等级
     *
     * @return business_level - 商户等级
     */
    public String getBusinessLevel() {
        return businessLevel;
    }

    /**
     * 设置商户等级
     *
     * @param businessLevel 商户等级
     */
    public void setBusinessLevel(String businessLevel) {
        this.businessLevel = businessLevel;
    }

    /**
     * 获取创建用户ID
     *
     * @return crt_user_id - 创建用户ID
     */
    public Date getCrtUserId() {
        return crtUserId;
    }

    /**
     * 设置创建用户ID
     *
     * @param crtUserId 创建用户ID
     */
    public void setCrtUserId(Date crtUserId) {
        this.crtUserId = crtUserId;
    }

    /**
     * 获取创建用户名
     *
     * @return crt_user_name - 创建用户名
     */
    public Date getCrtUserName() {
        return crtUserName;
    }

    /**
     * 设置创建用户名
     *
     * @param crtUserName 创建用户名
     */
    public void setCrtUserName(Date crtUserName) {
        this.crtUserName = crtUserName;
    }

    /**
     * 获取创建时间
     *
     * @return crt_time - 创建时间
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * 设置创建时间
     *
     * @param crtTime 创建时间
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * 获取更新用户ID
     *
     * @return upd_user_id - 更新用户ID
     */
    public String getUpdUserId() {
        return updUserId;
    }

    /**
     * 设置更新用户ID
     *
     * @param updUserId 更新用户ID
     */
    public void setUpdUserId(String updUserId) {
        this.updUserId = updUserId;
    }

    /**
     * 获取更新用户名
     *
     * @return upd_user_name - 更新用户名
     */
    public String getUpdUserName() {
        return updUserName;
    }

    /**
     * 设置更新用户名
     *
     * @param updUserName 更新用户名
     */
    public void setUpdUserName(String updUserName) {
        this.updUserName = updUserName;
    }

    /**
     * 获取更新时间
     *
     * @return upd_time - 更新时间
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * 设置更新时间
     *
     * @param updTime 更新时间
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * 获取扩展字段
     *
     * @return attr1 - 扩展字段
     */
    public String getAttr1() {
        return attr1;
    }

    /**
     * 设置扩展字段
     *
     * @param attr1 扩展字段
     */
    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    /**
     * 获取扩展字段
     *
     * @return attr2 - 扩展字段
     */
    public String getAttr2() {
        return attr2;
    }

    /**
     * 设置扩展字段
     *
     * @param attr2 扩展字段
     */
    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    /**
     * 获取扩展字段
     *
     * @return attr3 - 扩展字段
     */
    public String getAttr3() {
        return attr3;
    }

    /**
     * 设置扩展字段
     *
     * @param attr3 扩展字段
     */
    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    /**
     * 获取扩展字段
     *
     * @return attr4 - 扩展字段
     */
    public String getAttr4() {
        return attr4;
    }

    /**
     * 设置扩展字段
     *
     * @param attr4 扩展字段
     */
    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    /**
     * 获取扩展字段
     *
     * @return attr5 - 扩展字段
     */
    public String getAttr5() {
        return attr5;
    }

    /**
     * 设置扩展字段
     *
     * @param attr5 扩展字段
     */
    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }
}