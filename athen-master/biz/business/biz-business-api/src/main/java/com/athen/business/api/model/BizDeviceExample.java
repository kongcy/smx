package com.athen.business.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizDeviceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIsNull() {
            addCriterion("DEVICE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIsNotNull() {
            addCriterion("DEVICE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeEqualTo(String value) {
            addCriterion("DEVICE_CODE =", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotEqualTo(String value) {
            addCriterion("DEVICE_CODE <>", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThan(String value) {
            addCriterion("DEVICE_CODE >", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_CODE >=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThan(String value) {
            addCriterion("DEVICE_CODE <", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_CODE <=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLike(String value) {
            addCriterion("DEVICE_CODE like", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotLike(String value) {
            addCriterion("DEVICE_CODE not like", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIn(List<String> values) {
            addCriterion("DEVICE_CODE in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotIn(List<String> values) {
            addCriterion("DEVICE_CODE not in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeBetween(String value1, String value2) {
            addCriterion("DEVICE_CODE between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotBetween(String value1, String value2) {
            addCriterion("DEVICE_CODE not between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andWlhIsNull() {
            addCriterion("WLH is null");
            return (Criteria) this;
        }

        public Criteria andWlhIsNotNull() {
            addCriterion("WLH is not null");
            return (Criteria) this;
        }

        public Criteria andWlhEqualTo(String value) {
            addCriterion("WLH =", value, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhNotEqualTo(String value) {
            addCriterion("WLH <>", value, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhGreaterThan(String value) {
            addCriterion("WLH >", value, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhGreaterThanOrEqualTo(String value) {
            addCriterion("WLH >=", value, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhLessThan(String value) {
            addCriterion("WLH <", value, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhLessThanOrEqualTo(String value) {
            addCriterion("WLH <=", value, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhLike(String value) {
            addCriterion("WLH like", value, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhNotLike(String value) {
            addCriterion("WLH not like", value, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhIn(List<String> values) {
            addCriterion("WLH in", values, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhNotIn(List<String> values) {
            addCriterion("WLH not in", values, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhBetween(String value1, String value2) {
            addCriterion("WLH between", value1, value2, "wlh");
            return (Criteria) this;
        }

        public Criteria andWlhNotBetween(String value1, String value2) {
            addCriterion("WLH not between", value1, value2, "wlh");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNull() {
            addCriterion("DEVICE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("DEVICE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("DEVICE_NAME =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("DEVICE_NAME <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("DEVICE_NAME >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_NAME >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("DEVICE_NAME <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_NAME <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("DEVICE_NAME like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("DEVICE_NAME not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("DEVICE_NAME in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("DEVICE_NAME not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("DEVICE_NAME between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("DEVICE_NAME not between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("CHECK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("CHECK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Integer value) {
            addCriterion("CHECK_TIME =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Integer value) {
            addCriterion("CHECK_TIME <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Integer value) {
            addCriterion("CHECK_TIME >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("CHECK_TIME >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Integer value) {
            addCriterion("CHECK_TIME <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Integer value) {
            addCriterion("CHECK_TIME <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Integer> values) {
            addCriterion("CHECK_TIME in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Integer> values) {
            addCriterion("CHECK_TIME not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Integer value1, Integer value2) {
            addCriterion("CHECK_TIME between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("CHECK_TIME not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andSeqIsNull() {
            addCriterion("SEQ is null");
            return (Criteria) this;
        }

        public Criteria andSeqIsNotNull() {
            addCriterion("SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEqualTo(String value) {
            addCriterion("SEQ =", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotEqualTo(String value) {
            addCriterion("SEQ <>", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThan(String value) {
            addCriterion("SEQ >", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThanOrEqualTo(String value) {
            addCriterion("SEQ >=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThan(String value) {
            addCriterion("SEQ <", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThanOrEqualTo(String value) {
            addCriterion("SEQ <=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLike(String value) {
            addCriterion("SEQ like", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotLike(String value) {
            addCriterion("SEQ not like", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqIn(List<String> values) {
            addCriterion("SEQ in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotIn(List<String> values) {
            addCriterion("SEQ not in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqBetween(String value1, String value2) {
            addCriterion("SEQ between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotBetween(String value1, String value2) {
            addCriterion("SEQ not between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andCkNoIsNull() {
            addCriterion("CK_NO is null");
            return (Criteria) this;
        }

        public Criteria andCkNoIsNotNull() {
            addCriterion("CK_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCkNoEqualTo(String value) {
            addCriterion("CK_NO =", value, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoNotEqualTo(String value) {
            addCriterion("CK_NO <>", value, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoGreaterThan(String value) {
            addCriterion("CK_NO >", value, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoGreaterThanOrEqualTo(String value) {
            addCriterion("CK_NO >=", value, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoLessThan(String value) {
            addCriterion("CK_NO <", value, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoLessThanOrEqualTo(String value) {
            addCriterion("CK_NO <=", value, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoLike(String value) {
            addCriterion("CK_NO like", value, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoNotLike(String value) {
            addCriterion("CK_NO not like", value, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoIn(List<String> values) {
            addCriterion("CK_NO in", values, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoNotIn(List<String> values) {
            addCriterion("CK_NO not in", values, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoBetween(String value1, String value2) {
            addCriterion("CK_NO between", value1, value2, "ckNo");
            return (Criteria) this;
        }

        public Criteria andCkNoNotBetween(String value1, String value2) {
            addCriterion("CK_NO not between", value1, value2, "ckNo");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("DEVICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("DEVICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(String value) {
            addCriterion("DEVICE_TYPE =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(String value) {
            addCriterion("DEVICE_TYPE <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(String value) {
            addCriterion("DEVICE_TYPE >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_TYPE >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(String value) {
            addCriterion("DEVICE_TYPE <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_TYPE <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLike(String value) {
            addCriterion("DEVICE_TYPE like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotLike(String value) {
            addCriterion("DEVICE_TYPE not like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<String> values) {
            addCriterion("DEVICE_TYPE in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<String> values) {
            addCriterion("DEVICE_TYPE not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(String value1, String value2) {
            addCriterion("DEVICE_TYPE between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(String value1, String value2) {
            addCriterion("DEVICE_TYPE not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("CREATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("CREATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("CREATE_USER =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("CREATE_USER <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("CREATE_USER >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("CREATE_USER <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("CREATE_USER like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("CREATE_USER not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("CREATE_USER in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("CREATE_USER not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("CREATE_USER between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("UPDATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("UPDATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("UPDATE_USER =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("UPDATE_USER <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("UPDATE_USER >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_USER >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("UPDATE_USER <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_USER <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("UPDATE_USER like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("UPDATE_USER not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("UPDATE_USER in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("UPDATE_USER not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("UPDATE_USER between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("UPDATE_USER not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("TOTAL =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("TOTAL <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("TOTAL >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("TOTAL >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("TOTAL <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("TOTAL <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("TOTAL in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("TOTAL not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("TOTAL between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("TOTAL not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andUserCountIsNull() {
            addCriterion("USER_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andUserCountIsNotNull() {
            addCriterion("USER_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andUserCountEqualTo(Integer value) {
            addCriterion("USER_COUNT =", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountNotEqualTo(Integer value) {
            addCriterion("USER_COUNT <>", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountGreaterThan(Integer value) {
            addCriterion("USER_COUNT >", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_COUNT >=", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountLessThan(Integer value) {
            addCriterion("USER_COUNT <", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountLessThanOrEqualTo(Integer value) {
            addCriterion("USER_COUNT <=", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountIn(List<Integer> values) {
            addCriterion("USER_COUNT in", values, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountNotIn(List<Integer> values) {
            addCriterion("USER_COUNT not in", values, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountBetween(Integer value1, Integer value2) {
            addCriterion("USER_COUNT between", value1, value2, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_COUNT not between", value1, value2, "userCount");
            return (Criteria) this;
        }

        public Criteria andLendCountIsNull() {
            addCriterion("LEND_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andLendCountIsNotNull() {
            addCriterion("LEND_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andLendCountEqualTo(Integer value) {
            addCriterion("LEND_COUNT =", value, "lendCount");
            return (Criteria) this;
        }

        public Criteria andLendCountNotEqualTo(Integer value) {
            addCriterion("LEND_COUNT <>", value, "lendCount");
            return (Criteria) this;
        }

        public Criteria andLendCountGreaterThan(Integer value) {
            addCriterion("LEND_COUNT >", value, "lendCount");
            return (Criteria) this;
        }

        public Criteria andLendCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("LEND_COUNT >=", value, "lendCount");
            return (Criteria) this;
        }

        public Criteria andLendCountLessThan(Integer value) {
            addCriterion("LEND_COUNT <", value, "lendCount");
            return (Criteria) this;
        }

        public Criteria andLendCountLessThanOrEqualTo(Integer value) {
            addCriterion("LEND_COUNT <=", value, "lendCount");
            return (Criteria) this;
        }

        public Criteria andLendCountIn(List<Integer> values) {
            addCriterion("LEND_COUNT in", values, "lendCount");
            return (Criteria) this;
        }

        public Criteria andLendCountNotIn(List<Integer> values) {
            addCriterion("LEND_COUNT not in", values, "lendCount");
            return (Criteria) this;
        }

        public Criteria andLendCountBetween(Integer value1, Integer value2) {
            addCriterion("LEND_COUNT between", value1, value2, "lendCount");
            return (Criteria) this;
        }

        public Criteria andLendCountNotBetween(Integer value1, Integer value2) {
            addCriterion("LEND_COUNT not between", value1, value2, "lendCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountIsNull() {
            addCriterion("DISABLE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDisableCountIsNotNull() {
            addCriterion("DISABLE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDisableCountEqualTo(Integer value) {
            addCriterion("DISABLE_COUNT =", value, "disableCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountNotEqualTo(Integer value) {
            addCriterion("DISABLE_COUNT <>", value, "disableCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountGreaterThan(Integer value) {
            addCriterion("DISABLE_COUNT >", value, "disableCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISABLE_COUNT >=", value, "disableCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountLessThan(Integer value) {
            addCriterion("DISABLE_COUNT <", value, "disableCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountLessThanOrEqualTo(Integer value) {
            addCriterion("DISABLE_COUNT <=", value, "disableCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountIn(List<Integer> values) {
            addCriterion("DISABLE_COUNT in", values, "disableCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountNotIn(List<Integer> values) {
            addCriterion("DISABLE_COUNT not in", values, "disableCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountBetween(Integer value1, Integer value2) {
            addCriterion("DISABLE_COUNT between", value1, value2, "disableCount");
            return (Criteria) this;
        }

        public Criteria andDisableCountNotBetween(Integer value1, Integer value2) {
            addCriterion("DISABLE_COUNT not between", value1, value2, "disableCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountIsNull() {
            addCriterion("LIMIT_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andLimitCountIsNotNull() {
            addCriterion("LIMIT_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andLimitCountEqualTo(Integer value) {
            addCriterion("LIMIT_COUNT =", value, "limitCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountNotEqualTo(Integer value) {
            addCriterion("LIMIT_COUNT <>", value, "limitCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountGreaterThan(Integer value) {
            addCriterion("LIMIT_COUNT >", value, "limitCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("LIMIT_COUNT >=", value, "limitCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountLessThan(Integer value) {
            addCriterion("LIMIT_COUNT <", value, "limitCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountLessThanOrEqualTo(Integer value) {
            addCriterion("LIMIT_COUNT <=", value, "limitCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountIn(List<Integer> values) {
            addCriterion("LIMIT_COUNT in", values, "limitCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountNotIn(List<Integer> values) {
            addCriterion("LIMIT_COUNT not in", values, "limitCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountBetween(Integer value1, Integer value2) {
            addCriterion("LIMIT_COUNT between", value1, value2, "limitCount");
            return (Criteria) this;
        }

        public Criteria andLimitCountNotBetween(Integer value1, Integer value2) {
            addCriterion("LIMIT_COUNT not between", value1, value2, "limitCount");
            return (Criteria) this;
        }

        public Criteria andZhcsIsNull() {
            addCriterion("ZHCS is null");
            return (Criteria) this;
        }

        public Criteria andZhcsIsNotNull() {
            addCriterion("ZHCS is not null");
            return (Criteria) this;
        }

        public Criteria andZhcsEqualTo(String value) {
            addCriterion("ZHCS =", value, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsNotEqualTo(String value) {
            addCriterion("ZHCS <>", value, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsGreaterThan(String value) {
            addCriterion("ZHCS >", value, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsGreaterThanOrEqualTo(String value) {
            addCriterion("ZHCS >=", value, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsLessThan(String value) {
            addCriterion("ZHCS <", value, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsLessThanOrEqualTo(String value) {
            addCriterion("ZHCS <=", value, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsLike(String value) {
            addCriterion("ZHCS like", value, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsNotLike(String value) {
            addCriterion("ZHCS not like", value, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsIn(List<String> values) {
            addCriterion("ZHCS in", values, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsNotIn(List<String> values) {
            addCriterion("ZHCS not in", values, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsBetween(String value1, String value2) {
            addCriterion("ZHCS between", value1, value2, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZhcsNotBetween(String value1, String value2) {
            addCriterion("ZHCS not between", value1, value2, "zhcs");
            return (Criteria) this;
        }

        public Criteria andZngIsNull() {
            addCriterion("ZNG is null");
            return (Criteria) this;
        }

        public Criteria andZngIsNotNull() {
            addCriterion("ZNG is not null");
            return (Criteria) this;
        }

        public Criteria andZngEqualTo(String value) {
            addCriterion("ZNG =", value, "zng");
            return (Criteria) this;
        }

        public Criteria andZngNotEqualTo(String value) {
            addCriterion("ZNG <>", value, "zng");
            return (Criteria) this;
        }

        public Criteria andZngGreaterThan(String value) {
            addCriterion("ZNG >", value, "zng");
            return (Criteria) this;
        }

        public Criteria andZngGreaterThanOrEqualTo(String value) {
            addCriterion("ZNG >=", value, "zng");
            return (Criteria) this;
        }

        public Criteria andZngLessThan(String value) {
            addCriterion("ZNG <", value, "zng");
            return (Criteria) this;
        }

        public Criteria andZngLessThanOrEqualTo(String value) {
            addCriterion("ZNG <=", value, "zng");
            return (Criteria) this;
        }

        public Criteria andZngLike(String value) {
            addCriterion("ZNG like", value, "zng");
            return (Criteria) this;
        }

        public Criteria andZngNotLike(String value) {
            addCriterion("ZNG not like", value, "zng");
            return (Criteria) this;
        }

        public Criteria andZngIn(List<String> values) {
            addCriterion("ZNG in", values, "zng");
            return (Criteria) this;
        }

        public Criteria andZngNotIn(List<String> values) {
            addCriterion("ZNG not in", values, "zng");
            return (Criteria) this;
        }

        public Criteria andZngBetween(String value1, String value2) {
            addCriterion("ZNG between", value1, value2, "zng");
            return (Criteria) this;
        }

        public Criteria andZngNotBetween(String value1, String value2) {
            addCriterion("ZNG not between", value1, value2, "zng");
            return (Criteria) this;
        }

        public Criteria andRfidIsNull() {
            addCriterion("RFID is null");
            return (Criteria) this;
        }

        public Criteria andRfidIsNotNull() {
            addCriterion("RFID is not null");
            return (Criteria) this;
        }

        public Criteria andRfidEqualTo(String value) {
            addCriterion("RFID =", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotEqualTo(String value) {
            addCriterion("RFID <>", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidGreaterThan(String value) {
            addCriterion("RFID >", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidGreaterThanOrEqualTo(String value) {
            addCriterion("RFID >=", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidLessThan(String value) {
            addCriterion("RFID <", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidLessThanOrEqualTo(String value) {
            addCriterion("RFID <=", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidLike(String value) {
            addCriterion("RFID like", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotLike(String value) {
            addCriterion("RFID not like", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidIn(List<String> values) {
            addCriterion("RFID in", values, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotIn(List<String> values) {
            addCriterion("RFID not in", values, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidBetween(String value1, String value2) {
            addCriterion("RFID between", value1, value2, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotBetween(String value1, String value2) {
            addCriterion("RFID not between", value1, value2, "rfid");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeIsNull() {
            addCriterion("CHECK_BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeIsNotNull() {
            addCriterion("CHECK_BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeEqualTo(Date value) {
            addCriterion("CHECK_BEGIN_TIME =", value, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeNotEqualTo(Date value) {
            addCriterion("CHECK_BEGIN_TIME <>", value, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeGreaterThan(Date value) {
            addCriterion("CHECK_BEGIN_TIME >", value, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CHECK_BEGIN_TIME >=", value, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeLessThan(Date value) {
            addCriterion("CHECK_BEGIN_TIME <", value, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("CHECK_BEGIN_TIME <=", value, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeIn(List<Date> values) {
            addCriterion("CHECK_BEGIN_TIME in", values, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeNotIn(List<Date> values) {
            addCriterion("CHECK_BEGIN_TIME not in", values, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeBetween(Date value1, Date value2) {
            addCriterion("CHECK_BEGIN_TIME between", value1, value2, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("CHECK_BEGIN_TIME not between", value1, value2, "checkBeginTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeIsNull() {
            addCriterion("CHECK_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeIsNotNull() {
            addCriterion("CHECK_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeEqualTo(Date value) {
            addCriterion("CHECK_END_TIME =", value, "checkEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeNotEqualTo(Date value) {
            addCriterion("CHECK_END_TIME <>", value, "checkEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeGreaterThan(Date value) {
            addCriterion("CHECK_END_TIME >", value, "checkEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CHECK_END_TIME >=", value, "checkEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeLessThan(Date value) {
            addCriterion("CHECK_END_TIME <", value, "checkEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("CHECK_END_TIME <=", value, "checkEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeIn(List<Date> values) {
            addCriterion("CHECK_END_TIME in", values, "checkEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeNotIn(List<Date> values) {
            addCriterion("CHECK_END_TIME not in", values, "checkEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeBetween(Date value1, Date value2) {
            addCriterion("CHECK_END_TIME between", value1, value2, "checkEndTime");
            return (Criteria) this;
        }

        public Criteria andCheckEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("CHECK_END_TIME not between", value1, value2, "checkEndTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}