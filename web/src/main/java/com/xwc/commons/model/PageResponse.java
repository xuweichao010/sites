package com.xwc.commons.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2019/1/22  17:55
 * 业务：
 * 功能：
 */
public class PageResponse<T> {
    @ApiModelProperty("查询数据列表")
    private List<T> list;
    @ApiModelProperty("总记录条数")
    private Long totalCount;
    @ApiModelProperty("总页数")
    private Long totalPage;

    public static <T> PageResponse<T> init(List<T> list, Long totalCount, Long pageSize) {
        PageResponse<T> page = new PageResponse<>();
        page.setList(list);
        page.setTotalCount(totalCount);
        page.setTotalPage(new Double(Math.ceil(totalCount * 1D / pageSize)).longValue());
        return page;
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}
