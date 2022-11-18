DROP TABLE IF EXISTS public.oil_employee;
CREATE TABLE public.oil_employee (
    user_id varchar(50),
    org_id varchar(100) ,
    major varchar(50) ,
    employee_id varchar(100)  NOT NULL,
    in_use varchar(255) ,
    update_date timestamp(0),
    account_id varchar(100) ,
    source_photos text ,
    data_source varchar(255) ,
    employee_address varchar(255) ,
    remarks varchar(200) ,
    work_resume varchar(255) ,
    source_data_id varchar(100) ,
    employee_id_code_no varchar(100) ,
    employee_foreign_lan_level varchar(100) ,
    create_user_id varchar(100) ,
    mdm_code varchar(255) ,
    employee_birth_date timestamp(0),
    liable_employee_id varchar(100) ,
    one_card_solution varchar(100) ,
    employee_education_level varchar(255) ,
    update_user_id varchar(100) ,
    phone_num varchar(50) ,
    landline_number varchar(100) ,
    check_date timestamp(0),
    take_effect_time timestamp(0),
    employee_name varchar(255) ,
    bsflag int4,
    employee_health_info varchar(255) ,
    employee_type varchar(255) ,
    create_date timestamp(0),
    is_contracted_workforce varchar(255) ,
    employee_status varchar(255) ,
    employee_nation varchar(255) ,
    create_app_id varchar(100) ,
    act_org_id varchar(100) ,
    employee_blood_type varchar(255) ,
    account_status int4,
    data_region varchar(255) ,
    mail_address varchar(255) ,
    check_user_id varchar(100) ,
    employee_gender varchar(255) ,
    art_photos text ,
    employee_religion varchar(255)
)
;
COMMENT ON COLUMN public.oil_employee.org_id IS '机构id';
COMMENT ON COLUMN public.oil_employee.major IS '所学专业';
COMMENT ON COLUMN public.oil_employee.employee_id IS '人员id';
COMMENT ON COLUMN public.oil_employee.in_use IS '在用状态';
COMMENT ON COLUMN public.oil_employee.update_date IS '更新日期';
COMMENT ON COLUMN public.oil_employee.account_id IS '账号名称';
COMMENT ON COLUMN public.oil_employee.source_photos IS '照片底片';
COMMENT ON COLUMN public.oil_employee.data_source IS '数据来源';
COMMENT ON COLUMN public.oil_employee.employee_address IS '家庭住址';
COMMENT ON COLUMN public.oil_employee.remarks IS '备注';
COMMENT ON COLUMN public.oil_employee.work_resume IS '主要工作经历';
COMMENT ON COLUMN public.oil_employee.source_data_id IS '源库id标识';
COMMENT ON COLUMN public.oil_employee.employee_id_code_no IS '身份证号';
COMMENT ON COLUMN public.oil_employee.employee_foreign_lan_level IS '外语等级';
COMMENT ON COLUMN public.oil_employee.create_user_id IS '创建用户';
COMMENT ON COLUMN public.oil_employee.mdm_code IS '员工编码';
COMMENT ON COLUMN public.oil_employee.employee_birth_date IS '出生年月';
COMMENT ON COLUMN public.oil_employee.liable_employee_id IS '责任人id';
COMMENT ON COLUMN public.oil_employee.one_card_solution IS '一卡通号';
COMMENT ON COLUMN public.oil_employee.employee_education_level IS '文化程度';
COMMENT ON COLUMN public.oil_employee.update_user_id IS '更新用户';
COMMENT ON COLUMN public.oil_employee.phone_num IS '联系电话';
COMMENT ON COLUMN public.oil_employee.landline_number IS '固定电话';
COMMENT ON COLUMN public.oil_employee.check_date IS '审核日期';
COMMENT ON COLUMN public.oil_employee.take_effect_time IS '生效时间';
COMMENT ON COLUMN public.oil_employee.employee_name IS '姓名';
COMMENT ON COLUMN public.oil_employee.bsflag IS '删除标识';
COMMENT ON COLUMN public.oil_employee.employee_health_info IS '健康信息';
COMMENT ON COLUMN public.oil_employee.employee_type IS '职工性质';
COMMENT ON COLUMN public.oil_employee.create_date IS '创建日期';
COMMENT ON COLUMN public.oil_employee.is_contracted_workforce IS '是否劳务用工';
COMMENT ON COLUMN public.oil_employee.employee_status IS '人员状态';
COMMENT ON COLUMN public.oil_employee.employee_nation IS '民族';
COMMENT ON COLUMN public.oil_employee.create_app_id IS '创建应用';
COMMENT ON COLUMN public.oil_employee.act_org_id IS '所属组织机构';
COMMENT ON COLUMN public.oil_employee.employee_blood_type IS '血型';
COMMENT ON COLUMN public.oil_employee.account_status IS '账号状态';
COMMENT ON COLUMN public.oil_employee.data_region IS '油田标识';
COMMENT ON COLUMN public.oil_employee.mail_address IS '电子邮箱';
COMMENT ON COLUMN public.oil_employee.check_user_id IS '审核用户';
COMMENT ON COLUMN public.oil_employee.employee_gender IS '性别';
COMMENT ON COLUMN public.oil_employee.art_photos IS '艺术照';
COMMENT ON COLUMN public.oil_employee.employee_religion IS '政治面貌';
COMMENT ON COLUMN public.oil_employee.user_id IS 'user_id';

-- ----------------------------
-- Primary Key structure for table oil_employee
-- ----------------------------
ALTER TABLE public.oil_employee ADD CONSTRAINT master_employee_pkey PRIMARY KEY (employee_id);


-- 组织表结构

DROP TABLE IF EXISTS public.oil_organization;
CREATE TABLE public.oil_organization (
    directory_id varchar(50) COLLATE pg_catalog.default,
    lake_sync_flag int4,
    path varchar(200) COLLATE pg_catalog.default,
    org_id varchar(255) COLLATE pg_catalog.default NOT NULL,
    company_type varchar(255) COLLATE pg_catalog.default,
    in_use varchar(255) COLLATE pg_catalog.default,
    update_date timestamp(6),
    data_source varchar(255) COLLATE pg_catalog.default,
    is_empty_org varchar(255) COLLATE pg_catalog.default,
    remarks varchar(2000) COLLATE pg_catalog.default,
    status_desc varchar(500) COLLATE pg_catalog.default,
    canton varchar(255) COLLATE pg_catalog.default,
    home_url varchar(255) COLLATE pg_catalog.default,
    org_desc varchar(1000) COLLATE pg_catalog.default,
    org_type varchar(255) COLLATE pg_catalog.default,
    org_adm_level varchar(255) COLLATE pg_catalog.default,
    canton_code varchar(255) COLLATE pg_catalog.default,
    create_user_id varchar(100) COLLATE pg_catalog.default,
    lake_receipt_flag int4,
    mdm_code varchar(255) COLLATE pg_catalog.default,
    post_code varchar(255) COLLATE pg_catalog.default,
    forbid_if varchar(255) COLLATE pg_catalog.default,
    update_user_id varchar(100) COLLATE pg_catalog.default,
    org_name varchar(255) COLLATE pg_catalog.default,
    check_date timestamp(6),
    org_full_name varchar(255) COLLATE pg_catalog.default,
    org_s_name varchar(255) COLLATE pg_catalog.default,
    authorized_org_if varchar(255) COLLATE pg_catalog.default,
    ent_code varchar(255) COLLATE pg_catalog.default,
    org_level varchar(255) COLLATE pg_catalog.default,
    parent_id varchar(100) COLLATE pg_catalog.default,
    bsflag int4,
    create_date timestamp(6),
    tysq_org_id varchar(100) COLLATE pg_catalog.default,
    create_app_id varchar(100) COLLATE pg_catalog.default,
    org_code varchar(255) COLLATE pg_catalog.default,
    address varchar(255) COLLATE pg_catalog.default,
    data_region varchar(255) COLLATE pg_catalog.default,
    check_user_id varchar(255) COLLATE pg_catalog.default,
    fax varchar(255) COLLATE pg_catalog.default,
    tysq_code varchar(255) COLLATE pg_catalog.default,
    report_sequ_num varchar(50) COLLATE pg_catalog.default,
    org_function varchar(255) COLLATE pg_catalog.default
)
;
COMMENT ON COLUMN public.oil_organization.lake_sync_flag IS '同步标识';
COMMENT ON COLUMN public.oil_organization.path IS '层级关系';
COMMENT ON COLUMN public.oil_organization.org_id IS '机构ID';
COMMENT ON COLUMN public.oil_organization.company_type IS '企业性质';
COMMENT ON COLUMN public.oil_organization.in_use IS '在用状态';
COMMENT ON COLUMN public.oil_organization.update_date IS '更新时间';
COMMENT ON COLUMN public.oil_organization.data_source IS '数据来源';
COMMENT ON COLUMN public.oil_organization.is_empty_org IS '是否虚机构';
COMMENT ON COLUMN public.oil_organization.remarks IS '备注';
COMMENT ON COLUMN public.oil_organization.status_desc IS '排序';
COMMENT ON COLUMN public.oil_organization.canton IS '行政区名称';
COMMENT ON COLUMN public.oil_organization.home_url IS '单位主页';
COMMENT ON COLUMN public.oil_organization.org_desc IS '组织机构简介';
COMMENT ON COLUMN public.oil_organization.org_type IS '机构类型';
COMMENT ON COLUMN public.oil_organization.org_adm_level IS '机构行政级别';
COMMENT ON COLUMN public.oil_organization.canton_code IS '行政区代码';
COMMENT ON COLUMN public.oil_organization.create_user_id IS '创建用户';
COMMENT ON COLUMN public.oil_organization.lake_receipt_flag IS '投递标识';
COMMENT ON COLUMN public.oil_organization.mdm_code IS 'MDM编码';
COMMENT ON COLUMN public.oil_organization.post_code IS '邮政编码';
COMMENT ON COLUMN public.oil_organization.forbid_if IS '生产单位标识';
COMMENT ON COLUMN public.oil_organization.update_user_id IS '更新用户';
COMMENT ON COLUMN public.oil_organization.org_name IS '机构名称';
COMMENT ON COLUMN public.oil_organization.check_date IS '审核时间';
COMMENT ON COLUMN public.oil_organization.org_full_name IS '单位全称';
COMMENT ON COLUMN public.oil_organization.org_s_name IS '机构简称';
COMMENT ON COLUMN public.oil_organization.authorized_org_if IS '是否在编组织机构';
COMMENT ON COLUMN public.oil_organization.ent_code IS '企业代码';
COMMENT ON COLUMN public.oil_organization.org_level IS '机构级别';
COMMENT ON COLUMN public.oil_organization.parent_id IS '父级ID';
COMMENT ON COLUMN public.oil_organization.bsflag IS '删除标识';
COMMENT ON COLUMN public.oil_organization.create_date IS '创建时间';
COMMENT ON COLUMN public.oil_organization.tysq_org_id IS '统一授权机构';
COMMENT ON COLUMN public.oil_organization.create_app_id IS '创建应用';
COMMENT ON COLUMN public.oil_organization.org_code IS '机构编码';
COMMENT ON COLUMN public.oil_organization.address IS '邮寄地址';
COMMENT ON COLUMN public.oil_organization.data_region IS '油田标识';
COMMENT ON COLUMN public.oil_organization.check_user_id IS '审核用户';
COMMENT ON COLUMN public.oil_organization.fax IS '传真';
COMMENT ON COLUMN public.oil_organization.tysq_code IS '统一授权编码';
COMMENT ON COLUMN public.oil_organization.report_sequ_num IS '显示序号';
COMMENT ON COLUMN public.oil_organization.org_function IS '机构职能';
COMMENT ON COLUMN public.oil_organization.directory_id IS 'directory_id';

-- ----------------------------
-- Primary Key structure for table oil_organization
-- ----------------------------
ALTER TABLE public.oil_organization ADD CONSTRAINT oil_OrganizationLevel_pkey PRIMARY KEY (org_id);


DROP TABLE IF EXISTS "public"."oil_well";
CREATE TABLE "public"."oil_well" (
  "well_id" varchar(128) COLLATE "pg_catalog"."default" NOT NULL,
  "org_id" varchar COLLATE "pg_catalog"."default",
  "well_location_release_date" date,
  "spud_date" date,
  "key_well_level" varchar(128) COLLATE "pg_catalog"."default",
  "structure_pos" varchar(256) COLLATE "pg_catalog"."default",
  "update_date" date,
  "geo_description" varchar(128) COLLATE "pg_catalog"."default",
  "data_source" varchar(512) COLLATE "pg_catalog"."default",
  "remarks" varchar(2000) COLLATE "pg_catalog"."default",
  "source_data_id" varchar(256) COLLATE "pg_catalog"."default",
  "kbd" numeric(7,0),
  "well_legal_name" varchar(64) COLLATE "pg_catalog"."default",
  "platform_id" varchar(128) COLLATE "pg_catalog"."default",
  "well_purpose" varchar(128) COLLATE "pg_catalog"."default",
  "well_common_name" varchar(64) COLLATE "pg_catalog"."default",
  "create_user_id" varchar(64) COLLATE "pg_catalog"."default",
  "well_type" varchar(128) COLLATE "pg_catalog"."default",
  "kb" numeric(7,0),
  "completion_date" date,
  "project_id" varchar(128) COLLATE "pg_catalog"."default",
  "update_user_id" varchar(64) COLLATE "pg_catalog"."default",
  "prod_date" date,
  "check_date" date,
  "egl" varchar(255) COLLATE "pg_catalog"."default",
  "activity_id" varchar(128) COLLATE "pg_catalog"."default",
  "station_id" varchar(60) COLLATE "pg_catalog"."default",
  "bsflag" int4,
  "create_date" date,
  "seismic_line_no" varchar(128) COLLATE "pg_catalog"."default",
  "create_app_id" varchar(64) COLLATE "pg_catalog"."default",
  "data_region" varchar(64) COLLATE "pg_catalog"."default",
  "check_user_id" varchar(64) COLLATE "pg_catalog"."default",
  "abandon_date" date,
  "end_drilling_date" date,
  "abondon_type" varchar(128) COLLATE "pg_catalog"."default",
  "desg_well_id" varchar(128) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."oil_well"."well_id" IS '井id';
COMMENT ON COLUMN "public"."oil_well"."org_id" IS '机构id';
COMMENT ON COLUMN "public"."oil_well"."well_location_release_date" IS '井位下达日期';
COMMENT ON COLUMN "public"."oil_well"."spud_date" IS '开钻日期';
COMMENT ON COLUMN "public"."oil_well"."key_well_level" IS '重点井级别';
COMMENT ON COLUMN "public"."oil_well"."structure_pos" IS '构造位置';
COMMENT ON COLUMN "public"."oil_well"."update_date" IS '更新日期';
COMMENT ON COLUMN "public"."oil_well"."geo_description" IS '地理位置';
COMMENT ON COLUMN "public"."oil_well"."data_source" IS '数据来源';
COMMENT ON COLUMN "public"."oil_well"."remarks" IS '备注';
COMMENT ON COLUMN "public"."oil_well"."source_data_id" IS '源库id标识';
COMMENT ON COLUMN "public"."oil_well"."kbd" IS '补心高度';
COMMENT ON COLUMN "public"."oil_well"."well_legal_name" IS '拼音井号';
COMMENT ON COLUMN "public"."oil_well"."platform_id" IS '平台id';
COMMENT ON COLUMN "public"."oil_well"."well_purpose" IS '井别';
COMMENT ON COLUMN "public"."oil_well"."well_common_name" IS '井号';
COMMENT ON COLUMN "public"."oil_well"."create_user_id" IS '创建用户';
COMMENT ON COLUMN "public"."oil_well"."well_type" IS '井型';
COMMENT ON COLUMN "public"."oil_well"."kb" IS '补心海拔';
COMMENT ON COLUMN "public"."oil_well"."completion_date" IS '完井日期';
COMMENT ON COLUMN "public"."oil_well"."project_id" IS '地质单元id';
COMMENT ON COLUMN "public"."oil_well"."update_user_id" IS '更新用户';
COMMENT ON COLUMN "public"."oil_well"."prod_date" IS '投产日期';
COMMENT ON COLUMN "public"."oil_well"."check_date" IS '审核日期';
COMMENT ON COLUMN "public"."oil_well"."egl" IS '地面海拔';
COMMENT ON COLUMN "public"."oil_well"."activity_id" IS '项目id';
COMMENT ON COLUMN "public"."oil_well"."station_id" IS '站库id';
COMMENT ON COLUMN "public"."oil_well"."bsflag" IS '删除标识';
COMMENT ON COLUMN "public"."oil_well"."create_date" IS '创建日期';
COMMENT ON COLUMN "public"."oil_well"."seismic_line_no" IS '井旁地震测线号';
COMMENT ON COLUMN "public"."oil_well"."create_app_id" IS '创建应用';
COMMENT ON COLUMN "public"."oil_well"."data_region" IS '油田标识';
COMMENT ON COLUMN "public"."oil_well"."check_user_id" IS '审核用户';
COMMENT ON COLUMN "public"."oil_well"."abandon_date" IS '报废日期';
COMMENT ON COLUMN "public"."oil_well"."end_drilling_date" IS '完钻日期';
COMMENT ON COLUMN "public"."oil_well"."abondon_type" IS '报废类型';
COMMENT ON COLUMN "public"."oil_well"."desg_well_id" IS '设计井id';

-- ----------------------------
-- Primary Key structure for table oil_well
-- ----------------------------
ALTER TABLE "public"."oil_well" ADD CONSTRAINT "oil_well_pkey" PRIMARY KEY ("well_id");




DROP TABLE IF EXISTS public.info_directory_well;
CREATE TABLE public.info_directory_well (
 id varchar COLLATE pg_catalog.default NOT NULL,
 well_id varchar(255) COLLATE pg_catalog.default NOT NULL,
 directory_id varchar(255) COLLATE pg_catalog.default NOT NULL
)
;
COMMENT ON COLUMN public.info_directory_well.well_id IS '井id';
COMMENT ON COLUMN public.info_directory_well.directory_id IS '部门id';

-- ----------------------------
-- Primary Key structure for table info_directory_well
-- ----------------------------
ALTER TABLE public.info_directory_well ADD CONSTRAINT info_directory_well_pkey PRIMARY KEY (id);

DROP TABLE IF EXISTS "public"."oil_master_syn_state";
CREATE TABLE "public"."oil_master_syn_state" (
  biz_id varchar(64) COLLATE "pg_catalog"."default",
  state int4,
  type int4,
  request_param json NOT NULL,
  request_url varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."oil_master_syn_state"."state" IS '0 已同步 1 未同步';
COMMENT ON COLUMN "public"."oil_master_syn_state"."type" IS '业务类型';
COMMENT ON COLUMN "public"."oil_master_syn_state"."request_param" IS '同步对象';
COMMENT ON COLUMN "public"."oil_master_syn_state"."request_url" IS '请求地址';

-- ----------------------------
-- Indexes structure for table oil_master_syn_state
-- ----------------------------
CREATE INDEX "type" ON "public"."oil_master_syn_state" USING btree (
  "type" "pg_catalog"."int4_ops" ASC NULLS LAST
);

DROP TABLE IF EXISTS "public"."oil_dwinfo";
CREATE TABLE "public"."oil_dwinfo" (
  "cuid" varchar(256) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "dwbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwsxbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "ydm" varchar(50) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwmc" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwjc" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwqc" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwdymc" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwjb" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwpwsx" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "deptid" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "pdeptid" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwrsbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dqzt" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "xgcs" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "bcbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "xzbz" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "firsttime" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "lasttime" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yhid" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yhxm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yhdwbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yhdwmc" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "sjcqqx" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "ydwbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yktdw" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "hrrsbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON TABLE "public"."oil_dwinfo" IS '油田单位信息临时表';

-- ----------------------------
-- Indexes structure for table oil_dwinfo
-- ----------------------------
CREATE UNIQUE INDEX "sys_c0024894" ON "public"."oil_dwinfo" USING btree (
  "cuid" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table oil_dwinfo
-- ----------------------------
ALTER TABLE "public"."oil_dwinfo" ADD CONSTRAINT "sys_c0026278" PRIMARY KEY ("cuid");

DROP TABLE IF EXISTS "public"."oil_dwinfo";
CREATE TABLE "public"."oil_dwinfo" (
  "cuid" varchar(256) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "dwbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwsxbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "ydm" varchar(50) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwmc" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwjc" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwqc" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwdymc" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwjb" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwpwsx" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "deptid" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "pdeptid" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dwrsbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "dqzt" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "xgcs" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "bcbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "xzbz" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "firsttime" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "lasttime" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yhid" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yhxm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yhdwbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yhdwmc" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "sjcqqx" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "ydwbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "yktdw" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "hrrsbm" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON TABLE "public"."oil_dwinfo" IS '油田单位信息临时表';

-- ----------------------------
-- Indexes structure for table oil_dwinfo
-- ----------------------------
CREATE UNIQUE INDEX "sys_c0024894" ON "public"."oil_dwinfo" USING btree (
  "cuid" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table oil_dwinfo
-- ----------------------------
ALTER TABLE "public"."oil_dwinfo" ADD CONSTRAINT "sys_c0026278" PRIMARY KEY ("cuid");
