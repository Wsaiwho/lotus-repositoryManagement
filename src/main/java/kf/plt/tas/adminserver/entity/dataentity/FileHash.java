package kf.plt.tas.adminserver.entity.dataentity;

import javax.persistence.*;

@Table(name = "t_file_hash")
public class FileHash {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 文件名 文件所在的决定路径
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件 hash 值
     */
    private String hash;

    /**
     * 文件状态 0为不可信,1为可信
     */
    private String state;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

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
     * 获取文件名 文件所在的决定路径
     *
     * @return file_name - 文件名 文件所在的决定路径
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名 文件所在的决定路径
     *
     * @param fileName 文件名 文件所在的决定路径
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件 hash 值
     *
     * @return hash - 文件 hash 值
     */
    public String getHash() {
        return hash;
    }

    /**
     * 设置文件 hash 值
     *
     * @param hash 文件 hash 值
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * 获取文件状态 0为不可信,1为可信
     *
     * @return state - 文件状态 0为不可信,1为可信
     */
    public String getState() {
        return state;
    }

    /**
     * 设置文件状态 0为不可信,1为可信
     *
     * @param state 文件状态 0为不可信,1为可信
     */
    public void setState(String state) {
        this.state = state;
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