package com.xwc.controller.base.dto.role;


import com.xwc.commons.model.PageRequest;
import com.xwc.esbatis.anno.condition.enhance.Like;
import com.xwc.esbatis.anno.condition.enhance.NotNull;
import com.xwc.esbatis.anno.condition.enhance.RightLike;
import io.swagger.annotations.ApiModelProperty;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  11:26
 * 业务：
 * 功能：
 */
public class RoleFilterDto extends PageRequest {

    /**
     * 名称
     */
    @NotNull
    @ApiModelProperty("角色名称")
    @Like
    private String name;
    /**
     * 机构代码
     */
    @ApiModelProperty("机构代码")
    @RightLike
    private String orgCode;
    /**
     * 机构名称
     */
    @ApiModelProperty("机构名称")
    @Like
    private String orgName;


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
