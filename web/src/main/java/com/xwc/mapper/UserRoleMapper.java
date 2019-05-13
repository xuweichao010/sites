package com.xwc.mapper;

import com.xwc.controller.base.dto.user.UserFilterDto;
import com.xwc.entity.base.User;
import com.xwc.entity.base.UserRole;
import com.xwc.esbatis.anno.Count;
import com.xwc.esbatis.anno.GenerateSelectQuery;
import com.xwc.esbatis.anno.GenerateSelectSql;
import com.xwc.esbatis.interfaces.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/28  11:10
 * 业务：
 * 功能：
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole, Long> {

}
