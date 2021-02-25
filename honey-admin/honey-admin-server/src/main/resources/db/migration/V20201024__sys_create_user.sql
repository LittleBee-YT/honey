DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
    `id`             bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`       varchar(64)  NOT NULL COMMENT '用户名',
    `mobile`         varchar(64)  NOT NULL COMMENT '手机',
    `email`          varchar(128) NOT NULL COMMENT '邮箱',
    `password`       varchar(255) NOT NULL COMMENT '密码',
    `password_exist` tinyint(1)   NOT NULL COMMENT '密码是否存在(0:Not,1:Yes)',
    `pwd_expire_at`  date         NOT NULL COMMENT '密码过期时间',
    `enabled`        tinyint(1)  DEFAULT NULL COMMENT '是否启用(0:Not,1:Yes)',
    `locked`         tinyint(1)  DEFAULT NULL COMMENT '冻结FLAG(0:Not,1:Yes)',
    `nickname`       varchar(64)  NOT NULL COMMENT '昵称',
    `avatar`         varchar(255) NOT NULL COMMENT '头像',
    `tenant_id`      bigint(20)  DEFAULT NULL COMMENT '租户Id',
    `del_flag`       int(11)     DEFAULT NULL COMMENT '删除标记',
    `create_by`      bigint(20)  DEFAULT NULL COMMENT '创建者',
    `create_date`    datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`      bigint(20)  DEFAULT NULL COMMENT '更新者',
    `update_date`    datetime    DEFAULT NULL COMMENT '更新时间',
    `remarks`        varchar(11) DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`)
) COMMENT = '系统用户';


DROP TABLE IF EXISTS `sys_user_detail`;

CREATE TABLE `sys_user_detail`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint(64) NOT NULL COMMENT '用户名',
    `tenant_id`   bigint(20)  DEFAULT NULL COMMENT '租户Id',
    `del_flag`    int(11)     DEFAULT NULL COMMENT '删除标记',
    `create_by`   bigint(20)  DEFAULT NULL COMMENT '创建者',
    `create_date` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20)  DEFAULT NULL COMMENT '更新者',
    `update_date` datetime    DEFAULT NULL COMMENT '更新时间',
    `remarks`     varchar(11) DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`)
) COMMENT = '系统用户详情扩展';


DROP TABLE IF EXISTS `sys_user_event_log`;

CREATE TABLE `sys_user_event_log`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint(64) NOT NULL COMMENT '用户名',
    `tenant_id`   bigint(20)  DEFAULT NULL COMMENT '租户Id',
    `del_flag`    int(11)     DEFAULT NULL COMMENT '删除标记',
    `create_by`   bigint(20)  DEFAULT NULL COMMENT '创建者',
    `create_date` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20)  DEFAULT NULL COMMENT '更新者',
    `update_date` datetime    DEFAULT NULL COMMENT '更新时间',
    `remarks`     varchar(11) DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`)
) COMMENT = '系统用登陆日志';


DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `key`         varchar(64)  NOT NULL COMMENT '角色标示',
    `name`        varchar(128) NOT NULL COMMENT '角色名称',
    `hide`        tinyint(1)   NOT NULL COMMENT '隐藏',
    `type`        int(11)      NOT NULL COMMENT '角色类型',
    `sort`        int(11)      NOT NULL COMMENT '排序',
    `status`      int(11)      NOT NULL COMMENT '状态(1:启用,2:禁用)',
    `tenant_id`   bigint(20)  DEFAULT NULL COMMENT '租户Id',
    `del_flag`    int(11)     DEFAULT NULL COMMENT '删除标记',
    `create_by`   bigint(20)  DEFAULT NULL COMMENT '创建者',
    `create_date` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20)  DEFAULT NULL COMMENT '更新者',
    `update_date` datetime    DEFAULT NULL COMMENT '更新时间',
    `remarks`     varchar(11) DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`)
) COMMENT = '系统用户角色';


DROP TABLE IF EXISTS `sys_relation_user_role`;

CREATE TABLE `sys_relation_user_role`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint(20)  DEFAULT NULL COMMENT '用户Id',
    `role_id`     bigint(20)  DEFAULT NULL COMMENT '角色Id',
    `tenant_id`   bigint(20)  DEFAULT NULL COMMENT '租户Id',
    `del_flag`    int(11)     DEFAULT NULL COMMENT '删除标记',
    `create_by`   bigint(20)  DEFAULT NULL COMMENT '创建者',
    `create_date` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20)  DEFAULT NULL COMMENT '更新者',
    `update_date` datetime    DEFAULT NULL COMMENT '更新时间',
    `remarks`     varchar(11) DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`)
) COMMENT = '系统用户角色关联';

