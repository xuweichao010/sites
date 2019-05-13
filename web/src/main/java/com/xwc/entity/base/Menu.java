package com.xwc.entity.base;

import com.xwc.esbatis.anno.enums.KeyEnum;
import com.xwc.esbatis.anno.table.PrimaryKey;
import com.xwc.esbatis.anno.table.Table;

import java.io.Serializable;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  11:44
 * 业务：
 * 功能：
 */
@Table("t_menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = 322299642679834516L;
    /**
     * 主键
     */
    @PrimaryKey(type = KeyEnum.CUSTOM)
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
     * 类型;1:菜单  2:按钮
     */
    private Integer type;
    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 主键;这个字段决定这前端显示的内容
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 主键;这个字段决定这前端显示的内容
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
     * 代码
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 类型;1:菜单  2:按钮
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 类型;1:菜单  2:按钮
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 父节点ID
     */
    public Long getParentId() {
        return this.parentId;
    }

    /**
     * 父节点ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
