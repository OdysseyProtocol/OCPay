package com.odwallet.rechage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleBlockNumLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScheduleBlockNumLogExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumIsNull() {
            addCriterion("start_block_num is null");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumIsNotNull() {
            addCriterion("start_block_num is not null");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumEqualTo(Long value) {
            addCriterion("start_block_num =", value, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumNotEqualTo(Long value) {
            addCriterion("start_block_num <>", value, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumGreaterThan(Long value) {
            addCriterion("start_block_num >", value, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumGreaterThanOrEqualTo(Long value) {
            addCriterion("start_block_num >=", value, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumLessThan(Long value) {
            addCriterion("start_block_num <", value, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumLessThanOrEqualTo(Long value) {
            addCriterion("start_block_num <=", value, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumIn(List<Long> values) {
            addCriterion("start_block_num in", values, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumNotIn(List<Long> values) {
            addCriterion("start_block_num not in", values, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumBetween(Long value1, Long value2) {
            addCriterion("start_block_num between", value1, value2, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andStartBlockNumNotBetween(Long value1, Long value2) {
            addCriterion("start_block_num not between", value1, value2, "startBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumIsNull() {
            addCriterion("end_block_num is null");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumIsNotNull() {
            addCriterion("end_block_num is not null");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumEqualTo(Long value) {
            addCriterion("end_block_num =", value, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumNotEqualTo(Long value) {
            addCriterion("end_block_num <>", value, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumGreaterThan(Long value) {
            addCriterion("end_block_num >", value, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumGreaterThanOrEqualTo(Long value) {
            addCriterion("end_block_num >=", value, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumLessThan(Long value) {
            addCriterion("end_block_num <", value, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumLessThanOrEqualTo(Long value) {
            addCriterion("end_block_num <=", value, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumIn(List<Long> values) {
            addCriterion("end_block_num in", values, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumNotIn(List<Long> values) {
            addCriterion("end_block_num not in", values, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumBetween(Long value1, Long value2) {
            addCriterion("end_block_num between", value1, value2, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andEndBlockNumNotBetween(Long value1, Long value2) {
            addCriterion("end_block_num not between", value1, value2, "endBlockNum");
            return (Criteria) this;
        }

        public Criteria andCoinIdIsNull() {
            addCriterion("coin_id is null");
            return (Criteria) this;
        }

        public Criteria andCoinIdIsNotNull() {
            addCriterion("coin_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoinIdEqualTo(Integer value) {
            addCriterion("coin_id =", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdNotEqualTo(Integer value) {
            addCriterion("coin_id <>", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdGreaterThan(Integer value) {
            addCriterion("coin_id >", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coin_id >=", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdLessThan(Integer value) {
            addCriterion("coin_id <", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdLessThanOrEqualTo(Integer value) {
            addCriterion("coin_id <=", value, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdIn(List<Integer> values) {
            addCriterion("coin_id in", values, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdNotIn(List<Integer> values) {
            addCriterion("coin_id not in", values, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdBetween(Integer value1, Integer value2) {
            addCriterion("coin_id between", value1, value2, "coinId");
            return (Criteria) this;
        }

        public Criteria andCoinIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coin_id not between", value1, value2, "coinId");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeIsNull() {
            addCriterion("last_scan_time is null");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeIsNotNull() {
            addCriterion("last_scan_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeEqualTo(Date value) {
            addCriterion("last_scan_time =", value, "lastScanTime");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeNotEqualTo(Date value) {
            addCriterion("last_scan_time <>", value, "lastScanTime");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeGreaterThan(Date value) {
            addCriterion("last_scan_time >", value, "lastScanTime");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_scan_time >=", value, "lastScanTime");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeLessThan(Date value) {
            addCriterion("last_scan_time <", value, "lastScanTime");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_scan_time <=", value, "lastScanTime");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeIn(List<Date> values) {
            addCriterion("last_scan_time in", values, "lastScanTime");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeNotIn(List<Date> values) {
            addCriterion("last_scan_time not in", values, "lastScanTime");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeBetween(Date value1, Date value2) {
            addCriterion("last_scan_time between", value1, value2, "lastScanTime");
            return (Criteria) this;
        }

        public Criteria andLastScanTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_scan_time not between", value1, value2, "lastScanTime");
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