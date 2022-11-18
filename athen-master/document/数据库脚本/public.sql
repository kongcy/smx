/*
 Navicat Premium Data Transfer

 Source Server         : 11.55.10.170
 Source Server Type    : PostgreSQL
 Source Server Version : 100013
 Source Host           : 11.55.10.170:5432
 Source Catalog        : xtplat
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100013
 File Encoding         : 65001

 Date: 22/01/2021 10:02:24
*/


-- ----------------------------
-- Table structure for cfg_telephone
-- ----------------------------
DROP TABLE IF EXISTS "public"."cfg_telephone";
CREATE TABLE "public"."cfg_telephone" (
  "uuid" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "telephone" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for conference_template
-- ----------------------------
DROP TABLE IF EXISTS "public"."conference_template";
CREATE TABLE "public"."conference_template" (
  "template_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "template_name" varchar(255) COLLATE "pg_catalog"."default",
  "attendance" int2,
  "describe" varchar(255) COLLATE "pg_catalog"."default",
  "is_pre_main" int2,
  "is_default_lock" int2,
  "is_need_pwd" int2,
  "is_auto_mute" int2,
  "is_default_record" int2,
  "is_del" int2,
  "create_time" timestamp(0),
  "modify_time" timestamp(0),
  "creator_id" varchar(50) COLLATE "pg_catalog"."default",
  "creator_name" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."conference_template"."template_id" IS '模板id';
COMMENT ON COLUMN "public"."conference_template"."template_name" IS '模板名称';
COMMENT ON COLUMN "public"."conference_template"."attendance" IS '参会人数';
COMMENT ON COLUMN "public"."conference_template"."describe" IS '模板描述';
COMMENT ON COLUMN "public"."conference_template"."is_pre_main" IS '是否预设主会场（0：否 1：是）';
COMMENT ON COLUMN "public"."conference_template"."is_default_lock" IS '是否默认锁定会议（0：否 1：是）';
COMMENT ON COLUMN "public"."conference_template"."is_need_pwd" IS '是否会议密码（0：否 1：是）';
COMMENT ON COLUMN "public"."conference_template"."is_auto_mute" IS '成员加入会议时是否自动静音（0：否 1：是）';
COMMENT ON COLUMN "public"."conference_template"."is_default_record" IS '是否默认录制（0：否 1：是）';
COMMENT ON COLUMN "public"."conference_template"."is_del" IS '是否删除（0：否 1：是）';
COMMENT ON COLUMN "public"."conference_template"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."conference_template"."modify_time" IS '修改时间';
COMMENT ON COLUMN "public"."conference_template"."creator_id" IS '创建者id';
COMMENT ON COLUMN "public"."conference_template"."creator_name" IS '创建者名称';
COMMENT ON COLUMN "public"."conference_template"."password" IS '密码';

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
  "channel_index" varchar(10) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."dcfg_prepoint"."prepoint_id" IS '预置点id';
COMMENT ON COLUMN "public"."dcfg_prepoint"."prepoint_name" IS '预置点名称';
COMMENT ON COLUMN "public"."dcfg_prepoint"."device_id" IS '编码器id';
COMMENT ON COLUMN "public"."dcfg_prepoint"."angle" IS '角度';
COMMENT ON COLUMN "public"."dcfg_prepoint"."is_default" IS '是否默认预置点';
COMMENT ON COLUMN "public"."dcfg_prepoint"."creator" IS '创建人';
COMMENT ON COLUMN "public"."dcfg_prepoint"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."dcfg_prepoint"."channel_index" IS '通道号';

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
-- Table structure for group_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."group_info";
CREATE TABLE "public"."group_info" (
  "group_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "group_name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(32) COLLATE "pg_catalog"."default",
  "is_del" int4
)
;

-- ----------------------------
-- Table structure for hot_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."hot_resource";
CREATE TABLE "public"."hot_resource" (
  "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
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
COMMENT ON COLUMN "public"."info_device"."channel_num" IS '设备所属通道数量';

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
-- Table structure for info_directory
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_directory";
CREATE TABLE "public"."info_directory" (
  "directory_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "directory_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "directory_code" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "factory_info" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "parent_directory_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "longitude" numeric(16,4),
  "latitude" numeric(16,4),
  "cmd_parent_dir_id" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."info_directory"."directory_id" IS '部门id';
COMMENT ON COLUMN "public"."info_directory"."directory_name" IS '部门名称';
COMMENT ON COLUMN "public"."info_directory"."directory_code" IS '部门代码';
COMMENT ON COLUMN "public"."info_directory"."description" IS '部门描述';
COMMENT ON COLUMN "public"."info_directory"."factory_info" IS '出厂信息';
COMMENT ON COLUMN "public"."info_directory"."parent_directory_id" IS '上级部门id';
COMMENT ON COLUMN "public"."info_directory"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."info_directory"."cmd_parent_dir_id" IS '指挥上级';

-- ----------------------------
-- Table structure for info_domain
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_domain";
CREATE TABLE "public"."info_domain" (
  "id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "domain_code" varchar(20) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "domain_name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "public"."info_domain"."id" IS 'id';
COMMENT ON COLUMN "public"."info_domain"."domain_code" IS '域代码';
COMMENT ON COLUMN "public"."info_domain"."domain_name" IS '域名称';
COMMENT ON TABLE "public"."info_domain" IS '域信息表';

-- ----------------------------
-- Table structure for info_gb_system
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_gb_system";
CREATE TABLE "public"."info_gb_system" (
  "system_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(50) COLLATE "pg_catalog"."default",
  "gb_id" varchar(50) COLLATE "pg_catalog"."default",
  "node_id" varchar(50) COLLATE "pg_catalog"."default",
  "ip_address" varchar(50) COLLATE "pg_catalog"."default",
  "port" int2,
  "is_superior" bool,
  "create_date" date,
  "gb_id2" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."info_gb_system"."is_superior" IS 'gb系统做为上级为true,gb系统作为下级为false';

-- ----------------------------
-- Table structure for info_gbsystem_device
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_gbsystem_device";
CREATE TABLE "public"."info_gbsystem_device" (
  "id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "local_gb_id" varchar(50) COLLATE "pg_catalog"."default",
  "device_id" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for info_node
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_node";
CREATE TABLE "public"."info_node" (
  "node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "node_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "node_num" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "factory_info" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "parent_node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "is_local" bool NOT NULL,
  "ldap_uri" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "node_type" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."info_node"."node_id" IS '节点id';
COMMENT ON COLUMN "public"."info_node"."node_name" IS '节点名称';
COMMENT ON COLUMN "public"."info_node"."node_num" IS '节点前缀';
COMMENT ON COLUMN "public"."info_node"."factory_info" IS '厂商信息';
COMMENT ON COLUMN "public"."info_node"."parent_node_id" IS '上级节点id';
COMMENT ON COLUMN "public"."info_node"."is_local" IS '是否本地节点';
COMMENT ON COLUMN "public"."info_node"."ldap_uri" IS 'LDAP地址';

-- ----------------------------
-- Table structure for info_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_resource";
CREATE TABLE "public"."info_resource" (
  "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "factory_info" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "directory_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."info_resource"."resource_id" IS '资源id';
COMMENT ON COLUMN "public"."info_resource"."resource_sip_id" IS '资源sipid';
COMMENT ON COLUMN "public"."info_resource"."resource_name" IS '资源名称';
COMMENT ON COLUMN "public"."info_resource"."resource_type" IS '资源类型,枚举类型';
COMMENT ON COLUMN "public"."info_resource"."factory_info" IS '厂商信息';
COMMENT ON COLUMN "public"."info_resource"."directory_id" IS '所属部门id';
COMMENT ON COLUMN "public"."info_resource"."node_id" IS '注册节点id';
COMMENT ON COLUMN "public"."info_resource"."create_time" IS '创建时间';

-- ----------------------------
-- Table structure for info_resource_sort
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_resource_sort";
CREATE TABLE "public"."info_resource_sort" (
  "sort_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "cmd_index" int2,
  "org_index" int2
)
;

-- ----------------------------
-- Table structure for info_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_role";
CREATE TABLE "public"."info_role" (
  "role_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "role_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "role_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "directory_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "is_admin" bool NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "is_cmd" bool
)
;
COMMENT ON COLUMN "public"."info_role"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."info_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "public"."info_role"."description" IS '角色描述';
COMMENT ON COLUMN "public"."info_role"."role_type" IS '角色类型,枚举';
COMMENT ON COLUMN "public"."info_role"."directory_id" IS '所属部门id';
COMMENT ON COLUMN "public"."info_role"."is_admin" IS '是否管理员';
COMMENT ON COLUMN "public"."info_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."info_role"."is_cmd" IS '是否指挥角色';

-- ----------------------------
-- Table structure for info_role_func_rel
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_role_func_rel";
CREATE TABLE "public"."info_role_func_rel" (
  "role_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "right_id" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."info_role_func_rel"."role_id" IS 'FK info_role.role_id';
COMMENT ON COLUMN "public"."info_role_func_rel"."right_id" IS '权限id';

-- ----------------------------
-- Table structure for info_role_res_rel
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_role_res_rel";
CREATE TABLE "public"."info_role_res_rel" (
  "role_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "right_id" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."info_role_res_rel"."role_id" IS 'FK info_role.role_id';
COMMENT ON COLUMN "public"."info_role_res_rel"."resource_id" IS 'FK info_resource.resource_id';
COMMENT ON COLUMN "public"."info_role_res_rel"."right_id" IS '权限id';

-- ----------------------------
-- Table structure for info_scene
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_scene";
CREATE TABLE "public"."info_scene" (
  "scene_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "scene_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "chairman_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "begin_time" timestamp(6) NOT NULL,
  "is_paused" bool NOT NULL DEFAULT false,
  "need_pwd" bool NOT NULL,
  "pwd" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "scene_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "creator" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."info_scene"."scene_id" IS '场景id';
COMMENT ON COLUMN "public"."info_scene"."scene_name" IS '场景名称';
COMMENT ON COLUMN "public"."info_scene"."description" IS '场景描述';
COMMENT ON COLUMN "public"."info_scene"."chairman_id" IS '主席id';
COMMENT ON COLUMN "public"."info_scene"."begin_time" IS '开始时间';
COMMENT ON COLUMN "public"."info_scene"."is_paused" IS '是否暂停';
COMMENT ON COLUMN "public"."info_scene"."need_pwd" IS '是否设置密码';
COMMENT ON COLUMN "public"."info_scene"."pwd" IS '密码';
COMMENT ON COLUMN "public"."info_scene"."scene_type" IS '场景类型';
COMMENT ON COLUMN "public"."info_scene"."creator" IS '创建人';

-- ----------------------------
-- Table structure for info_scene_member
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_scene_member";
CREATE TABLE "public"."info_scene_member" (
  "scene_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "member_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "member_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "member_index" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."info_scene_member"."scene_id" IS 'FK info_scene.scene_id';
COMMENT ON COLUMN "public"."info_scene_member"."member_id" IS '成员id';
COMMENT ON COLUMN "public"."info_scene_member"."member_type" IS '成员类型';
COMMENT ON COLUMN "public"."info_scene_member"."member_index" IS '顺序';

-- ----------------------------
-- Table structure for info_ser_unit
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_ser_unit";
CREATE TABLE "public"."info_ser_unit" (
  "ser_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "ser_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "ser_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "ser_ip" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "ser_port" int2 NOT NULL,
  "ser_type" int2 NOT NULL,
  "ser_pwd" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "ser_proto" int2 NOT NULL,
  "factory_info" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."info_ser_unit"."ser_id" IS '服务单元id';
COMMENT ON COLUMN "public"."info_ser_unit"."ser_sip_id" IS '服务单元sipid,根据节点前缀生成';
COMMENT ON COLUMN "public"."info_ser_unit"."ser_name" IS '服务单元名称';
COMMENT ON COLUMN "public"."info_ser_unit"."ser_ip" IS 'ip';
COMMENT ON COLUMN "public"."info_ser_unit"."ser_port" IS '端口号';
COMMENT ON COLUMN "public"."info_ser_unit"."ser_type" IS '类型';
COMMENT ON COLUMN "public"."info_ser_unit"."ser_pwd" IS '密码';
COMMENT ON COLUMN "public"."info_ser_unit"."ser_proto" IS '协议';
COMMENT ON COLUMN "public"."info_ser_unit"."factory_info" IS '出厂信息';
COMMENT ON COLUMN "public"."info_ser_unit"."node_id" IS '节点id';

-- ----------------------------
-- Table structure for info_strategy
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_strategy";
CREATE TABLE "public"."info_strategy" (
  "strategy_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "type" int2,
  "interval_time" int2,
  "enable_voice" int2,
  "split_type" int2,
  "strategy_name" varchar(50) COLLATE "pg_catalog"."default",
  "screen_type" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for info_strategy_device
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_strategy_device";
CREATE TABLE "public"."info_strategy_device" (
  "strategy_id" varchar(50) COLLATE "pg_catalog"."default",
  "device_id" varchar(50) COLLATE "pg_catalog"."default",
  "group_id" varchar(32) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for info_strategy_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_strategy_group";
CREATE TABLE "public"."info_strategy_group" (
  "group_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "strategy_id" varchar(50) COLLATE "pg_catalog"."default",
  "group_name" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for info_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_user";
CREATE TABLE "public"."info_user" (
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "user_account" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "user_pwd" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "valid_start_time" timestamp(6) NOT NULL,
  "valid_end_time" timestamp(6) NOT NULL,
  "level" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "locked" varchar(30) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."info_user"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."info_user"."user_account" IS '用户账号';
COMMENT ON COLUMN "public"."info_user"."user_pwd" IS '用户密码';
COMMENT ON COLUMN "public"."info_user"."valid_start_time" IS '有效时间起';
COMMENT ON COLUMN "public"."info_user"."valid_end_time" IS '有效时间止';
COMMENT ON COLUMN "public"."info_user"."level" IS '优先级,枚举类型';
COMMENT ON COLUMN "public"."info_user"."locked" IS '锁定状态,枚举类型';

-- ----------------------------
-- Table structure for info_user_func_rel
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_user_func_rel";
CREATE TABLE "public"."info_user_func_rel" (
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "right_id" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."info_user_func_rel"."user_id" IS 'FK info_user.user_id';
COMMENT ON COLUMN "public"."info_user_func_rel"."right_id" IS '权限id';

-- ----------------------------
-- Table structure for info_user_res_rel
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_user_res_rel";
CREATE TABLE "public"."info_user_res_rel" (
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "right_id" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."info_user_res_rel"."user_id" IS 'FK info_user.user_id';
COMMENT ON COLUMN "public"."info_user_res_rel"."resource_id" IS 'FK info_resource.resource_id';
COMMENT ON COLUMN "public"."info_user_res_rel"."right_id" IS '权限id';

-- ----------------------------
-- Table structure for info_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS "public"."info_user_role_rel";
CREATE TABLE "public"."info_user_role_rel" (
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "role_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for patrol_strategy
-- ----------------------------
DROP TABLE IF EXISTS "public"."patrol_strategy";
CREATE TABLE "public"."patrol_strategy" (
  "strategy_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "strategy_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "strategy_type" int2 NOT NULL,
  "patrol_interval" int2,
  "is_open_voice" int2,
  "window_layout" int2,
  "patrol_screen" varchar(50) COLLATE "pg_catalog"."default",
  "device_id" varchar(50) COLLATE "pg_catalog"."default",
  "create_time" timestamp(0),
  "modify_time" timestamp(0),
  "is_del" int2,
  "user_id" varchar(518) COLLATE "pg_catalog"."default" NOT NULL,
  "type" int2
)
;
COMMENT ON COLUMN "public"."patrol_strategy"."strategy_id" IS '策略id';
COMMENT ON COLUMN "public"."patrol_strategy"."strategy_name" IS '策略名称';
COMMENT ON COLUMN "public"."patrol_strategy"."strategy_type" IS '策略类型(0:快速巡查策略 1:计划巡查策略)';
COMMENT ON COLUMN "public"."patrol_strategy"."patrol_interval" IS '巡查间隔';
COMMENT ON COLUMN "public"."patrol_strategy"."is_open_voice" IS '是否开启声音(0:否 1:是)';
COMMENT ON COLUMN "public"."patrol_strategy"."window_layout" IS '窗口布局(1:一分屏 2:二分屏 3:四分屏 4:1+3分屏 5:六分屏 6:1+5分屏 7:九分屏 8:十六分屏)';
COMMENT ON COLUMN "public"."patrol_strategy"."patrol_screen" IS '巡查屏(0:屏幕2，1:屏幕3)';
COMMENT ON COLUMN "public"."patrol_strategy"."device_id" IS '设备id';
COMMENT ON COLUMN "public"."patrol_strategy"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."patrol_strategy"."modify_time" IS '修改时间';
COMMENT ON COLUMN "public"."patrol_strategy"."is_del" IS '是否删除(0:未删除 1:已删除)';
COMMENT ON COLUMN "public"."patrol_strategy"."user_id" IS '用户id,角色id';
COMMENT ON COLUMN "public"."patrol_strategy"."type" IS '类型（1：user_id存储用户id   2：user_id存储角色id)';

-- ----------------------------
-- Table structure for record_detail
-- ----------------------------
DROP TABLE IF EXISTS "public"."record_detail";
CREATE TABLE "public"."record_detail" (
  "id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(255) COLLATE "pg_catalog"."default",
  "resource_id" varchar(255) COLLATE "pg_catalog"."default",
  "device_sip_id" varchar(255) COLLATE "pg_catalog"."default",
  "begin_date" timestamp(6),
  "end_date" timestamp(6),
  "task_id" varchar(255) COLLATE "pg_catalog"."default",
  "resource_type" int2,
  "record_plan_id" varchar(255) COLLATE "pg_catalog"."default",
  "device_type" int2
)
;
COMMENT ON COLUMN "public"."record_detail"."user_id" IS '录像创建人id';
COMMENT ON COLUMN "public"."record_detail"."resource_id" IS '可存用户id和设备id';
COMMENT ON COLUMN "public"."record_detail"."begin_date" IS '开始时间';
COMMENT ON COLUMN "public"."record_detail"."end_date" IS '结束时间';
COMMENT ON COLUMN "public"."record_detail"."task_id" IS '录播子系统返回的taskId';
COMMENT ON COLUMN "public"."record_detail"."resource_type" IS '0:用户 1：设备';
COMMENT ON COLUMN "public"."record_detail"."record_plan_id" IS '录像计划id';

-- ----------------------------
-- Table structure for record_plan
-- ----------------------------
DROP TABLE IF EXISTS "public"."record_plan";
CREATE TABLE "public"."record_plan" (
  "plan_id" varchar(255) COLLATE "pg_catalog"."default",
  "plan_name" varchar(255) COLLATE "pg_catalog"."default",
  "is_enable" bool,
  "begin_date" timestamp(0),
  "end_date" timestamp(0),
  "is_all_day" bool,
  "plan_interval" int2,
  "create_date" timestamp(0),
  "create_id" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."record_plan"."plan_id" IS '计划id';
COMMENT ON COLUMN "public"."record_plan"."plan_name" IS '计划名称';
COMMENT ON COLUMN "public"."record_plan"."is_enable" IS '是否激活';
COMMENT ON COLUMN "public"."record_plan"."begin_date" IS '开始生效日期';
COMMENT ON COLUMN "public"."record_plan"."end_date" IS '结束生效日期';
COMMENT ON COLUMN "public"."record_plan"."is_all_day" IS '是否全天录制';
COMMENT ON COLUMN "public"."record_plan"."plan_interval" IS '时段范围：0:30分钟 1:1小时 2:2小时 3:3小时 4:4小时 6:6小时 8:8小时';
COMMENT ON COLUMN "public"."record_plan"."create_date" IS '创建时间';
COMMENT ON COLUMN "public"."record_plan"."create_id" IS '创建人';

-- ----------------------------
-- Table structure for record_plan_item
-- ----------------------------
DROP TABLE IF EXISTS "public"."record_plan_item";
CREATE TABLE "public"."record_plan_item" (
  "index" int4,
  "week" int2,
  "is_enable" bool,
  "record_plan_id" varchar(255) COLLATE "pg_catalog"."default",
  "begin" int2,
  "end" int2
)
;
COMMENT ON COLUMN "public"."record_plan_item"."index" IS '序号';
COMMENT ON COLUMN "public"."record_plan_item"."week" IS '星期 0-6';
COMMENT ON COLUMN "public"."record_plan_item"."is_enable" IS '是否激活';
COMMENT ON COLUMN "public"."record_plan_item"."record_plan_id" IS '对应的录像计划id';
COMMENT ON COLUMN "public"."record_plan_item"."begin" IS '开始';
COMMENT ON COLUMN "public"."record_plan_item"."end" IS '结束';

-- ----------------------------
-- Table structure for record_plan_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."record_plan_resource";
CREATE TABLE "public"."record_plan_resource" (
  "index" int4,
  "resource_id" varchar(255) COLLATE "pg_catalog"."default",
  "device_sip_id" varchar(255) COLLATE "pg_catalog"."default",
  "resource_type" int2 NOT NULL,
  "device_type" int2,
  "record_plan_id" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."record_plan_resource"."index" IS '序号';
COMMENT ON COLUMN "public"."record_plan_resource"."resource_id" IS '被录资源id';
COMMENT ON COLUMN "public"."record_plan_resource"."device_sip_id" IS '被录资源sipId';
COMMENT ON COLUMN "public"."record_plan_resource"."resource_type" IS '0:user 1:device 2:node';
COMMENT ON COLUMN "public"."record_plan_resource"."device_type" IS '设备类型';
COMMENT ON COLUMN "public"."record_plan_resource"."record_plan_id" IS '关联的录像计划id';

-- ----------------------------
-- Table structure for stat_bus_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."stat_bus_type";
CREATE TABLE "public"."stat_bus_type" (
  "stat_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "bus_type" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "begin_time" timestamp(6) NOT NULL DEFAULT now(),
  "end_time" timestamp(6) NOT NULL DEFAULT now()
)
;
COMMENT ON COLUMN "public"."stat_bus_type"."stat_id" IS '主键';

-- ----------------------------
-- Table structure for stat_channel
-- ----------------------------
DROP TABLE IF EXISTS "public"."stat_channel";
CREATE TABLE "public"."stat_channel" (
  "device_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "channel_num" int2 NOT NULL,
  "channel_status" varchar(30) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for stat_channel_copy1
-- ----------------------------
DROP TABLE IF EXISTS "public"."stat_channel_copy1";
CREATE TABLE "public"."stat_channel_copy1" (
  "device_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "channel_num" int2 NOT NULL,
  "channel_status" varchar(30) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for stat_device
-- ----------------------------
DROP TABLE IF EXISTS "public"."stat_device";
CREATE TABLE "public"."stat_device" (
  "device_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "device_status" varchar(30) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."stat_device"."device_sip_id" IS '设备sipid';
COMMENT ON COLUMN "public"."stat_device"."device_status" IS '设备状态';

-- ----------------------------
-- Table structure for stat_node_link
-- ----------------------------
DROP TABLE IF EXISTS "public"."stat_node_link";
CREATE TABLE "public"."stat_node_link" (
  "node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "parent_node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "node_status" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" date,
  "last_refresh_time" date
)
;
COMMENT ON COLUMN "public"."stat_node_link"."node_id" IS '子节点id';
COMMENT ON COLUMN "public"."stat_node_link"."parent_node_id" IS '父节点id';
COMMENT ON COLUMN "public"."stat_node_link"."node_status" IS '节点联通状态';
COMMENT ON COLUMN "public"."stat_node_link"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."stat_node_link"."last_refresh_time" IS '最后刷新时间';

-- ----------------------------
-- Table structure for stat_ser_unit
-- ----------------------------
DROP TABLE IF EXISTS "public"."stat_ser_unit";
CREATE TABLE "public"."stat_ser_unit" (
  "ser_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "ser_status" varchar(30) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."stat_ser_unit"."ser_sip_id" IS '服务单元sipid';
COMMENT ON COLUMN "public"."stat_ser_unit"."ser_status" IS '服务单元状态';

-- ----------------------------
-- Table structure for stat_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."stat_user";
CREATE TABLE "public"."stat_user" (
  "user_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "user_status" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "encoder_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "decoder_sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."stat_user"."user_sip_id" IS '用户sipid';
COMMENT ON COLUMN "public"."stat_user"."user_status" IS '用户状态';
COMMENT ON COLUMN "public"."stat_user"."node_id" IS '登录节点id';
COMMENT ON COLUMN "public"."stat_user"."encoder_sip_id" IS '绑定编码器sipid';
COMMENT ON COLUMN "public"."stat_user"."decoder_sip_id" IS '绑定解码器sipid';

-- ----------------------------
-- Table structure for stat_user_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."stat_user_token";
CREATE TABLE "public"."stat_user_token" (
  "token_key" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "terminal_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "user_status" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "valid_time" timestamp(6) NOT NULL,
  "ip_address" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "is_local_node" bool NOT NULL
)
;
COMMENT ON COLUMN "public"."stat_user_token"."token_key" IS 'Tokenkey';
COMMENT ON COLUMN "public"."stat_user_token"."user_id" IS 'FK info_user.user_id';
COMMENT ON COLUMN "public"."stat_user_token"."terminal_type" IS '终端类型';
COMMENT ON COLUMN "public"."stat_user_token"."user_status" IS '用户状态';
COMMENT ON COLUMN "public"."stat_user_token"."valid_time" IS '有效期';
COMMENT ON COLUMN "public"."stat_user_token"."ip_address" IS '登录ip';
COMMENT ON COLUMN "public"."stat_user_token"."node_id" IS '登录节点id';
COMMENT ON COLUMN "public"."stat_user_token"."is_local_node" IS '是否本地节点登录';

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS "public"."student";
CREATE TABLE "public"."student" (
  "id" int4 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "sex" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "age" int4 NOT NULL,
  "remark" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."student"."id" IS '主键id';

-- ----------------------------
-- Table structure for syst_enum
-- ----------------------------
DROP TABLE IF EXISTS "public"."syst_enum";
CREATE TABLE "public"."syst_enum" (
  "id" int2 NOT NULL,
  "type" int2 NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."syst_enum"."id" IS '枚举id';
COMMENT ON COLUMN "public"."syst_enum"."type" IS '枚举类型';
COMMENT ON COLUMN "public"."syst_enum"."name" IS '枚举名称';

-- ----------------------------
-- Table structure for syst_sipid
-- ----------------------------
DROP TABLE IF EXISTS "public"."syst_sipid";
CREATE TABLE "public"."syst_sipid" (
  "node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "current_num" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."syst_sipid"."node_id" IS '节点id';
COMMENT ON COLUMN "public"."syst_sipid"."current_num" IS '当前使用编号';

-- ----------------------------
-- Table structure for syst_sipid_collect
-- ----------------------------
DROP TABLE IF EXISTS "public"."syst_sipid_collect";
CREATE TABLE "public"."syst_sipid_collect" (
  "node_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "sip_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."syst_sipid_collect"."node_id" IS '节点id';
COMMENT ON COLUMN "public"."syst_sipid_collect"."sip_id" IS '回收的sipid';

-- ----------------------------
-- Table structure for template_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."template_user";
CREATE TABLE "public"."template_user" (
  "template_id" varchar(16) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "user_name" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."template_user"."template_id" IS '模板id';
COMMENT ON COLUMN "public"."template_user"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."template_user"."user_name" IS '用户名称';

-- ----------------------------
-- Table structure for ucfg_arrange_conference
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_arrange_conference";
CREATE TABLE "public"."ucfg_arrange_conference" (
  "scene_id" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "scene_name" varchar(255) COLLATE "pg_catalog"."default",
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "scheme_id" varchar(50) COLLATE "pg_catalog"."default",
  "pwd" varchar(255) COLLATE "pg_catalog"."default",
  "media_type" varchar(255) COLLATE "pg_catalog"."default",
  "microphone_ability" int4,
  "arrange_time" varchar(40) COLLATE "pg_catalog"."default",
  "creator" varchar(50) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "arrange_length" int2
)
;
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."scene_id" IS '会议编号';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."scene_name" IS '会议名称';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."description" IS '会议描述';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."scheme_id" IS '显示方案ID';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."pwd" IS '密码';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."media_type" IS '视频会议：videoAndAudio  音频会议：audio';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."microphone_ability" IS '麦克风 1:关闭  0：开启';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."arrange_time" IS '预约时间';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."creator" IS '创建人';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."ucfg_arrange_conference"."arrange_length" IS '会议时长';
COMMENT ON TABLE "public"."ucfg_arrange_conference" IS '预约会议表';

-- ----------------------------
-- Table structure for ucfg_custom
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_custom";
CREATE TABLE "public"."ucfg_custom" (
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "category" int2 NOT NULL,
  "category_value" varchar(200) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."ucfg_custom"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."ucfg_custom"."category" IS '用户习惯分类';
COMMENT ON COLUMN "public"."ucfg_custom"."category_value" IS '分类值';

-- ----------------------------
-- Table structure for ucfg_favorite
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_favorite";
CREATE TABLE "public"."ucfg_favorite" (
  "user_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."ucfg_favorite"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."ucfg_favorite"."resource_id" IS '资源id';
COMMENT ON COLUMN "public"."ucfg_favorite"."create_time" IS '收藏时间';

-- ----------------------------
-- Table structure for ucfg_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_group";
CREATE TABLE "public"."ucfg_group" (
  "group_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "group_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "group_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "scheme_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "pwd" varchar(20) COLLATE "pg_catalog"."default",
  "conference_type" varchar(30) COLLATE "pg_catalog"."default",
  "creator" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."ucfg_group"."group_id" IS '分组id';
COMMENT ON COLUMN "public"."ucfg_group"."group_name" IS '分组名称';
COMMENT ON COLUMN "public"."ucfg_group"."description" IS '分组描述';
COMMENT ON COLUMN "public"."ucfg_group"."group_type" IS '分组类型';
COMMENT ON COLUMN "public"."ucfg_group"."scheme_id" IS '显示方案id';
COMMENT ON COLUMN "public"."ucfg_group"."pwd" IS '会议密码';
COMMENT ON COLUMN "public"."ucfg_group"."conference_type" IS '会议模式';

-- ----------------------------
-- Table structure for ucfg_group_member
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_group_member";
CREATE TABLE "public"."ucfg_group_member" (
  "group_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "parent_resource_id" varchar(50) COLLATE "pg_catalog"."default",
  "member_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "member_index" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."ucfg_group_member"."group_id" IS 'FK ucfg_group.group_id';
COMMENT ON COLUMN "public"."ucfg_group_member"."resource_id" IS '资源id';
COMMENT ON COLUMN "public"."ucfg_group_member"."parent_resource_id" IS '上级资源id';
COMMENT ON COLUMN "public"."ucfg_group_member"."member_type" IS '成员类型';
COMMENT ON COLUMN "public"."ucfg_group_member"."member_index" IS '顺序';

-- ----------------------------
-- Table structure for ucfg_history_conference
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_history_conference";
CREATE TABLE "public"."ucfg_history_conference" (
  "conference_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "conference_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "conference_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "chairman_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "scheme_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "pwd" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "start_time" timestamp(6) NOT NULL,
  "end_time" timestamp(6) NOT NULL,
  "creator" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "is_video" bool,
  "group_id" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."ucfg_history_conference"."conference_id" IS '会议id';
COMMENT ON COLUMN "public"."ucfg_history_conference"."conference_name" IS '会议名称';
COMMENT ON COLUMN "public"."ucfg_history_conference"."description" IS '会议描述';
COMMENT ON COLUMN "public"."ucfg_history_conference"."conference_type" IS '会议类型sceneType：SpeakConference 0；DiscussConference 1；SpeakConferenceMP 2；
DiscussConferenceMP 3；';
COMMENT ON COLUMN "public"."ucfg_history_conference"."chairman_id" IS '主席id';
COMMENT ON COLUMN "public"."ucfg_history_conference"."scheme_id" IS '显示方案id';
COMMENT ON COLUMN "public"."ucfg_history_conference"."pwd" IS '会议密码';
COMMENT ON COLUMN "public"."ucfg_history_conference"."start_time" IS '开始时间';
COMMENT ON COLUMN "public"."ucfg_history_conference"."end_time" IS '结束时间';
COMMENT ON COLUMN "public"."ucfg_history_conference"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."ucfg_history_conference"."is_video" IS '是否录像';
COMMENT ON COLUMN "public"."ucfg_history_conference"."group_id" IS '会议组id';

-- ----------------------------
-- Table structure for ucfg_history_conference_item
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_history_conference_item";
CREATE TABLE "public"."ucfg_history_conference_item" (
  "conference_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "member_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "member_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "member_index" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."ucfg_history_conference_item"."conference_id" IS 'FK ucfg_history_conference.conference_id';
COMMENT ON COLUMN "public"."ucfg_history_conference_item"."member_id" IS '成员id';
COMMENT ON COLUMN "public"."ucfg_history_conference_item"."member_type" IS '会议成员类型';
COMMENT ON COLUMN "public"."ucfg_history_conference_item"."member_index" IS '顺序';

-- ----------------------------
-- Table structure for ucfg_scheme
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_scheme";
CREATE TABLE "public"."ucfg_scheme" (
  "scheme_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "scheme_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "scheme_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "split_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "creator" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."ucfg_scheme"."scheme_id" IS '方案id';
COMMENT ON COLUMN "public"."ucfg_scheme"."scheme_name" IS '方案名称';
COMMENT ON COLUMN "public"."ucfg_scheme"."scheme_type" IS '方案类型';
COMMENT ON COLUMN "public"."ucfg_scheme"."split_type" IS '分屏模式';
COMMENT ON COLUMN "public"."ucfg_scheme"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."ucfg_scheme"."create_time" IS '创建时间';

-- ----------------------------
-- Table structure for ucfg_scheme_screen
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_scheme_screen";
CREATE TABLE "public"."ucfg_scheme_screen" (
  "scheme_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "scheme_screens" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "screen_role_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "polling_interval" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."ucfg_scheme_screen"."scheme_id" IS 'FK ucfg_scheme.scheme_id';
COMMENT ON COLUMN "public"."ucfg_scheme_screen"."scheme_screens" IS '分屏';
COMMENT ON COLUMN "public"."ucfg_scheme_screen"."screen_role_type" IS '分屏类型';
COMMENT ON COLUMN "public"."ucfg_scheme_screen"."polling_interval" IS '轮询时间';

-- ----------------------------
-- Table structure for ucfg_vcode
-- ----------------------------
DROP TABLE IF EXISTS "public"."ucfg_vcode";
CREATE TABLE "public"."ucfg_vcode" (
  "ip_address" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "terminal_type" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "vcode" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "valid_time" timestamp(6) NOT NULL
)
;

-- ----------------------------
-- Primary Key structure for table cfg_telephone
-- ----------------------------
ALTER TABLE "public"."cfg_telephone" ADD CONSTRAINT "cfg_telephone_pkey" PRIMARY KEY ("uuid");

-- ----------------------------
-- Primary Key structure for table dcfg_behavior_rule
-- ----------------------------
ALTER TABLE "public"."dcfg_behavior_rule" ADD CONSTRAINT "dcfg_behavior_rule_pkey" PRIMARY KEY ("rule_id");

-- ----------------------------
-- Primary Key structure for table dcfg_behavior_rule_alarmto
-- ----------------------------
ALTER TABLE "public"."dcfg_behavior_rule_alarmto" ADD CONSTRAINT "dcfg_behavior_rule_alarmto_pkey" PRIMARY KEY ("rule_id", "alarm_to");

-- ----------------------------
-- Primary Key structure for table dcfg_history_alarm
-- ----------------------------
ALTER TABLE "public"."dcfg_history_alarm" ADD CONSTRAINT "dcfg_history_alarm_pkey" PRIMARY KEY ("alarm_id");

-- ----------------------------
-- Primary Key structure for table dcfg_history_alarm_user
-- ----------------------------
ALTER TABLE "public"."dcfg_history_alarm_user" ADD CONSTRAINT "dcfg_history_alarm_user_pkey" PRIMARY KEY ("alarm_id", "user_id");

-- ----------------------------
-- Primary Key structure for table dcfg_osdstyle
-- ----------------------------
ALTER TABLE "public"."dcfg_osdstyle" ADD CONSTRAINT "dcfg_osdstyle_pkey" PRIMARY KEY ("resource_id", "style_index");

-- ----------------------------
-- Primary Key structure for table dcfg_osdstyle_item
-- ----------------------------
ALTER TABLE "public"."dcfg_osdstyle_item" ADD CONSTRAINT "dcfg_osdstyle_item_pkey" PRIMARY KEY ("resource_id", "style_index", "element_index");

-- ----------------------------
-- Primary Key structure for table dcfg_prepoint
-- ----------------------------
ALTER TABLE "public"."dcfg_prepoint" ADD CONSTRAINT "dcfg_prepoint_pkey" PRIMARY KEY ("prepoint_id");

-- ----------------------------
-- Primary Key structure for table dcfg_subscription
-- ----------------------------
ALTER TABLE "public"."dcfg_subscription" ADD CONSTRAINT "dcfg_subscription_pkey" PRIMARY KEY ("subscription_id");

-- ----------------------------
-- Primary Key structure for table dcfg_synchronization
-- ----------------------------
ALTER TABLE "public"."dcfg_synchronization" ADD CONSTRAINT "dcfg_synchronization_pkey" PRIMARY KEY ("synchronization_id");

-- ----------------------------
-- Primary Key structure for table dcfg_yt_takeover
-- ----------------------------
ALTER TABLE "public"."dcfg_yt_takeover" ADD CONSTRAINT "dcfg_yt_takeover_pkey" PRIMARY KEY ("device_sip_id");

-- ----------------------------
-- Primary Key structure for table group_info
-- ----------------------------
ALTER TABLE "public"."group_info" ADD CONSTRAINT "group_info_pkey" PRIMARY KEY ("group_id");

-- ----------------------------
-- Indexes structure for table hot_resource
-- ----------------------------
CREATE INDEX "userId" ON "public"."hot_resource" USING btree (
  "user_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table hot_resource
-- ----------------------------
ALTER TABLE "public"."hot_resource" ADD CONSTRAINT "res_user_id" UNIQUE ("resource_id", "user_id");
COMMENT ON CONSTRAINT "res_user_id" ON "public"."hot_resource" IS '联合索引';

-- ----------------------------
-- Primary Key structure for table info_channel_info
-- ----------------------------
ALTER TABLE "public"."info_channel_info" ADD CONSTRAINT "info_channel_info_pkey" PRIMARY KEY ("channel_id");

-- ----------------------------
-- Indexes structure for table info_department_sort
-- ----------------------------
CREATE UNIQUE INDEX "index_department_id" ON "public"."info_department_sort" USING btree (
  "department_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table info_department_sort
-- ----------------------------
ALTER TABLE "public"."info_department_sort" ADD CONSTRAINT "info_department_sort_pkey" PRIMARY KEY ("sort_id");

-- ----------------------------
-- Primary Key structure for table info_device
-- ----------------------------
ALTER TABLE "public"."info_device" ADD CONSTRAINT "info_device_pkey" PRIMARY KEY ("device_id");

-- ----------------------------
-- Primary Key structure for table info_device_channel
-- ----------------------------
ALTER TABLE "public"."info_device_channel" ADD CONSTRAINT "info_device_channel_pkey" PRIMARY KEY ("device_id", "channel_id");

-- ----------------------------
-- Primary Key structure for table info_device_token
-- ----------------------------
ALTER TABLE "public"."info_device_token" ADD CONSTRAINT "info_device_token_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table info_directory
-- ----------------------------
ALTER TABLE "public"."info_directory" ADD CONSTRAINT "info_directory_pkey" PRIMARY KEY ("directory_id");

-- ----------------------------
-- Uniques structure for table info_domain
-- ----------------------------
ALTER TABLE "public"."info_domain" ADD CONSTRAINT "domain_code_unique" UNIQUE ("domain_code");
COMMENT ON CONSTRAINT "domain_code_unique" ON "public"."info_domain" IS '域代码唯一键';

-- ----------------------------
-- Primary Key structure for table info_gb_system
-- ----------------------------
ALTER TABLE "public"."info_gb_system" ADD CONSTRAINT "info_gb_system_pkey" PRIMARY KEY ("system_id");

-- ----------------------------
-- Primary Key structure for table info_gbsystem_device
-- ----------------------------
ALTER TABLE "public"."info_gbsystem_device" ADD CONSTRAINT "info_gbsystem_device_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table info_node
-- ----------------------------
ALTER TABLE "public"."info_node" ADD CONSTRAINT "info_node_pkey" PRIMARY KEY ("node_id");

-- ----------------------------
-- Uniques structure for table info_resource
-- ----------------------------
ALTER TABLE "public"."info_resource" ADD CONSTRAINT "info_resource_resource_sip_id_key" UNIQUE ("resource_sip_id");

-- ----------------------------
-- Primary Key structure for table info_resource
-- ----------------------------
ALTER TABLE "public"."info_resource" ADD CONSTRAINT "info_resource_pkey" PRIMARY KEY ("resource_id");

-- ----------------------------
-- Indexes structure for table info_resource_sort
-- ----------------------------
CREATE UNIQUE INDEX "index_resource_id" ON "public"."info_resource_sort" USING btree (
  "resource_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table info_role
-- ----------------------------
ALTER TABLE "public"."info_role" ADD CONSTRAINT "info_role_pkey" PRIMARY KEY ("role_id");

-- ----------------------------
-- Primary Key structure for table info_role_func_rel
-- ----------------------------
ALTER TABLE "public"."info_role_func_rel" ADD CONSTRAINT "info_role_func_rel_pkey" PRIMARY KEY ("role_id", "right_id");

-- ----------------------------
-- Primary Key structure for table info_role_res_rel
-- ----------------------------
ALTER TABLE "public"."info_role_res_rel" ADD CONSTRAINT "info_role_res_rel_pkey" PRIMARY KEY ("role_id", "resource_id", "right_id");

-- ----------------------------
-- Primary Key structure for table info_scene
-- ----------------------------
ALTER TABLE "public"."info_scene" ADD CONSTRAINT "info_scene_pkey" PRIMARY KEY ("scene_id");

-- ----------------------------
-- Primary Key structure for table info_scene_member
-- ----------------------------
ALTER TABLE "public"."info_scene_member" ADD CONSTRAINT "info_scene_member_pkey" PRIMARY KEY ("scene_id", "member_id", "member_type", "member_index");

-- ----------------------------
-- Uniques structure for table info_ser_unit
-- ----------------------------
ALTER TABLE "public"."info_ser_unit" ADD CONSTRAINT "info_ser_unit_ser_sip_id_key" UNIQUE ("ser_sip_id");

-- ----------------------------
-- Primary Key structure for table info_ser_unit
-- ----------------------------
ALTER TABLE "public"."info_ser_unit" ADD CONSTRAINT "info_ser_unit_pkey" PRIMARY KEY ("ser_id");

-- ----------------------------
-- Primary Key structure for table info_strategy_group
-- ----------------------------
ALTER TABLE "public"."info_strategy_group" ADD CONSTRAINT "info_strategy_group_pkey" PRIMARY KEY ("group_id");

-- ----------------------------
-- Primary Key structure for table info_user
-- ----------------------------
ALTER TABLE "public"."info_user" ADD CONSTRAINT "info_user_pkey" PRIMARY KEY ("user_id");

-- ----------------------------
-- Primary Key structure for table info_user_func_rel
-- ----------------------------
ALTER TABLE "public"."info_user_func_rel" ADD CONSTRAINT "info_user_func_rel_pkey" PRIMARY KEY ("user_id", "right_id");

-- ----------------------------
-- Primary Key structure for table info_user_res_rel
-- ----------------------------
ALTER TABLE "public"."info_user_res_rel" ADD CONSTRAINT "info_user_res_rel_pkey" PRIMARY KEY ("user_id", "resource_id", "right_id");

-- ----------------------------
-- Primary Key structure for table info_user_role_rel
-- ----------------------------
ALTER TABLE "public"."info_user_role_rel" ADD CONSTRAINT "info_user_role_rel_pkey" PRIMARY KEY ("user_id", "role_id");

-- ----------------------------
-- Primary Key structure for table record_detail
-- ----------------------------
ALTER TABLE "public"."record_detail" ADD CONSTRAINT "resource_record_detail_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table stat_channel
-- ----------------------------
ALTER TABLE "public"."stat_channel" ADD CONSTRAINT "stat_channel_pkey" PRIMARY KEY ("device_sip_id", "channel_num");

-- ----------------------------
-- Primary Key structure for table stat_channel_copy1
-- ----------------------------
ALTER TABLE "public"."stat_channel_copy1" ADD CONSTRAINT "stat_channel_copy1_pkey" PRIMARY KEY ("device_sip_id", "channel_num");

-- ----------------------------
-- Primary Key structure for table stat_device
-- ----------------------------
ALTER TABLE "public"."stat_device" ADD CONSTRAINT "stat_device_pkey" PRIMARY KEY ("device_sip_id");

-- ----------------------------
-- Primary Key structure for table stat_node_link
-- ----------------------------
ALTER TABLE "public"."stat_node_link" ADD CONSTRAINT "stat_node_link_pkey" PRIMARY KEY ("node_id", "parent_node_id");

-- ----------------------------
-- Primary Key structure for table stat_ser_unit
-- ----------------------------
ALTER TABLE "public"."stat_ser_unit" ADD CONSTRAINT "stat_ser_unit_pkey" PRIMARY KEY ("ser_sip_id");

-- ----------------------------
-- Primary Key structure for table stat_user
-- ----------------------------
ALTER TABLE "public"."stat_user" ADD CONSTRAINT "stat_user_pkey" PRIMARY KEY ("user_sip_id");

-- ----------------------------
-- Primary Key structure for table stat_user_token
-- ----------------------------
ALTER TABLE "public"."stat_user_token" ADD CONSTRAINT "stat_user_token_pkey" PRIMARY KEY ("token_key");

-- ----------------------------
-- Primary Key structure for table syst_enum
-- ----------------------------
ALTER TABLE "public"."syst_enum" ADD CONSTRAINT "syst_enum_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table syst_sipid
-- ----------------------------
ALTER TABLE "public"."syst_sipid" ADD CONSTRAINT "syst_sipid_pkey" PRIMARY KEY ("node_id");

-- ----------------------------
-- Primary Key structure for table syst_sipid_collect
-- ----------------------------
ALTER TABLE "public"."syst_sipid_collect" ADD CONSTRAINT "syst_sipid_collect_pkey" PRIMARY KEY ("node_id", "sip_id");

-- ----------------------------
-- Primary Key structure for table ucfg_arrange_conference
-- ----------------------------
ALTER TABLE "public"."ucfg_arrange_conference" ADD CONSTRAINT "ucfg_arrange_conference_pkey" PRIMARY KEY ("scene_id");

-- ----------------------------
-- Primary Key structure for table ucfg_custom
-- ----------------------------
ALTER TABLE "public"."ucfg_custom" ADD CONSTRAINT "ucfg_custom_pkey" PRIMARY KEY ("user_id", "category");

-- ----------------------------
-- Primary Key structure for table ucfg_favorite
-- ----------------------------
ALTER TABLE "public"."ucfg_favorite" ADD CONSTRAINT "ucfg_favorite_pkey" PRIMARY KEY ("user_id", "resource_id");

-- ----------------------------
-- Primary Key structure for table ucfg_group
-- ----------------------------
ALTER TABLE "public"."ucfg_group" ADD CONSTRAINT "ucfg_group_pkey" PRIMARY KEY ("group_id");

-- ----------------------------
-- Primary Key structure for table ucfg_group_member
-- ----------------------------
ALTER TABLE "public"."ucfg_group_member" ADD CONSTRAINT "ucfg_group_member_pkey" PRIMARY KEY ("group_id", "resource_id");

-- ----------------------------
-- Primary Key structure for table ucfg_history_conference
-- ----------------------------
ALTER TABLE "public"."ucfg_history_conference" ADD CONSTRAINT "ucfg_history_conference_pkey" PRIMARY KEY ("conference_id");

-- ----------------------------
-- Primary Key structure for table ucfg_history_conference_item
-- ----------------------------
ALTER TABLE "public"."ucfg_history_conference_item" ADD CONSTRAINT "ucfg_history_conference_item_pkey" PRIMARY KEY ("conference_id", "member_id");

-- ----------------------------
-- Primary Key structure for table ucfg_scheme
-- ----------------------------
ALTER TABLE "public"."ucfg_scheme" ADD CONSTRAINT "ucfg_scheme_pkey" PRIMARY KEY ("scheme_id");

-- ----------------------------
-- Primary Key structure for table ucfg_vcode
-- ----------------------------
ALTER TABLE "public"."ucfg_vcode" ADD CONSTRAINT "ucfg_vcode_pkey" PRIMARY KEY ("ip_address", "terminal_type");

-- ----------------------------
-- Foreign Keys structure for table dcfg_behavior_device
-- ----------------------------
ALTER TABLE "public"."dcfg_behavior_device" ADD CONSTRAINT "dcfg_behavior_device_device_id_fkey" FOREIGN KEY ("device_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table dcfg_behavior_rule
-- ----------------------------
ALTER TABLE "public"."dcfg_behavior_rule" ADD CONSTRAINT "dcfg_behavior_rule_device_id_fkey" FOREIGN KEY ("device_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table dcfg_behavior_rule_alarmto
-- ----------------------------
ALTER TABLE "public"."dcfg_behavior_rule_alarmto" ADD CONSTRAINT "dcfg_behavior_rule_alarmto_rule_id_fkey" FOREIGN KEY ("rule_id") REFERENCES "dcfg_behavior_rule" ("rule_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table dcfg_behavior_rule_linkage
-- ----------------------------
ALTER TABLE "public"."dcfg_behavior_rule_linkage" ADD CONSTRAINT "rule_id" FOREIGN KEY ("rule_id") REFERENCES "dcfg_behavior_rule" ("rule_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table dcfg_history_alarm_linkage
-- ----------------------------
ALTER TABLE "public"."dcfg_history_alarm_linkage" ADD CONSTRAINT "alarm_id" FOREIGN KEY ("alarm_id") REFERENCES "dcfg_history_alarm" ("alarm_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table dcfg_history_alarm_user
-- ----------------------------
ALTER TABLE "public"."dcfg_history_alarm_user" ADD CONSTRAINT "alarm_id" FOREIGN KEY ("alarm_id") REFERENCES "dcfg_history_alarm" ("alarm_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table dcfg_osdstyle
-- ----------------------------
ALTER TABLE "public"."dcfg_osdstyle" ADD CONSTRAINT "osdStyle_resourceid_resource_resourceId_Fk" FOREIGN KEY ("resource_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table dcfg_osdstyle_item
-- ----------------------------
ALTER TABLE "public"."dcfg_osdstyle_item" ADD CONSTRAINT "dcfg_osdstyle_item_resource_id_fkey" FOREIGN KEY ("resource_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table info_device
-- ----------------------------
ALTER TABLE "public"."info_device" ADD CONSTRAINT "info_device_device_id_fkey" FOREIGN KEY ("device_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table info_device_channel
-- ----------------------------
ALTER TABLE "public"."info_device_channel" ADD CONSTRAINT "info_device_channel_device_id_fkey" FOREIGN KEY ("device_id") REFERENCES "info_resource" ("resource_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table info_role
-- ----------------------------
ALTER TABLE "public"."info_role" ADD CONSTRAINT "info_role_directory_id_fkey" FOREIGN KEY ("directory_id") REFERENCES "info_directory" ("directory_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table info_role_func_rel
-- ----------------------------
ALTER TABLE "public"."info_role_func_rel" ADD CONSTRAINT "info_role_func_rel_role_id_fkey" FOREIGN KEY ("role_id") REFERENCES "info_role" ("role_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table info_role_res_rel
-- ----------------------------
ALTER TABLE "public"."info_role_res_rel" ADD CONSTRAINT "info_role_res_rel_resource_id_fkey" FOREIGN KEY ("resource_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table info_scene_member
-- ----------------------------
ALTER TABLE "public"."info_scene_member" ADD CONSTRAINT "info_scene_member_scene_id_fkey" FOREIGN KEY ("scene_id") REFERENCES "info_scene" ("scene_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table info_user
-- ----------------------------
ALTER TABLE "public"."info_user" ADD CONSTRAINT "user_userid_resource_resourceid_FK" FOREIGN KEY ("user_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table info_user_func_rel
-- ----------------------------
ALTER TABLE "public"."info_user_func_rel" ADD CONSTRAINT "info_user_func_rel_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table info_user_role_rel
-- ----------------------------
ALTER TABLE "public"."info_user_role_rel" ADD CONSTRAINT "info_user_role_rel_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table stat_node_link
-- ----------------------------
ALTER TABLE "public"."stat_node_link" ADD CONSTRAINT "stat_node_link_node_id_fkey" FOREIGN KEY ("node_id") REFERENCES "info_node" ("node_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table stat_ser_unit
-- ----------------------------
ALTER TABLE "public"."stat_ser_unit" ADD CONSTRAINT "stat_ser_unit_ser_sip_id_fkey" FOREIGN KEY ("ser_sip_id") REFERENCES "info_ser_unit" ("ser_sip_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table stat_user
-- ----------------------------
ALTER TABLE "public"."stat_user" ADD CONSTRAINT "stat_user_node_id_fkey" FOREIGN KEY ("node_id") REFERENCES "info_node" ("node_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table syst_sipid
-- ----------------------------
ALTER TABLE "public"."syst_sipid" ADD CONSTRAINT "syst_sipid_node_id_fkey" FOREIGN KEY ("node_id") REFERENCES "info_node" ("node_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table syst_sipid_collect
-- ----------------------------
ALTER TABLE "public"."syst_sipid_collect" ADD CONSTRAINT "syst_sipid_collect_node_id_fkey" FOREIGN KEY ("node_id") REFERENCES "info_node" ("node_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table ucfg_custom
-- ----------------------------
ALTER TABLE "public"."ucfg_custom" ADD CONSTRAINT "ucfg_custom_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table ucfg_favorite
-- ----------------------------
ALTER TABLE "public"."ucfg_favorite" ADD CONSTRAINT "ucfg_favorite_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "info_resource" ("resource_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table ucfg_group_member
-- ----------------------------
ALTER TABLE "public"."ucfg_group_member" ADD CONSTRAINT "group_id" FOREIGN KEY ("group_id") REFERENCES "ucfg_group" ("group_id") ON DELETE CASCADE ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table ucfg_history_conference_item
-- ----------------------------
ALTER TABLE "public"."ucfg_history_conference_item" ADD CONSTRAINT "conference_id" FOREIGN KEY ("conference_id") REFERENCES "ucfg_history_conference" ("conference_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table ucfg_scheme_screen
-- ----------------------------
ALTER TABLE "public"."ucfg_scheme_screen" ADD CONSTRAINT "scheme_id" FOREIGN KEY ("scheme_id") REFERENCES "ucfg_scheme" ("scheme_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
