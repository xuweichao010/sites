package com.xwc.commons.model;


import io.swagger.annotations.ApiModelProperty;

/**
 * 创建人：徐卫超
 * 创建时间：2019/1/22  17:57
 * 业务：
 * 功能：
 */
public class PageRequest {

    @ApiModelProperty("每页记录条数 默认：10")
    private Long offset = 10L;
    @ApiModelProperty("查询第多少页 默认: 1页")
    private Long start = 1L;

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getStart() {
        return (start - 1) * offset;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getStartSource() {
        return start;
    }
}
