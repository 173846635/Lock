package com.dhy.yycompany.lock.bean;

import java.util.ArrayList;
import java.util.List;

public class DailyRecordInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DailyRecordInfoExample() {
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

        public Criteria andDIdIsNull() {
            addCriterion("d_id is null");
            return (Criteria) this;
        }

        public Criteria andDIdIsNotNull() {
            addCriterion("d_id is not null");
            return (Criteria) this;
        }

        public Criteria andDIdEqualTo(Integer value) {
            addCriterion("d_id =", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdNotEqualTo(Integer value) {
            addCriterion("d_id <>", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdGreaterThan(Integer value) {
            addCriterion("d_id >", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("d_id >=", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdLessThan(Integer value) {
            addCriterion("d_id <", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdLessThanOrEqualTo(Integer value) {
            addCriterion("d_id <=", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdIn(List<Integer> values) {
            addCriterion("d_id in", values, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdNotIn(List<Integer> values) {
            addCriterion("d_id not in", values, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdBetween(Integer value1, Integer value2) {
            addCriterion("d_id between", value1, value2, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdNotBetween(Integer value1, Integer value2) {
            addCriterion("d_id not between", value1, value2, "dId");
            return (Criteria) this;
        }

        public Criteria andDUuidIsNull() {
            addCriterion("d_uuid is null");
            return (Criteria) this;
        }

        public Criteria andDUuidIsNotNull() {
            addCriterion("d_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andDUuidEqualTo(String value) {
            addCriterion("d_uuid =", value, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidNotEqualTo(String value) {
            addCriterion("d_uuid <>", value, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidGreaterThan(String value) {
            addCriterion("d_uuid >", value, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidGreaterThanOrEqualTo(String value) {
            addCriterion("d_uuid >=", value, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidLessThan(String value) {
            addCriterion("d_uuid <", value, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidLessThanOrEqualTo(String value) {
            addCriterion("d_uuid <=", value, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidLike(String value) {
            addCriterion("d_uuid like", value, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidNotLike(String value) {
            addCriterion("d_uuid not like", value, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidIn(List<String> values) {
            addCriterion("d_uuid in", values, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidNotIn(List<String> values) {
            addCriterion("d_uuid not in", values, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidBetween(String value1, String value2) {
            addCriterion("d_uuid between", value1, value2, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUuidNotBetween(String value1, String value2) {
            addCriterion("d_uuid not between", value1, value2, "dUuid");
            return (Criteria) this;
        }

        public Criteria andDUserIdIsNull() {
            addCriterion("d_user_id is null");
            return (Criteria) this;
        }

        public Criteria andDUserIdIsNotNull() {
            addCriterion("d_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andDUserIdEqualTo(Integer value) {
            addCriterion("d_user_id =", value, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDUserIdNotEqualTo(Integer value) {
            addCriterion("d_user_id <>", value, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDUserIdGreaterThan(Integer value) {
            addCriterion("d_user_id >", value, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("d_user_id >=", value, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDUserIdLessThan(Integer value) {
            addCriterion("d_user_id <", value, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("d_user_id <=", value, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDUserIdIn(List<Integer> values) {
            addCriterion("d_user_id in", values, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDUserIdNotIn(List<Integer> values) {
            addCriterion("d_user_id not in", values, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDUserIdBetween(Integer value1, Integer value2) {
            addCriterion("d_user_id between", value1, value2, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("d_user_id not between", value1, value2, "dUserId");
            return (Criteria) this;
        }

        public Criteria andDLockIdIsNull() {
            addCriterion("d_lock_id is null");
            return (Criteria) this;
        }

        public Criteria andDLockIdIsNotNull() {
            addCriterion("d_lock_id is not null");
            return (Criteria) this;
        }

        public Criteria andDLockIdEqualTo(Integer value) {
            addCriterion("d_lock_id =", value, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDLockIdNotEqualTo(Integer value) {
            addCriterion("d_lock_id <>", value, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDLockIdGreaterThan(Integer value) {
            addCriterion("d_lock_id >", value, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDLockIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("d_lock_id >=", value, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDLockIdLessThan(Integer value) {
            addCriterion("d_lock_id <", value, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDLockIdLessThanOrEqualTo(Integer value) {
            addCriterion("d_lock_id <=", value, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDLockIdIn(List<Integer> values) {
            addCriterion("d_lock_id in", values, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDLockIdNotIn(List<Integer> values) {
            addCriterion("d_lock_id not in", values, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDLockIdBetween(Integer value1, Integer value2) {
            addCriterion("d_lock_id between", value1, value2, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDLockIdNotBetween(Integer value1, Integer value2) {
            addCriterion("d_lock_id not between", value1, value2, "dLockId");
            return (Criteria) this;
        }

        public Criteria andDTimeIsNull() {
            addCriterion("d_time is null");
            return (Criteria) this;
        }

        public Criteria andDTimeIsNotNull() {
            addCriterion("d_time is not null");
            return (Criteria) this;
        }

        public Criteria andDTimeEqualTo(String value) {
            addCriterion("d_time =", value, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeNotEqualTo(String value) {
            addCriterion("d_time <>", value, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeGreaterThan(String value) {
            addCriterion("d_time >", value, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeGreaterThanOrEqualTo(String value) {
            addCriterion("d_time >=", value, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeLessThan(String value) {
            addCriterion("d_time <", value, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeLessThanOrEqualTo(String value) {
            addCriterion("d_time <=", value, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeLike(String value) {
            addCriterion("d_time like", value, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeNotLike(String value) {
            addCriterion("d_time not like", value, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeIn(List<String> values) {
            addCriterion("d_time in", values, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeNotIn(List<String> values) {
            addCriterion("d_time not in", values, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeBetween(String value1, String value2) {
            addCriterion("d_time between", value1, value2, "dTime");
            return (Criteria) this;
        }

        public Criteria andDTimeNotBetween(String value1, String value2) {
            addCriterion("d_time not between", value1, value2, "dTime");
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