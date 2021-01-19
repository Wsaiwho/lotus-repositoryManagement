package kf.plt.tas.adminserver.entity.dataentity;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
@Table(name = "t_xy_log")
public class XyLog {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 操作类型
     */
    @Column(name = "operation_type")
    private String operationType;

    /**
     * 修改字段
     */
    @Column(name = "update_Field")
    private String updateField;

    /**
     * 修改用户 id
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * ip 地址
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 请求uri
     */
    @Column(name = "uri")
    private String uri;

    /**
     * 用户字段名,系统用户名
     */
    @Column(name = "update_message")
    private String updateMessage;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取操作类型
     *
     * @return operation_type - 操作类型
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * 设置操作类型
     *
     * @param operationType 操作类型
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * 获取修改字段
     *
     * @return update_Field - 修改字段
     */
    public String getUpdateField() {
        return updateField;
    }

    /**
     * 设置修改字段
     *
     * @param updateField 修改字段
     */
    public void setUpdateField(String updateField) {
        this.updateField = updateField;
    }

    /**
     * 获取修改用户 id
     *
     * @return update_user - 修改用户 id
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改用户 id
     *
     * @param updateUser 修改用户 id
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取ip 地址
     *
     * @return ip_address - ip 地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置ip 地址
     *
     * @param ipAddress ip 地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
     * 获取修改表名
     *
     * @return update_table - 修改表名
     */
    public String getUri() {
        return uri;
    }

    /**
     * 设置修改表名
     *
     * @param updateTable 修改表名
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 获取用户字段名,系统用户名
     *
     * @return update_message - 用户字段名,系统用户名
     */
    public String getUpdateMessage() {
        return updateMessage;
    }

    /**
     * 设置用户字段名,系统用户名
     *
     * @param updateMessage 用户字段名,系统用户名
     */
    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
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