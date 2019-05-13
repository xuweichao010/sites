package com.xwc.controller.base.dto.menu;

import com.xwc.entity.base.Menu;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/6  14:45
 * 业务：
 * 功能：
 */
public class MenuTreeDto extends MenuDto {
    @ApiModelProperty("子菜单信息")
    private List<MenuTreeDto> child;

    @ApiModelProperty("菜单按钮信息")
    private List<String> buttom;

    public List<MenuTreeDto> getChild() {
        return child;
    }

    public void setChild(List<MenuTreeDto> child) {
        this.child = child;
    }

    public List<String> getButtom() {
        return buttom;
    }

    public void setButtom(List<String> buttom) {
        this.buttom = buttom;
    }

    public static MenuTreeDto convert(Menu menu) {
        MenuTreeDto menuTree = new MenuTreeDto();
        BeanUtils.copyProperties(menu, menuTree);
        return menuTree;
    }
}
