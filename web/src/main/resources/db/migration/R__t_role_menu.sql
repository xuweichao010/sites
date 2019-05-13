/**
角色菜单映射管理
 */
CREATE TABLE  IF NOT EXISTS  `t_role_menu` (
    role_id BIGINT NOT NULL   COMMENT '角色ID' ,
    menu_id BIGINT NOT NULL   COMMENT '菜单ID' ,
    PRIMARY KEY (role_id,menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单映射管理';

SELECT      COUNT(*)
INTO        @index
FROM        information_schema.COLUMNS
WHERE       Table_SCHEMA = DATABASE()
            AND   TABLE_NAME = 't_role_menu'
            AND   COLUMN_NAME = 'role_id';
SET         @SQL = IF(@index >= 0,
                      'ALTER TABLE t_role_menu MODIFY COLUMN `role_id` BIGINT COMMENT \' 角色ID \'',
                      'SELECT \'role_id MODIFY BIGINT\';');
PREPARE     statement
FROM        @SQL;
EXECUTE     statement;

SELECT      COUNT(*)
INTO        @index
FROM        information_schema.COLUMNS
WHERE       Table_SCHEMA = DATABASE()
            AND   TABLE_NAME = 't_role_menu'
            AND   COLUMN_NAME = 'menu_id';
SET         @SQL = IF(@index >= 0,
                      'ALTER TABLE t_role_menu MODIFY COLUMN `menu_id` BIGINT COMMENT \' 菜单ID \'',
                      'SELECT \'meun_id MODIFY BIGINT\';');
PREPARE     statement
FROM        @SQL;
EXECUTE     statement;

