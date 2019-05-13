package com.xwc.controller.base.dto.user;

import com.xwc.commons.model.PageRequest;
import com.xwc.esbatis.anno.condition.enhance.Like;
import com.xwc.esbatis.anno.condition.enhance.RightLike;
import io.swagger.annotations.ApiModelProperty;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/30  10:08
 * 业务：
 * 功能：
 */
public class UserFilterDto extends PageRequest {


    @ApiModelProperty("账号")
    @Like
    private String account;


    @ApiModelProperty("用户名")
    @Like
    private String name;

    @ApiModelProperty("机构名")
    @Like
    private String orgName;

    @ApiModelProperty("机构代码")
    @RightLike
    private String orgCode;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
