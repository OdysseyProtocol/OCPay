package com.odwallet.rechage.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserCoinBalanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserCoinBalanceExample() {
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

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
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

        public Criteria andCoinNameIsNull() {
            addCriterion("coin_name is null");
            return (Criteria) this;
        }

        public Criteria andCoinNameIsNotNull() {
            addCriterion("coin_name is not null");
            return (Criteria) this;
        }

        public Criteria andCoinNameEqualTo(String value) {
            addCriterion("coin_name =", value, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameNotEqualTo(String value) {
            addCriterion("coin_name <>", value, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameGreaterThan(String value) {
            addCriterion("coin_name >", value, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameGreaterThanOrEqualTo(String value) {
            addCriterion("coin_name >=", value, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameLessThan(String value) {
            addCriterion("coin_name <", value, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameLessThanOrEqualTo(String value) {
            addCriterion("coin_name <=", value, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameLike(String value) {
            addCriterion("coin_name like", value, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameNotLike(String value) {
            addCriterion("coin_name not like", value, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameIn(List<String> values) {
            addCriterion("coin_name in", values, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameNotIn(List<String> values) {
            addCriterion("coin_name not in", values, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameBetween(String value1, String value2) {
            addCriterion("coin_name between", value1, value2, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinNameNotBetween(String value1, String value2) {
            addCriterion("coin_name not between", value1, value2, "coinName");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceIsNull() {
            addCriterion("coin_balance is null");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceIsNotNull() {
            addCriterion("coin_balance is not null");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceEqualTo(BigDecimal value) {
            addCriterion("coin_balance =", value, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceNotEqualTo(BigDecimal value) {
            addCriterion("coin_balance <>", value, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceGreaterThan(BigDecimal value) {
            addCriterion("coin_balance >", value, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_balance >=", value, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceLessThan(BigDecimal value) {
            addCriterion("coin_balance <", value, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coin_balance <=", value, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceIn(List<BigDecimal> values) {
            addCriterion("coin_balance in", values, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceNotIn(List<BigDecimal> values) {
            addCriterion("coin_balance not in", values, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_balance between", value1, value2, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andCoinBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coin_balance not between", value1, value2, "coinBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceIsNull() {
            addCriterion("show_balance is null");
            return (Criteria) this;
        }

        public Criteria andShowBalanceIsNotNull() {
            addCriterion("show_balance is not null");
            return (Criteria) this;
        }

        public Criteria andShowBalanceEqualTo(BigDecimal value) {
            addCriterion("show_balance =", value, "showBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceNotEqualTo(BigDecimal value) {
            addCriterion("show_balance <>", value, "showBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceGreaterThan(BigDecimal value) {
            addCriterion("show_balance >", value, "showBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("show_balance >=", value, "showBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceLessThan(BigDecimal value) {
            addCriterion("show_balance <", value, "showBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("show_balance <=", value, "showBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceIn(List<BigDecimal> values) {
            addCriterion("show_balance in", values, "showBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceNotIn(List<BigDecimal> values) {
            addCriterion("show_balance not in", values, "showBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("show_balance between", value1, value2, "showBalance");
            return (Criteria) this;
        }

        public Criteria andShowBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("show_balance not between", value1, value2, "showBalance");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNull() {
            addCriterion("user_status is null");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNotNull() {
            addCriterion("user_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserStatusEqualTo(Byte value) {
            addCriterion("user_status =", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotEqualTo(Byte value) {
            addCriterion("user_status <>", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThan(Byte value) {
            addCriterion("user_status >", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("user_status >=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThan(Byte value) {
            addCriterion("user_status <", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThanOrEqualTo(Byte value) {
            addCriterion("user_status <=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusIn(List<Byte> values) {
            addCriterion("user_status in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotIn(List<Byte> values) {
            addCriterion("user_status not in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusBetween(Byte value1, Byte value2) {
            addCriterion("user_status between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("user_status not between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeIsNull() {
            addCriterion("last_trading_time is null");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeIsNotNull() {
            addCriterion("last_trading_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeEqualTo(Date value) {
            addCriterion("last_trading_time =", value, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeNotEqualTo(Date value) {
            addCriterion("last_trading_time <>", value, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeGreaterThan(Date value) {
            addCriterion("last_trading_time >", value, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_trading_time >=", value, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeLessThan(Date value) {
            addCriterion("last_trading_time <", value, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_trading_time <=", value, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeIn(List<Date> values) {
            addCriterion("last_trading_time in", values, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeNotIn(List<Date> values) {
            addCriterion("last_trading_time not in", values, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeBetween(Date value1, Date value2) {
            addCriterion("last_trading_time between", value1, value2, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andLastTradingTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_trading_time not between", value1, value2, "lastTradingTime");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNull() {
            addCriterion("transfer_status is null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNotNull() {
            addCriterion("transfer_status is not null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusEqualTo(Byte value) {
            addCriterion("transfer_status =", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotEqualTo(Byte value) {
            addCriterion("transfer_status <>", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThan(Byte value) {
            addCriterion("transfer_status >", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("transfer_status >=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThan(Byte value) {
            addCriterion("transfer_status <", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThanOrEqualTo(Byte value) {
            addCriterion("transfer_status <=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIn(List<Byte> values) {
            addCriterion("transfer_status in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotIn(List<Byte> values) {
            addCriterion("transfer_status not in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusBetween(Byte value1, Byte value2) {
            addCriterion("transfer_status between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("transfer_status not between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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