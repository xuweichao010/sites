/**
  菜单管理
 */
CREATE TABLE IF NOT EXISTS `t_menu` (
  `id`        BIGINT         NOT NULL   COMMENT '菜单的唯一标识' ,
  `name`      VARCHAR(32)    COMMENT '名称' ,
  `code`      VARCHAR(32)    COMMENT '按钮编号',
  `type`      INT(1)         COMMENT '类型 1:菜单  2:按钮' ,
  `parent_id` BIGINT         COMMENT '父节点ID' ,
  PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '菜单管理';

SELECT      COUNT(*)
INTO        @index
FROM        information_schema.COLUMNS
WHERE       Table_SCHEMA = DATABASE()
            AND   TABLE_NAME = 't_menu'
            AND   COLUMN_NAME = 'id';
SET         @SQL = IF(@index >= 0,
                      'ALTER TABLE t_menu MODIFY COLUMN `id` BIGINT COMMENT \' 菜单的唯一标识 \'',
                      'SELECT \'id MODIFY BIGINT\';');
PREPARE     statement
FROM        @SQL;
EXECUTE     statement;

SELECT      COUNT(*)
INTO        @index
FROM        information_schema.COLUMNS
WHERE       Table_SCHEMA = DATABASE()
            AND   TABLE_NAME = 't_menu'
            AND   COLUMN_NAME = 'parent_id';
SET         @SQL = IF(@index >= 0,
                      'ALTER TABLE t_menu MODIFY COLUMN `parent_id` BIGINT COMMENT \' 父节点ID \'',
                      'SELECT \'parent_id MODIFY BIGINT\';');
PREPARE     statement
FROM        @SQL;
EXECUTE     statement;


