package com.xtxk.system.api.model;

import java.util.ArrayList;
import java.util.List;

public class SystemConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemConfigExample() {
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
            addCriterion("`ID` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`ID` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("`ID` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("`ID` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("`ID` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`ID` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("`ID` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("`ID` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("`ID` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("`ID` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("`ID` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("`ID` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andConKeyIsNull() {
            addCriterion("`CON_KEY` is null");
            return (Criteria) this;
        }

        public Criteria andConKeyIsNotNull() {
            addCriterion("`CON_KEY` is not null");
            return (Criteria) this;
        }

        public Criteria andConKeyEqualTo(String value) {
            addCriterion("`CON_KEY` =", value, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyNotEqualTo(String value) {
            addCriterion("`CON_KEY` <>", value, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyGreaterThan(String value) {
            addCriterion("`CON_KEY` >", value, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyGreaterThanOrEqualTo(String value) {
            addCriterion("`CON_KEY` >=", value, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyLessThan(String value) {
            addCriterion("`CON_KEY` <", value, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyLessThanOrEqualTo(String value) {
            addCriterion("`CON_KEY` <=", value, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyLike(String value) {
            addCriterion("`CON_KEY` like", value, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyNotLike(String value) {
            addCriterion("`CON_KEY` not like", value, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyIn(List<String> values) {
            addCriterion("`CON_KEY` in", values, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyNotIn(List<String> values) {
            addCriterion("`CON_KEY` not in", values, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyBetween(String value1, String value2) {
            addCriterion("`CON_KEY` between", value1, value2, "conKey");
            return (Criteria) this;
        }

        public Criteria andConKeyNotBetween(String value1, String value2) {
            addCriterion("`CON_KEY` not between", value1, value2, "conKey");
            return (Criteria) this;
        }

        public Criteria andConValueIsNull() {
            addCriterion("`CON_VALUE` is null");
            return (Criteria) this;
        }

        public Criteria andConValueIsNotNull() {
            addCriterion("`CON_VALUE` is not null");
            return (Criteria) this;
        }

        public Criteria andConValueEqualTo(String value) {
            addCriterion("`CON_VALUE` =", value, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueNotEqualTo(String value) {
            addCriterion("`CON_VALUE` <>", value, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueGreaterThan(String value) {
            addCriterion("`CON_VALUE` >", value, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueGreaterThanOrEqualTo(String value) {
            addCriterion("`CON_VALUE` >=", value, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueLessThan(String value) {
            addCriterion("`CON_VALUE` <", value, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueLessThanOrEqualTo(String value) {
            addCriterion("`CON_VALUE` <=", value, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueLike(String value) {
            addCriterion("`CON_VALUE` like", value, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueNotLike(String value) {
            addCriterion("`CON_VALUE` not like", value, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueIn(List<String> values) {
            addCriterion("`CON_VALUE` in", values, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueNotIn(List<String> values) {
            addCriterion("`CON_VALUE` not in", values, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueBetween(String value1, String value2) {
            addCriterion("`CON_VALUE` between", value1, value2, "conValue");
            return (Criteria) this;
        }

        public Criteria andConValueNotBetween(String value1, String value2) {
            addCriterion("`CON_VALUE` not between", value1, value2, "conValue");
            return (Criteria) this;
        }

        public Criteria andConCommentIsNull() {
            addCriterion("`CON_COMMENT` is null");
            return (Criteria) this;
        }

        public Criteria andConCommentIsNotNull() {
            addCriterion("`CON_COMMENT` is not null");
            return (Criteria) this;
        }

        public Criteria andConCommentEqualTo(String value) {
            addCriterion("`CON_COMMENT` =", value, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentNotEqualTo(String value) {
            addCriterion("`CON_COMMENT` <>", value, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentGreaterThan(String value) {
            addCriterion("`CON_COMMENT` >", value, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentGreaterThanOrEqualTo(String value) {
            addCriterion("`CON_COMMENT` >=", value, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentLessThan(String value) {
            addCriterion("`CON_COMMENT` <", value, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentLessThanOrEqualTo(String value) {
            addCriterion("`CON_COMMENT` <=", value, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentLike(String value) {
            addCriterion("`CON_COMMENT` like", value, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentNotLike(String value) {
            addCriterion("`CON_COMMENT` not like", value, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentIn(List<String> values) {
            addCriterion("`CON_COMMENT` in", values, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentNotIn(List<String> values) {
            addCriterion("`CON_COMMENT` not in", values, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentBetween(String value1, String value2) {
            addCriterion("`CON_COMMENT` between", value1, value2, "conComment");
            return (Criteria) this;
        }

        public Criteria andConCommentNotBetween(String value1, String value2) {
            addCriterion("`CON_COMMENT` not between", value1, value2, "conComment");
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