/*
 Navicat Premium Data Transfer

 Source Server         : 30.55.10.222
 Source Server Type    : PostgreSQL
 Source Server Version : 100013
 Source Host           : 30.55.10.222:5433
 Source Catalog        : xtplat
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100013
 File Encoding         : 65001

 Date: 11/08/2021 10:37:58
*/


-- ----------------------------
-- Table structure for cfg_device_message
-- ----------------------------
DROP TABLE IF EXISTS "public"."cfg_device_message";
CREATE TABLE "public"."cfg_device_message" (
                                               "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                               "user_account" varchar(16) COLLATE "pg_catalog"."default" NOT NULL,
                                               "resource_id" varchar(50) COLLATE "pg_catalog"."default",
                                               "resource_name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
                                               "device_sip_id" varchar(16) COLLATE "pg_catalog"."default" NOT NULL,
                                               "channel_num" int2 NOT NULL DEFAULT '-1'::integer,
                                               "user_name" varchar(128) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."cfg_device_message"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."cfg_device_message"."user_account" IS '用户账号';
COMMENT ON COLUMN "public"."cfg_device_message"."resource_id" IS '资源id';
COMMENT ON COLUMN "public"."cfg_device_message"."resource_name" IS '设备名称';
COMMENT ON COLUMN "public"."cfg_device_message"."device_sip_id" IS '设备sipid';
COMMENT ON COLUMN "public"."cfg_device_message"."channel_num" IS '通道编号';
COMMENT ON COLUMN "public"."cfg_device_message"."user_name" IS '用户名称';

-- ----------------------------
-- Records of cfg_device_message
-- ----------------------------
INSERT INTO "public"."cfg_device_message" VALUES ('6ed7c2fcd79d493da0936b366fde71c8', '220test07', '4c8140b2d0da4e5187c88a441eadbea5', '172.16.5.113-gb', '22000009399', -1, '220test07');
INSERT INTO "public"."cfg_device_message" VALUES ('6ed7c2fcd79d493da0936b366fde71c8', '220test07', '635e84cd013440b582cfe28b070d8299', '172.16.10.198-GB设备', '22000009408', -1, '220test07');
INSERT INTO "public"."cfg_device_message" VALUES ('6ed7c2fcd79d493da0936b366fde71c8', '220test07', '750dae650080499e91595687a7e3a2d6', '172.16.10.150-GB', '22000011018', -1, '220test07');
INSERT INTO "public"."cfg_device_message" VALUES ('6ed7c2fcd79d493da0936b366fde71c8', '220test07', '80d7d215339d49639d9050289b00992f', '220-172.16.10.200-GB', '22000003730', -1, '220test07');
INSERT INTO "public"."cfg_device_message" VALUES ('6ed7c2fcd79d493da0936b366fde71c8', '220test07', 'ce8b5590615543babce8f4c282dbda33', '220-172.16.10.199-GB', '22000003729', -1, '220test07');
INSERT INTO "public"."cfg_device_message" VALUES ('2ab8f4a7083941d6a3bd7d54355c3d3c', '220test06', 'cff861f7230e4caeaa08aaab03786d58', '15758943541896485236', '22000006959', 0, '220test06');
INSERT INTO "public"."cfg_device_message" VALUES ('4e5ba46e7c69415690f0dccfcf5116ca', '220test02', '4c8140b2d0da4e5187c88a441eadbea5', '172.16.5.113-gb', '22000009399', -1, '220test02');

-- ----------------------------
-- Table structure for cfg_directory_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."cfg_directory_resource";
CREATE TABLE "public"."cfg_directory_resource" (
                                                   "resource_id" varchar(64) COLLATE "pg_catalog"."default",
                                                   "directory_id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
                                                   "level" int4,
                                                   "parent_directory_id" varchar(64) COLLATE "pg_catalog"."default",
                                                   "gd_id" varchar(64) COLLATE "pg_catalog"."default",
                                                   "channel_index" varchar(16) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."cfg_directory_resource"."resource_id" IS '资源id';
COMMENT ON COLUMN "public"."cfg_directory_resource"."directory_id" IS '机构Id';
COMMENT ON COLUMN "public"."cfg_directory_resource"."level" IS '级别';
COMMENT ON COLUMN "public"."cfg_directory_resource"."parent_directory_id" IS '上级部门';
COMMENT ON COLUMN "public"."cfg_directory_resource"."gd_id" IS '通道ID';
COMMENT ON COLUMN "public"."cfg_directory_resource"."channel_index" IS '通道编号集合';

-- ----------------------------
-- Records of cfg_directory_resource
-- ----------------------------
INSERT INTO "public"."cfg_directory_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', 'c8d994d0814c471594fa66862888b8ed', 3, 'e42b83d211764d19b4d80c88ac6ce123', NULL, NULL);
INSERT INTO "public"."cfg_directory_resource" VALUES ('19a1cd1d22e54309985ca8942ddf01e9', 'c8d994d0814c471594fa66862888b8ed', NULL, 'e42b83d211764d19b4d80c88ac6ce123', '', '0,1');
INSERT INTO "public"."cfg_directory_resource" VALUES ('df277203b0ac43f08d46168a1cc76ee5', 'c8d994d0814c471594fa66862888b8ed', 3, 'e42b83d211764d19b4d80c88ac6ce123', NULL, NULL);
INSERT INTO "public"."cfg_directory_resource" VALUES ('-1', 'e42b83d211764d19b4d80c88ac6ce123', 2, 'c525497b6585412aa6419fa3816b1f87', NULL, NULL);

-- ----------------------------
-- Table structure for cfg_online_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."cfg_online_user";
CREATE TABLE "public"."cfg_online_user" (
                                            "id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                            "account" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
                                            "ip" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
                                            "login_time" timestamp(0) NOT NULL,
                                            "port" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
                                            "q_web_server_ip" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of cfg_online_user
-- ----------------------------
INSERT INTO "public"."cfg_online_user" VALUES ('8b0c8696b1734fff839e39b93f332726', 'admin', '0:0:0:0:0:0:0:1', '2021-05-19 11:13:56', '4443', '172.16.7.149');
INSERT INTO "public"."cfg_online_user" VALUES ('53737133822f4656ad76612d5d9d0332', '220test02', '172.16.12.10', '2021-05-21 10:30:05', '4443', '172.16.7.149');

-- ----------------------------
-- Table structure for cfg_plugin
-- ----------------------------
DROP TABLE IF EXISTS "public"."cfg_plugin";
CREATE TABLE "public"."cfg_plugin" (
                                       "plugin_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
                                       "ip" varchar(255) COLLATE "pg_catalog"."default",
                                       "check_plugin" bit(255),
                                       "decode_type" bit(255),
                                       "decode_resolution" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."cfg_plugin"."plugin_id" IS '插件ID';

-- ----------------------------
-- Records of cfg_plugin
-- ----------------------------

-- ----------------------------
-- Table structure for cfg_plugin_server
-- ----------------------------
DROP TABLE IF EXISTS "public"."cfg_plugin_server";
CREATE TABLE "public"."cfg_plugin_server" (
                                              "server_ip" varchar(16) COLLATE "pg_catalog"."default" NOT NULL,
                                              "port" int4 NOT NULL,
                                              "node_id" varchar(64) COLLATE "pg_catalog"."default",
                                              "server_id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."cfg_plugin_server"."server_ip" IS '服务器ip';
COMMENT ON COLUMN "public"."cfg_plugin_server"."port" IS '服务端口';
COMMENT ON COLUMN "public"."cfg_plugin_server"."node_id" IS '节点id';
COMMENT ON COLUMN "public"."cfg_plugin_server"."server_id" IS '主键id';

-- ----------------------------
-- Records of cfg_plugin_server
-- ----------------------------
INSERT INTO "public"."cfg_plugin_server" VALUES ('172.16.12.122', 4443, NULL, 'eg799aeded124e0e8f2add2e5aab83d6');
INSERT INTO "public"."cfg_plugin_server" VALUES ('172.16.12.122', 4453, NULL, 'kiuh9aeded124e0e8f2add2e5aab83d6');
INSERT INTO "public"."cfg_plugin_server" VALUES ('172.16.12.122', 4463, NULL, 'loiaaeded124e0e8f2add2e5aab83d6');
INSERT INTO "public"."cfg_plugin_server" VALUES ('172.16.12.122', 4473, NULL, '10876aeded124e0e8f2add2e5aab83d6');

-- ----------------------------
-- Table structure for cfg_table
-- ----------------------------
DROP TABLE IF EXISTS "public"."cfg_table";
CREATE TABLE "public"."cfg_table" (
                                      "id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                      "application" varchar(256) COLLATE "pg_catalog"."default" NOT NULL,
                                      "module" varchar(128) COLLATE "pg_catalog"."default" NOT NULL,
                                      "key" varchar(128) COLLATE "pg_catalog"."default" NOT NULL,
                                      "value" varchar(256) COLLATE "pg_catalog"."default" NOT NULL,
                                      "desc" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "public"."cfg_table"."application" IS '应用名，GLOBAL为全局';
COMMENT ON COLUMN "public"."cfg_table"."module" IS '应用模块名(system,order...)';
COMMENT ON COLUMN "public"."cfg_table"."key" IS 'key值';
COMMENT ON COLUMN "public"."cfg_table"."value" IS '对应value值';
COMMENT ON COLUMN "public"."cfg_table"."desc" IS '描述';

-- ----------------------------
-- Records of cfg_table
-- ----------------------------
INSERT INTO "public"."cfg_table" VALUES ('da630a5e18ab4177b70a8ff28c9e7f9a', 'xiaoyu', 'third', 'rtoc.config.url', ' http://10.79.2.113:8866/idac/xiaoyu/tlm/meeting', '请求RTOC会议接口');
INSERT INTO "public"."cfg_table" VALUES ('23247993f88b46e09f8b50aa6cfecd8c', 'xiaoyu', 'third', 'xiaoyu.config.proxyHost', '10.22.129.21', '代理IP (proxy7.bj.petrochina)');
INSERT INTO "public"."cfg_table" VALUES ('9ae3bc00634e4c39b548237bf3f2f9a5', 'xiaoyu', 'third', 'xiaoyu.config.proxyPort', '8080', '代理端口');
INSERT INTO "public"."cfg_table" VALUES ('third.xiaoyu.config.controlPassword', 'xiaoyu', 'third', 'xiaoyu.config.controlPassword', '123456', '默认会议控制密码');
INSERT INTO "public"."cfg_table" VALUES ('a4b528eaf1f44dbebb0fcd0c439936c2', 'xiaoyu', 'third', 'xiaoyu.config.ipcDeviceSyncInterval', '21600000', 'ipc设备定时同步时间间隔（毫秒）六小时同步一次，6*60*60*1000');
INSERT INTO "public"."cfg_table" VALUES ('40c114e568c745d8b3c1bb93be4910c8', 'xiaoyu', 'third', 'xiaoyu.config.isProxy', 'true', '是否部分启用代理 true -- 表示是  false -- 表示否');
INSERT INTO "public"."cfg_table" VALUES ('0d5c909da6704d3fa6b8802c802e6bed', 'third-login', 'third', 'config.ywbm', 'DMTJHPT', '业务编码');
INSERT INTO "public"."cfg_table" VALUES ('fffd9201764240488cca2795da1a3ee2', 'xiaoyu', 'spring', 'datasource.driver-class-name', 'org.postgresql.Driver', '');
INSERT INTO "public"."cfg_table" VALUES ('fffd9201764240488cca2795da1a3ee2', 'xiaoyu', 'spring', 'datasource.type', 'com.alibaba.druid.pool.DruidDataSource', '');
INSERT INTO "public"."cfg_table" VALUES ('d34d8da88ee84203a4b95fa1917789da', '*', 'dataApi', 'url', 'http://18.55.10.113/DataApiService', '');
INSERT INTO "public"."cfg_table" VALUES ('41a22014510141a7859ef2999b9247b2', 'video', 'system', 'rtmp.handle', 'live', '');
INSERT INTO "public"."cfg_table" VALUES ('0b95aceb5908488c89aa3bf4f6588783', 'video', 'noplugin', 'mediaComonent', '172.16.7.149:4443,4453,4463,4473@172.16.7.150:4443,4453,4463@172.16.7.151:4443', '免插件登录IP');
INSERT INTO "public"."cfg_table" VALUES ('c4f606084e9b42e78030ebc133abeaaf', 'video', 'noplugin', 'maxLoginTimes', '4', '免插件登录次数限制');
INSERT INTO "public"."cfg_table" VALUES ('361ce8a521914de3b1db17c182a37e27', 'video', 'noplugin', 'mediaComonentLocalPort', '4443', '免插件端口');
INSERT INTO "public"."cfg_table" VALUES ('e8c16771e7744264a231210f9dc573c7', 'video', 'system', 'rtmp.port', '1935', 'rtmp 端口');
INSERT INTO "public"."cfg_table" VALUES ('0a2138894081402b8714630de486876b', 'video', 'mediaComonent', 'maps', '4443:0,4444:1,4445:2,4446:3', '媒体地图？？？');
INSERT INTO "public"."cfg_table" VALUES ('fbc8ee6c03db4bdfb8a57e5b2eefc7cf', 'monitor', 'spring', 'datasource.userName', 'postgres', '');
INSERT INTO "public"."cfg_table" VALUES ('lva4e33b47d048cc876663ad82158d6', 'monitor', 'spring', 'datasource.type', 'com.alibaba.druid.pool.DruidDataSource', NULL);
INSERT INTO "public"."cfg_table" VALUES ('4359782389f04ad694bd3d41804c1687', 'video', 'mediaComonent', 'ports', '4443,4444,4445,4446', '媒体端口');
INSERT INTO "public"."cfg_table" VALUES ('79b7ea1a73634272ba63853bec488672', 'video', 'mediaComonent', 'cron', ' 0/2 * * * * ?', '媒体表达式');
INSERT INTO "public"."cfg_table" VALUES ('ff799aeded124e0e8f2add2e5aab83d6', 'monitor', 'third', 'diag.url', 'http://172.16.7.185:8099/share/resource/diag_share121', '外部视频诊断url');
INSERT INTO "public"."cfg_table" VALUES ('94439fda813b4a6789b9f5b75a0e3b63', 'video', 'system', 'ffmpeg.timeOut', '3', 'ffmpeg 超时时间');
INSERT INTO "public"."cfg_table" VALUES ('11617ec0cfbd40f7be0597d08e21b9e5', 'video', 'system', 'play.service.port', '8081', 'play 端口');
INSERT INTO "public"."cfg_table" VALUES ('53273293610247c6bf11a0b8e6c3ba64', 'xiaoyu', 'spring', 'datasource.url', 'jdbc:postgresql://10.79.25.142:5433/xtplat', '');
INSERT INTO "public"."cfg_table" VALUES ('dac24b94ef5f4124b46ecfc06e8649df', 'video', 'system', 'play.ws.ip', '172.16.7.149', 'ws IP');
INSERT INTO "public"."cfg_table" VALUES ('b8a4e33b47d048cc876663ad82158d63', 'xiaoyu', 'spring', 'datasource.password', 'postgresql', '');
INSERT INTO "public"."cfg_table" VALUES ('17d2f2fb067542b4b566edbc3f04f3f1', 'monitor', 'third', 'diag.pwd', 'Eastwit12#$', '视频诊断密码');
INSERT INTO "public"."cfg_table" VALUES ('a94a4a2183d549d1a7847377d1525a08', 'video', 'system', 'play.ws.port', '8082', 'ws 端口');
INSERT INTO "public"."cfg_table" VALUES ('475388bcb6ee4bb4a04de655591a4f42', 'xiaoyu', 'third', 'xiaoyu.config.token', 'aa73593f7697fa00cec08ea2a665d4683bab44233ce68453b04844c6d3b603ce', '');
INSERT INTO "public"."cfg_table" VALUES ('53708d29dd98498fa8d2ab2f8e3bb609', 'xiaoyu', 'third', 'xiaoyu.config.enterpriseId', '27463c87157be19b7bddb62febfbd12f5f18932d', '企业ID');
INSERT INTO "public"."cfg_table" VALUES ('61e0f053dc404f1da7da2ca7f87e8135', '*', '', 'topNum', '20', '');
INSERT INTO "public"."cfg_table" VALUES ('2d8e7bd896c34c46ad38b3f277790588', 'video', 'system', 'play.ws.secret', 'supersecret', 'ws 秘钥');
INSERT INTO "public"."cfg_table" VALUES ('0d42c1bb81af4fb39472d5b63b452165', '*', '', 'online', 'false', '');
INSERT INTO "public"."cfg_table" VALUES ('da2141f409d14f4c8e7b5d104f167614', 'xiaoyu', 'temp', 'config.additionalUsers', 'true', '开启会议添加额外的用户，多个逗号分隔。顺序：熊主任，');
INSERT INTO "public"."cfg_table" VALUES ('bc4054b47b124eae98ab78feac556120', 'video', 'agent', 'host', '172.16.7.149', 'rtmp Host');
INSERT INTO "public"."cfg_table" VALUES ('5c944e63636b430f89f68f42f28b5668', 'xiaoyu', 'temp', 'config.defaultIPCDeviceName', '博孜1302( 80008)-罐区', '默认IPC设备名称 ');
INSERT INTO "public"."cfg_table" VALUES ('3bb33ab6d3af42a29ab22f7483257447', 'monitor', 'spring', 'datasource.url', 'jdbc:postgresql://10.79.25.142:5433/xtplat', '');
INSERT INTO "public"."cfg_table" VALUES ('d148ddfff6dc4f37abdacbd0ed68d1f3', 'xiaoyu', 'temp', 'config.isUseAdditionalUsers', 'true', '开启会议，是否默认添加熊主任的号码');
INSERT INTO "public"."cfg_table" VALUES ('45247993f89b49e09f8b50aa6cfecd8c', 'monitor', 'spring', 'datasource.driver-class-name', 'org.postgresql.Driver', NULL);
INSERT INTO "public"."cfg_table" VALUES ('fffd9201764240488cca2795da1a3ee2', 'xiaoyu', 'spring', 'datasource.userName', 'postgres', '');
INSERT INTO "public"."cfg_table" VALUES ('8a444e1d891f4e8281fa2279721cfba8', 'xiaoyu', 'third', 'xiaoyu.config.isProxys', 'true', '是否全部启用代理 true -- 表示是  false -- 表示否');
INSERT INTO "public"."cfg_table" VALUES ('5ec4b4b475dd42da98d1c6c1a383396d', 'video', 'system', 'rtmp.ip', '172.16.7.149', 'rtmp IP');
INSERT INTO "public"."cfg_table" VALUES ('1b068f5dcd2d4c03bb1d8acae41607a1', 'monitor', 'third', 'diag.name', 'admin', '外部视频诊断用户名');
INSERT INTO "public"."cfg_table" VALUES ('bdcab382cea349b58ca5db0e14d79b9a', 'monitor', 'spring', 'datasource.password', 'postgresql', '');
INSERT INTO "public"."cfg_table" VALUES ('3ab670b033fd44d388a630ea1f05c372', 'third-login', 'spring', 'datasource.password', 'postgresql', '');
INSERT INTO "public"."cfg_table" VALUES ('e6c0cd3424034b5697626c2d3a21275f', 'third-login', 'spring', 'datasource.userName', 'postgres', '');
INSERT INTO "public"."cfg_table" VALUES ('69867f31285347fa8f3eee00e9b89af3', 'third-login', 'spring', 'datasource.url', 'jdbc:postgresql://30.55.10.222:5433/xtplat', '');
INSERT INTO "public"."cfg_table" VALUES ('58eba3bedd54433da3185040b9f33f6d', 'third-login', 'third', 'config.userAccount', 'dmtjhpt', '');
INSERT INTO "public"."cfg_table" VALUES ('583f8d7517684f4f8d73f7c78724fae2', 'third-login', 'third', 'config.ywbmMc', '塔里木油田音视讯融合平台', '业务编码名称');
INSERT INTO "public"."cfg_table" VALUES ('2743a52295484cb4932575810cf251cc', 'third-login', 'third', 'config.url', 'http://10.79.3.15:8000/axis/services/TarimTysq', '');
INSERT INTO "public"."cfg_table" VALUES ('23247993f88b46e09f8b50aa6cfecd8s', '*', 'handler', 'packagesName', 'com.xtxk.monitor.api.handler', NULL);

-- ----------------------------
-- Table structure for cfg_user_plugin
-- ----------------------------
DROP TABLE IF EXISTS "public"."cfg_user_plugin";
CREATE TABLE "public"."cfg_user_plugin" (
                                            "user_id" varchar COLLATE "pg_catalog"."default" NOT NULL,
                                            "plugin_id" varchar COLLATE "pg_catalog"."default",
                                            "decode_type" bit(4),
                                            "decode_resolution" varchar(64) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of cfg_user_plugin
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_behavior_device
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_behavior_device";
CREATE TABLE "public"."dcfg_behavior_device" (
                                                 "device_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "behavior_device_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "behavior_Device_channel" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_behavior_device"."device_id" IS '设备id';
COMMENT ON COLUMN "public"."dcfg_behavior_device"."behavior_device_id" IS '行为分析服务器id';
COMMENT ON COLUMN "public"."dcfg_behavior_device"."behavior_Device_channel" IS '通道号';

-- ----------------------------
-- Records of dcfg_behavior_device
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_behavior_rule
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_behavior_rule";
CREATE TABLE "public"."dcfg_behavior_rule" (
                                               "rule_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                               "rule_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                               "device_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                               "alarm_enabled" bool NOT NULL,
                                               "alarm_event" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
                                               "cycle_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
                                               "cycle_pars" jsonb NOT NULL,
                                               "alarm_level" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
                                               "linkage_enabled" bool NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_behavior_rule"."rule_id" IS '规则id';
COMMENT ON COLUMN "public"."dcfg_behavior_rule"."rule_name" IS '规则名称';
COMMENT ON COLUMN "public"."dcfg_behavior_rule"."device_id" IS '设备id';
COMMENT ON COLUMN "public"."dcfg_behavior_rule"."alarm_enabled" IS '是否报警';
COMMENT ON COLUMN "public"."dcfg_behavior_rule"."alarm_event" IS '报警事件';
COMMENT ON COLUMN "public"."dcfg_behavior_rule"."cycle_type" IS '报警时段类型';
COMMENT ON COLUMN "public"."dcfg_behavior_rule"."cycle_pars" IS '报警时段参数';
COMMENT ON COLUMN "public"."dcfg_behavior_rule"."alarm_level" IS '报警级别';
COMMENT ON COLUMN "public"."dcfg_behavior_rule"."linkage_enabled" IS '是否联动';

-- ----------------------------
-- Records of dcfg_behavior_rule
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_behavior_rule_alarmto
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_behavior_rule_alarmto";
CREATE TABLE "public"."dcfg_behavior_rule_alarmto" (
                                                       "rule_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                       "alarm_to" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_behavior_rule_alarmto"."rule_id" IS 'FK dcfg_behavior_rule.rule_id';
COMMENT ON COLUMN "public"."dcfg_behavior_rule_alarmto"."alarm_to" IS '报警对象id';

-- ----------------------------
-- Records of dcfg_behavior_rule_alarmto
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_behavior_rule_linkage
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_behavior_rule_linkage";
CREATE TABLE "public"."dcfg_behavior_rule_linkage" (
                                                       "rule_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                       "link_event" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
                                                       "link_pars" jsonb NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_behavior_rule_linkage"."rule_id" IS 'FK dcfg_behavior_rule.rule_id';
COMMENT ON COLUMN "public"."dcfg_behavior_rule_linkage"."link_event" IS '联动事件';
COMMENT ON COLUMN "public"."dcfg_behavior_rule_linkage"."link_pars" IS '联动参数';

-- ----------------------------
-- Records of dcfg_behavior_rule_linkage
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_history_alarm
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_history_alarm";
CREATE TABLE "public"."dcfg_history_alarm" (
                                               "alarm_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                               "device_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                               "alarm_event" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
                                               "alarm_level" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
                                               "alarm_time" timestamp(6) NOT NULL,
                                               "sender_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_history_alarm"."alarm_id" IS '报警id';
COMMENT ON COLUMN "public"."dcfg_history_alarm"."device_id" IS '设备id';
COMMENT ON COLUMN "public"."dcfg_history_alarm"."alarm_event" IS '报警事件';
COMMENT ON COLUMN "public"."dcfg_history_alarm"."alarm_level" IS '报警级别';
COMMENT ON COLUMN "public"."dcfg_history_alarm"."alarm_time" IS '报警时间';
COMMENT ON COLUMN "public"."dcfg_history_alarm"."sender_id" IS '发送者';

-- ----------------------------
-- Records of dcfg_history_alarm
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_history_alarm_linkage
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_history_alarm_linkage";
CREATE TABLE "public"."dcfg_history_alarm_linkage" (
                                                       "alarm_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                       "link_event" varchar(30) COLLATE "pg_catalog"."default",
                                                       "alarm_msg" varchar(200) COLLATE "pg_catalog"."default",
                                                       "uri" varchar(100) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."dcfg_history_alarm_linkage"."alarm_id" IS 'FK dcfg_history_alarm.alarm_id';
COMMENT ON COLUMN "public"."dcfg_history_alarm_linkage"."link_event" IS '联动事件';
COMMENT ON COLUMN "public"."dcfg_history_alarm_linkage"."alarm_msg" IS '报警消息';
COMMENT ON COLUMN "public"."dcfg_history_alarm_linkage"."uri" IS 'uri';

-- ----------------------------
-- Records of dcfg_history_alarm_linkage
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_history_alarm_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_history_alarm_user";
CREATE TABLE "public"."dcfg_history_alarm_user" (
                                                    "alarm_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                    "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                    "is_read" bool NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_history_alarm_user"."alarm_id" IS 'FK dcfg_history_alarm.alarm_id';
COMMENT ON COLUMN "public"."dcfg_history_alarm_user"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."dcfg_history_alarm_user"."is_read" IS '是否已读';

-- ----------------------------
-- Records of dcfg_history_alarm_user
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_osdstyle
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_osdstyle";
CREATE TABLE "public"."dcfg_osdstyle" (
                                          "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                          "style_index" int2 NOT NULL,
                                          "screen_wdith" int2 NOT NULL,
                                          "screen_height" int2 NOT NULL,
                                          "create_time" timestamp(6) NOT NULL,
                                          "is_apply" bool NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_osdstyle"."resource_id" IS '资源id';
COMMENT ON COLUMN "public"."dcfg_osdstyle"."style_index" IS '样式index';
COMMENT ON COLUMN "public"."dcfg_osdstyle"."screen_wdith" IS '屏幕宽度';
COMMENT ON COLUMN "public"."dcfg_osdstyle"."screen_height" IS '屏幕高度';
COMMENT ON COLUMN "public"."dcfg_osdstyle"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."dcfg_osdstyle"."is_apply" IS '是否应用';

-- ----------------------------
-- Records of dcfg_osdstyle
-- ----------------------------
INSERT INTO "public"."dcfg_osdstyle" VALUES ('8b514cfb14fd4141a2595340973552f2', 1, 635, 300, '2021-04-23 14:28:49.973', 't');
INSERT INTO "public"."dcfg_osdstyle" VALUES ('a54f0268aff24f538e532e5cfea75163', 1, 635, 300, '2021-04-23 14:58:13.134', 't');
INSERT INTO "public"."dcfg_osdstyle" VALUES ('e23bc77b24274198a96764276fa868c5', 1, 603, 300, '2021-05-18 09:48:35.569', 't');
INSERT INTO "public"."dcfg_osdstyle" VALUES ('16b03eccc54c4bd5850ec92ea6713579', 1, 617, 300, '2021-08-10 06:39:05.053', 't');

-- ----------------------------
-- Table structure for dcfg_osdstyle_item
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_osdstyle_item";
CREATE TABLE "public"."dcfg_osdstyle_item" (
                                               "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                               "style_index" int2 NOT NULL,
                                               "element_index" int2 NOT NULL,
                                               "element_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
                                               "element_content" jsonb
)
;
COMMENT ON COLUMN "public"."dcfg_osdstyle_item"."resource_id" IS '资源id';
COMMENT ON COLUMN "public"."dcfg_osdstyle_item"."style_index" IS '样式index';
COMMENT ON COLUMN "public"."dcfg_osdstyle_item"."element_index" IS '元素index';
COMMENT ON COLUMN "public"."dcfg_osdstyle_item"."element_type" IS '元素类型';
COMMENT ON COLUMN "public"."dcfg_osdstyle_item"."element_content" IS '元素明细';

-- ----------------------------
-- Records of dcfg_osdstyle_item
-- ----------------------------
INSERT INTO "public"."dcfg_osdstyle_item" VALUES ('8b514cfb14fd4141a2595340973552f2', 1, 0, 'ResourceName', '{"x": 21, "y": 25, "fontSize": 3, "itemType": 3, "fontColorB": 19, "fontColorG": 75, "fontColorR": 206, "fontFamlily": 4, "textContent": "220-172.16.5.161"}');
INSERT INTO "public"."dcfg_osdstyle_item" VALUES ('8b514cfb14fd4141a2595340973552f2', 1, 1, 'DateTime', '{"x": 25, "y": 255, "index": 1, "fontSize": 6, "itemType": 1, "fontColorB": 19, "fontColorG": 75, "fontColorR": 206, "fontFamlily": 4, "textContent": "2021-04-23 22:30:32"}');
INSERT INTO "public"."dcfg_osdstyle_item" VALUES ('a54f0268aff24f538e532e5cfea75163', 1, 0, 'ResourceName', '{"x": 48, "y": 62, "fontSize": 9, "itemType": 3, "fontColorB": 19, "fontColorG": 69, "fontColorR": 206, "fontFamlily": 4, "textContent": "220-172.16.5.215"}');
INSERT INTO "public"."dcfg_osdstyle_item" VALUES ('a54f0268aff24f538e532e5cfea75163', 1, 1, 'DateTime', '{"x": 37, "y": 226, "index": 1, "fontSize": 9, "itemType": 1, "fontColorB": 19, "fontColorG": 69, "fontColorR": 206, "fontFamlily": 4, "textContent": "2021-04-23 23:03:41"}');
INSERT INTO "public"."dcfg_osdstyle_item" VALUES ('e23bc77b24274198a96764276fa868c5', 1, 0, 'ResourceName', '{"x": 96, "y": 41, "fontSize": 3, "itemType": 3, "fontColorB": 206, "fontColorG": 91, "fontColorR": 19, "fontFamlily": 4, "textContent": "13.55.10.161-云台"}');
INSERT INTO "public"."dcfg_osdstyle_item" VALUES ('e23bc77b24274198a96764276fa868c5', 1, 1, 'ResourceName', '{"x": 126, "y": 50, "index": 1, "fontSize": 3, "itemType": 3, "fontColorB": 206, "fontColorG": 91, "fontColorR": 19, "fontFamlily": 4, "textContent": "13.55.10.161-云台"}');
INSERT INTO "public"."dcfg_osdstyle_item" VALUES ('e23bc77b24274198a96764276fa868c5', 1, 2, 'ResourceName', '{"x": 80, "y": 54, "index": 2, "fontSize": 3, "itemType": 3, "fontColorB": 206, "fontColorG": 91, "fontColorR": 19, "fontFamlily": 4, "textContent": "13.55.10.161-云台"}');
INSERT INTO "public"."dcfg_osdstyle_item" VALUES ('16b03eccc54c4bd5850ec92ea6713579', 1, 0, 'ResourceName', '{"x": 30, "y": 21, "fontSize": 8, "itemType": 3, "fontColorB": 19, "fontColorG": 63, "fontColorR": 206, "fontFamlily": 4, "textContent": "220-172.16.5.81"}');
INSERT INTO "public"."dcfg_osdstyle_item" VALUES ('16b03eccc54c4bd5850ec92ea6713579', 1, 1, 'ResourceName', '{"x": 341, "y": 23, "index": 1, "fontSize": 7, "itemType": 3, "fontColorB": 231, "fontColorG": 40, "fontColorR": 7, "fontFamlily": 4, "textContent": "9999999"}');

-- ----------------------------
-- Table structure for dcfg_prepoint
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_prepoint";
CREATE TABLE "public"."dcfg_prepoint" (
                                          "prepoint_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                          "prepoint_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                          "device_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                          "angle" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                          "is_default" bool NOT NULL,
                                          "creator" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                          "create_time" timestamp(6) NOT NULL,
                                          "channel_index" varchar(10) COLLATE "pg_catalog"."default" DEFAULT 0
)
;
COMMENT ON COLUMN "public"."dcfg_prepoint"."prepoint_id" IS '预置点id';
COMMENT ON COLUMN "public"."dcfg_prepoint"."prepoint_name" IS '预置点名称';
COMMENT ON COLUMN "public"."dcfg_prepoint"."device_id" IS '编码器id';
COMMENT ON COLUMN "public"."dcfg_prepoint"."angle" IS '角度';
COMMENT ON COLUMN "public"."dcfg_prepoint"."is_default" IS '是否默认预置点';
COMMENT ON COLUMN "public"."dcfg_prepoint"."creator" IS '创建人';
COMMENT ON COLUMN "public"."dcfg_prepoint"."create_time" IS '创建时间';

-- ----------------------------
-- Records of dcfg_prepoint
-- ----------------------------
INSERT INTO "public"."dcfg_prepoint" VALUES ('556dbb01-fb49-42c1-8509-3a01d6074581', '222', '22000000016', '1', 't', '8ea6851443e04a5ca54bab6a6b9af997', '2021-07-22 06:39:08.683', '0');
INSERT INTO "public"."dcfg_prepoint" VALUES ('0a6f6d02-e995-4b20-bca8-d5886eb52dca', '33', '22000000016', '0', 't', '6ed7c2fcd79d493da0936b366fde71c8', '2021-07-09 01:58:27.825', '0');
INSERT INTO "public"."dcfg_prepoint" VALUES ('4910c529-560e-4e40-ade9-1b48f326b0d1', '11111', '22000000016', '2', 't', 'faf2919f0d2949e299c0e646abc20bcb', '2021-08-03 06:30:52.47', '0');

-- ----------------------------
-- Table structure for dcfg_subscription
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_subscription";
CREATE TABLE "public"."dcfg_subscription" (
                                              "source_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                              "source_screen_index" int2 NOT NULL,
                                              "target_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                              "target_screen_index" int2 NOT NULL,
                                              "create_time" timestamp(6) NOT NULL,
                                              "is_actived" bool NOT NULL,
                                              "subscription_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_subscription"."source_id" IS '源解码器id';
COMMENT ON COLUMN "public"."dcfg_subscription"."source_screen_index" IS '源解码器分屏';
COMMENT ON COLUMN "public"."dcfg_subscription"."target_id" IS '目标解码器id';
COMMENT ON COLUMN "public"."dcfg_subscription"."target_screen_index" IS '目标解码器分屏';
COMMENT ON COLUMN "public"."dcfg_subscription"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."dcfg_subscription"."is_actived" IS '是否激活';

-- ----------------------------
-- Records of dcfg_subscription
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_synchronization
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_synchronization";
CREATE TABLE "public"."dcfg_synchronization" (
                                                 "source_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "target_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "create_time" timestamp(6) NOT NULL,
                                                 "is_actived" bool NOT NULL,
                                                 "synchronization_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_synchronization"."source_id" IS '源解码器id';
COMMENT ON COLUMN "public"."dcfg_synchronization"."target_id" IS '目标解码器id';
COMMENT ON COLUMN "public"."dcfg_synchronization"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."dcfg_synchronization"."is_actived" IS '是否激活';

-- ----------------------------
-- Records of dcfg_synchronization
-- ----------------------------

-- ----------------------------
-- Table structure for dcfg_yt_takeover
-- ----------------------------
DROP TABLE IF EXISTS "public"."dcfg_yt_takeover";
CREATE TABLE "public"."dcfg_yt_takeover" (
                                             "device_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                             "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."dcfg_yt_takeover"."device_sip_id" IS '设备id';
COMMENT ON COLUMN "public"."dcfg_yt_takeover"."user_id" IS '用户id';

-- ----------------------------
-- Records of dcfg_yt_takeover
-- ----------------------------

-- ----------------------------
-- Table structure for directory_device_rel
-- ----------------------------
DROP TABLE IF EXISTS "public"."directory_device_rel";
CREATE TABLE "public"."directory_device_rel" (
                                                 "directory_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."directory_device_rel"."directory_id" IS '部门ID';
COMMENT ON COLUMN "public"."directory_device_rel"."resource_id" IS '资源ID';

-- ----------------------------
-- Records of directory_device_rel
-- ----------------------------
INSERT INTO "public"."directory_device_rel" VALUES ('c8d994d0814c471594fa66862888b8ed', '19a1cd1d22e54309985ca8942ddf01e9');
INSERT INTO "public"."directory_device_rel" VALUES ('e294a0410cb74bc9a3b49e68c43134be', '19a1cd1d22e54309985ca8942ddf01e9');
INSERT INTO "public"."directory_device_rel" VALUES ('c8d994d0814c471594fa66862888b8ed', '67f12bed21d442759d0ac8fcf9f97dcf');
INSERT INTO "public"."directory_device_rel" VALUES ('e294a0410cb74bc9a3b49e68c43134be', '16b03eccc54c4bd5850ec92ea6713579');
INSERT INTO "public"."directory_device_rel" VALUES ('e294a0410cb74bc9a3b49e68c43134be', '8b514cfb14fd4141a2595340973552f2');
INSERT INTO "public"."directory_device_rel" VALUES ('c8d994d0814c471594fa66862888b8ed', '5be170623c05447d8f7ce24c464685c9');
INSERT INTO "public"."directory_device_rel" VALUES ('a5257ccb15f14684ba3cdfe69dbbe6c4', '67f12bed21d442759d0ac8fcf9f97dcf');
INSERT INTO "public"."directory_device_rel" VALUES ('a5257ccb15f14684ba3cdfe69dbbe6c4', '8b514cfb14fd4141a2595340973552f2');
INSERT INTO "public"."directory_device_rel" VALUES ('a5257ccb15f14684ba3cdfe69dbbe6c4', '5be170623c05447d8f7ce24c464685c9');
INSERT INTO "public"."directory_device_rel" VALUES ('c8d994d0814c471594fa66862888b8ed', '19a1cd1d22e54309985ca8942ddf01e9');

-- ----------------------------
-- Table structure for file_channel
-- ----------------------------
DROP TABLE IF EXISTS "public"."file_channel";
CREATE TABLE "public"."file_channel" (
                                         "channel_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
                                         "channel_name" varchar(255) COLLATE "pg_catalog"."default",
                                         "is_sharing" bool,
                                         "creator_id" varchar(255) COLLATE "pg_catalog"."default",
                                         "creator_date" date
)
;

-- ----------------------------
-- Records of file_channel
-- ----------------------------
INSERT INTO "public"."file_channel" VALUES ('d7e88d0e-2d65-4c10-9a51-7bf102357266', 'test1', 'f', '955d07e537044030912d19bffbb773be', '2020-11-10');
INSERT INTO "public"."file_channel" VALUES ('c25b88df-a09d-44da-8673-9003e8867210', 'test', 'f', '21c26709ceef495e910ac8dc2df6b6b0', '2021-03-15');
INSERT INTO "public"."file_channel" VALUES ('52cd3cb7-094d-4751-b7f5-9a9781a4adca', 'test', 'f', '21c26709ceef495e910ac8dc2df6b6b0', '2021-03-15');
INSERT INTO "public"."file_channel" VALUES ('5e746e1e-df89-4f14-81d4-719992cba5d2', 'test', 'f', '21c26709ceef495e910ac8dc2df6b6b0', '2021-03-15');
INSERT INTO "public"."file_channel" VALUES ('768e4d6e-6694-4ab6-a956-5f1124e21e14', 'aaa', 'f', '21c26709ceef495e910ac8dc2df6b6b0', '2021-03-16');
INSERT INTO "public"."file_channel" VALUES ('1e722418-a13a-4f25-9a61-0e825ed32a70', 'test', 'f', 'ec1b94492d584d8993aa1d11d845cea2', '2021-03-16');
INSERT INTO "public"."file_channel" VALUES ('c1b079f9-45dd-4607-83bd-1339f770365a', 'aaa', 'f', '66770244df0b4e2cb40a1a580f8341d4', '2021-03-16');
INSERT INTO "public"."file_channel" VALUES ('63d2e74a-1991-4965-a08a-f36a524bb947', '****a', 'f', '21c26709ceef495e910ac8dc2df6b6b0', '2021-03-16');
INSERT INTO "public"."file_channel" VALUES ('e648f375-8db2-4aa2-af5c-fb0d45d91242', 'aaaaaaaaaaaaaaaabc', 'f', '21c26709ceef495e910ac8dc2df6b6b0', '2021-03-16');
INSERT INTO "public"."file_channel" VALUES ('70a3992d-6fa0-4211-ae1b-d1e1b6b518ab', '555555', 'f', '21c26709ceef495e910ac8dc2df6b6b0', '2021-03-17');
INSERT INTO "public"."file_channel" VALUES ('f808cf36-9305-46b8-8441-1e61374df068', 'test', 'f', '2ab8f4a7083941d6a3bd7d54355c3d3c', '2021-04-16');

-- ----------------------------
-- Table structure for file_channel_item
-- ----------------------------
DROP TABLE IF EXISTS "public"."file_channel_item";
CREATE TABLE "public"."file_channel_item" (
                                              "item_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
                                              "file_name" varchar(255) COLLATE "pg_catalog"."default",
                                              "file_size" int8,
                                              "media_id" varchar(255) COLLATE "pg_catalog"."default",
                                              "channel_id" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of file_channel_item
-- ----------------------------
INSERT INTO "public"."file_channel_item" VALUES ('bf3072f7-a7d7-4c7e-b596-8a0f8e1454b1', 'ok.mp4', 3, 'hN_MOr2GR', 'd7e88d0e-2d65-4c10-9a51-7bf102357266');

-- ----------------------------
-- Table structure for group_device
-- ----------------------------
DROP TABLE IF EXISTS "public"."group_device";
CREATE TABLE "public"."group_device" (
                                         "strategy_id" varchar(128) COLLATE "pg_catalog"."default" NOT NULL,
                                         "group_id" varchar(128) COLLATE "pg_catalog"."default",
                                         "device_id" varchar(128) COLLATE "pg_catalog"."default" NOT NULL,
                                         "device_name" varchar(128) COLLATE "pg_catalog"."default",
                                         "res_ch" varchar(8) COLLATE "pg_catalog"."default",
                                         "resource_type" varchar(8) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."group_device"."device_name" IS '设备名称';

-- ----------------------------
-- Records of group_device
-- ----------------------------
INSERT INTO "public"."group_device" VALUES ('', '', 'e23bc77b24274198a96764276fa868c5', '13.55.10.161-云台', '0', '1');
INSERT INTO "public"."group_device" VALUES ('', '', 'a54f0268aff24f538e532e5cfea75163', '220-172.16.5.215', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621391234650', '', 'e23bc77b24274198a96764276fa868c5', '13.55.10.161-云台', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621391234650', '', '8b514cfb14fd4141a2595340973552f2', '220-172.16.5.161', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621392425597', '', 'e23bc77b24274198a96764276fa868c5', '13.55.10.161-云台', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621392425597', '', '8b514cfb14fd4141a2595340973552f2', '220-172.16.5.161', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621392425597', '', 'a54f0268aff24f538e532e5cfea75163', '220-172.16.5.215', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621392425597', '', '16b03eccc54c4bd5850ec92ea6713579', '220-30.55.10.52', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621392425597', '', '5be170623c05447d8f7ce24c464685c9', '220-30.55.10.54-sip', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621392425597', '', '4c8140b2d0da4e5187c88a441eadbea5', '172.16.5.113-gb', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621913701342', '', 'c525497b6585412aa6419fa3816b1f87', '220--兴图新科', '0', NULL);
INSERT INTO "public"."group_device" VALUES ('1621913701342', '', '8b514cfb14fd4141a2595340973552f2', '220-172.16.5.161', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1621913721736', '', '8b514cfb14fd4141a2595340973552f2', '220-172.16.5.161', '0', '1');
INSERT INTO "public"."group_device" VALUES ('1627635704064', '', 'c525497b6585412aa6419fa3816b1f87', '220--兴图新科', '0', NULL);

-- ----------------------------
-- Table structure for hot_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."hot_resource";
CREATE TABLE "public"."hot_resource" (
                                         "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                         "resource_sip_id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                         "resource_name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                         "resource_type" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
                                         "directory_id" varchar(50) COLLATE "pg_catalog"."default",
                                         "create_time" timestamp(6),
                                         "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                         "num" int8 DEFAULT 0,
                                         "channel_index" int2,
                                         "device_type" varchar(255) COLLATE "pg_catalog"."default",
                                         "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."hot_resource"."resource_id" IS '资源id';
COMMENT ON COLUMN "public"."hot_resource"."resource_sip_id" IS '资源sipid';
COMMENT ON COLUMN "public"."hot_resource"."resource_name" IS '资源名称';
COMMENT ON COLUMN "public"."hot_resource"."resource_type" IS '资源类型,枚举类型';
COMMENT ON COLUMN "public"."hot_resource"."directory_id" IS '所属部门id';
COMMENT ON COLUMN "public"."hot_resource"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."hot_resource"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."hot_resource"."num" IS '数量';
COMMENT ON COLUMN "public"."hot_resource"."channel_index" IS '渠道编号';
COMMENT ON COLUMN "public"."hot_resource"."update_time" IS '修改时间';

-- ----------------------------
-- Records of hot_resource
-- ----------------------------
INSERT INTO "public"."hot_resource" VALUES ('e23bc77b24274198a96764276fa868c5', '22000022702', '13.55.10.161-云台', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 10:17:37.668', '6ed7c2fcd79d493da0936b366fde71c8', 1, NULL, NULL, '2021-05-19 10:17:37.668');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-19 10:31:29.768', '3712e17a88ff48539c5cde01294a3559', 5, NULL, NULL, '2021-08-09 19:00:10.266');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-19 18:09:54.216', 'cb6854b956ab4d38bc717b8a9be68185', 4736, NULL, NULL, '2021-05-20 13:49:21.064');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-20 18:20:05.986', '4e5ba46e7c69415690f0dccfcf5116ca', 7, NULL, NULL, '2021-06-22 10:37:52.166');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-19 18:09:54.219', 'cb6854b956ab4d38bc717b8a9be68185', 191, NULL, NULL, '2021-05-20 13:41:56.395');
INSERT INTO "public"."hot_resource" VALUES ('e23bc77b24274198a96764276fa868c5', '22000022702', '13.55.10.161-云台', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 10:16:51.324', '3712e17a88ff48539c5cde01294a3559', 6, NULL, NULL, '2021-05-19 10:32:06.271');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-21 16:52:47.555', '2ab8f4a7083941d6a3bd7d54355c3d3c', 88, NULL, NULL, '2021-08-06 14:45:30.473');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 16:36:34.5', 'cb6854b956ab4d38bc717b8a9be68185', 4747, NULL, NULL, '2021-05-20 18:21:27.732');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-20 16:22:19.727', '1e54477fbd6d41979f5f7ba2eb113e32', 7, NULL, NULL, '2021-05-20 16:38:48.333');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 16:36:37.9', 'cb6854b956ab4d38bc717b8a9be68185', 233, NULL, NULL, '2021-05-20 18:21:27.735');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 10:16:51.376', '3712e17a88ff48539c5cde01294a3559', 17, NULL, NULL, '2021-08-06 16:32:57.197');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-25 09:35:38.961', '8ea6851443e04a5ca54bab6a6b9af997', 10, NULL, NULL, '2021-08-09 09:44:57.433');
INSERT INTO "public"."hot_resource" VALUES ('80d7d215339d49639d9050289b00992f', '22000003730', '220-172.16.10.200-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-19 10:31:29.782', '3712e17a88ff48539c5cde01294a3559', 2, NULL, NULL, '2021-05-19 10:32:06.317');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 18:09:22.267', 'cb6854b956ab4d38bc717b8a9be68185', 212, NULL, NULL, '2021-05-20 18:21:27.737');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 18:09:22.271', 'cb6854b956ab4d38bc717b8a9be68185', 129, NULL, NULL, '2021-05-20 18:21:27.739');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-25 09:35:38.957', '8ea6851443e04a5ca54bab6a6b9af997', 4, NULL, NULL, '2021-06-11 14:30:23.309');
INSERT INTO "public"."hot_resource" VALUES ('e23bc77b24274198a96764276fa868c5', '22000022702', '13.55.10.161-云台', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 16:36:31.887', 'cb6854b956ab4d38bc717b8a9be68185', 3040, NULL, NULL, '2021-05-20 06:18:57.459');
INSERT INTO "public"."hot_resource" VALUES ('80d7d215339d49639d9050289b00992f', '22000003730', '220-172.16.10.200-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-25 16:50:27.081', '4e5ba46e7c69415690f0dccfcf5116ca', 4, NULL, NULL, '2021-06-03 14:23:13.136');
INSERT INTO "public"."hot_resource" VALUES ('80d7d215339d49639d9050289b00992f', '22000003730', '220-172.16.10.200-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-19 18:09:54.225', 'cb6854b956ab4d38bc717b8a9be68185', 67, NULL, NULL, '2021-05-20 13:51:10.316');
INSERT INTO "public"."hot_resource" VALUES ('e23bc77b24274198a96764276fa868c5', '22000022702', '13.55.10.161-云台', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 19:22:28.436', '4e5ba46e7c69415690f0dccfcf5116ca', 1, NULL, NULL, '2021-05-19 19:22:28.436');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-20 16:22:19.73', '1e54477fbd6d41979f5f7ba2eb113e32', 7, NULL, NULL, '2021-05-20 16:38:48.337');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-20 16:22:19.734', '1e54477fbd6d41979f5f7ba2eb113e32', 7, NULL, NULL, '2021-05-20 16:38:48.339');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-20 15:58:54.947', '1e54477fbd6d41979f5f7ba2eb113e32', 8, NULL, NULL, '2021-05-20 16:38:48.343');
INSERT INTO "public"."hot_resource" VALUES ('e23bc77b24274198a96764276fa868c5', '22000022702', '13.55.10.161-云台', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-20 16:38:31.932', '1e54477fbd6d41979f5f7ba2eb113e32', 2, NULL, NULL, '2021-05-20 16:38:37.443');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 10:16:51.352', '3712e17a88ff48539c5cde01294a3559', 14, NULL, NULL, '2021-05-21 09:15:57.59');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 10:16:51.365', '3712e17a88ff48539c5cde01294a3559', 13, NULL, NULL, '2021-05-21 09:15:57.592');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-19 10:31:29.776', '3712e17a88ff48539c5cde01294a3559', 5, NULL, NULL, '2021-08-09 19:00:10.27');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-25 09:35:38.953', '8ea6851443e04a5ca54bab6a6b9af997', 2, NULL, NULL, '2021-05-25 10:48:11.619');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-20 18:20:05.98', '4e5ba46e7c69415690f0dccfcf5116ca', 19, NULL, NULL, '2021-06-22 10:37:50.632');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 19:22:28.44', '4e5ba46e7c69415690f0dccfcf5116ca', 5, NULL, NULL, '2021-05-25 16:49:56.581');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 10:17:43.598', '6ed7c2fcd79d493da0936b366fde71c8', 4, NULL, NULL, '2021-06-29 15:47:04.171');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-20 18:20:05.976', '4e5ba46e7c69415690f0dccfcf5116ca', 18, NULL, NULL, '2021-06-22 10:37:56.032');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 10:16:51.38', '3712e17a88ff48539c5cde01294a3559', 17, NULL, NULL, '2021-08-09 19:00:10.261');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-21 09:26:15.109', '4e5ba46e7c69415690f0dccfcf5116ca', 5, NULL, NULL, '2021-06-03 14:23:13.116');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-20 09:34:39.883', '2ab8f4a7083941d6a3bd7d54355c3d3c', 10, NULL, NULL, '2021-08-06 14:49:21.873');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-25 09:35:38.963', '8ea6851443e04a5ca54bab6a6b9af997', 8, NULL, NULL, '2021-08-09 11:56:51.854');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-19 10:17:39.094', '6ed7c2fcd79d493da0936b366fde71c8', 6, NULL, NULL, '2021-06-29 15:48:31.801');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-27 11:17:13.507', '2ab8f4a7083941d6a3bd7d54355c3d3c', 6, NULL, NULL, '2021-06-18 17:23:44.549');
INSERT INTO "public"."hot_resource" VALUES ('4c8140b2d0da4e5187c88a441eadbea5', '22000009399', '172.16.5.113-gb', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-26 16:18:51.357', '2ab8f4a7083941d6a3bd7d54355c3d3c', 16, NULL, NULL, '2021-06-23 11:39:38.457');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-03 19:17:42.265', 'c578c001f01947888b156b4049e543fa', 18, NULL, NULL, '2021-08-09 09:50:29.756');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-21 10:52:27.621', '518d1566b3694a6cb040fdfd78aac48a', 3, NULL, NULL, '2021-06-22 14:00:32.893');
INSERT INTO "public"."hot_resource" VALUES ('80d7d215339d49639d9050289b00992f', '22000003730', '220-172.16.10.200-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-26 15:24:20.485', '2ab8f4a7083941d6a3bd7d54355c3d3c', 2, NULL, NULL, '2021-05-27 11:17:48.173');
INSERT INTO "public"."hot_resource" VALUES ('80d7d215339d49639d9050289b00992f', '22000003730', '220-172.16.10.200-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-01 09:46:28.452', '8ea6851443e04a5ca54bab6a6b9af997', 2, NULL, NULL, '2021-06-01 16:09:26.3');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-26 11:24:23.613', '518d1566b3694a6cb040fdfd78aac48a', 11, NULL, NULL, '2021-06-22 14:58:36.263');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-26 10:09:06.718', 'c578c001f01947888b156b4049e543fa', 5, NULL, NULL, '2021-07-30 18:17:00.134');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-28 09:19:25.944', 'a941a69c22a548a9aa74844ce4edc6c9', 12, NULL, NULL, '2021-06-30 09:29:38.156');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-21 09:26:13.726', '4e5ba46e7c69415690f0dccfcf5116ca', 5, NULL, NULL, '2021-06-03 14:23:13.131');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-04 14:17:59.162', 'faf2919f0d2949e299c0e646abc20bcb', 59, NULL, NULL, '2021-08-09 16:07:14.636');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-25 17:40:29.462', '2ab8f4a7083941d6a3bd7d54355c3d3c', 35, NULL, NULL, '2021-06-07 17:11:59.427');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-26 16:18:45.71', '2ab8f4a7083941d6a3bd7d54355c3d3c', 23, NULL, NULL, '2021-08-06 14:48:47.698');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-28 09:38:00.159', 'a941a69c22a548a9aa74844ce4edc6c9', 10, NULL, NULL, '2021-08-02 16:40:51.686');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-04 10:30:44.809', 'c578c001f01947888b156b4049e543fa', 15, NULL, NULL, '2021-08-09 09:50:29.757');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-01 09:46:28.432', '8ea6851443e04a5ca54bab6a6b9af997', 8, NULL, NULL, '2021-08-09 11:16:04.02');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-28 09:37:27.351', 'a941a69c22a548a9aa74844ce4edc6c9', 22, NULL, NULL, '2021-08-10 16:05:34.143');
INSERT INTO "public"."hot_resource" VALUES ('80d7d215339d49639d9050289b00992f', '22000003730', '220-172.16.10.200-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-04 14:17:59.171', 'faf2919f0d2949e299c0e646abc20bcb', 9, NULL, NULL, '2021-06-08 09:28:53.888');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-01 09:46:28.443', '8ea6851443e04a5ca54bab6a6b9af997', 9, NULL, NULL, '2021-08-09 11:56:51.851');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-26 11:24:23.625', '518d1566b3694a6cb040fdfd78aac48a', 6, NULL, NULL, '2021-06-29 10:24:38.798');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-26 16:40:21.363', 'c578c001f01947888b156b4049e543fa', 10, NULL, NULL, '2021-07-20 16:25:25.087');
INSERT INTO "public"."hot_resource" VALUES ('80d7d215339d49639d9050289b00992f', '22000003730', '220-172.16.10.200-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-28 09:38:00.153', 'a941a69c22a548a9aa74844ce4edc6c9', 4, NULL, NULL, '2021-06-02 10:20:21.752');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-18 09:58:36.548', 'faf2919f0d2949e299c0e646abc20bcb', 4, NULL, NULL, '2021-07-30 10:35:15.044');
INSERT INTO "public"."hot_resource" VALUES ('80d7d215339d49639d9050289b00992f', '22000003730', '220-172.16.10.200-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-04 10:30:44.792', 'c578c001f01947888b156b4049e543fa', 2, NULL, NULL, '2021-06-04 10:52:32.115');
INSERT INTO "public"."hot_resource" VALUES ('a54f0268aff24f538e532e5cfea75163', '22000015592', '220-172.16.5.215', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-04 18:25:58.523', 'faf2919f0d2949e299c0e646abc20bcb', 14, NULL, NULL, '2021-07-23 14:33:39.804');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-07 11:14:50.484', '1906cbdd951f4600abdf769ce2ec0448', 1, NULL, NULL, '2021-06-07 11:14:50.484');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-05-28 09:38:00.169', 'a941a69c22a548a9aa74844ce4edc6c9', 8, NULL, NULL, '2021-08-02 16:40:51.689');
INSERT INTO "public"."hot_resource" VALUES ('34020000001320022307', '22000040320', '通道1', '7', '19a1cd1d22e54309985ca8942ddf01e9', '2021-06-07 09:56:38.149', 'faf2919f0d2949e299c0e646abc20bcb', 3, 0, NULL, '2021-06-07 10:33:33.815');
INSERT INTO "public"."hot_resource" VALUES ('34020000001320022307', '22000040320', '通道1', '7', '19a1cd1d22e54309985ca8942ddf01e9', '2021-06-07 10:36:03.94', '2ab8f4a7083941d6a3bd7d54355c3d3c', 5, 0, NULL, '2021-06-07 10:49:22.641');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-07 11:14:57.058', '1906cbdd951f4600abdf769ce2ec0448', 1, NULL, NULL, '2021-06-07 11:14:57.058');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-26 11:24:23.611', '518d1566b3694a6cb040fdfd78aac48a', 3, NULL, NULL, '2021-06-21 10:29:01.92');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-26 14:53:00.913', '2ab8f4a7083941d6a3bd7d54355c3d3c', 41, NULL, NULL, '2021-08-06 14:48:47.69');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-04 18:25:58.53', 'faf2919f0d2949e299c0e646abc20bcb', 35, NULL, NULL, '2021-08-09 16:07:14.641');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-26 11:24:23.621', '518d1566b3694a6cb040fdfd78aac48a', 10, NULL, NULL, '2021-06-29 10:24:38.808');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-02 11:50:25.822', 'c578c001f01947888b156b4049e543fa', 14, NULL, NULL, '2021-08-09 09:50:29.759');
INSERT INTO "public"."hot_resource" VALUES ('4c8140b2d0da4e5187c88a441eadbea5', '22000009399', '172.16.5.113-gb', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-21 14:02:16.081', 'a941a69c22a548a9aa74844ce4edc6c9', 1, NULL, NULL, '2021-06-21 14:02:16.081');
INSERT INTO "public"."hot_resource" VALUES ('4c8140b2d0da4e5187c88a441eadbea5', '22000009399', '172.16.5.113-gb', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-29 11:52:26.047', '6ed7c2fcd79d493da0936b366fde71c8', 5, NULL, NULL, '2021-06-29 14:00:21.122');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-22 13:59:30.665', '518d1566b3694a6cb040fdfd78aac48a', 3, NULL, NULL, '2021-06-29 10:24:51.296');
INSERT INTO "public"."hot_resource" VALUES ('8b514cfb14fd4141a2595340973552f2', '22000015585', '220-172.16.5.161', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-21 14:02:24.747', 'a941a69c22a548a9aa74844ce4edc6c9', 3, NULL, NULL, '2021-06-29 18:34:22.481');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-05-28 09:37:27.344', 'a941a69c22a548a9aa74844ce4edc6c9', 25, NULL, NULL, '2021-08-02 16:40:51.692');
INSERT INTO "public"."hot_resource" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '22000000016', '220-30.55.10.52', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-29 11:52:11.872', '6ed7c2fcd79d493da0936b366fde71c8', 11, NULL, NULL, '2021-08-05 09:14:51.541');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-29 11:52:19.237', '6ed7c2fcd79d493da0936b366fde71c8', 21, NULL, NULL, '2021-08-05 09:14:51.548');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-07-20 16:02:30.513', 'c578c001f01947888b156b4049e543fa', 19, NULL, NULL, '2021-08-09 09:50:29.752');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-04 14:17:59.147', 'faf2919f0d2949e299c0e646abc20bcb', 35, NULL, NULL, '2021-08-09 16:07:14.643');
INSERT INTO "public"."hot_resource" VALUES ('5be170623c05447d8f7ce24c464685c9', '22000015576', '220-30.55.10.54-sip', 'Device', '380b0478d4b94e7bb9022dbbd6ccde35', '2021-06-04 18:25:58.535', 'faf2919f0d2949e299c0e646abc20bcb', 34, NULL, NULL, '2021-08-09 16:07:14.645');
INSERT INTO "public"."hot_resource" VALUES ('635e84cd013440b582cfe28b070d8299', '22000009408', '172.16.10.198-GB设备', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-07-22 18:14:24.739', '6ed7c2fcd79d493da0936b366fde71c8', 3, NULL, NULL, '2021-07-23 09:54:51.598');
INSERT INTO "public"."hot_resource" VALUES ('ce8b5590615543babce8f4c282dbda33', '22000003729', '220-172.16.10.199-GB', 'Device', '1d340b9dcd724aaeab6acc4503dfa7b3', '2021-06-29 15:41:27.206', '6ed7c2fcd79d493da0936b366fde71c8', 4, NULL, NULL, '2021-07-23 09:58:40.893');

-- ----------------------------
-- Table structure for info_channel_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_channel_info";
CREATE TABLE "public"."info_channel_info" (
                                              "channel_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                              "device_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                              "device_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                              "register_node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                              "channel_type" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                              "channel_name" varchar(50) COLLATE "pg_catalog"."default",
                                              "channel_index" int2,
                                              "channel_yt" varchar(500) COLLATE "pg_catalog"."default",
                                              "video_type" varchar(50) COLLATE "pg_catalog"."default",
                                              "gb_id" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of info_channel_info
-- ----------------------------
INSERT INTO "public"."info_channel_info" VALUES ('89e83995-f4ec-4d35-9277-3a461ff62d32', 'cff861f7230e4caeaa08aaab03786d58', '22000006959', '0a523fae18c542aa8c57442df46a0381', 'GBNVREncoderChannel', '15758943541896485236', 0, '{"allowMoveControl":false,"allowFocusControl":false,"allowApertureControl":false,"allowSpeedControl":false,"allowHeatControl":false,"allowWiperControl":false}', 'H264', '15758943541896485236');
INSERT INTO "public"."info_channel_info" VALUES ('af700b09-250f-4441-8a83-8727ea7e5d31', '19a1cd1d22e54309985ca8942ddf01e9', '22000040320', '0a523fae18c542aa8c57442df46a0381', 'GBNVREncoderChannel', '通道1', 0, '', 'H264', '34020000001320022307');
INSERT INTO "public"."info_channel_info" VALUES ('eec8e2fb-d5d7-4d65-845d-47525a1d9701', '19a1cd1d22e54309985ca8942ddf01e9', '22000040320', '0a523fae18c542aa8c57442df46a0381', 'GBNVREncoderChannel', '通道2', 1, '', 'H264', '34020000001320022308');

-- ----------------------------
-- Table structure for info_department_sort
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_department_sort";
CREATE TABLE "public"."info_department_sort" (
                                                 "sort_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "department_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "org_index" int2,
                                                 "cmd_index" int2
)
;

-- ----------------------------
-- Records of info_department_sort
-- ----------------------------

-- ----------------------------
-- Table structure for info_device
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_device";
CREATE TABLE "public"."info_device" (
                                        "device_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                        "device_category" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
                                        "device_ip" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
                                        "device_yt" varchar(300) COLLATE "pg_catalog"."default" NOT NULL,
                                        "device_main_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
                                        "device_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
                                        "channel_num" int2,
                                        "system_res_id" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."info_device"."device_id" IS '设备id';
COMMENT ON COLUMN "public"."info_device"."device_category" IS '设备型号';
COMMENT ON COLUMN "public"."info_device"."device_ip" IS '设备ip';
COMMENT ON COLUMN "public"."info_device"."device_yt" IS '云台权限';
COMMENT ON COLUMN "public"."info_device"."device_main_type" IS '设备大类,枚举类型';
COMMENT ON COLUMN "public"."info_device"."device_type" IS '设备明细类,枚举类型';

-- ----------------------------
-- Records of info_device
-- ----------------------------
INSERT INTO "public"."info_device" VALUES ('635e84cd013440b582cfe28b070d8299', '', '172.16.10.198', '', 'Encoder', 'GBSIPEncoder', 0, NULL);
INSERT INTO "public"."info_device" VALUES ('43bdb4121a744fbc81a58daac5847b42', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('61e6f385a3cd4fbba6e6b3b2d8f7ef0c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('45211a6f3ee14dae964f3c60d5226390', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a8a6208ef1f4404184dea7bd3a8a0049', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6edbfcd2488849f29f9d1c2ea5f5e6f1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4e9a35bc3dca4e80b268e5a4872b1c20', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0287952aaefa4e8abf36b3875f1c96f6', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('05238107e06441949912f7fb4a3946e3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('44078f4eb36e4e5c877e845eacdd0188', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('daa305ffdacb48dba5d0170406c51aca', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('652a5744de0b46f78b66dc819a8cd81b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('63c6ad72afa847b7b381b09e255791a5', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bfca486b94e54d768f8c27d748a50580', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6f3ab0a7a2f04c6ea4e8acad7880c491', '', '172.16.7.131', '', 'Encoder', 'GBSIPEncoder', 0, NULL);
INSERT INTO "public"."info_device" VALUES ('a8861eea77024d3792a619a231064a76', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0b8da2231a0345a0940ec9efb71c65b5', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cf4e5d83865e493ebe69dbd3d4299b09', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('036e7798805c4f23bad46c2055e43dff', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b0a8d25228b74ff8af702d685d872504', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('125f63e7988d4f9a8bf2fdcd31977b55', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c84adf736ea140a88de157997816f0e4', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('16b03eccc54c4bd5850ec92ea6713579', '', '30.55.10.52', '{"allowMoveControl":true,"allowFocusControl":true,"allowApertureControl":true,"allowSpeedControl":true,"allowHeatControl":true,"allowWiperControl":true}', 'Encoder', 'SIPEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f515f1354a3b493fbc40e84b4c2b996a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('862664913bc741da8b46c5ce95235883', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c67083dfde4d4fcaa8528dc576c0ec40', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4d5962f559be4142b08249c8533cb678', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f68962f07ce342048c9fde4d83142a9a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('26137c59cb8846e1a805b4fde5c463a6', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f85dce47b8da462d9a75ca0c23138cf0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1fed1a7e60dc4d5e8efc77751d93b360', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('299866b78fa64196a55cbf11f808482a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bf738867b72841ffa8f22d6420845fa4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('df79847785f14ac39e856da7fc1e325c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('97f1fccd3a36408b8fedd097409f6f5f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('345d22a9e4f245039c2b64f7d63b0515', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8d3292e3efca43fc88184cd4784fa05e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('90065284d96844d9bdf0ebcfb76162fc', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('255361b8e870406da801abe1829dadf1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9c7502b94ed44a7c8ecaac5ebcf59404', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1740815b08724db785af2f31017d7279', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b51637c6e12d48baa9609fbbc3b0873a', '', '', '', 'Encoder', 'XTSoftEncoder', 0, NULL);
INSERT INTO "public"."info_device" VALUES ('c034f5ac2db4439e980ef584183bdd85', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cdafa2b65138436e804a406d4ef1d933', '', '', '', 'Encoder', 'XTSoftEncoder', 0, NULL);
INSERT INTO "public"."info_device" VALUES ('75cc79acbe1f45cbba082c424404f350', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0c16d3bde5024c498ed414ee350ef489', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('eb54bb3f6061426e9ac186efa6a265db', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5039fcc4287d474b9fc75035639dbfcc', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5dcadc111941450e8de69b827deea8bf', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('54af106f62d84e8ea7762d4d052351b9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('20fa67f82f704032af0fa09bb577aa8d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5d3a9ff967784205824069994995a792', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('21a08092f085464396b1cb42254196d8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d3eb2622e909487199f312dfc3db6c67', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7118244bc1804436b5445f420b7ea0e1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0945c6f673cd41dfb697d4faa1453c99', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7843d68349594bb395c388897b8bc7ca', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('28b7011dbb4e4ca2803721fa8734b7d2', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('80b917b40a0d4298ad82145a8d0d4e3f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('81afbc16f5d4435f853a51293ac6842c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0b0485731013432f93eedc910bc8ab5b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('afb8dcac9439443fb8225a8764ae1d8f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6914282dd27540d1a00e211cea6050f5', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f31416f1c04647278fc2ccb4e9467e38', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cf13862ab0984b3b910ce17f74c404c9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8bb68bf826b34b4bb3a32388a88f69e0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('743bb67c65d04199b39c8ac7426a2da8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6e1a5f2ffec44683a81ed5f908a2fcca', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('84a8627dd34f46cf9f9810dec0613bfb', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9635c49cb9964b10856bf98942cb0850', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f8eacf00cbbe45818685b486b94f29b8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fdfb7072a8f7490fa2a5af59a15da6fc', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('686bb31f651844a7bc00cacff94d707f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ccef9ed944124b8aa65ebac05a90bdfc', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('76b35ecf9f7c42b79f283ebd7e897033', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cffe79ca7b1c4d1e9ee829b7e542ad04', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('599c62e0b2144c7794319a1f5929d4d0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2dfc7da4d84848b49eaf22c4ee853c5f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ad714233f5fd4b268a9122b14023d704', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('920f48209b82478b80b8e359a00f76cd', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bc25b41441344cb995847b7e3715f208', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('948a8675901f40a6bcfc8c5eec258eb5', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('49dffbd4b61b47a59a1c943f6f0b3871', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9c34f5447d064299b2f48198834da26e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b3f3683600db40a6baa71242c35fae6b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d92b4b9ea1fc4dc48c5b5f4a2ba0046c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2289ae8725ab41c8b1dfded1de1132bb', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('614187268e3a480a9be8a6a8f1e479bf', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8e202447127e42d288e43d61a280296f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('88030a62d60743c9a22f573a11fb839e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0bd5ea7939184911abe2cf3b30eaf56c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d60c30e403064ce69d65a46f64110b53', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5b82e00500fd4defacba99cc524bd361', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0604b7a587e94afb888a481a5706e31d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('692d713295f5474a973adc14eeb0b413', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d46a8c9f877b43ddaf6615ecfa4d5e24', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0d788915e6be44f1b06c5a2324a4cf7f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('21e2e182b0d543558c25188f80c82947', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('30b04429ef8847a597854686cf7446cb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3b53ecf0373c4205a184ac91035e1a57', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a42cccbd5dbc4d37ae640038d41bb9dc', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('19a1cd1d22e54309985ca8942ddf01e9', '', '172.16.3.66', '', 'Encoder', 'GBNVREncoder', 2, NULL);
INSERT INTO "public"."info_device" VALUES ('6b77ece0437e4f08a83ea645c33c9dde', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e9c7309b8b8443558951634e3a8f507a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5670bb9d0ec84b20a51523bf4b4c7fb9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('03de1c98aa51422880a578aba1e0264d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('53de37279b01497ab85e223d00d9b211', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b9766227e3db4d34b961948cfbac052a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f750b63779f84fd194e0efa98fe6fa24', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('efa951bb26db4ddc9568e6dc1f26e421', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4519e3c934204b049f4061ad2a94f76f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1697e64fbd1343d5bdd5b53797142fb9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('63cf30984404423d82ef56a5b9e82bf7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0c07aa579d4e42bd9ee3ecc2b035a2ad', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c67967a24af149aea5273c4e6bce5a0d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bafbc90f2762472786cd813cc974ae88', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3b7c2240fd7742c5be5a1902de53d1c8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a0265971e5894bccb880822840cba91b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('52cd4c37fd8d429ab3061075ec623fd8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f4fd20a0e9f447378186749b30ba3d76', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1b95c0fd53e24706886c37634bf8e70c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c719aa1b944b4eaca1082c20b787a44b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0f64e6d2df8340c9814a2b5e448dd57e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7821a64c7bf24893bb5edd00068f1290', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('195aafaea6d944ab8ede1687f0895f33', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('37d922e11a634284a9f77eee46c3c746', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8e16dacf23024018a5d87bdb38fb6bbf', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7a6736b028fb4825afa96bba97647466', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a68dc8a9b60940c2aac48b524190fd85', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('18169c92e94042bfa484d51fa3942b8d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fa34edaf8aa342dd96eb2011a66d6740', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ba975ace486445cabbd84e914ce7e04a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fe626789e3de458885afea31867fb95d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b3c75553e6d04b30bf07762f8e06f2c3', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e526aca80be54dcdab54ed82bfeb4715', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('38cc48cefd4e439b9c645f22296fca91', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5df947fc2f344702ba62683cb605232e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c07367dcc5eb4379a32430cf786d048a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('23f07d52170b4985bd2960036066093f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('64c38987e69147b3a0325d6ff46fdf12', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('54c1f2d6abd4465e8d461c8e36923b0c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a89590e027de4f84a8323e7ba8d3a896', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c228c80b57df4063b02496f7f5784051', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4d2ee42e9a254325936a3d221dd2a49b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('318ea5a8a6294b49b28be0f5a264e741', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('555662a1c06c4cb691b6543984df75df', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('87bb12be067c4abebd5ae2ceec3bcf79', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6d7df2ce807c4ccf8527b4e090c98ed4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('362a78c4e59c46cfaea6f0f9eca0d174', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('934d0438c785429387208fdb5751609d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7311bb9452e94e78b1722bc6693c8209', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('98eaae1c22e942c2a3b777d09d5d8199', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5e3c9f8661ce417f8e817c07a818a588', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2222b5bf16d24f0ca5e740ebdec6c095', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8458a8d8061e4c37a221fa27f2d07358', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('015f5399d5544c48ae37e5957d762f88', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('edec5a7e460d411d86c85f4c87c963f6', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('682754524e42459cab862199983b66ad', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('04da41265c9c492b88ac79309e4c1e8c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('98e55d0704704630806c321cd3d6d20f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('090e184befaf46b6a90b6067195d07b8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('68dfe96ca449412ba284b7b9283f3379', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('05bdc10f3f1142b193df1e09e1874dfb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('739da381c0e145558549872ffc4990fa', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3db1c3d9e91347158087ea315f07704f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a7ebd473130b4b9e867dd53e32f94c89', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ad2397f92266446da88a13a12666329e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ff066be26a3c48cca444c4d95f3ca02c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9f00952490724343b2b909eabd47b78e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('80299477aebc460088f399d6130eae27', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7cd97a2499d44398a561cde760606704', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e269b6e3c5c54644bcf1e4e81bc3e7de', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c607c8e9323a4523a16ceb4b97391bf1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c226897b92d04072810b6df98b34b61c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('40d8d138b3074d5db92e1075a1e7fee4', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('453284ef8a654ac387f8d107637e1220', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('835444778c4f4cce9bb7dc2e25a32ef5', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fc303e3f35374fa8864a823bd8d43085', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('557b0276fb2843399986557a7fe957f9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('596cd2cfe1864f9b8a190218e853f480', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e8dc6a02080946249f7ef1c7b5acbd4a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('786e6e0a04f742a598c26008c84924c0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5a614569273d48198bd6f39434670763', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('33e4f2e79a104e2c8c66a3c0cef9cfa8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('77cf8529b48b4debabf27a6410a20f61', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('85aacc876a2f484f8289ea979632405a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('71b94c963adc4b648c833935ee7909c4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('95d5a75a53ff42aa9c2cf7ee4ab81a23', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('32948b035f144e4986e2a2ee0f353101', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0dd13a40971545e9b65e04d8522ef214', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('220417b8c3b0471abba1921fdcccaefd', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('61125f80fa0749fc870b3b23e16120c9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a531279fb0354c9492c2b381adbc0a24', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ba7be099f96549d1be1a5325f8b30bc8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5c09fdbf45e0487a8e4a4a237e1566ea', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('04961ea432de43cfb27a187d31505a3c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a67e40c69e5947d69d5e5ccc371f7491', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a90b22b435f24905b7f1858397bf5b98', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7487515a3ecc410c87a96f7f5bd0142e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b7f742c750094437b21b9f1f934833a0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d0839bebc9a24276b1b7c6e49f71a2ea', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e19e2e3a2620419baf1bca4b3a6e0d95', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('445ec9524b1b4b08aa9ff87fb4bf7deb', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9d5f4a6fb2844d4b95d34d379f1f46b8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('994a83da62d543f89d08fa376456ea1a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8b514cfb14fd4141a2595340973552f2', '', '172.16.5.161', '{"allowMoveControl":true,"allowFocusControl":true,"allowApertureControl":true,"allowSpeedControl":true,"allowHeatControl":true,"allowWiperControl":true}', 'Encoder', 'SIPEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6cb069a252354985b2034dc75eebcfca', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('88befc24b92b41319bbeeb8ca2ca4527', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('eca344aa4ea54facaf164b7990b99894', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b9fcc7992194483197ab1faf1ba3cd38', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('41fe9383f0bf418aa60d7eb3fe527657', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7762f8b90eec4bd098a794e456d16bc5', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6f7ad468c1674931b19e621e62acc25d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c5b20369a503466e93f409fdf982c242', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7f1fd0bff0cc4cd0afd0cfb9d51ebaa9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cc33474c52244b1ca5177238f3c6fc12', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a6e29f6ca02949a98f379848e264be88', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8cf4a762cb074b7c95fc23785371f1be', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0f38a58c68cb4d03acad881c031e7c47', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('080a75c1640641a39abba6df46b3210c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d81abc8eb0e44543bf6d5de87516de4d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('56b59df90a264506a3728a468ac74c2d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('69941737f47f45f9adab9566d7856ad0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('531253bbc6eb4db7a0e42e1861a32f56', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ee06c259186341e4904723d3672b31a4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('30dd3ff733af4008bd5e9206038b945f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3c7f4cddaacd49aaa7aa16d3598fd55c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('21ac1c050cd24d2ba56c2e60055a6c9b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0045c4e1703149da85d0ebeda2eb17c1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('800d1a784d5b427e857a8a228e0c10c7', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f05b2f288ecb45fbb0cc66f56e41c04b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e782bcdc02ae43c292875eb497a10393', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('127a925bf13e48408c8d2470327db528', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('080564e5942c4dc497214f8c9d3f4af3', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2cb896b037b24d0dba673ecaa39715f9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('aa69dd54360c48869c83c62ad05d3f24', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b54d7aaf7cb54627b43ae33b48380229', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('986a022bf92d41eca30d6b8a2bd99bb8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('005736386dda40e7b730fa6da40dd53c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c1bc0c38a4a249389bd41c2db90c7f7b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('05f62f98a4fe4612963fe22f8fbd17db', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a4c6b55206f24901b9044532972d35fa', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f8becf44010146638e47cd16b5bec868', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2a6a5cb4bf4e486e92d13b2ef604238b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2ee5740f9722479dbd4356dc72d0cb02', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('377321d23b244e2a826588ce3aac27a7', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0cc6e9ec1a9f447b9421f668418602d0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('69479b32f4a44864b623a8bbe17d257b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0f1b2a330f8a49cc8a6e98d1672ceb9a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('33ba551d8bef487089b7cdcbca9f1765', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ff40c17e787d43ea96f32f430a2e6cfd', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9c7f127b9bef40e390a766f00df25082', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('90465155a1d64425a09be2f60ff2d0c7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('22428ec569ca41978f43797f0b532d9c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6b82be1567d9414aad7a5d6fe1971cba', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4218b293a95d42c5a0f5100147e65330', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('eef6d06f2b0945348a152b1e47ae62c7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bffdd6389a7a484497a7da9625cff288', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d0c454845a9749219244c1ee91c4195a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('328fdccb693d4e058c69c7cd910d3a45', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b3b57d400cbf4494aba7eebca4403f58', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('773df4d104174696a1fb2cfdfe52db84', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('57dd10fb7bfa4e8ea4ce84be3d0b427c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('dccab10e94044c7ea9165bcf0b7c1fc0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0ecfa7ddf103424b8331fdc4b16f4c5e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('635f83540faa47d186a115ca6dbf243a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ada2788719924f4e914969b90d3d77f2', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0fe2167959b04a5f9346f06ce4a32ec7', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3f4a8333fe8140cab044b738ea1add13', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9219cf92d49743feb339f66088a06763', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('71104680c8f64acc878643922f427623', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('67c99a196e1d46f591ae52760662da10', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('43ecc128debf4f5e818466c37fd48aab', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('33d3ee3cff9847a7a7612c001520b645', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8615a73a82534e199319d627f78bdfea', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d837c85131594745abcac48969eb5e84', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ab4df29042e4470c94aff197fd25e262', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('38fb17f2fa1e4824ad02a9ee6592548e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('947565e806514d05bdfb4c31627d270c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('69a928b6b16e4f849c7fe17c9f9f1dea', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5bb2bf2f004b460b8c210a136cb59bbe', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('505561d3389b4ba1a4f59dd8fa233d29', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('faf68c2b4b914be89602b948c43f506a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bd917467ba1e410f872c82b5e6f25395', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4a37e6fc4352447ea9dfc5f9f2cd5c2f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('636bb6f2161c4cbfbe5b8dfbc6cb9a70', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cd80588553194b03be176cf4249ab0f7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6156881d856045ec96f5a44608bc464e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ce8122dd19d94341a65f7ffaeb4e7313', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('df96223ec13b46deb12776bae92d2ca3', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0d58ac2f19b74284a6d4733c3f9f7b2c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b3467cda1a16412c913f7dc9905bc857', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('215fa49127f6409db9eb610a7797fa76', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7cfd2b8e7eaf45c89e4e487666b4d788', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('faf5e1821d1243ccad138689521e4953', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ce6afb9f507340af9658ff94ed025394', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a791516fe0694c0cb7fc1f7c7c17ab0c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('aa70d27569264b9a840c62c57d046c81', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d526921ffac5452fb3e7fdc580f53efb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('be10b3a40865418e845d17d8f9202325', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4186de0cc74444d28989389afa152442', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e07e06d02c2b4602872b2df7c0d1fb1f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('21aba23f28c44fac8f263ca1599bdd28', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f3b52ba98e024837b9f89f51c676d2a2', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('669253ce9c9b4987a35ff17e8b59451b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('afa7a2c1649847418d1fa3fca7caf099', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('83250989072848319b6805fcf19697ba', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('13c0dc8e2ea24634bf11ae401ce7a6d3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('964ffefa0f094d239dce3541b4aed6dc', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('22b6a6a9145749bc84621cca1b35168e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6873a6d392744e47bc4c3f08b7376c0f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('caa36f6ef6f24e0cb7bc2dab46db6731', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d2243411975f4d8a9c7679577496d9f7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1d86b85f4582479ab80b59211227863d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ea39ce11d9484fbcae0e7a288e56e048', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('356dbf6fe4e94afdb82b95df563524ec', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a9a96c96eb624300a2e830fa8caa1097', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('007c2103c9a34be8be814ec9d08f9444', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d4b4ab76361c4e61908768658c172adf', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0b121f2a43004f949809b4529d362802', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d1028e94324a4b12a0fc459aad646529', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2526ee1ec8e948409bbecef35ec725a8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cc68a9cfebb04da3b41428cd3fdd3663', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('67f12bed21d442759d0ac8fcf9f97dcf', '', '172.16.16.5', '{"allowMoveControl":true,"allowFocusControl":true,"allowApertureControl":true,"allowSpeedControl":true,"allowHeatControl":true,"allowWiperControl":true}', 'Encoder', 'SIPEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('52aeb478f430416cb1ebc7d1fc71e06c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('192274582a3c4c23aa5868741a592c51', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e6fe6cd3c27241b38d7b5fd77460e17f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4588ef716f244647b8cbc2e0b01a66c1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('760a8252b1eb430b81204eb9211a913a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2a9168b216cc41eb919e45436075d54e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('42666648c12f4d0bbae5dcaaa03421a8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6f5ab44334b9499596408abebc7b51d0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('829b5508896548ccad62a76afff3c21f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b9d00af0f4ba45cc869a95ee8d14f483', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fff9871855034fcb9e1d055cbc6b37cd', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('99ea247b1819412d9c6fc93ad6090635', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('acd1787e6cd24822a580b7aa24eeeec0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a54f0268aff24f538e532e5cfea75163', '', '172.16.5.215', '{"allowMoveControl":true,"allowFocusControl":true,"allowApertureControl":true,"allowSpeedControl":true,"allowHeatControl":true,"allowWiperControl":true}', 'Encoder', 'SIPEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fe467ef8188c40b1974aeb2d9e4a54b2', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d6643963772341098a6860f879c017d1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ec77ec3c51094fd7aa7a1004608ac428', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c05ab01a3d5e453eacfd245731da57ab', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3acc28b757b844a680b082c58fb09990', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('95047803fe7d466eba06e37ba5ed2c26', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ceff53a697534805ad1a71002e17b1f8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('afdfd2402bbd48e78582606ef1f07657', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('45a1884fb4bb4083a2ca400bd0932327', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('dc612d667f094c3fa5b9ab2ff71ae667', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9d2088502651412885d9148b8fe8bfd0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('981d7e18a1ff4ee9b3c092eec158ed48', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('afa5e93754d140e0a65cf03a81df674a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a08999ef0ffc49f0b2cf08452a46bb03', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4a586f7fd451451f970908500149934a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('14f335d78a3a476980dd991b3ea71ad1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c8a7a6cd5bde48e487b0ead340512b46', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7e620b479ae142eda2abf715b9082803', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('92798d4767704872becb27c26bc37ccd', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7ad508f58b884532abc674a231ec6ab1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('84d301de420546bd84b9ea0089a5ccc1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('65c53e6a72d743bc92ee870c816dbdb3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c6be62fb688847bcae0962f0b87bfd61', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ba218c34db734dcab006a57bd8a41aa0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f9292850b64249d5a20ae8c39ff74330', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f8f6e9fdd01840238f92cb506f91b035', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c3f7df3a4efc42ec8836b5fe4911291d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('700b814b33dd4cc39aebe2d7dd2a78a0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0c2853c610a142bfa58665a344fd6afa', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('373e24da45f04d5f98ecdb01d6c1bdcb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('286c5586ddf04d4eaae7ae2065b62098', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6688b0f8af5d4093a9dc74333fc119e7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bbb14fca047a485384c5e4424529f65d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('55413e5375754861b5e6cce2d0c4741b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3220ad266bf74be6af0f7ed5645ce5b1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('154d49cfd52340b78ff430d68e763aff', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b35ffa89eefc485d8ef74e882fd985f8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3b4e42f573744b6a89e4af930e12ea3b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('49885595cec84d0f9659b2f76eaff494', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0bc43b6083ba4c7685c270ef4bb18f14', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('16eed067ada14621978a140d01c0e988', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6a416a93f0764535a5a3fde5189fa083', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('417fc892da3f464a8c7f2c2522c89968', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('358a894e087c41efba537a1129e64c26', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('990cf5bbfeb247b1b2c46dbb18f27602', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8dfaeb72a37941288ede09b77bdc7d93', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('129810c435d94dd6a3790f4ef1f82188', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f0a0495b6189439dad4b5568a34ae176', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9f178b5bb1e74353a8c2e0bfbc898381', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f988dacff96a4f9b99e7da871d736da0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7265e32af638455c8954cc0a5301691a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('02a1dc900c2043dfb755450abed0d37f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('46330b60889b416f9137e2823ba05253', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('61ce8c3515fd4f939fdda510d32f2112', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('22d853e5eb1745878257cf9ab6f63fde', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8607b894fec249dcb74ef1e1e64315f8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c02f6f3d46104311b86810368500aca9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2e99e92611274645816720bf3cd7f56f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c33f7d9b58b641359ca69506330c5cda', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4480be779bba4cb0b2dc8023f1d7c0ef', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3d73b2e1951b495abf27d36d5c5518cd', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('00b1d416c5ad4600890750455ecc091e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d7d05a8c5e94407bafd60c20bf47feb9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0b50a3a90b36450fb9885e58bc0ba0ff', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('490fac14ca8d4d61bdc3aa1915d25bcd', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6b5fdb4c033d4691b394baeef79afe01', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('90d8744a030e4023933cf12b20e29d2e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('df3f2da182b5499fb240c04d857e6c94', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b2dee632b029496c851c0e720e7150c7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8e65c6e7fe3246b29e58e44bb73dc090', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2ede561877b44ab98580a4d183786fda', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2faa507f10ee47ba9784a41d6b9a8b96', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('924078e039794833b04f182984c0a653', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fd1100047fb44344af90209b4ff65ef9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4c7c30de9e0c4894ac2c5c72360532ab', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a366700c81f84827b6bb418c65a28e5e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9c6d4715340b4d76951a403e2812f1fe', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a924011d34c740cd847e074b1828a804', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3074a0c816c14a35b1fbdb30be4f2540', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1f9f4207cfd14ff49e8a6ea46825f9ca', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6471711ee0c442bfb8426548cf597a32', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a1b37dc3f987442a86fa313d7d136445', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('18aad1b09b91414d859e317af7ae5d3d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c1b79f0075d44044aba43c922cbcc3a4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c594e911af694124bc338d95baa97e53', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b84d5dd5f8b8440e95d20239ed59b0e9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('08e44508bd14406eb0a32103e9125691', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fa12cd86457a4faeadf47d5b38600a50', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1d1188cfea9d4c7686a85d179644a0b1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('438cf9a8318e4d29a72342ceba168c5e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6d4e33d8cc2947d4beef888d2f0d671b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('644b688de4f441b09e23d757670c5e25', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0d3da7eb3161409a9ccd78612a4e69d6', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('81d513feecec4700a706a73888f3c2cf', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fbb36830789b4df99a03b8f914bc6cc3', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4c8140b2d0da4e5187c88a441eadbea5', '', '172.16.5.113', '', 'Encoder', 'GBSIPEncoder', 0, NULL);
INSERT INTO "public"."info_device" VALUES ('bab75b24b8ff40c59736ef337e7a65a7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('51a4a7242f4347f28b43ef2271fc8047', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3227cabc3c1b4b0fb07188f13917717b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9cd32304ca794c10b2fae1bc69a66c48', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c04235e91abe4364add4d9678746e925', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bfbc87a0e50c4f789eb4b93792c1acc0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bc5157fcc5574f8eb8f814bb7b7c4dab', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('487a380b650641e2aa990c33eb7272cb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9f7520befec247029d15be473ec8708a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('00bc1c10b6e14872a1668a10b0c4eff3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a97942e472eb41b99a6a11148a026f13', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d52340de8fc2446db6aa345e12207f65', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5e766c4976e34e1f878f81f9257e3dc9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('71681636acbe4a85b429e70dcf224210', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8bc0f742a7f54b3a86b8c01c4b71a0fd', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('42aa2931a60746e7b3d42c60cef76c82', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('03d72fe125b44207b791bd01b06e0dab', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('65956814c4db4b198492b5495a1f62fc', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c00345ecac39467591b0b224c92381d1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f5b722eb9fa04cc3bf61d5ddf5c372b9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('68107e4526f8492a90c8c90ef62c4a26', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9d1f93b78da0462c8efec4a8cb4a25d3', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3fb53acd9e9d4275a198a37ad6ee73e1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('03208a20a3df491aa6b203260d1a2828', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('12fce23072f14c3eadc6939ef369a4c9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ebf2f7aac36043bd97086335af48c821', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9079183a4afc4803affcb26182aea49b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d4c0b232d54740fc9d651aa254678f60', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('050559c78e1f48bca17a351b7f32cac8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('11cd94dbfaef4b0abae78f49f9df05e7', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8b761d3a72a94b519071027e9fb98882', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('04a2ad59260b416fb46813869d5f5e88', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9457c189e48f48a2aef6e8cce204c9ab', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f7403d10f11b412b926d7e8f25220058', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5efde2f2d94445fcaf6e8daf0e20f66a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('21d4d017fdf14f42910a65d5d6f83245', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3304c6d4f45842c49f81d2484afafa51', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5ead768a66aa44968227dc9419b58b2d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a868594885e04cb4ba9a281f0a5faf44', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7a618ce21d444738aeb1728589da4837', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a5ebad17315547bfabd91ba07db129d2', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('01e3c6ca74a948d9a2c640f84e262003', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9bc45466adaf4bed885aaaa154bbb75a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('284ecaad8db6461ab19c107459f1b487', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('31173e7f1248417a836eca56179a6576', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8dd97ce07959480e98408f0ffd5b3e41', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d3e2dce5afe84b1a8e5973dc6e54714b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('770dcb5e2091443c92fa1d2957d446f5', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('03c37aa52d5e407993cdd833c9a9917e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2cb34113ecb8456ebb892cc068f72688', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6d1387eb40204f6d8e425634b7e46e2c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8d063ae731ef4107a4e3b3a91353d123', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('dbdcbe05786f4da6bb1a06cf823e1425', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('67097953e25245b9b7931e6e06ea1e20', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b810a342de12494a9eff566bffa6fc0b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('94a8b8db9a6543f68cb4c18f9ae2d7cb', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6a129b7f10d0492cb794008ca89dffaa', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c6c89024d0394602b2c282b302d467b4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('948b7240ea2b4171bc97861db396fa03', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e53475ce68fb430c9210b54ed88d0d87', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a74094225f2b40a2a41558e683d3d28b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a44e3e92d492463bbd279bf6a0d86101', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('426e2e9a6c9549ab97f3a5e8d61cf0ae', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('88eefe80ec234a5c8d29090251ae7e4f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8ded8ec235ca46deacdc42ad5f6d583c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('241e2d1d2ec44126bae37e98a8afdbaa', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2380df31bebf49e491da400935d4dc12', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e160027d8db64e9cad708eedb41ad7e9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c6f464ebbf5d49a292405093f4b3578c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4a38ce7d9c5b4330a89bbbb4288ec8bf', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fa4545932fdd4907808347496fc6cca8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c11cbfd96c6144e88d6f0c12c024eed9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ef12f5cd6aa54e648de217af0a6c96f3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ad1cab4bfeb148ca9b0797a185af46c9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d0fc4e65efd541dd9376b641414d127b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('aacfa1df984a4a63ac564b2092cab9da', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2d932444dff54730bc68791c79c51db6', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('19e8b80d746048959db36eaf6bea304f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('91e1e54a9dfd4a2eb754b4b7ef1cedc9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('81c4f3e531dc46908d493fdefddeabd1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c56c231b78a24518ba4077ae12e6becb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c72c117061fb42a895b4e392560eef79', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('657a1fc0bf8840b0acd5682d10ab046e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a1a265f90d654f2b93a75fec20477e24', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('df277203b0ac43f08d46168a1cc76ee5', '', '23.55.10.23', '{"allowMoveControl":true,"allowFocusControl":true,"allowApertureControl":true,"allowSpeedControl":true,"allowHeatControl":true,"allowWiperControl":true}', 'Encoder', 'SIPEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('27d1d64af42142af9bff88071034f64c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fee3fdcf8581490dafb3a4f5bf537e47', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bfa8c7baace9489fb1259511078f8ffc', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2a4583116e804c798d0022b13eb1609c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8616e134b1db45659a63d5d61e1e75b7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('521937d82c3d48e29bbba23a04f523c6', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0e5de5a2bb6c4cce8ee59ed8f0efd676', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('eed48472dbb440aaaeb998bddf77b22b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7239ea026d7642ccbf8eca11a68b2e72', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0954433c661840e7b7c92edc8cbd28f1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a7eb20b710a841ba98566d27bc66f6ac', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3d0c842aacea40a89400ac22cb322680', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5afad142405e4c80a06244bbbd7b1186', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8da83885618f412f9f273923d5aa3e8a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('db099aced3ba4fb1816c02f6794bef99', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3fe6405e0f4d40bfa3df46344a732fa4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('243cdafad31046409c271477952c2c2f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e9ea9e4da1b245c09fe06fb84e4394e3', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7c506a407d51456db555224aec9c2947', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a425384217ca4ee1995d5ab7932ad381', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('82a3b4bbd4e341e687930f22b021030d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b39e1b8382f744569ac7bf9d40a88edb', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('651f74bd47584b98a80da4f7f6ed69b8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('77d34d4bf6ea4eed966c5dacc5babf16', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9d80cb4f43dd4940901dc651323dd3ec', '', '172.16.14.199', '', 'Encoder', 'GBSIPEncoder', 0, NULL);
INSERT INTO "public"."info_device" VALUES ('e9a31d43665e489fbd365ae1e4cd1160', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ac7f59bff4004c77adc99bffea64ad12', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ac96ac2266c243719ebfcf9fc2dd384f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b66517f3cdd6481f945bb166aec9d9c7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2268d3d749ef4a8087716325dd7bf988', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b1d5119100774e5ea8c09e5c1e8926c0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ce666c1269094fb1a8d372e9b20833ac', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('951e797047ae4d50a2e334c0f8d6dde1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c26f9777de3647b1b1d3df5ea525d693', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('21e3bc3aa5f543b1a23c049d392fcbab', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('db80be5da5374daaba2d956118471280', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b58ba205e9e442e99604c27ec9ecd1ad', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b3c5ad58dfaf42a4a80d2e2e651aea4f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('72ccf9ab2fe64c518e9f42555fc3b963', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a36d15c6662742868ceafa3f11ae5bb1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('82c5f1bf9a7c4709b9eb279f1199b0af', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('225ade12048146eeab22a56c820e716e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5f574c14e67a49dabdc47576201f5cad', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0377b2838de841c290066c296a9dbf4a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('de454f68e82740e89ca4ddf0c71216a5', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bc91a66e8b214e14b73fc2143774c24d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bddc1d48ae2e4b7bbd6837b41b6782ae', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ea67050e5465493ca40a6323a5b45253', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('dde2947829d14cc7b985b79311a2433d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4913703378cb4d1e8c07f29e1c651198', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('19f56893301b409786b6d8391b8dc58b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8cb82da427a64138965d502176c6cce7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1c76f3d6d07d4ffe9148041ccc5d58f4', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('456d6be640f74a5fb86f4d4313db735e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c189ae135ebc42a1bb50d313608170d8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('83182c2b90b245359a05d8f36c8921e2', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('98ffe675f96a4434acd95cc5c773908e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('094eea7a9a584fb19f049ef55fad1ffb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8e9da26dd14b49ebbee613807ee17bcd', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9cddaad74db0479b8d99e4d09fe1c19b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('35a7268ef9d84aa19e0038f6d33067fd', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2fdda4aeb18e4d55af4ee3a7250ac4e0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bff2b371f3a145fd997d898790d82570', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('db0f5ef758b344a8a2fcd868e2114633', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c7a6a77b645a424587ffd6744660bc02', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1ff7e0f319c942cda914d81af5752e01', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('39fd61223cda4f25aad2ab4af1a40550', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('871c9871ef7645b3a48cea18a91727de', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4cec09d281374ceea53a81a5f5b700d9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5d08f4e54fcd4059884bbaad3258668d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2ee41859e4f34b3a84b83581339768bf', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8a19ad5ee0d644f09060401535916317', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a6fd34f266f34b2b9329bb44399d09e8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('63c8a345713543b79eda1a5139213a9f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4b8b32d806f84577a17902e614596a61', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('05e990d6b9f84f3ea1dc535355105947', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('04705a3fd3c540bd891835d398420bf1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c3cd48d84558423f87f3922b4dc79582', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a4177487d34940caaee655584e63eae8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0ca2269b35414bf19bd0191a27d6ea08', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cd69252e1c5c46dda0c88ba1593c14c3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7e1dfaa23b804298b7dc654eb721e5c7', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3be504f833744e6e93c27de3aa7ad9dc', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e566be9a2179414984855f2b3cec4457', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c6254610b31b4e1aa898327d13cd8908', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1767646479c04c8599e90ee1cb210d13', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8a9708f808e649d0b57fb33c0d72a1be', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ce46521e252c4f02a7603d4961d6830e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('84bc96de55634da99056affdd02089fd', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6e819d3edad14d1fb55928690053e826', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3665c7b7777c4c4faa1f7616c21b3dd1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ec0324e9cf4d4877b7c22b3b7c51a80b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('469c92cf09d54ce8a735eecc13874186', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('385b51decd3f404db7904188b763fdaa', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('90616330ef91415fa6bebff72c645578', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fb6f3d52c0df463d9fc84ede455fa1e8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c9d0d7ee7851458498a0dd3ac2b56ed1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7786d99eef78444aa7c9d2f68204b92b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('39daedfd54894d0a8d2ab98b09ea6241', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e8f5add10cd0458380bc19164f989851', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('838510992fd341c1b2330ebf8d651f66', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8201a44f109b40ebb34f126f9e8a1605', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f178623f01574749b8bca67861345a99', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4da91f6543ca42d7858856b7db18ce13', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d19862403ed24eed994bb82a6e35799e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bc21a0e0d5a24b4189849cedc50b2c69', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('626941f73de64d83a269f263500b9ebb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('db3fded743ab430185e4b30bc04424a7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ce8b5590615543babce8f4c282dbda33', '', '172.16.10.199', '', 'Encoder', 'GBSIPEncoder', 0, NULL);
INSERT INTO "public"."info_device" VALUES ('e482019f667e45c586bdb8d434e6f977', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('825523da6c4a4a53a56dfafcec29e558', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('03dad11bfd474fb7a3ccaa62dec6f5ff', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8daa2de0d3d04be2810ef5ad034e367e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5f906ef06beb46d0bd0d63e0b6ea0bc9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('31c2e6ff403645fa9b768981bad344cd', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c53e41c9d95b41aeac6722e23783958f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3acc23b09cf1482397cf12d6fa35f4ec', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('95b8f82a45c746ffb21b4a0dfa85f3fd', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('381a215b0a8c47dd8a0fdd5dc20a0b94', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cee777441232440591b832eb5eeb6145', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a66b185413fb4e86867c9ac8f1eb031c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('51863fc0b2fb4bb09b8f74a0fe09b1de', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('71ff2fbb23324418a12b113965c90266', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('dd356d972bd24fa0babc7e958190a2be', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('80d7d215339d49639d9050289b00992f', '', '172.16.10.200', '', 'Encoder', 'GBSIPEncoder', 0, NULL);
INSERT INTO "public"."info_device" VALUES ('ffa30f30d3ce4a26b1f0b92df46d294a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1eb3688e826549ce8f1a494be84ec1f0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4a47693c50fd47c682b0d07105accb66', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('414bc02335294b4da4a40d6706910b7c', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5d90f347fa094d789ae485e1aed018da', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('54e779df282d48d89ebd2cfd9d4aed47', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b4f2cd48dc5144f28e61183ebfab6bc9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('75e8f7b3d9fc47e5bc33f8912302f6e5', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('234144b1cfff479c966f986743bd6544', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8245f5b07f294535a4a7f5af28a628ba', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a69b3a93a832427a81a81efc92157756', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cd02c23dffa6438298ef35b58ccafd13', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6304e48a1fc94035aa2e06c4bd14171a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6f4d897eb00f4d35a6d1c45e94cff010', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b18dccdf7e334105a4dd6ce38adbce92', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0b7ca644205d4e9f95410ee18592945b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1414624aa1a04171b70366aee0bf803e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ff5ae5b9965545c4ac15fd3fc47d17d8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9a37e09e017447d4a47f0d6d84a4ee9b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1428e67dfab84601b9d91f04066ada16', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('36e4d9e21ada40a19f114fd0cd831420', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6968b19a33a141c5969c10a3bbe8d31b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5bef4e1922ad4cf6802b44cfbf4a4cc5', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6c274540617c49b99eaded59879810d8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('df349f6321554403a54c275cf3bc9660', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('85e2deaa5a454c189ef8ddbb457cb477', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b0b2f0bac5bc45debb43a42aa1d49103', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('05364ddc59504c199f4acee65f6b302f', '', '172.16.5.130', '', 'Encoder', 'GBSIPEncoder', 0, NULL);
INSERT INTO "public"."info_device" VALUES ('58335db80a2745f8a6e99c7702cbab00', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c20a83a63e3f4bdcb210b0bcc475431c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f8a0f10450f54793abbb844e9e658d2a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b364028fc6fe472dbc293057d5664c5e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('014097ff71034b1880786abc03dbb894', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e23bc77b24274198a96764276fa868c5', '', '13.55.10.161', '{"allowMoveControl":true,"allowFocusControl":true,"allowApertureControl":true,"allowSpeedControl":true,"allowHeatControl":true,"allowWiperControl":true}', 'Encoder', 'SIPEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1da29e74ca854509a207f343bb2ad056', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fa5fe06560304936aa9562f3f5b8a974', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0cb5a57ae01045d29265518b401351c3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('365341b500384b3f8fd67bc3f37654fe', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5fa9446d7d3a479ea0b460d53543bfaf', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d89fde205a2c469b8a88dad0f5d0e2e1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b6cbf5e9ef354977844484a65d96f6fc', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('116344a736bd48cf9dac7e026fa67387', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e7c829c772e049c99f6dbfde4ff37405', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('aecd63776bc043b8a4a2b0b166dd389f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bbe4d9c9a80f48daad89ceb7d4856e15', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4bb9e60357ad4ddcbf1fce6197529b81', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d8f1578cdbe544c8ba1af50c8fafe30a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('60eaf6a332554c33932e614a6521a331', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ad6f7400cc104d7a82e79b1d73970561', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f319a54c735a4921bc62b45ceffc1aa5', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('be8c4b15079f4c1cb4e018038b190d08', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0c35136c850a4158b5ff0ec27d6659db', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('814d33a8ed224663963e5c261f8bcf15', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('dddcfc98b6ee402ebcdb209304420684', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('058e1ab69db243928e10b9b193f5d352', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('90c22939efa2400baa8a67f7ca68dd2f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9917d8c5904546d19a2b73203a12a973', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f4d9e3c74bdf4942b0f64de2503a5211', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b0356f93f9704001aed204497cd0d6ee', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('08874b11d6b44bcaa898911b8382d008', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8b460c05f10f403290462850ddfd577a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('80d46b2d7537482faa227dc1c22097e8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('74a8feeb90ed4f76a5ccfec2220e05b1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('40c0f8e490714458a8c5ef8f723e8189', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0a912d3150924a04a3c404c1ece8430c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3875b4de01b24bd68283006693d4b10a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('598cff6022ca4bd18953988d13fc26ec', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('99e14001e8be491799df1d21fa1abc5f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e990a379a64d4d658be77ac063a48669', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('59940a6f027b4482aff8d32e3082956f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('384338819ffe41ce803efb292a0df226', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('979d0810a3f04c888b7157e4064011ce', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b3b41bf9651249c98cf05f94f0df8a41', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e41403224cd4494ba53a868ce29377f0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('32e605681d67456aa2ceb22f20ca6113', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fbd82bdaa0df496cafb406834010e1c8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('434639359b86432485d15aa4b3680e35', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('74318276fa194bf2b7c4250e23efe1e0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5215b89dd43f459c9293869e5bcb7285', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7cb727571cbb4a7791e2dd96df579b3b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('735f49c6e2d041caa59a1fa884f1c0cd', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2a19cdd9eb4147b3ba5b43467bc4500b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6c34c350eb664cc28618f96a0dd3c8a5', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f308343cff3d4beb964a488dedb736e4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8a527f268f8d428b9cd898f676cb6d1f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e4944889590e40e68954a5a3f5882a9b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c10e65899cfd46878439546ff8b717a4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7e18b174699b4c8c9fbf862d3b246250', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('10f1455cd24241599e90ad6cc0516644', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('570954cf58c445f3bd7d803a63634f47', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('60ade98794f94a8e96be7ac4ee03efbb', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b1407c196c6c46f48f58daae2cd20990', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('41d0eba447b34b5eb9ad423f271c3abc', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9fe5e18555844059b1c3caf28c18126e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fa87f7db038149eebab29dd0c93c088f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('25ce628cf0d14e3b94ff04015d00ffdf', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a8f80a60f14e4443872dc0e20cb11ff4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c8b81a67fca54ba191b74c26f5524dc9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('371ebf91cf36467e84663d8729f744af', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c38abe704b704b25bbc8e8e592681982', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f0e900ab1bef462f8e1dc133786e63f0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('161de5607dd447b980b8b943a336cf4b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e64df81881704512a74d82e9399715ce', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('068a93c85b174c938a16a4e006511daf', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('35db5ac2582b45b29a27af916da0a9c1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('22af161e83f94e198e64b9c16ebd7b9b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d61688f85cb4471bab88e366eb5ada0c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ed5b8805f5354d9ab51f56446cfb4244', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e51c4a66be0249859233de3ae0108679', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bf677104c9474995956810b65d2eb73a', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c1d4f6ec15df4441bcda87a94feb9bd9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8384c8287b1e4188a2fd3117b3841ebd', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a957436f3b324a92a220eec712481153', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('61d9db4c88ed4b53a3d01d6d9e4e0581', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c59f11868e8142008a6d5c3aba4ec419', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4f9fd438d29f42e6ba7fdfa3e231b3aa', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('92826b9112904dab9afde7605536117f', '', '172.16.5.217', '', 'Decoder', 'SIPDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ce327cb40ec74fd9bfea816fc7241449', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a5ed87a9f6bf4c72ab3ffc19bf94111b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('08c85b26c50a497b9564f8c294e13312', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('81bb78eea20046c9a522dadb48958f11', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8c701c7aad9742d696a30242dbc295a8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('236555e978634e38982e8f01f29a9888', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0b01b868800c4bde88debf514335c154', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e4c89b3a2015489a94cd4ce332e16c12', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b424718409a94f5695c0698072585559', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('849753fe31ab4d6e8c7a12113f9dc9f9', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('70339b620885455c9b4c2de3b013c659', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e0582e566087412f96e8432a3d2b3eb1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8e4b5cd421744d53a77bf32640c13a25', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fbb8fae0d964423295793067e423bf1b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('76b7e303fdae4209840a8b9ff1a2a1ee', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('75c6c2dbae084bc09723fefe98da2115', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f0c5876ba13b43a096ed25955d6a49b6', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2ffc1a965c854049a3126e85c6d123d8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('21eb51d48ce54df5bbcf313b87a3ee82', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a349a3e9ff1d490b8577f582b8a3b10f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c300dcb9aebf4966850d9d67a52ca7b8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('aca099051d924a7b9fbc9c27bb5bec1e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a12b93394db345f99b68f2dcdc5dbda0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('18cf90b0a79245c9a8aa1ce6c8668171', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('92b840e9fde74deda9015fe22f32a803', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0c3b4a8ddfba477986d2126935d8f07e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d169a9cea97b46a0ab16c79f51694c68', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7398f8035db64c96ad79b8804bfdae01', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f84d953741084c248491df30ddb8f2f4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('245b8476a8dd45adb691b1375e249b1d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f97dda8bb5d44df8b08e32f2a99b9d3c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('011bc6a37e1844ee8332a36237068402', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ae332940888c4301bbaa7f0fe9369aae', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('12f48d0a40794bdfb716aa2e746bb0a8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('72e1328b9612497b869dbf264bcd5534', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('df9f8908c1904cd2a25999a56c3ed025', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('daa6c4ef8d564fb8890c62101ee284a3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ef9574f9b51e4b71ab9cb8dbdcbec777', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a28c8f29743a40db8dbec3d9daa89a86', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('30b41238e51b41c09afb8e51952e0c27', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b1efc4239ef0488b913b9327c3fe17ad', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7cfca4e806654c1e8436275b79688a3d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('849a78be377346358330ec533564d0ff', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bc920cbd9d114afd9564b741f15e926f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('11b7c57ed8eb4aadb96b1824db845612', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3f37fffda1744867a9c7e5b6d7057b7f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('cb4c1dfb306f4ef680063cafae315cf0', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f8deb33c71d4400aba8235bbc10e172d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1a5c1f28f4d34d0ca7e9bc0d798c739d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('314a256949074afebb61d16ee16cfc14', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a34027f7c6974bfb9a0e85ed534cdf4e', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('303768b865b440ee8515473d93d051a7', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2ccf1c3b9dd9439f99acb643889af3dc', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fc723e29d2bc424c9e57657df07369f1', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('13a585e2615147818ccb4998ef74bbd5', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c86a134e788f49028f1a0a64b6e6ea8d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('bec27645495b43c1833b60c2ec6a16f4', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d37fd931cf00423d9a0957b17e7639d6', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a9e93ccec8ed43dba97dbfd47e87af85', '', '172.16.5.213', '', 'Decoder', 'SIPDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('1e641a3e482349acb829bc1048fe347d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a9f14549427940259a5a527e5a88caad', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('512566f93eea4a3f9ca4987d98192022', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('128db2683d2a48ff802f9a53c2788b40', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2b4bc4dd9f634ec08d20dbb60a1190dd', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d3b6cd0348c74581b4ddc3a5166b93b7', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fcf0386d80de4c5595ea91fd1e5139d6', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('283b0f50cf6d48bf9d585da40c616574', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9b8c316d365b444fabb1a73844b9ff09', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e0b08ee26f7f42898bb9ad513d343207', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0f82350a71d5407ab24dd74ea99a2d21', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4cb1a4b8a80f4d9bb741d22bed78fa83', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('eabb59772cfb4f91bfc1038958680606', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3efa1220fc4e4f428e166f20db00531a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c183c82427aa4f1aa747a1599e6e714e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('73a6d6240d0746e39a9f7674af87e2fe', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c5d4c1fb2c1b41cd95a6dde6110e493e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('aa9c362af4ce4138a2d044e026e04254', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3070ac5257ac43e8b482e0394e004f90', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('67831f56e5174037aee0b74ebe4c38f6', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('dd79c5917e414ace8f884668725185cb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('adbfc47b672646c683bd655c024e3347', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4415ef1b86f843c686a2bc9f316170c8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f1f855288f7845b5ad1bb1ce8bc9c8e2', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('7a5f5e058e5f4f1ba304f5f34d036236', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fc9cf645dcb847d8b9e05ff4bace05cb', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6e995902af394c1cb5b85b2b0ff2a0c1', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5b86ed47e8c944aaab0849945f8b2743', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('91343f0ca4d6400ca0f69b21b81db234', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2e934eb09f7644fd98c854d7ebbe9cee', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d62bede814614254b6afc2fdcc287449', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5c5cb9c06452460081e21436f07e4589', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4e5d3fd36fce467bbdae6c85c21e985f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2bb9f176058045f280f86f8708fd921f', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('255f67702c014ab998f24f2ca4ecc700', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('923d046d5c264180a07ce3bd350beb10', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2eaa29d23e884ff3b2f83c2485d7c1d5', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5f9242a38cf248ac800ca0657151d9ef', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('618901338ff6449699d43336262c7b7c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('8e87b3c4267e4bbc98506d81909ef5e3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('06999ccc7fee4341a346cace89985cde', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('658950957955499dae348754cb20e419', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4a48eb27c0ad4463a063f66a30faf6e7', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('e0e05310c0864c3b9079179c7ca71f8f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('75a35bd4c16247c9bf35732da5484df9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9563c48702f6435e98422a1d3f3b5734', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('569baddc3e0c471ebdfc6bb36ad7d2e8', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('372ba746e59743e28082dafc7696d972', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6a1c333f7796437fb26ae80cf7fe2b3d', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('fa010031351b42e19774bd360448a8ae', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5a035c9c4c5f48d0a95dad74ff3313f0', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('800c11bfd0474608a32bb85a1775ece8', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6d2fbccb079a4edfba82acf2b17b1568', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('06789ad9e67c4d0b8573672dd225171b', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2b2ee39fdfc04c4ca68134a178d08ec3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5a08094da9714e0dab1ddae82a13d887', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d806a0b1c365462ab047e3acd611ba5b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('a751f7985f1f4cdd9152f23caf56f937', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('2b5b3fe047d24ea48045a13e2c62a4a2', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b90dfe50042946ec8292354c170d7fda', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('d5816b756bc24e82b0717e886b30576b', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5be170623c05447d8f7ce24c464685c9', '', '30.55.10.54', '{"allowMoveControl":true,"allowFocusControl":true,"allowApertureControl":true,"allowSpeedControl":true,"allowHeatControl":true,"allowWiperControl":true}', 'Encoder', 'SIPEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5aad6ab0549940d0bb7fc12cd5c7775a', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('5118babec7fd44e9ba8b0b6e39295949', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b720648e243d4334970d6bba40e3ba88', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('84e50c98b12040fdabc86b28ead58d87', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('f3e539b3185946d6b7cc53f326b21ddd', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6871eefc3bb84ad39e53dc234eb63a7d', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('3c47d3bfe80348e4b1a3771bd971c0bd', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('ae3e70152d3f4196abdd2d35146d247f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('eb40c1af865e455aa64ba59ba307c82c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('981a4051a5f2467babced19505ad2be5', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('b5404d2bc1914f74aea394de950a7f9c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('4f1a8b72814740228b59fb5bc1556130', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('20dd9992135644798df289cab9d6e4f3', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('33ccaea7f54c4a4d92ef8d390af4a804', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('390e4ab46c8d494aa9ee795e6847ef4e', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('304515febca9484599566c53ecd8ced6', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('6ffb11ff0ec04f7cab3d4283ef659c16', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('9f63b327120f4ee5a9b17b14ac03c991', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('79859766d9ce49399543af69ea4d6d3f', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('98e4027e87b24688847093413bf778f9', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('c5826f85ddaf46e68726f1d06b4ad3ea', '', '', '', 'Decoder', 'XTSoftDecoder', NULL, NULL);
INSERT INTO "public"."info_device" VALUES ('0a0e5055aaa64bb4891da0509fd3c41c', '', '', '', 'Encoder', 'XTSoftEncoder', NULL, NULL);

-- ----------------------------
-- Table structure for info_device_channel
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_device_channel";
CREATE TABLE "public"."info_device_channel" (
                                                "device_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                "channel_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                "channel_name" varchar(100) COLLATE "pg_catalog"."default",
                                                "channel_index" int2,
                                                "channel_yt" varchar(300) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."info_device_channel"."device_id" IS '设备ID';
COMMENT ON COLUMN "public"."info_device_channel"."channel_id" IS '通道ID';
COMMENT ON COLUMN "public"."info_device_channel"."channel_name" IS '通道名称';
COMMENT ON COLUMN "public"."info_device_channel"."channel_index" IS '通道索引';
COMMENT ON COLUMN "public"."info_device_channel"."channel_yt" IS '云台权限';

-- ----------------------------
-- Records of info_device_channel
-- ----------------------------

-- ----------------------------
-- Table structure for info_device_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_device_token";
CREATE TABLE "public"."info_device_token" (
                                              "id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                              "sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                              "token" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of info_device_token
-- ----------------------------
INSERT INTO "public"."info_device_token" VALUES ('492cd255-8fe2-414a-929f-4916b38bb8e1', '22000011533', '-MYD1wUAUZ74oDm4MvEJ');
INSERT INTO "public"."info_device_token" VALUES ('f8089a29-a22f-46af-83ef-04e84c0314bc', '22000011243', '-MY3jLLvwdXQxWGhYoDr');
INSERT INTO "public"."info_device_token" VALUES ('ed4f27f5-05d3-471d-8378-02c2b26698fd', '22000011535', '-MYD1wUAUZ74oDm4MvEJ');
INSERT INTO "public"."info_device_token" VALUES ('9ed4ae48-c438-414c-a4ef-5b8da8e26ae7', '22000009681', '-MX6Q69-NjNnPs78N6yC');
INSERT INTO "public"."info_device_token" VALUES ('5faffd78-86d8-423a-ab49-8a0ca5959278', '22000009683', '-MX6Q69-NjNnPs78N6yC');
INSERT INTO "public"."info_device_token" VALUES ('e647c80c-37ac-42b8-a6e1-1b70801a65b6', '22000009685', '-MX6Q69-NjNnPs78N6yC');
INSERT INTO "public"."info_device_token" VALUES ('d115c993-a939-4d40-a287-0e49c73e4b02', '22000011539', '-MYDM8hKVhiOBwj6tzRw');
INSERT INTO "public"."info_device_token" VALUES ('1e5b3e34-45e2-4689-93c5-7e1f041738e5', '22000009456', '-MWxA_CmVxg6EfNnibli');
INSERT INTO "public"."info_device_token" VALUES ('2a88f238-0b84-4301-beec-561fcdef6627', '22000014035', '-MYn-RYyEGwVP7iwUxgX');
INSERT INTO "public"."info_device_token" VALUES ('81a10780-ff7f-4e8b-8af3-8d1644567845', '22000007360', '-MVKoeMuhtJDq_zvAa98');
INSERT INTO "public"."info_device_token" VALUES ('6fc76e11-76ab-414f-9050-1cf3d6d32597', '22000011723', '-MYJUqCwyUvSeyEFCdmk');
INSERT INTO "public"."info_device_token" VALUES ('8596ef6d-b965-4b58-81f2-6020b26c1695', '22000005273', '-MUwEhvYdC5yBXx3md6U');
INSERT INTO "public"."info_device_token" VALUES ('a2ebff86-e40b-42da-b844-0f0dd5637601', '22000005276', '-MUvpE-B9r_yqElwEyFR');
INSERT INTO "public"."info_device_token" VALUES ('aa4f13e2-f5c7-4b9f-b1d6-152087236997', '22000011725', '-MYJWlzapOqGwIBdVpgf');
INSERT INTO "public"."info_device_token" VALUES ('37ac7fa4-30cb-474b-bc57-2db061a540b9', '22000010162', '-MXe7GCEoFCSTbeL-5Mv');
INSERT INTO "public"."info_device_token" VALUES ('33cdaad5-3232-483d-8b89-05b4f7b923a0', '22000003789', '-MUMTPcHOSnZV32NKc3V');
INSERT INTO "public"."info_device_token" VALUES ('658a260d-b185-4aa9-923f-bfe868193bdc', '22000006432', '-MV4GqevZdqom7jZynV_');
INSERT INTO "public"."info_device_token" VALUES ('10009758-cb4b-4d65-b40a-e67165035c88', '22000011547', '-MYDQLR6ZPyRBtfNcik0');
INSERT INTO "public"."info_device_token" VALUES ('0f954ad0-888c-48fe-b8e4-4f39759779e6', '22000006436', '-MV43ZG7kGxRPVoHnRUm');
INSERT INTO "public"."info_device_token" VALUES ('558a0a75-4d0d-453c-8ba7-f1443384e8ca', '22000003840', '-MUMaQRlmkIgnPdYQDB1');
INSERT INTO "public"."info_device_token" VALUES ('99d90ea0-2620-4067-afe2-3d0cc65c1ae7', '22000011549', '-MYDQLR6ZPyRBtfNcik0');
INSERT INTO "public"."info_device_token" VALUES ('a151118f-0915-45e1-a245-fcce500e3485', '22000006307', '-MV45johTp1dYGMasBJI');
INSERT INTO "public"."info_device_token" VALUES ('dd9dd59b-1e73-4c31-9ee8-fd0d8c92e930', '22000005238', '-MV-HfyL547rcnq7UVp1');
INSERT INTO "public"."info_device_token" VALUES ('f712560a-ecc1-4590-ba50-eca4352e68a8', '22000006308', '-MV45johTp1dYGMasBJI');
INSERT INTO "public"."info_device_token" VALUES ('a88313db-8825-4ad9-8fe6-cb034126e1b0', '22000011727', '-MYJYE30otSHQoZyLtKP');
INSERT INTO "public"."info_device_token" VALUES ('ef5bcb05-52a5-4f8f-8f4a-2e906071afe1', '22000011728', '-MYJYE30otSHQoZyLtKP');
INSERT INTO "public"."info_device_token" VALUES ('16bff0dc-0957-405b-b551-6c8c3f7eeac0', '22000011730', '-MYJYbXrxC5WcRW9Avs3');
INSERT INTO "public"."info_device_token" VALUES ('503bcde8-2cf0-42c6-bb6c-b4bf7ae208b8', '22000009292', '-MWgSYBQdocWeKs09PoT');
INSERT INTO "public"."info_device_token" VALUES ('d2548d99-c474-44cc-a240-765c097ccb9d', '22000009294', '-MWgSYBQdocWeKs09PoT');
INSERT INTO "public"."info_device_token" VALUES ('802dd99c-3b2c-4e43-9d80-c93dd3764679', '22000006318', '-MV1Cf4j9_7RcUiEvoBj');
INSERT INTO "public"."info_device_token" VALUES ('c9881e7c-82e5-4c69-851e-000bd3f73a97', '22000011559', '-MYDM8hKVhiOBwj6tzRw');
INSERT INTO "public"."info_device_token" VALUES ('f717756a-b51b-4192-a227-87a4c830c009', '22000006446', '-MV49zm9d798QYvTk0CD');
INSERT INTO "public"."info_device_token" VALUES ('6a6a3b97-f65a-425c-9e9e-4e51ad8bdca0', '22000011643', '-MYIYjnY7aSLQ1AtCiBJ');
INSERT INTO "public"."info_device_token" VALUES ('73b90831-27ba-40c4-9426-40d222f534ea', '22000010589', '-MXewmHpG1O_cYhoC7Sg');
INSERT INTO "public"."info_device_token" VALUES ('db941e1a-0506-45d7-a7a8-f22b1c174e6a', '22000011405', '-MY96Y41zw1DazzJZxik');
INSERT INTO "public"."info_device_token" VALUES ('c2e0fab2-a4cf-44ba-8a17-0dee97f34845', '22000006395', '-MV4NiYMWGPjjcbCI-zX');
INSERT INTO "public"."info_device_token" VALUES ('0d700125-4f61-48cb-9203-c2d67024172d', '22000011407', '-MY96Y41zw1DazzJZxik');
INSERT INTO "public"."info_device_token" VALUES ('dabf04f2-6fa3-4d8b-818c-89c440ef384b', '22000010044', '-MXH08uCkpLbDqqJdADK');
INSERT INTO "public"."info_device_token" VALUES ('0222aeb8-a913-4046-ab1e-6d42d6b02d29', '22000009720', '-MXAzkNQh5GSafc-h_tA');
INSERT INTO "public"."info_device_token" VALUES ('184eba43-26f5-4e34-860c-7c9af8a124ab', '22000009722', '-MXAzkNQh5GSafc-h_tA');
INSERT INTO "public"."info_device_token" VALUES ('a3b21164-0e20-4add-b8dd-df38f4a67cd3', '22000011865', '-MYNFuKwCeVCLKjsndzJ');
INSERT INTO "public"."info_device_token" VALUES ('dc0c3278-7993-4c3f-8e64-43fa3cab406f', '22000006780', '-MV5igq1fg15EKykoC1e');
INSERT INTO "public"."info_device_token" VALUES ('efa8244c-8c62-45d1-ad9b-ed0e99beb574', '22000011736', '-MYJWlzapOqGwIBdVpgf');
INSERT INTO "public"."info_device_token" VALUES ('f069e94c-9c3c-4f93-b796-9c90f70f37b5', '22000011273', '-MY3szDGQ_8rnLXtkRUt');
INSERT INTO "public"."info_device_token" VALUES ('f57b88fe-e9e8-4ffb-bd5e-cdae7e512c29', '22000009379', '-MWbodHTifqPaxw5LAYy');
INSERT INTO "public"."info_device_token" VALUES ('22321cce-7a82-4cac-a7b0-4437d2a5151d', '22000011738', '-MYJWlzapOqGwIBdVpgf');
INSERT INTO "public"."info_device_token" VALUES ('1b45a5d1-b677-4f27-894d-568f4ffe2e0a', '22000011740', '-MYJaMa25C18X-mel6vS');
INSERT INTO "public"."info_device_token" VALUES ('fc8d3e46-f3f3-47c3-8a75-9ddf1458ee67', '22000008236', '-MW3UnTkEQCzrmoSeAvZ');
INSERT INTO "public"."info_device_token" VALUES ('4d81d04d-5201-4830-9138-85b340b3fe31', '22000011279', '-MY3v_glGTHEXV3wRAGT');
INSERT INTO "public"."info_device_token" VALUES ('935b06a3-0367-424f-b316-36909f00f59a', '22000011004', '-MXo8-5ILweVieR4vxPc');
INSERT INTO "public"."info_device_token" VALUES ('8c10fca0-847e-4446-9c55-8a40e9d0ccab', '22000010545', '-MXfG2aK2yt7Ynciemof');
INSERT INTO "public"."info_device_token" VALUES ('81d68a9a-03b7-4e82-a403-03ff60c260f8', '22000011007', '-MXo_zSzgheLBhZ9NJhI');
INSERT INTO "public"."info_device_token" VALUES ('6bc62f50-ba74-4ce7-930c-590a8881e215', '22000011489', '-MY9qrpn2griwOtzioMt');
INSERT INTO "public"."info_device_token" VALUES ('11aacc69-42c2-41ac-9b33-9a17352a990a', '22000011743', '-MYJalWPoTguFrJQ5Uoh');
INSERT INTO "public"."info_device_token" VALUES ('257ac175-e2eb-4c8a-bbbc-2c5f23b90c89', '22000011745', '-MYJalWPoTguFrJQ5Uoh');
INSERT INTO "public"."info_device_token" VALUES ('2ceee5e2-1ada-45e1-8459-9e501eccf46b', '22000011656', '-MYJ5hnmS98c_oZp3N0m');
INSERT INTO "public"."info_device_token" VALUES ('2c6ec611-abb7-47a7-85fe-fb63b51e9ad5', '22000011747', '-MYJc2V1DKp4IfQhJNpv');
INSERT INTO "public"."info_device_token" VALUES ('ffea8952-44f0-4d3b-b477-32f4e69b6178', '22000011499', '-MY9qrpn2griwOtzioMt');
INSERT INTO "public"."info_device_token" VALUES ('54f5bbe8-b90b-4a1a-a66f-54223c2d3140', '22000011501', '-MY9qrpn2griwOtzioMt');
INSERT INTO "public"."info_device_token" VALUES ('071224c1-8ace-49bc-87b9-a9ba3f2336f4', '22000011024', '-MXpKCDhma3uyp-s9Usz');
INSERT INTO "public"."info_device_token" VALUES ('4f555a46-4c72-48f0-a139-87fd126c67c5', '22000011503', '-MY9qrpn2griwOtzioMt');
