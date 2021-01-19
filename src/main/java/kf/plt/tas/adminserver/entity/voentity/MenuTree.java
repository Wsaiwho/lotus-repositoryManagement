package kf.plt.tas.adminserver.entity.voentity;

import kf.plt.service.common.vo.TreeNodeVO;
import lombok.Data;

/**
 * 菜单树
 * 
 * @author wangs
 * @date 2019/10/14
 *
 */
@Data
public class MenuTree extends TreeNodeVO<MenuTree>{

	/**
     * 菜单 ID
     */
    private String menuId;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单描述
     */
    private String menuDesc;
	
}
