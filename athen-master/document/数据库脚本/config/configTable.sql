/*
Navicat MySQL Data Transfer

Source Server         : 47.52.198.122
Source Server Version : 80014
Source Host           : 47.52.198.122:3306
Source Database       : athen-config-test

Target Server Type    : MYSQL
Target Server Version : 80014
File Encoding         : 65001

Date: 2019-08-13 18:08:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for config_table
-- ----------------------------
DROP TABLE IF EXISTS `config_table`;
CREATE TABLE `config_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application` varchar(256) NOT NULL default 'configTable' COMMENT '应用名，GLOBAL为全局',
  `module` varchar(128) NOT NULL COMMENT '应用模块名(system,order...)',
  `key` varchar(128) NOT NULL COMMENT 'key值',
  `value` varchar(256) NOT NULL COMMENT '对应value值',
  `desc` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of config_table
-- ----------------------------