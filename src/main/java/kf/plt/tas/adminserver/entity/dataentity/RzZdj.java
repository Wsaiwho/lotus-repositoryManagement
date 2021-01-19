package kf.plt.tas.adminserver.entity.dataentity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_rz_zdj")
public class RzZdj {
    /**
     * 主键id
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
     * 名称
     */
    @Column(name = "terminal_name")
    private String terminalName;

    /**
     * 机型
     */
    @Column(name = "machine_type")
    private String machineType;

    /**
     * 创建人id
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 修改时间
     */
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
     * 获取名称
     *
     * @return terminal_name - 名称
     */
    public String getTerminalName() {
        return terminalName;
    }

    /**
     * 设置名称
     *
     * @param terminalName 名称
     */
    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    /**
     * 获取机型
     *
     * @return machine_type - 机型
     */
    public String getMachineType() {
        return machineType;
    }

    /**
     * 设置机型
     *
     * @param machineType 机型
     */
    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    /**
     * 获取创建人id
     *
     * @return create_user - 创建人id
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人id
     *
     * @param createUser 创建人id
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
     * 获取修改人
     *
     * @return update_user - 修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改人
     *
     * @param updateUser 修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
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
}