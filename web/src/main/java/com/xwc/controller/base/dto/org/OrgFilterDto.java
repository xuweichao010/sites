package com.xwc.controller.base.dto.org;

import com.xwc.commons.model.PageRequest;
import com.xwc.entity.base.Org;
import com.xwc.esbatis.anno.condition.enhance.Like;
import com.xwc.esbatis.anno.condition.enhance.NotNull;
import com.xwc.esbatis.anno.condition.enhance.RightLike;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/26  22:02
 * 业务：
 * 功能：
 */
public class OrgFilterDto extends PageRequest{


    @ApiModelProperty("机构名称")
    @Like
    private String name;

    @ApiModelProperty("父机构编码")
    @RightLike
    private String parentCode;

    @NotNull
    @ApiModelProperty("父机构名称")
    @Like
    private String parentName;


    public String getName() {
        return name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public String getParentName() {
        return parentName;
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

    public static OrgFilterDto convert(Org org) {
        OrgFilterDto dto = new OrgFilterDto();
        BeanUtils.copyProperties(org, dto);
        return dto;
    }
}
