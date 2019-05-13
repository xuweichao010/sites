package com.xwc.entity.base;

import com.xwc.controller.base.dto.user.UserDto;
import com.xwc.esbatis.anno.enums.KeyEnum;
import com.xwc.esbatis.anno.table.PrimaryKey;
import com.xwc.esbatis.anno.table.Table;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/27  10:17
 * 业务：
 * 功能：
 */
@Table("t_user_role")
@SuppressWarnings("unused")
public class UserRole implements Serializable {
    private static final long serialVersionUID = -4279599274719815691L;
    /**
     * 用户ID
     */
    @PrimaryKey(type = KeyEnum.CUSTOM)
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

}
