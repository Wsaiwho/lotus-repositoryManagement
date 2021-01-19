package kf.plt.tas.adminserver.biz;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kf.plt.admin.jwt.core.context.BaseContextHandler;
import kf.plt.service.common.msg.ObjectRestResponse;
import kf.plt.service.common.util.TreeUtil;
import kf.plt.tas.adminserver.entity.constant.MenuConstant;
import kf.plt.tas.adminserver.entity.dataentity.KxMenu;
import kf.plt.tas.adminserver.entity.voentity.MenuTree;
import kf.plt.tas.adminserver.mapper.KxMenuMapper;

/**
 * 菜单业务层
 * 
 * @author wangs
 * @date 2019/10/14
 *
 */
@Service
public class MenuBiz  extends KxBusinessBiz<KxMenuMapper, KxMenu>{
	
    @Autowired
    private KxMenuMapper kxMenuMapper;
	
	/**
     * 根据用户Id查询菜单,返回树结构
     * @param kxMenu
     * @return
	 * @throws Exception 
     */
	public ObjectRestResponse<String> queryMenuByUser() throws Exception {
		List<KxMenu> menus =  queryMenuByUserList();
		List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
		for (KxMenu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            node.setParentId(menu.getMenuParent());
            node.setId(menu.getMenuId());
            trees.add(node);
        }
        List<MenuTree> menuTrees = TreeUtil.bulid(trees, MenuConstant.MENU_ROOT_PARENT, null);
        return new ObjectRestResponse<>().data(menuTrees.size() > 0 ? menuTrees.get(0).getChildren() : menuTrees);
	}
	
	/**
     * 根据用户Id查询菜单,返回列表
     * @param kxMenu
     * @return
	 * @throws Exception 
     */
	public List<KxMenu> queryMenuByUserList() throws Exception {
		String userId = BaseContextHandler.get("id").toString();
		// 获取用户是否为超级管理员权限
		String isSuperAdmin = BaseContextHandler.get("isSuperAdmin") != null ? BaseContextHandler.get("isSuperAdmin").toString() : "0";
		List<KxMenu> menus = null;
		// 判断用户是否为超级管理员
		if ("1".equals(isSuperAdmin)) {
			// 获取所有的菜单
			menus = kxMenuMapper.selectAll();
		}else {
			// 获取用户角色的菜单
			menus = kxMenuMapper.selectMenuByUserId(userId);
		}
        return menus;
	}
	
	
	/**
	 * 根据角色Id查询菜单页(返回的是树结构)
	 * @return
	 * @throws Exception
	 */
	public ObjectRestResponse<String> queryMenuByRoleId(String roleId) throws Exception {
		List<KxMenu> menus = kxMenuMapper.selectMenuByRoleId(roleId);
		List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
		for (KxMenu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            node.setParentId(menu.getMenuParent());
            node.setId(menu.getMenuId());
            trees.add(node);
        }
        List<MenuTree> menuTrees = TreeUtil.bulid(trees, MenuConstant.MENU_ROOT_PARENT, null);
        return new ObjectRestResponse<>().data(menuTrees.size() > 0 ? menuTrees.get(0).getChildren() : menuTrees);
	}
	
	/**
	 * 根据角色Id查询菜单页(返回的是菜单数组)
	 * @return
	 * @throws Exception
	 */
	public ObjectRestResponse<String> queryArrayMenuByRoleId(String roleId) throws Exception {
		List<KxMenu> menus = kxMenuMapper.selectMenuByRoleId(roleId);
		List<String> menuList = new ArrayList<>();
		for(KxMenu menu : menus) {
			menuList.add(menu.getMenuId());
		}
        return new ObjectRestResponse<>().data(menuList);
	}
	
	
	
}
