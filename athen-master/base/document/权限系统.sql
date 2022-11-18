
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for spring_session
-- ----------------------------
DROP TABLE IF EXISTS `spring_session`;
CREATE TABLE `spring_session`  (
                                   `SESSION_ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                                   `CREATION_TIME` bigint(20) NOT NULL,
                                   `LAST_ACCESS_TIME` bigint(20) NOT NULL,
                                   `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
                                   `PRINCIPAL_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   PRIMARY KEY (`SESSION_ID`) USING BTREE,
                                   INDEX `SPRING_SESSION_IX1`(`LAST_ACCESS_TIME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for spring_session_attributes
-- ----------------------------
DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE `spring_session_attributes`  (
                                              `SESSION_ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                                              `ATTRIBUTE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                                              `ATTRIBUTE_BYTES` blob NULL,
                                              PRIMARY KEY (`SESSION_ID`, `ATTRIBUTE_NAME`) USING BTREE,
                                              INDEX `SPRING_SESSION_ATTRIBUTES_IX1`(`SESSION_ID`) USING BTREE,
                                              CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_ID`) REFERENCES `spring_session` (`SESSION_ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_resource
--权限菜单
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
                                 `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                                 `NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名称',
                                 `CODE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源编号',
                                 `SUP_ID` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID',
                                 `PATH` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单路径(id组成)',
                                 `DEPTH` int(11) NULL DEFAULT NULL COMMENT '菜单级别',
                                 `URL` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
                                 `METHOD` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源操作方法(GET,POST,PUT,DELETE), 多个用逗号分隔, 支持 * 通配',
                                 `SEQ` int(11) NULL DEFAULT NULL COMMENT '菜单排序',
                                 `ICON` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
                                 `TYPE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单类型',
                                 `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
                                 `CREATE_USER` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
                                 `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                 `UPDATE_USER` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
                                 `STATE` int(11) NOT NULL COMMENT '状态(0 启用 1禁用)',
                                 PRIMARY KEY (`ID`) USING BTREE,
                                 UNIQUE INDEX `URL_METHOD`(`URL`, `METHOD`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('10', '首页', 'main', 0, '10.', 1, '/main', '', 1, 'glyphicon glyphicon-th', 'MENU', '2016-08-24 15:30:31', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('11', '第三方管理', 'third', 0, '11.', 1, '/third', '', 3, 'glyphicon glyphicon-user', 'MENU', '2016-08-24 15:37:32', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('12', '用户列表', 'third-user', 11, '11.12.', 2, '/third/user', '', 1, 'glyphicon glyphicon-user', 'MENU', '2016-08-24 15:38:02', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('13', '系统设置', 'system', 0, '13.', 1, '/system', '', 2, 'glyphicon glyphicon-asterisk', 'MENU', '2016-08-24 15:52:20', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('14', '用户管理', 'system-resource', 13, '13.14.', 2, '/system/user', '', 1, '', 'MENU', '2016-08-24 15:54:47', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('15', '角色管理', 'system-role', 13, '13.15.', 2, '/system/role', '', 2, NULL, 'MENU', '2016-08-24 15:56:24', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('16', '资源管理', 'system-resource', 13, '13.16.', 2, '/system/resource', '', 3, NULL, 'MENU', '2016-08-24 15:56:54', NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                             `NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
                             `CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
                             `DESCRIPTION` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
                             `CREATE_USER` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
                             `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
                             PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('10', '超级管理员', 'ADMIN', NULL, '0', '2016-08-22 11:33:15');

-- ----------------------------
-- Table structure for sys_role_resource
-- 角色资源
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
                                      `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                                      `ROLE_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
                                      `RES_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源ID',
                                      PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1', '10', '10');
INSERT INTO `sys_role_resource` VALUES ('2', '10', '13');
INSERT INTO `sys_role_resource` VALUES ('3', '10', '16');
INSERT INTO `sys_role_resource` VALUES ('4', '10', '14');
INSERT INTO `sys_role_resource` VALUES ('5', '10', '15');

-- ----------------------------
-- Table structure for sys_user
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                             `LOGIN_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
                             `PASSWORD` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
                             `USER_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                             `PHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
                             `EMAIL` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                             `STATE` int(1) NOT NULL COMMENT '状态',
                             `LOGIN_IP` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录IP',
                             `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
                             `CREATE_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                             PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', '1258', '604693031@qq.com', 1, NULL, '2016-08-23 09:50:14', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- 用户角色
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                                  `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
                                  `ROLE_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
                                  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '10', '10');

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config`  (
                                  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                  `CON_KEY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '键',
                                  `CON_VALUE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '值',
                                  `CON_COMMENT` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
                                  PRIMARY KEY (`ID`) USING BTREE,
                                  UNIQUE INDEX `CON_KEY`(`CON_KEY`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
