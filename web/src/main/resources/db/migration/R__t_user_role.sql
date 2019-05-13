/**
用户和角色关系管理
 */
CREATE TABLE  IF NOT EXISTS  t_user_role(
    user_id BIGINT NOT NULL   COMMENT '用户ID' ,
    role_id VARCHAR(32) NOT NULL   COMMENT '角色ID' ,
    PRIMARY KEY (user_id,role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关系管理';




