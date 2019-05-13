package com.xwc.controller.base;

import com.xwc.commons.model.JsonMessage;
import com.xwc.commons.model.PageResponse;
import com.xwc.controller.base.dto.menu.MenuDto;
import com.xwc.controller.base.dto.menu.MenuTreeDto;
import com.xwc.controller.base.dto.org.OrgTreeDto;
import com.xwc.controller.base.dto.role.RoleDto;
import com.xwc.controller.base.dto.user.UserDto;
import com.xwc.controller.base.dto.user.UserFilterDto;
import com.xwc.entity.base.Menu;
import com.xwc.entity.base.Org;
import com.xwc.entity.base.User;
import com.xwc.service.auth.AuthInfoService;
import com.xwc.service.base.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/30  10:05
 * 业务：
 * 功能：
 */
@RestController
@RequestMapping("/user")
@Api(tags = "base_user", description = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthInfoService authInfoService;

    @PostMapping()
    @ApiOperation("添加用户")
    public JsonMessage<Void> addUser(@Validated UserDto body) {
        Org org = authInfoService.org();
        if (!body.getOrgCode().startsWith(org.getCode()))
            return JsonMessage.failed("组织权限受限", "用户机构" + org.getCode() + "数据机构" + body.getOrgCode());
        userService.add(User.convert(body));
        return JsonMessage.succeed();
    }


    @PutMapping("/{userId}")
    @ApiOperation("修改用户")
    public JsonMessage<Void> updateUser(@PathVariable("userId") Long userId, @Validated UserDto body) {
        Org org = authInfoService.org();
        if (!body.getOrgCode().startsWith(org.getCode()))
            return JsonMessage.failed("组织权限受限", "用户机构" + org.getCode() + "数据机构" + body.getOrgCode());
        body.setId(userId);
        userService.update(User.convert(body));
        return JsonMessage.succeed();
    }

    @DeleteMapping("/{account}")
    @ApiOperation("删除用户")
    public JsonMessage<Void> deleteUser(@PathVariable("account") String account) {
        userService.del(account);
        return JsonMessage.succeed();
    }

    @PutMapping("")
    @ApiOperation("修改用户")
    public JsonMessage<PageResponse<UserDto>> list(UserFilterDto filter) {
        List<UserDto> list = userService.list(filter).stream().map(UserDto::convert).collect(Collectors.toList());
        Long count = userService.count(filter);
        return JsonMessage.succeed(PageResponse.init(list, count, filter.getOffset()));
    }

    @PutMapping("/{userId}/role")
    @ApiOperation("分配角色")
    public JsonMessage<PageResponse<UserDto>> list(@PathVariable("userId") Long userId, @RequestBody HashSet<Long> roleList) {
        userService.grantRole(userId, roleList);
        return JsonMessage.succeed();
    }

    @GetMapping("/role")
    @ApiOperation("获取用户角色")
    public JsonMessage<List<RoleDto>> list(@RequestParam(required = false) Long userId) {
        if (userId == null) {
            userId = authInfoService.userId();
        } else {
            userId = userService.getUser(userId).getId();
        }
        return JsonMessage.succeed(userService.roleByUser(userId).stream().map(RoleDto::convert).collect(Collectors.toList()));
    }

    @GetMapping("/menu/tree")
    @ApiOperation("获取用户菜单信息")
    public JsonMessage<MenuTreeDto> menuTree() {
        Long userId = authInfoService.userId();
        List<Menu> menus = userService.listMenuByUser(userId);
        MenuTreeDto menuDto = new MenuTreeDto();
        menuDto.setId(0L);
        return JsonMessage.succeed(convertTree(menuDto, menus));
    }

    private MenuTreeDto convertTree(MenuTreeDto root, List<Menu> childs) {
        root.setChild(childs.stream().filter(menu -> menu.getType() == 1 && menu.getParentId().equals(root.getId())).map(MenuTreeDto::convert).collect(Collectors.toList()));
        if (root.getChild().isEmpty()) {
            root.setButtom(childs.stream().filter(menu -> menu.getType() == 2 && menu.getParentId().equals(root.getId())).map(Menu::getCode).collect(Collectors.toList()));
        } else {
            root.getChild().forEach(menu -> convertTree(menu, childs));
        }
        return root;
    }

}
