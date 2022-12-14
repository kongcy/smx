
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
--????????????
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
                                 `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                                 `NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '????????????',
                                 `CODE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                 `SUP_ID` bigint(20) NULL DEFAULT NULL COMMENT '?????????ID',
                                 `PATH` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '????????????(id??????)',
                                 `DEPTH` int(11) NULL DEFAULT NULL COMMENT '????????????',
                                 `URL` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '??????URL',
                                 `METHOD` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '??????????????????(GET,POST,PUT,DELETE), ?????????????????????, ?????? * ??????',
                                 `SEQ` int(11) NULL DEFAULT NULL COMMENT '????????????',
                                 `ICON` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                 `TYPE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '????????????',
                                 `CREATE_TIME` datetime NOT NULL COMMENT '????????????',
                                 `CREATE_USER` bigint(20) NULL DEFAULT NULL COMMENT '?????????',
                                 `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '????????????',
                                 `UPDATE_USER` bigint(20) NULL DEFAULT NULL COMMENT '?????????',
                                 `STATE` int(11) NOT NULL COMMENT '??????(0 ?????? 1??????)',
                                 PRIMARY KEY (`ID`) USING BTREE,
                                 UNIQUE INDEX `URL_METHOD`(`URL`, `METHOD`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('10', '??????', 'main', 0, '10.', 1, '/main', '', 1, 'glyphicon glyphicon-th', 'MENU', '2016-08-24 15:30:31', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('11', '???????????????', 'third', 0, '11.', 1, '/third', '', 3, 'glyphicon glyphicon-user', 'MENU', '2016-08-24 15:37:32', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('12', '????????????', 'third-user', 11, '11.12.', 2, '/third/user', '', 1, 'glyphicon glyphicon-user', 'MENU', '2016-08-24 15:38:02', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('13', '????????????', 'system', 0, '13.', 1, '/system', '', 2, 'glyphicon glyphicon-asterisk', 'MENU', '2016-08-24 15:52:20', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('14', '????????????', 'system-resource', 13, '13.14.', 2, '/system/user', '', 1, '', 'MENU', '2016-08-24 15:54:47', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('15', '????????????', 'system-role', 13, '13.15.', 2, '/system/role', '', 2, NULL, 'MENU', '2016-08-24 15:56:24', NULL, NULL, NULL, 1);
INSERT INTO `sys_resource` VALUES ('16', '????????????', 'system-resource', 13, '13.16.', 2, '/system/resource', '', 3, NULL, 'MENU', '2016-08-24 15:56:54', NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ?????????
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                             `NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '?????????',
                             `CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '????????????',
                             `DESCRIPTION` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '????????????',
                             `CREATE_USER` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '?????????',
                             `CREATE_TIME` datetime NOT NULL COMMENT '????????????',
                             PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('10', '???????????????', 'ADMIN', NULL, '0', '2016-08-22 11:33:15');

-- ----------------------------
-- Table structure for sys_role_resource
-- ????????????
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
                                      `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                                      `ROLE_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '??????ID',
                                      `RES_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '??????ID',
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
-- ?????????
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                             `LOGIN_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '????????????',
                             `PASSWORD` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '????????????',
                             `USER_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             `PHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '?????????',
                             `EMAIL` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '??????',
                             `STATE` int(1) NOT NULL COMMENT '??????',
                             `LOGIN_IP` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '??????IP',
                             `CREATE_TIME` datetime NOT NULL COMMENT '????????????',
                             `CREATE_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '?????????',
                             PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '???????????????', '1258', '604693031@qq.com', 1, NULL, '2016-08-23 09:50:14', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ????????????
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                                  `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '??????ID',
                                  `ROLE_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '??????ID',
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
                                  `CON_KEY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '???',
                                  `CON_VALUE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '???',
                                  `CON_COMMENT` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '??????',
                                  PRIMARY KEY (`ID`) USING BTREE,
                                  UNIQUE INDEX `CON_KEY`(`CON_KEY`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
