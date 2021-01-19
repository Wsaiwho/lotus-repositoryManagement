package kf.plt.tas.adminserver.entity.dataentity;

import javax.persistence.*;

@Table(name = "t_kx_menu")
public class KxMenu {
    /**
     * 菜单 ID
     */
    @Id
    @Column(name = "menu_id")
    private String menuId;

    /**
     * 菜单编码
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 父菜单
     */
    @Column(name = "menu_parent")
    private String menuParent;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单描述
     */
    @Column(name = "menu_desc")
    private String menuDesc;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

    /**
     * 获取菜单 ID
     *
     * @return menu_id - 菜单 ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单 ID
     *
     * @param menuId 菜单 ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取菜单编码
     *
     * @return menu_code - 菜单编码
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单编码
     *
     * @param menuCode 菜单编码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 获取父菜单
     *
     * @return menu_parent - 父菜单
     */
    public String getMenuParent() {
        return menuParent;
    }

    /**
     * 设置父菜单
     *
     * @param menuParent 父菜单
     */
    public void setMenuParent(String menuParent) {
        this.menuParent = menuParent;
    }

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取菜单描述
     *
     * @return menu_desc - 菜单描述
     */
    public String getMenuDesc() {
        return menuDesc;
    }

    /**
     * 设置菜单描述
     *
     * @param menuDesc 菜单描述
     */
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
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