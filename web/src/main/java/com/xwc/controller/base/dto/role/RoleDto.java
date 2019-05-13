package com.xwc.controller.base.dto.role;


import com.xwc.entity.base.Role;
import com.xwc.esbatis.anno.condition.enhance.NotNull;
import com.xwc.esbatis.anno.table.PrimaryKey;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  11:26
 * 业务：
 * 功能：
 */
public class RoleDto {
    /**
     * 主键
     */
    @PrimaryKey()
    private Long id;
    /**
     * 名称
     */
    @NotNull
    @ApiModelProperty("角色名称")
    private String name;
    /**
     * 机构代码
     */
    @ApiModelProperty("机构代码")
    private String orgCode;
    /**
     * 机构名称
     */
    @ApiModelProperty("机构名称")
    private String orgName;

    public static RoleDto convert(Role role) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
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
