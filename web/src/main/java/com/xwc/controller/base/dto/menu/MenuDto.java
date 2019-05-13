package com.xwc.controller.base.dto.menu;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/6  14:45
 * 业务：
 * 功能：
 */
public class MenuDto {

    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 代码
     */
    private String code;

    /**
     * 父节点ID
     */
    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
