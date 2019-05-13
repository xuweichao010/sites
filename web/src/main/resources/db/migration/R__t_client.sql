/**
客户端管理
 */
CREATE TABLE  IF NOT EXISTS  t_client(
  `client_id`       VARCHAR(32)  NOT NULL   COMMENT 'id' ,
  `secret`          VARCHAR(64)  COMMENT '授权码' ,
  `scope`           VARCHAR(32)  COMMENT '授权范围' ,
  `grant_type`      VARCHAR(128) COMMENT '授权类型' ,
  `description`     VARCHAR(64)  COMMENT '客户端信息' ,
  `org_name`        VARCHAR(32)  COMMENT '所属机构' ,
  `org_code`        VARCHAR(32)  COMMENT '机构代码' ,
  `access_seconds`  INT          COMMENT 'token有效期' ,
  `refresh_seconds` INT          COMMENT 'refresh_token的有效期' ,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户端管理';




