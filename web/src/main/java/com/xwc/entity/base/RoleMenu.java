package com.xwc.entity.base;


import com.xwc.esbatis.anno.enums.KeyEnum;
import com.xwc.esbatis.anno.table.PrimaryKey;
import com.xwc.esbatis.anno.table.Table;

import java.io.Serializable;


/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  11:26
 * 业务：
 * 功能：
 */
@Table("t_role_menu")
@SuppressWarnings("unused")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -6069912550518847359L;
    /**
     * 角色ID
     */
    @PrimaryKey(type = KeyEnum.CUSTOM)
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 角色ID
     */
    public Long getRoleId() {
        return this.roleId;
    }

    /**
     * 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
