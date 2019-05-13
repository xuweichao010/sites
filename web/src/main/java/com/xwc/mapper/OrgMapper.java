package com.xwc.mapper;

import com.xwc.controller.base.dto.org.OrgFilterDto;
import com.xwc.entity.base.Org;
import com.xwc.esbatis.anno.Count;
import com.xwc.esbatis.anno.GenerateSelectQuery;
import com.xwc.esbatis.anno.GenerateSelectSql;
import com.xwc.esbatis.anno.condition.enhance.RightLike;
import com.xwc.esbatis.interfaces.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/26  22:31
 * 业务：
 * 功能：
 */
@Mapper
public interface OrgMapper extends BaseMapper<Org, String> {


    @GenerateSelectQuery
    List<Org> list(OrgFilterDto filter);

    @GenerateSelectQuery
    @Count
    Long count(OrgFilterDto filter);

    @GenerateSelectSql
    List<Org> listByLikeParent(@RightLike String code);
}
