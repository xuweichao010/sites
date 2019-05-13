/**
角色管理
 */
CREATE TABLE  IF NOT EXISTS  `t_role` (
    `id`          BIGINT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    `name`        VARCHAR(32)    COMMENT '名称' ,
    `org_code`    VARCHAR(32)    COMMENT '机构名' ,
    `org_name`    VARCHAR(32)    COMMENT '机构代码' ,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色管理';

