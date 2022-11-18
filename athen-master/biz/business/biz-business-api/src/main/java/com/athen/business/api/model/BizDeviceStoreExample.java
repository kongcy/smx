package com.athen.business.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizDeviceStoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceStoreExample() {
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

        public Criteria andCfIsNull() {
            addCriterion("CF is null");
            return (Criteria) this;
        }

        public Criteria andCfIsNotNull() {
            addCriterion("CF is not null");
            return (Criteria) this;
        }

        public Criteria andCfEqualTo(String value) {
            addCriterion("CF =", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfNotEqualTo(String value) {
            addCriterion("CF <>", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfGreaterThan(String value) {
            addCriterion("CF >", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfGreaterThanOrEqualTo(String value) {
            addCriterion("CF >=", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfLessThan(String value) {
            addCriterion("CF <", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfLessThanOrEqualTo(String value) {
            addCriterion("CF <=", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfLike(String value) {
            addCriterion("CF like", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfNotLike(String value) {
            addCriterion("CF not like", value, "cf");
            return (Criteria) this;
        }

        public Criteria andCfIn(List<String> values) {
            addCriterion("CF in", values, "cf");
            return (Criteria) this;
        }

        public Criteria andCfNotIn(List<String> values) {
            addCriterion("CF not in", values, "cf");
            return (Criteria) this;
        }

        public Criteria andCfBetween(String value1, String value2) {
            addCriterion("CF between", value1, value2, "cf");
            return (Criteria) this;
        }

        public Criteria andCfNotBetween(String value1, String value2) {
            addCriterion("CF not between", value1, value2, "cf");
            return (Criteria) this;
        }

        public Criteria andFjIsNull() {
            addCriterion("FJ is null");
            return (Criteria) this;
        }

        public Criteria andFjIsNotNull() {
            addCriterion("FJ is not null");
            return (Criteria) this;
        }

        public Criteria andFjEqualTo(String value) {
            addCriterion("FJ =", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotEqualTo(String value) {
            addCriterion("FJ <>", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjGreaterThan(String value) {
            addCriterion("FJ >", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjGreaterThanOrEqualTo(String value) {
            addCriterion("FJ >=", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLessThan(String value) {
            addCriterion("FJ <", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLessThanOrEqualTo(String value) {
            addCriterion("FJ <=", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLike(String value) {
            addCriterion("FJ like", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotLike(String value) {
            addCriterion("FJ not like", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjIn(List<String> values) {
            addCriterion("FJ in", values, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotIn(List<String> values) {
            addCriterion("FJ not in", values, "fj");
            return (Criteria) this;
        }

        public Criteria andFjBetween(String value1, String value2) {
            addCriterion("FJ between", value1, value2, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotBetween(String value1, String value2) {
            addCriterion("FJ not between", value1, value2, "fj");
            return (Criteria) this;
        }

        public Criteria andHjIsNull() {
            addCriterion("HJ is null");
            return (Criteria) this;
        }

        public Criteria andHjIsNotNull() {
            addCriterion("HJ is not null");
            return (Criteria) this;
        }

        public Criteria andHjEqualTo(String value) {
            addCriterion("HJ =", value, "hj");
            return (Criteria) this;
        }

        public Criteria andHjNotEqualTo(String value) {
            addCriterion("HJ <>", value, "hj");
            return (Criteria) this;
        }

        public Criteria andHjGreaterThan(String value) {
            addCriterion("HJ >", value, "hj");
            return (Criteria) this;
        }

        public Criteria andHjGreaterThanOrEqualTo(String value) {
            addCriterion("HJ >=", value, "hj");
            return (Criteria) this;
        }

        public Criteria andHjLessThan(String value) {
            addCriterion("HJ <", value, "hj");
            return (Criteria) this;
        }

        public Criteria andHjLessThanOrEqualTo(String value) {
            addCriterion("HJ <=", value, "hj");
            return (Criteria) this;
        }

        public Criteria andHjLike(String value) {
            addCriterion("HJ like", value, "hj");
            return (Criteria) this;
        }

        public Criteria andHjNotLike(String value) {
            addCriterion("HJ not like", value, "hj");
            return (Criteria) this;
        }

        public Criteria andHjIn(List<String> values) {
            addCriterion("HJ in", values, "hj");
            return (Criteria) this;
        }

        public Criteria andHjNotIn(List<String> values) {
            addCriterion("HJ not in", values, "hj");
            return (Criteria) this;
        }

        public Criteria andHjBetween(String value1, String value2) {
            addCriterion("HJ between", value1, value2, "hj");
            return (Criteria) this;
        }

        public Criteria andHjNotBetween(String value1, String value2) {
            addCriterion("HJ not between", value1, value2, "hj");
            return (Criteria) this;
        }

        public Criteria andXhIsNull() {
            addCriterion("XH is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("XH is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(String value) {
            addCriterion("XH =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(String value) {
            addCriterion("XH <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(String value) {
            addCriterion("XH >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(String value) {
            addCriterion("XH >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(String value) {
            addCriterion("XH <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(String value) {
            addCriterion("XH <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLike(String value) {
            addCriterion("XH like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotLike(String value) {
            addCriterion("XH not like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<String> values) {
            addCriterion("XH in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<String> values) {
            addCriterion("XH not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(String value1, String value2) {
            addCriterion("XH between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(String value1, String value2) {
            addCriterion("XH not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andCsIsNull() {
            addCriterion("CS is null");
            return (Criteria) this;
        }

        public Criteria andCsIsNotNull() {
            addCriterion("CS is not null");
            return (Criteria) this;
        }

        public Criteria andCsEqualTo(String value) {
            addCriterion("CS =", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotEqualTo(String value) {
            addCriterion("CS <>", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThan(String value) {
            addCriterion("CS >", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThanOrEqualTo(String value) {
            addCriterion("CS >=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThan(String value) {
            addCriterion("CS <", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThanOrEqualTo(String value) {
            addCriterion("CS <=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLike(String value) {
            addCriterion("CS like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotLike(String value) {
            addCriterion("CS not like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsIn(List<String> values) {
            addCriterion("CS in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotIn(List<String> values) {
            addCriterion("CS not in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsBetween(String value1, String value2) {
            addCriterion("CS between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotBetween(String value1, String value2) {
            addCriterion("CS not between", value1, value2, "cs");
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