package com.stormfives.ocpay.advertisment.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvertismentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvertismentExample() {
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

        public Criteria andHomePageIdIsNull() {
            addCriterion("home_page_id is null");
            return (Criteria) this;
        }

        public Criteria andHomePageIdIsNotNull() {
            addCriterion("home_page_id is not null");
            return (Criteria) this;
        }

        public Criteria andHomePageIdEqualTo(Integer value) {
            addCriterion("home_page_id =", value, "homePageId");
            return (Criteria) this;
        }

        public Criteria andHomePageIdNotEqualTo(Integer value) {
            addCriterion("home_page_id <>", value, "homePageId");
            return (Criteria) this;
        }

        public Criteria andHomePageIdGreaterThan(Integer value) {
            addCriterion("home_page_id >", value, "homePageId");
            return (Criteria) this;
        }

        public Criteria andHomePageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("home_page_id >=", value, "homePageId");
            return (Criteria) this;
        }

        public Criteria andHomePageIdLessThan(Integer value) {
            addCriterion("home_page_id <", value, "homePageId");
            return (Criteria) this;
        }

        public Criteria andHomePageIdLessThanOrEqualTo(Integer value) {
            addCriterion("home_page_id <=", value, "homePageId");
            return (Criteria) this;
        }

        public Criteria andHomePageIdIn(List<Integer> values) {
            addCriterion("home_page_id in", values, "homePageId");
            return (Criteria) this;
        }

        public Criteria andHomePageIdNotIn(List<Integer> values) {
            addCriterion("home_page_id not in", values, "homePageId");
            return (Criteria) this;
        }

        public Criteria andHomePageIdBetween(Integer value1, Integer value2) {
            addCriterion("home_page_id between", value1, value2, "homePageId");
            return (Criteria) this;
        }

        public Criteria andHomePageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("home_page_id not between", value1, value2, "homePageId");
            return (Criteria) this;
        }

        public Criteria andDirectTypeIsNull() {
            addCriterion("direct_type is null");
            return (Criteria) this;
        }

        public Criteria andDirectTypeIsNotNull() {
            addCriterion("direct_type is not null");
            return (Criteria) this;
        }

        public Criteria andDirectTypeEqualTo(Integer value) {
            addCriterion("direct_type =", value, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectTypeNotEqualTo(Integer value) {
            addCriterion("direct_type <>", value, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectTypeGreaterThan(Integer value) {
            addCriterion("direct_type >", value, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("direct_type >=", value, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectTypeLessThan(Integer value) {
            addCriterion("direct_type <", value, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectTypeLessThanOrEqualTo(Integer value) {
            addCriterion("direct_type <=", value, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectTypeIn(List<Integer> values) {
            addCriterion("direct_type in", values, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectTypeNotIn(List<Integer> values) {
            addCriterion("direct_type not in", values, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectTypeBetween(Integer value1, Integer value2) {
            addCriterion("direct_type between", value1, value2, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("direct_type not between", value1, value2, "directType");
            return (Criteria) this;
        }

        public Criteria andDirectUrlIsNull() {
            addCriterion("direct_url is null");
            return (Criteria) this;
        }

        public Criteria andDirectUrlIsNotNull() {
            addCriterion("direct_url is not null");
            return (Criteria) this;
        }

        public Criteria andDirectUrlEqualTo(String value) {
            addCriterion("direct_url =", value, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlNotEqualTo(String value) {
            addCriterion("direct_url <>", value, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlGreaterThan(String value) {
            addCriterion("direct_url >", value, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlGreaterThanOrEqualTo(String value) {
            addCriterion("direct_url >=", value, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlLessThan(String value) {
            addCriterion("direct_url <", value, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlLessThanOrEqualTo(String value) {
            addCriterion("direct_url <=", value, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlLike(String value) {
            addCriterion("direct_url like", value, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlNotLike(String value) {
            addCriterion("direct_url not like", value, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlIn(List<String> values) {
            addCriterion("direct_url in", values, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlNotIn(List<String> values) {
            addCriterion("direct_url not in", values, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlBetween(String value1, String value2) {
            addCriterion("direct_url between", value1, value2, "directUrl");
            return (Criteria) this;
        }

        public Criteria andDirectUrlNotBetween(String value1, String value2) {
            addCriterion("direct_url not between", value1, value2, "directUrl");
            return (Criteria) this;
        }

        public Criteria andShowSortIsNull() {
            addCriterion("show_sort is null");
            return (Criteria) this;
        }

        public Criteria andShowSortIsNotNull() {
            addCriterion("show_sort is not null");
            return (Criteria) this;
        }

        public Criteria andShowSortEqualTo(Integer value) {
            addCriterion("show_sort =", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortNotEqualTo(Integer value) {
            addCriterion("show_sort <>", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortGreaterThan(Integer value) {
            addCriterion("show_sort >", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_sort >=", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortLessThan(Integer value) {
            addCriterion("show_sort <", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortLessThanOrEqualTo(Integer value) {
            addCriterion("show_sort <=", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortIn(List<Integer> values) {
            addCriterion("show_sort in", values, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortNotIn(List<Integer> values) {
            addCriterion("show_sort not in", values, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortBetween(Integer value1, Integer value2) {
            addCriterion("show_sort between", value1, value2, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortNotBetween(Integer value1, Integer value2) {
            addCriterion("show_sort not between", value1, value2, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowImgIsNull() {
            addCriterion("show_img is null");
            return (Criteria) this;
        }

        public Criteria andShowImgIsNotNull() {
            addCriterion("show_img is not null");
            return (Criteria) this;
        }

        public Criteria andShowImgEqualTo(String value) {
            addCriterion("show_img =", value, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgNotEqualTo(String value) {
            addCriterion("show_img <>", value, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgGreaterThan(String value) {
            addCriterion("show_img >", value, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgGreaterThanOrEqualTo(String value) {
            addCriterion("show_img >=", value, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgLessThan(String value) {
            addCriterion("show_img <", value, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgLessThanOrEqualTo(String value) {
            addCriterion("show_img <=", value, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgLike(String value) {
            addCriterion("show_img like", value, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgNotLike(String value) {
            addCriterion("show_img not like", value, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgIn(List<String> values) {
            addCriterion("show_img in", values, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgNotIn(List<String> values) {
            addCriterion("show_img not in", values, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgBetween(String value1, String value2) {
            addCriterion("show_img between", value1, value2, "showImg");
            return (Criteria) this;
        }

        public Criteria andShowImgNotBetween(String value1, String value2) {
            addCriterion("show_img not between", value1, value2, "showImg");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
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

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Integer value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Integer value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Integer value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Integer value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Integer value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Integer> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Integer> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Integer value1, Integer value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Integer value1, Integer value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
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