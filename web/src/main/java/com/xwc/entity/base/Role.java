package com.xwc.entity.base;


import com.xwc.controller.base.dto.role.RoleDto;
import com.xwc.esbatis.anno.table.Ignore;
import com.xwc.esbatis.anno.table.PrimaryKey;
import com.xwc.esbatis.anno.table.Table;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  11:26
 * 业务：
 * 功能：
 */
@Table("t_role")
@SuppressWarnings("unused")
public class Role {
    /**
     * 主键
     */
    @PrimaryKey()
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 机构名
     */
    private String orgCode;
    /**
     * 机构代码
     */
    private String orgName;



    public static Role convert(RoleDto body) {
        Role role = new Role();
        BeanUtils.copyProperties(body, role);
        return role;
    }

    /**
     * 主键
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 机构名
     */
    public String getOrgCode() {
        return this.orgCode;
    }

    /**
     * 机构名
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 机构代码
     */
    public String getOrgName() {
        return this.orgName;
    }

    /**
     * 机构代码
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


}
