package com.xwc.controller.base;

import com.xwc.commons.model.JsonMessage;
import com.xwc.commons.model.PageResponse;
import com.xwc.controller.base.dto.menu.MenuTreeDto;
import com.xwc.controller.base.dto.org.OrgTreeDto;
import com.xwc.controller.base.dto.role.RoleDto;
import com.xwc.controller.base.dto.role.RoleFilterDto;
import com.xwc.entity.base.Menu;
import com.xwc.entity.base.Org;
import com.xwc.entity.base.Role;
import com.xwc.service.auth.AuthInfoService;
import com.xwc.service.base.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/role")
@Api(tags = "base_role", description = "角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthInfoService authInfoService;

    @PostMapping()
    @ApiOperation("添加角色")
    public JsonMessage<Void> addUser(@Validated RoleDto body) {
        roleService.add(Role.convert(body));
        return JsonMessage.succeed();
    }

    @GetMapping()
    @ApiOperation("查询角色信息")
    public JsonMessage<PageResponse<RoleDto>> addUser(@Validated RoleFilterDto filter) {
        Org org = authInfoService.org();
        if(filter.getOrgCode() ==  null || !filter.getOrgCode().startsWith(org.getCode())){
            filter.setOrgCode(org.getCode());
        }
        List<RoleDto> list = roleService.list(filter).stream().map(RoleDto::convert).collect(Collectors.toList());
        Long count = roleService.count(filter);
        return JsonMessage.succeed(PageResponse.init(list, count, filter.getOffset()));
    }

    @PutMapping()
    @ApiOperation("修改角色")
    public JsonMessage<Void> updateUser(@Validated RoleDto body) {
        roleService.update(Role.convert(body));
        return JsonMessage.succeed();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除角色")
    public JsonMessage<Void> delUser(@Validated RoleDto body) {
        roleService.update(Role.convert(body));
        return JsonMessage.succeed();
    }

    @PutMapping("/{id}/menu")
    @ApiOperation("赋予角色权限")
    public JsonMessage<Void> grantMenu(@PathVariable("id") Long roleId, @RequestBody HashSet<Long> menuIdList) {
        roleService.grandMenu(menuIdList, roleId);
        return JsonMessage.succeed();
    }



}
