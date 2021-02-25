DROP TABLE IF EXISTS `sys_operation_log`;

CREATE TABLE `sys_operation_log`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `op_module`     varchar(64)  DEFAULT NULL COMMENT '功能模块',
    `op_type`       varchar(32)  DEFAULT NULL COMMENT '操作类型',
    `op_desc`       varchar(256)  DEFAULT NULL COMMENT '操作描述',
    `request_param`     text  DEFAULT NULL COMMENT '请求参数',
    `result_body` text  DEFAULT NULL COMMENT '返回参数',
    `uri`         varchar(256)  DEFAULT NULL COMMENT '请求uri',
    `method`      varchar(64)  DEFAULT NULL COMMENT '操作方法',
    `op_user`     varchar(32)  DEFAULT NULL COMMENT '操作人',
    `login_ip`    varchar(16)  DEFAULT NULL COMMENT '用户登陆ip',
    `tenant_id`   bigint(20)  DEFAULT NULL COMMENT '租户Id',
    `del_flag`    int(11)     DEFAULT NULL COMMENT '删除标记',
    `create_by`   bigint(20)  DEFAULT NULL COMMENT '创建者',
    `create_date` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20)  DEFAULT NULL COMMENT '更新者',
    `update_date` datetime    DEFAULT NULL COMMENT '更新时间',
    `remarks`     varchar(256) DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`)
) COMMENT = '操作日志记录表';

DROP TABLE IF EXISTS `sys_operation_error_log`;

CREATE TABLE `sys_operation_error_log`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `op_module`     varchar(64)  DEFAULT NULL COMMENT '功能模块',
    `op_type`       varchar(32)  DEFAULT NULL COMMENT '操作类型',
    `op_desc`       varchar(256)  DEFAULT NULL COMMENT '操作描述',
    `request_param`     text  DEFAULT NULL COMMENT '请求参数',
    `error_message` text  DEFAULT NULL COMMENT '错误消息',
    `uri`         varchar(256)  DEFAULT NULL COMMENT '请求uri',
    `method`      varchar(64)  DEFAULT NULL COMMENT '操作方法',
    `op_user`     varchar(32)  DEFAULT NULL COMMENT '操作人',
    `login_ip`    varchar(16)  DEFAULT NULL COMMENT '用户登陆ip',
    `tenant_id`   bigint(20)  DEFAULT NULL COMMENT '租户Id',
    `del_flag`    int(11)     DEFAULT NULL COMMENT '删除标记',
    `create_by`   bigint(20)  DEFAULT NULL COMMENT '创建者',
    `create_date` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20)  DEFAULT NULL COMMENT '更新者',
    `update_date` datetime    DEFAULT NULL COMMENT '更新时间',
    `remarks`     varchar(256) DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`)
) COMMENT = '操作日志错误记录表';

