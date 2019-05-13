package com.xwc.mapper;

import com.xwc.entity.base.Menu;
import com.xwc.esbatis.anno.GenerateSelectSql;
import com.xwc.esbatis.anno.condition.enhance.In;
import com.xwc.esbatis.interfaces.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  14:02
 * 业务：
 * 功能：
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu, String> {

    @GenerateSelectSql(colums = "id")
    List<String> listByIds(@Param("idList") @In(colum = "id") Collection<Long> idList);

    @GenerateSelectSql(colums = "id")
    List<String> findAll();
}
