package com.xwc.controller.base.dto.org;

import com.xwc.entity.base.Org;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/30  9:07
 * 业务：
 * 功能：
 */
public class OrgTreeDto extends OrgDto {
    @ApiModelProperty("子机构信息")
    private List<OrgTreeDto> list;


    public static OrgTreeDto convert(Org org) {
        OrgTreeDto orgTreeDto = new OrgTreeDto();
        BeanUtils.copyProperties(org, orgTreeDto);
        return orgTreeDto;
    }

    public List<OrgTreeDto> getList() {
        return list;
    }

    public void setList(List<OrgTreeDto> list) {
        this.list = list;
    }
}
