/**
  更新内容数据库内容
  (1)短信发送模块
  (2)添加媒资检索模块
  (3)同一设备绑定多个部门
  (4)常用资源
  (5)巡查策略
  **/

-- ----------------------------
-- ---------------------------------------------------------------（1）同一设备绑定多个部门业务表 begin-----------------------------------------
-- ----------------------------
DROP TABLE IF EXISTS "public"."cfg_directory_resource";
CREATE TABLE "public"."cfg_directory_resource" (
                                                   "resource_id" varchar(64) COLLATE "pg_catalog"."default",
                                                   "directory_id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
                                                   "level" int4,
                                                   "parent_directory_id" varchar(64) COLLATE "pg_catalog"."default",
                                                   "gd_id" varchar(64) COLLATE "pg_catalog"."default",
                                                   "channel_index" varchar(16) COLLATE "pg_catalog"."default"
);
COMMENT ON COLUMN "public"."cfg_directory_resource"."resource_id" IS '资源id';
COMMENT ON COLUMN "public"."cfg_directory_resource"."directory_id" IS '机构Id';
COMMENT ON COLUMN "public"."cfg_directory_resource"."level" IS '级别';
COMMENT ON COLUMN "public"."cfg_directory_resource"."parent_directory_id" IS '上级部门';
COMMENT ON COLUMN "public"."cfg_directory_resource"."gd_id" IS '通道ID';
COMMENT ON COLUMN "public"."cfg_directory_resource"."channel_index" IS '通道编号集合';
-- ---------------------------------------------------------------（1）同一设备绑定多个部门业务表 end-----------------------------------------

-- ----------------------------
-- (2) ----------------------------------------------------------短信发送模块 业务表 begin-------------------------------------------------------------------------
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
--
-- ----------------------------
CREATE INDEX "channel_num_index" ON "public"."cfg_device_message" USING btree (
  "channel_num" "pg_catalog"."int2_ops" ASC NULLS LAST
);
CREATE INDEX "device_sip_id_index" ON "public"."cfg_device_message" USING btree (
  "device_sip_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table cfg_device_message
-- ----------------------------
ALTER TABLE "public"."cfg_device_message" ADD CONSTRAINT "user_resource_channel_id" UNIQUE ("user_id", "resource_id", "channel_num");
COMMENT ON CONSTRAINT "user_resource_channel_id" ON "public"."cfg_device_message" IS '联合唯一键';
----------------------------------------------------------短信发送模块 业务表 end-------------------------------------------------------------------------
-- ----------------------------
-- ---------------------------------------------------------------常用资源 begin-----------------------------------------
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
);
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
-- ---------------------------------------------------------------常用资源 end-----------------------------------------

-- ----------------------------
------------------------------------------------------------------媒资检索--------------------------------------------------------------
-- ----------------------------
DROP TABLE IF EXISTS "public"."record_resource_type";
CREATE TABLE "public"."record_resource_type" (
                                                 "resource_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                                 "record_type" int2 NOT NULL,
                                                 "resource_type" int2 DEFAULT 1
)
;
COMMENT ON COLUMN "public"."record_resource_type"."resource_id" IS '设备id';
COMMENT ON COLUMN "public"."record_resource_type"."record_type" IS '录像类型 (1 存储服务录像 2手动录像 3报警录像 )';
COMMENT ON COLUMN "public"."record_resource_type"."resource_type" IS '资源类型(0 用户 1设备)';

-- ----------------------------
-- Indexes structure for table record_resource_type
-- ----------------------------
CREATE INDEX "record_resource_type_record_type_idx" ON "public"."record_resource_type" USING btree (
  "record_type" "pg_catalog"."int2_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table record_resource_type
-- ----------------------------
ALTER TABLE "public"."record_resource_type" ADD CONSTRAINT "record_type_type_code_key" UNIQUE ("resource_id");
------------------------------------------------------------------媒资检索-------------------------------------------------------------

-- ----------------------------
------------------------------------------------------------------巡查策略--------------------------------------------------------------
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
------------------------------------------------------------------巡查策略--------------------------------------------------------------