package kf.plt.tas.adminserver.entity.dataentity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_message")
public class Message {
    /**
     * 主键 id
     */
    @Id
    private Integer id;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 用户 id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 标记 0-未读,1-已读
     */
    @Column(name = "mark")
    private String mark;

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
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键 id
     *
     * @param id 主键 id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取用户 id
     *
     * @return user_id - 用户 id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户 id
     *
     * @param userId 用户 id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取标记 0-未读,1-已读
     *
     * @return mark - 标记 0-未读,1-已读
     */
    public String getMark() {
        return mark;
    }

    /**
     * 设置标记 0-未读,1-已读
     *
     * @param mark 标记 0-未读,1-已读
     */
    public void setMark(String mark) {
        this.mark = mark;
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