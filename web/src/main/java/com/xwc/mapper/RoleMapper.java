package com.xwc.mapper;

import com.xwc.controller.base.dto.role.RoleFilterDto;
import com.xwc.entity.base.Role;
import com.xwc.esbatis.anno.Count;
import com.xwc.esbatis.anno.GenerateSelectQuery;
import com.xwc.esbatis.anno.GenerateSelectSql;
import com.xwc.esbatis.anno.condition.enhance.In;
import com.xwc.esbatis.interfaces.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  13:44
 * 业务：
 * 功能：
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role, Long> {


    @GenerateSelectSql
    Role byNameAndOrgCode(String name, String orgCode);

    @GenerateSelectSql(colums = "id")
    List<Long> listByRoleId(@Param("roleList") @In(colum = "id") Set<Long> roleList, String orgCode);

    @GenerateSelectQuery
    List<Role> list(RoleFilterDto filter);

    @GenerateSelectQuery
    @Count
    Long count(RoleFilterDto filter);
}
