package com.stormfives.ocpay.token.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OauthAccessokenExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OauthAccessokenExample() {
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

        public Criteria andResourceOwnerIdIsNull() {
            addCriterion("resource_owner_id is null");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdIsNotNull() {
            addCriterion("resource_owner_id is not null");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdEqualTo(Integer value) {
            addCriterion("resource_owner_id =", value, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdNotEqualTo(Integer value) {
            addCriterion("resource_owner_id <>", value, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdGreaterThan(Integer value) {
            addCriterion("resource_owner_id >", value, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("resource_owner_id >=", value, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdLessThan(Integer value) {
            addCriterion("resource_owner_id <", value, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdLessThanOrEqualTo(Integer value) {
            addCriterion("resource_owner_id <=", value, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdIn(List<Integer> values) {
            addCriterion("resource_owner_id in", values, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdNotIn(List<Integer> values) {
            addCriterion("resource_owner_id not in", values, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdBetween(Integer value1, Integer value2) {
            addCriterion("resource_owner_id between", value1, value2, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andResourceOwnerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("resource_owner_id not between", value1, value2, "resourceOwnerId");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNull() {
            addCriterion("access_token is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNotNull() {
            addCriterion("access_token is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenEqualTo(String value) {
            addCriterion("access_token =", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotEqualTo(String value) {
            addCriterion("access_token <>", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThan(String value) {
            addCriterion("access_token >", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("access_token >=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThan(String value) {
            addCriterion("access_token <", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("access_token <=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLike(String value) {
            addCriterion("access_token like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotLike(String value) {
            addCriterion("access_token not like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIn(List<String> values) {
            addCriterion("access_token in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotIn(List<String> values) {
            addCriterion("access_token not in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenBetween(String value1, String value2) {
            addCriterion("access_token between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotBetween(String value1, String value2) {
            addCriterion("access_token not between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIsNull() {
            addCriterion("refresh_token is null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIsNotNull() {
            addCriterion("refresh_token is not null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenEqualTo(String value) {
            addCriterion("refresh_token =", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotEqualTo(String value) {
            addCriterion("refresh_token <>", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenGreaterThan(String value) {
            addCriterion("refresh_token >", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenGreaterThanOrEqualTo(String value) {
            addCriterion("refresh_token >=", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLessThan(String value) {
            addCriterion("refresh_token <", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLessThanOrEqualTo(String value) {
            addCriterion("refresh_token <=", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLike(String value) {
            addCriterion("refresh_token like", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotLike(String value) {
            addCriterion("refresh_token not like", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIn(List<String> values) {
            addCriterion("refresh_token in", values, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotIn(List<String> values) {
            addCriterion("refresh_token not in", values, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenBetween(String value1, String value2) {
            addCriterion("refresh_token between", value1, value2, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotBetween(String value1, String value2) {
            addCriterion("refresh_token not between", value1, value2, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andExpiresInIsNull() {
            addCriterion("expires_in is null");
            return (Criteria) this;
        }

        public Criteria andExpiresInIsNotNull() {
            addCriterion("expires_in is not null");
            return (Criteria) this;
        }

        public Criteria andExpiresInEqualTo(Integer value) {
            addCriterion("expires_in =", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInNotEqualTo(Integer value) {
            addCriterion("expires_in <>", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInGreaterThan(Integer value) {
            addCriterion("expires_in >", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInGreaterThanOrEqualTo(Integer value) {
            addCriterion("expires_in >=", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInLessThan(Integer value) {
            addCriterion("expires_in <", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInLessThanOrEqualTo(Integer value) {
            addCriterion("expires_in <=", value, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInIn(List<Integer> values) {
            addCriterion("expires_in in", values, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInNotIn(List<Integer> values) {
            addCriterion("expires_in not in", values, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInBetween(Integer value1, Integer value2) {
            addCriterion("expires_in between", value1, value2, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andExpiresInNotBetween(Integer value1, Integer value2) {
            addCriterion("expires_in not between", value1, value2, "expiresIn");
            return (Criteria) this;
        }

        public Criteria andRevokedAtIsNull() {
            addCriterion("revoked_at is null");
            return (Criteria) this;
        }

        public Criteria andRevokedAtIsNotNull() {
            addCriterion("revoked_at is not null");
            return (Criteria) this;
        }

        public Criteria andRevokedAtEqualTo(Date value) {
            addCriterion("revoked_at =", value, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andRevokedAtNotEqualTo(Date value) {
            addCriterion("revoked_at <>", value, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andRevokedAtGreaterThan(Date value) {
            addCriterion("revoked_at >", value, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andRevokedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("revoked_at >=", value, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andRevokedAtLessThan(Date value) {
            addCriterion("revoked_at <", value, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andRevokedAtLessThanOrEqualTo(Date value) {
            addCriterion("revoked_at <=", value, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andRevokedAtIn(List<Date> values) {
            addCriterion("revoked_at in", values, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andRevokedAtNotIn(List<Date> values) {
            addCriterion("revoked_at not in", values, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andRevokedAtBetween(Date value1, Date value2) {
            addCriterion("revoked_at between", value1, value2, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andRevokedAtNotBetween(Date value1, Date value2) {
            addCriterion("revoked_at not between", value1, value2, "revokedAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andScopeIsNull() {
            addCriterion("scope is null");
            return (Criteria) this;
        }

        public Criteria andScopeIsNotNull() {
            addCriterion("scope is not null");
            return (Criteria) this;
        }

        public Criteria andScopeEqualTo(String value) {
            addCriterion("scope =", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotEqualTo(String value) {
            addCriterion("scope <>", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeGreaterThan(String value) {
            addCriterion("scope >", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeGreaterThanOrEqualTo(String value) {
            addCriterion("scope >=", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLessThan(String value) {
            addCriterion("scope <", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLessThanOrEqualTo(String value) {
            addCriterion("scope <=", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLike(String value) {
            addCriterion("scope like", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotLike(String value) {
            addCriterion("scope not like", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeIn(List<String> values) {
            addCriterion("scope in", values, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotIn(List<String> values) {
            addCriterion("scope not in", values, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeBetween(String value1, String value2) {
            addCriterion("scope between", value1, value2, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotBetween(String value1, String value2) {
            addCriterion("scope not between", value1, value2, "scope");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("count not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdIsNull() {
            addCriterion("merchant_app_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdIsNotNull() {
            addCriterion("merchant_app_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdEqualTo(Integer value) {
            addCriterion("merchant_app_id =", value, "merchantAppId");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdNotEqualTo(Integer value) {
            addCriterion("merchant_app_id <>", value, "merchantAppId");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdGreaterThan(Integer value) {
            addCriterion("merchant_app_id >", value, "merchantAppId");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_app_id >=", value, "merchantAppId");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdLessThan(Integer value) {
            addCriterion("merchant_app_id <", value, "merchantAppId");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_app_id <=", value, "merchantAppId");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdIn(List<Integer> values) {
            addCriterion("merchant_app_id in", values, "merchantAppId");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdNotIn(List<Integer> values) {
            addCriterion("merchant_app_id not in", values, "merchantAppId");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdBetween(Integer value1, Integer value2) {
            addCriterion("merchant_app_id between", value1, value2, "merchantAppId");
            return (Criteria) this;
        }

        public Criteria andMerchantAppIdNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_app_id not between", value1, value2, "merchantAppId");
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