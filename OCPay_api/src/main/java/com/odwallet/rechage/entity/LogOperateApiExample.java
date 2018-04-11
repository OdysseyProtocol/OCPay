package com.odwallet.rechage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogOperateApiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogOperateApiExample() {
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

        public Criteria andLogidIsNull() {
            addCriterion("logid is null");
            return (Criteria) this;
        }

        public Criteria andLogidIsNotNull() {
            addCriterion("logid is not null");
            return (Criteria) this;
        }

        public Criteria andLogidEqualTo(Integer value) {
            addCriterion("logid =", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotEqualTo(Integer value) {
            addCriterion("logid <>", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidGreaterThan(Integer value) {
            addCriterion("logid >", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidGreaterThanOrEqualTo(Integer value) {
            addCriterion("logid >=", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidLessThan(Integer value) {
            addCriterion("logid <", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidLessThanOrEqualTo(Integer value) {
            addCriterion("logid <=", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidIn(List<Integer> values) {
            addCriterion("logid in", values, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotIn(List<Integer> values) {
            addCriterion("logid not in", values, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidBetween(Integer value1, Integer value2) {
            addCriterion("logid between", value1, value2, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotBetween(Integer value1, Integer value2) {
            addCriterion("logid not between", value1, value2, "logid");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNull() {
            addCriterion("merchant_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("merchant_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Integer value) {
            addCriterion("merchant_id =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Integer value) {
            addCriterion("merchant_id <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Integer value) {
            addCriterion("merchant_id >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_id >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Integer value) {
            addCriterion("merchant_id <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_id <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Integer> values) {
            addCriterion("merchant_id in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Integer> values) {
            addCriterion("merchant_id not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Integer value1, Integer value2) {
            addCriterion("merchant_id between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_id not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andActpathIsNull() {
            addCriterion("actpath is null");
            return (Criteria) this;
        }

        public Criteria andActpathIsNotNull() {
            addCriterion("actpath is not null");
            return (Criteria) this;
        }

        public Criteria andActpathEqualTo(String value) {
            addCriterion("actpath =", value, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathNotEqualTo(String value) {
            addCriterion("actpath <>", value, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathGreaterThan(String value) {
            addCriterion("actpath >", value, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathGreaterThanOrEqualTo(String value) {
            addCriterion("actpath >=", value, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathLessThan(String value) {
            addCriterion("actpath <", value, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathLessThanOrEqualTo(String value) {
            addCriterion("actpath <=", value, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathLike(String value) {
            addCriterion("actpath like", value, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathNotLike(String value) {
            addCriterion("actpath not like", value, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathIn(List<String> values) {
            addCriterion("actpath in", values, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathNotIn(List<String> values) {
            addCriterion("actpath not in", values, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathBetween(String value1, String value2) {
            addCriterion("actpath between", value1, value2, "actpath");
            return (Criteria) this;
        }

        public Criteria andActpathNotBetween(String value1, String value2) {
            addCriterion("actpath not between", value1, value2, "actpath");
            return (Criteria) this;
        }

        public Criteria andInIsNull() {
            addCriterion("in is null");
            return (Criteria) this;
        }

        public Criteria andInIsNotNull() {
            addCriterion("in is not null");
            return (Criteria) this;
        }

        public Criteria andInEqualTo(String value) {
            addCriterion("in =", value, "in");
            return (Criteria) this;
        }

        public Criteria andInNotEqualTo(String value) {
            addCriterion("in <>", value, "in");
            return (Criteria) this;
        }

        public Criteria andInGreaterThan(String value) {
            addCriterion("in >", value, "in");
            return (Criteria) this;
        }

        public Criteria andInGreaterThanOrEqualTo(String value) {
            addCriterion("in >=", value, "in");
            return (Criteria) this;
        }

        public Criteria andInLessThan(String value) {
            addCriterion("in <", value, "in");
            return (Criteria) this;
        }

        public Criteria andInLessThanOrEqualTo(String value) {
            addCriterion("in <=", value, "in");
            return (Criteria) this;
        }

        public Criteria andInLike(String value) {
            addCriterion("in like", value, "in");
            return (Criteria) this;
        }

        public Criteria andInNotLike(String value) {
            addCriterion("in not like", value, "in");
            return (Criteria) this;
        }

        public Criteria andInIn(List<String> values) {
            addCriterion("in in", values, "in");
            return (Criteria) this;
        }

        public Criteria andInNotIn(List<String> values) {
            addCriterion("in not in", values, "in");
            return (Criteria) this;
        }

        public Criteria andInBetween(String value1, String value2) {
            addCriterion("in between", value1, value2, "in");
            return (Criteria) this;
        }

        public Criteria andInNotBetween(String value1, String value2) {
            addCriterion("in not between", value1, value2, "in");
            return (Criteria) this;
        }

        public Criteria andSeedIsNull() {
            addCriterion("seed is null");
            return (Criteria) this;
        }

        public Criteria andSeedIsNotNull() {
            addCriterion("seed is not null");
            return (Criteria) this;
        }

        public Criteria andSeedEqualTo(String value) {
            addCriterion("seed =", value, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedNotEqualTo(String value) {
            addCriterion("seed <>", value, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedGreaterThan(String value) {
            addCriterion("seed >", value, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedGreaterThanOrEqualTo(String value) {
            addCriterion("seed >=", value, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedLessThan(String value) {
            addCriterion("seed <", value, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedLessThanOrEqualTo(String value) {
            addCriterion("seed <=", value, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedLike(String value) {
            addCriterion("seed like", value, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedNotLike(String value) {
            addCriterion("seed not like", value, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedIn(List<String> values) {
            addCriterion("seed in", values, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedNotIn(List<String> values) {
            addCriterion("seed not in", values, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedBetween(String value1, String value2) {
            addCriterion("seed between", value1, value2, "seed");
            return (Criteria) this;
        }

        public Criteria andSeedNotBetween(String value1, String value2) {
            addCriterion("seed not between", value1, value2, "seed");
            return (Criteria) this;
        }

        public Criteria andConsumetimeIsNull() {
            addCriterion("consumetime is null");
            return (Criteria) this;
        }

        public Criteria andConsumetimeIsNotNull() {
            addCriterion("consumetime is not null");
            return (Criteria) this;
        }

        public Criteria andConsumetimeEqualTo(Integer value) {
            addCriterion("consumetime =", value, "consumetime");
            return (Criteria) this;
        }

        public Criteria andConsumetimeNotEqualTo(Integer value) {
            addCriterion("consumetime <>", value, "consumetime");
            return (Criteria) this;
        }

        public Criteria andConsumetimeGreaterThan(Integer value) {
            addCriterion("consumetime >", value, "consumetime");
            return (Criteria) this;
        }

        public Criteria andConsumetimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("consumetime >=", value, "consumetime");
            return (Criteria) this;
        }

        public Criteria andConsumetimeLessThan(Integer value) {
            addCriterion("consumetime <", value, "consumetime");
            return (Criteria) this;
        }

        public Criteria andConsumetimeLessThanOrEqualTo(Integer value) {
            addCriterion("consumetime <=", value, "consumetime");
            return (Criteria) this;
        }

        public Criteria andConsumetimeIn(List<Integer> values) {
            addCriterion("consumetime in", values, "consumetime");
            return (Criteria) this;
        }

        public Criteria andConsumetimeNotIn(List<Integer> values) {
            addCriterion("consumetime not in", values, "consumetime");
            return (Criteria) this;
        }

        public Criteria andConsumetimeBetween(Integer value1, Integer value2) {
            addCriterion("consumetime between", value1, value2, "consumetime");
            return (Criteria) this;
        }

        public Criteria andConsumetimeNotBetween(Integer value1, Integer value2) {
            addCriterion("consumetime not between", value1, value2, "consumetime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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