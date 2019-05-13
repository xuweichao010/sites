package com.xwc.controller.base;

import com.xwc.commons.model.JsonMessage;
import com.xwc.commons.model.PageResponse;
import com.xwc.controller.base.dto.org.OrgDto;
import com.xwc.controller.base.dto.org.OrgFilterDto;
import com.xwc.controller.base.dto.org.OrgTreeDto;
import com.xwc.entity.base.Org;
import com.xwc.service.base.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/15  14:20
 * 功能：用户管理
 * 业务：用户中心
 */

@RestController
@RequestMapping("/org")
@Api(tags = "base_org", description = "机构管理")
public class OrgController {


    @Autowired
    private OrgService orgService;

    @PostMapping()
    @ApiOperation("添加机构")
    public JsonMessage<Void> addOrg(@Validated @RequestBody OrgDto body) {
        orgService.add(body);
        return JsonMessage.succeed();
    }

    @GetMapping("/{code}")
    @ApiOperation("获取机构数据")
    public JsonMessage<OrgDto> getOrg(@PathVariable("code") String code) {
        Org org = orgService.get(code);
        return JsonMessage.succeed(OrgDto.convert(org));
    }

    @GetMapping("tree/{code}")
    @ApiOperation("获取机构树")
    public JsonMessage<OrgTreeDto> getOrgTree(@PathVariable("code") String code) {
        Org org = orgService.get(code);
        OrgTreeDto root = OrgTreeDto.convert(org);
        List<OrgTreeDto> collect = orgService.getParentCode(code).stream().map(OrgTreeDto::convert).collect(Collectors.toList());
        return JsonMessage.succeed(convertTree(root, collect));
    }


    @PutMapping("/{code}")
    @ApiOperation("修改机构数据")
    public JsonMessage<Void> updateOrg(@PathVariable("code") String code, @RequestBody OrgDto body) {
        body.setCode(code);
        orgService.update(Org.convert(body));
        return JsonMessage.succeed();
    }

    @GetMapping("")
    @ApiOperation("查询机构列表")
    public JsonMessage<PageResponse<OrgDto>> findList(OrgFilterDto filter) {
        List<OrgDto> list = orgService.list(filter).stream().map(OrgDto::convert).collect(Collectors.toList());
        Long count = orgService.count(filter);
        return JsonMessage.succeed(PageResponse.init(list, count, filter.getOffset()));
    }

    private OrgTreeDto convertTree(OrgTreeDto root, List<OrgTreeDto> childs) {
        root.setList(childs.stream().filter(item -> root.getCode().equals(item.getParentCode())).collect(Collectors.toList()));
        for (OrgTreeDto child : root.getList()) {
             convertTree(child, childs);
        }
        return root;
    }
}
