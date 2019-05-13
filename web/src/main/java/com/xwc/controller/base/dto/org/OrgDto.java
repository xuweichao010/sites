package com.xwc.controller.base.dto.org;

import com.xwc.entity.base.Org;
import com.xwc.esbatis.anno.condition.enhance.NotNull;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/26  22:02
 * 业务：
 * 功能：
 */
public class OrgDto {

    @ApiModelProperty("机构编码")
    private String code;

    @ApiModelProperty("机构名称")
    @NotNull
    private String name;

    @ApiModelProperty("父机构编码")
    @NotNull
    private String parentCode;

    @NotNull
    @ApiModelProperty("父机构名称")
    private String parentName;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public static OrgDto convert(Org org) {
        OrgDto dto = new OrgDto();
        BeanUtils.copyProperties(org, dto);
        return dto;
    }
}
