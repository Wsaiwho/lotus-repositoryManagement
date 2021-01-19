package kf.plt.tas.adminserver.entity.dataentity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_kx_tcmcheck_log")
public class KxTcmcheckLog {
    /**
     * 主键 id
     */
    @Id
    private String id;

    /**
     * 检验值
     */
    private String bm;

    /**
     * 认证时间
     */
    @Column(name = "certification_time")
    private Date certificationTime;

    /**
     * 失败状态 0是失败,1 是成功
     */
    private String isfail;

    /**
     * 失败原因
     */
    private String reason;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

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
     * 获取检验值
     *
     * @return bm - 检验值
     */
    public String getBm() {
        return bm;
    }

    /**
     * 设置检验值
     *
     * @param bm 检验值
     */
    public void setBm(String bm) {
        this.bm = bm;
    }

    /**
     * 获取认证时间
     *
     * @return certification_time - 认证时间
     */
    public Date getCertificationTime() {
        return certificationTime;
    }

    /**
     * 设置认证时间
     *
     * @param certificationTime 认证时间
     */
    public void setCertificationTime(Date certificationTime) {
        this.certificationTime = certificationTime;
    }

    /**
     * 获取失败状态 0是失败,1 是成功
     *
     * @return isfail - 失败状态 0是失败,1 是成功
     */
    public String getIsfail() {
        return isfail;
    }

    /**
     * 设置失败状态 0是失败,1 是成功
     *
     * @param isfail 失败状态 0是失败,1 是成功
     */
    public void setIsfail(String isfail) {
        this.isfail = isfail;
    }

    /**
     * 获取失败原因
     *
     * @return reason - 失败原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置失败原因
     *
     * @param reason 失败原因
     */
    public void setReason(String reason) {
        this.reason = reason;
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