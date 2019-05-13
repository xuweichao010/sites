package com.xwc.mapper;

import com.xwc.controller.base.dto.user.UserFilterDto;
import com.xwc.entity.base.Menu;
import com.xwc.entity.base.Role;
import com.xwc.entity.base.User;
import com.xwc.esbatis.anno.Count;
import com.xwc.esbatis.anno.GenerateSelectQuery;
import com.xwc.esbatis.anno.GenerateSelectSql;
import com.xwc.esbatis.interfaces.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/28  11:10
 * 业务：
 * 功能：
 */
@Mapper
public interface UserMapper extends BaseMapper<User, Long> {

    @GenerateSelectSql()
    User byAccount(String account);

    @GenerateSelectQuery
    List<User> list(UserFilterDto filter);

    @GenerateSelectQuery
    @Count
    Long count(UserFilterDto filter);


    @Select(" SELECT * FROM t_user_role ur INNER JOIN t_role r ON ur.role_id = r.id where ur.user_id = #{userId} ")
    List<Role> roleByUser(Long userId);

    @Select(" SELECT DISTINCT m.* FROM t_user_role ur " +
            " INNER JOIN t_role r ON ur.role_id = r.id " +
            " INNER JOIN t_role_menu rm on r.id = rm.role_id " +
            " INNER JOIN t_menu m ON rm.menu_id = m.id where ur.user_id = #{userId}")
    List<Menu> listMenuByUser(Long userId);
}
