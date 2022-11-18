package com.athen.business.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizDeviceBackRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceBackRecordExample() {
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

        public Criteria andBackUserIsNull() {
            addCriterion("BACK_USER is null");
            return (Criteria) this;
        }

        public Criteria andBackUserIsNotNull() {
            addCriterion("BACK_USER is not null");
            return (Criteria) this;
        }

        public Criteria andBackUserEqualTo(String value) {
            addCriterion("BACK_USER =", value, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserNotEqualTo(String value) {
            addCriterion("BACK_USER <>", value, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserGreaterThan(String value) {
            addCriterion("BACK_USER >", value, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserGreaterThanOrEqualTo(String value) {
            addCriterion("BACK_USER >=", value, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserLessThan(String value) {
            addCriterion("BACK_USER <", value, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserLessThanOrEqualTo(String value) {
            addCriterion("BACK_USER <=", value, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserLike(String value) {
            addCriterion("BACK_USER like", value, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserNotLike(String value) {
            addCriterion("BACK_USER not like", value, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserIn(List<String> values) {
            addCriterion("BACK_USER in", values, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserNotIn(List<String> values) {
            addCriterion("BACK_USER not in", values, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserBetween(String value1, String value2) {
            addCriterion("BACK_USER between", value1, value2, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackUserNotBetween(String value1, String value2) {
            addCriterion("BACK_USER not between", value1, value2, "backUser");
            return (Criteria) this;
        }

        public Criteria andBackTimeIsNull() {
            addCriterion("BACK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBackTimeIsNotNull() {
            addCriterion("BACK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBackTimeEqualTo(Date value) {
            addCriterion("BACK_TIME =", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeNotEqualTo(Date value) {
            addCriterion("BACK_TIME <>", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeGreaterThan(Date value) {
            addCriterion("BACK_TIME >", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BACK_TIME >=", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeLessThan(Date value) {
            addCriterion("BACK_TIME <", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeLessThanOrEqualTo(Date value) {
            addCriterion("BACK_TIME <=", value, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeIn(List<Date> values) {
            addCriterion("BACK_TIME in", values, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeNotIn(List<Date> values) {
            addCriterion("BACK_TIME not in", values, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeBetween(Date value1, Date value2) {
            addCriterion("BACK_TIME between", value1, value2, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackTimeNotBetween(Date value1, Date value2) {
            addCriterion("BACK_TIME not between", value1, value2, "backTime");
            return (Criteria) this;
        }

        public Criteria andBackCountIsNull() {
            addCriterion("BACK_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andBackCountIsNotNull() {
            addCriterion("BACK_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBackCountEqualTo(Integer value) {
            addCriterion("BACK_COUNT =", value, "backCount");
            return (Criteria) this;
        }

        public Criteria andBackCountNotEqualTo(Integer value) {
            addCriterion("BACK_COUNT <>", value, "backCount");
            return (Criteria) this;
        }

        public Criteria andBackCountGreaterThan(Integer value) {
            addCriterion("BACK_COUNT >", value, "backCount");
            return (Criteria) this;
        }

        public Criteria andBackCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("BACK_COUNT >=", value, "backCount");
            return (Criteria) this;
        }

        public Criteria andBackCountLessThan(Integer value) {
            addCriterion("BACK_COUNT <", value, "backCount");
            return (Criteria) this;
        }

        public Criteria andBackCountLessThanOrEqualTo(Integer value) {
            addCriterion("BACK_COUNT <=", value, "backCount");
            return (Criteria) this;
        }

        public Criteria andBackCountIn(List<Integer> values) {
            addCriterion("BACK_COUNT in", values, "backCount");
            return (Criteria) this;
        }

        public Criteria andBackCountNotIn(List<Integer> values) {
            addCriterion("BACK_COUNT not in", values, "backCount");
            return (Criteria) this;
        }

        public Criteria andBackCountBetween(Integer value1, Integer value2) {
            addCriterion("BACK_COUNT between", value1, value2, "backCount");
            return (Criteria) this;
        }

        public Criteria andBackCountNotBetween(Integer value1, Integer value2) {
            addCriterion("BACK_COUNT not between", value1, value2, "backCount");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("OPERATOR is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("OPERATOR is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("OPERATOR =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("OPERATOR <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("OPERATOR >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATOR >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("OPERATOR <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("OPERATOR <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("OPERATOR like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("OPERATOR not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("OPERATOR in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("OPERATOR not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("OPERATOR between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("OPERATOR not between", value1, value2, "operator");
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