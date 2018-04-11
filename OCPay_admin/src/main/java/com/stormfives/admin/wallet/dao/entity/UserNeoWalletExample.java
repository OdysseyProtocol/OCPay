package com.stormfives.admin.wallet.dao.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserNeoWalletExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserNeoWalletExample() {
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

        public Criteria andNeoWalletAddressIsNull() {
            addCriterion("neo_wallet_address is null");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressIsNotNull() {
            addCriterion("neo_wallet_address is not null");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressEqualTo(String value) {
            addCriterion("neo_wallet_address =", value, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressNotEqualTo(String value) {
            addCriterion("neo_wallet_address <>", value, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressGreaterThan(String value) {
            addCriterion("neo_wallet_address >", value, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressGreaterThanOrEqualTo(String value) {
            addCriterion("neo_wallet_address >=", value, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressLessThan(String value) {
            addCriterion("neo_wallet_address <", value, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressLessThanOrEqualTo(String value) {
            addCriterion("neo_wallet_address <=", value, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressLike(String value) {
            addCriterion("neo_wallet_address like", value, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressNotLike(String value) {
            addCriterion("neo_wallet_address not like", value, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressIn(List<String> values) {
            addCriterion("neo_wallet_address in", values, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressNotIn(List<String> values) {
            addCriterion("neo_wallet_address not in", values, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressBetween(String value1, String value2) {
            addCriterion("neo_wallet_address between", value1, value2, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andNeoWalletAddressNotBetween(String value1, String value2) {
            addCriterion("neo_wallet_address not between", value1, value2, "neoWalletAddress");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcIsNull() {
            addCriterion("balance_qlc is null");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcIsNotNull() {
            addCriterion("balance_qlc is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcEqualTo(BigDecimal value) {
            addCriterion("balance_qlc =", value, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcNotEqualTo(BigDecimal value) {
            addCriterion("balance_qlc <>", value, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcGreaterThan(BigDecimal value) {
            addCriterion("balance_qlc >", value, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_qlc >=", value, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcLessThan(BigDecimal value) {
            addCriterion("balance_qlc <", value, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_qlc <=", value, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcIn(List<BigDecimal> values) {
            addCriterion("balance_qlc in", values, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcNotIn(List<BigDecimal> values) {
            addCriterion("balance_qlc not in", values, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_qlc between", value1, value2, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceQlcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_qlc not between", value1, value2, "balanceQlc");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoIsNull() {
            addCriterion("balance_neo is null");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoIsNotNull() {
            addCriterion("balance_neo is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoEqualTo(BigDecimal value) {
            addCriterion("balance_neo =", value, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoNotEqualTo(BigDecimal value) {
            addCriterion("balance_neo <>", value, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoGreaterThan(BigDecimal value) {
            addCriterion("balance_neo >", value, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_neo >=", value, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoLessThan(BigDecimal value) {
            addCriterion("balance_neo <", value, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_neo <=", value, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoIn(List<BigDecimal> values) {
            addCriterion("balance_neo in", values, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoNotIn(List<BigDecimal> values) {
            addCriterion("balance_neo not in", values, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_neo between", value1, value2, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceNeoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_neo not between", value1, value2, "balanceNeo");
            return (Criteria) this;
        }

        public Criteria andBalanceOntIsNull() {
            addCriterion("balance_ont is null");
            return (Criteria) this;
        }

        public Criteria andBalanceOntIsNotNull() {
            addCriterion("balance_ont is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceOntEqualTo(BigDecimal value) {
            addCriterion("balance_ont =", value, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andBalanceOntNotEqualTo(BigDecimal value) {
            addCriterion("balance_ont <>", value, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andBalanceOntGreaterThan(BigDecimal value) {
            addCriterion("balance_ont >", value, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andBalanceOntGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_ont >=", value, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andBalanceOntLessThan(BigDecimal value) {
            addCriterion("balance_ont <", value, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andBalanceOntLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_ont <=", value, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andBalanceOntIn(List<BigDecimal> values) {
            addCriterion("balance_ont in", values, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andBalanceOntNotIn(List<BigDecimal> values) {
            addCriterion("balance_ont not in", values, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andBalanceOntBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_ont between", value1, value2, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andBalanceOntNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_ont not between", value1, value2, "balanceOnt");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyIsNull() {
            addCriterion("eth_wallet_private_key is null");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyIsNotNull() {
            addCriterion("eth_wallet_private_key is not null");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyEqualTo(String value) {
            addCriterion("eth_wallet_private_key =", value, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyNotEqualTo(String value) {
            addCriterion("eth_wallet_private_key <>", value, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyGreaterThan(String value) {
            addCriterion("eth_wallet_private_key >", value, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyGreaterThanOrEqualTo(String value) {
            addCriterion("eth_wallet_private_key >=", value, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyLessThan(String value) {
            addCriterion("eth_wallet_private_key <", value, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyLessThanOrEqualTo(String value) {
            addCriterion("eth_wallet_private_key <=", value, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyLike(String value) {
            addCriterion("eth_wallet_private_key like", value, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyNotLike(String value) {
            addCriterion("eth_wallet_private_key not like", value, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyIn(List<String> values) {
            addCriterion("eth_wallet_private_key in", values, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyNotIn(List<String> values) {
            addCriterion("eth_wallet_private_key not in", values, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyBetween(String value1, String value2) {
            addCriterion("eth_wallet_private_key between", value1, value2, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletPrivateKeyNotBetween(String value1, String value2) {
            addCriterion("eth_wallet_private_key not between", value1, value2, "ethWalletPrivateKey");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressIsNull() {
            addCriterion("eth_wallet_address is null");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressIsNotNull() {
            addCriterion("eth_wallet_address is not null");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressEqualTo(String value) {
            addCriterion("eth_wallet_address =", value, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressNotEqualTo(String value) {
            addCriterion("eth_wallet_address <>", value, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressGreaterThan(String value) {
            addCriterion("eth_wallet_address >", value, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressGreaterThanOrEqualTo(String value) {
            addCriterion("eth_wallet_address >=", value, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressLessThan(String value) {
            addCriterion("eth_wallet_address <", value, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressLessThanOrEqualTo(String value) {
            addCriterion("eth_wallet_address <=", value, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressLike(String value) {
            addCriterion("eth_wallet_address like", value, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressNotLike(String value) {
            addCriterion("eth_wallet_address not like", value, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressIn(List<String> values) {
            addCriterion("eth_wallet_address in", values, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressNotIn(List<String> values) {
            addCriterion("eth_wallet_address not in", values, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressBetween(String value1, String value2) {
            addCriterion("eth_wallet_address between", value1, value2, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andEthWalletAddressNotBetween(String value1, String value2) {
            addCriterion("eth_wallet_address not between", value1, value2, "ethWalletAddress");
            return (Criteria) this;
        }

        public Criteria andBalanceEthIsNull() {
            addCriterion("balance_eth is null");
            return (Criteria) this;
        }

        public Criteria andBalanceEthIsNotNull() {
            addCriterion("balance_eth is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEthEqualTo(BigDecimal value) {
            addCriterion("balance_eth =", value, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBalanceEthNotEqualTo(BigDecimal value) {
            addCriterion("balance_eth <>", value, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBalanceEthGreaterThan(BigDecimal value) {
            addCriterion("balance_eth >", value, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBalanceEthGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_eth >=", value, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBalanceEthLessThan(BigDecimal value) {
            addCriterion("balance_eth <", value, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBalanceEthLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_eth <=", value, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBalanceEthIn(List<BigDecimal> values) {
            addCriterion("balance_eth in", values, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBalanceEthNotIn(List<BigDecimal> values) {
            addCriterion("balance_eth not in", values, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBalanceEthBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_eth between", value1, value2, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBalanceEthNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_eth not between", value1, value2, "balanceEth");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeIsNull() {
            addCriterion("bind_wallet_time is null");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeIsNotNull() {
            addCriterion("bind_wallet_time is not null");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeEqualTo(Date value) {
            addCriterion("bind_wallet_time =", value, "bindWalletTime");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeNotEqualTo(Date value) {
            addCriterion("bind_wallet_time <>", value, "bindWalletTime");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeGreaterThan(Date value) {
            addCriterion("bind_wallet_time >", value, "bindWalletTime");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("bind_wallet_time >=", value, "bindWalletTime");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeLessThan(Date value) {
            addCriterion("bind_wallet_time <", value, "bindWalletTime");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeLessThanOrEqualTo(Date value) {
            addCriterion("bind_wallet_time <=", value, "bindWalletTime");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeIn(List<Date> values) {
            addCriterion("bind_wallet_time in", values, "bindWalletTime");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeNotIn(List<Date> values) {
            addCriterion("bind_wallet_time not in", values, "bindWalletTime");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeBetween(Date value1, Date value2) {
            addCriterion("bind_wallet_time between", value1, value2, "bindWalletTime");
            return (Criteria) this;
        }

        public Criteria andBindWalletTimeNotBetween(Date value1, Date value2) {
            addCriterion("bind_wallet_time not between", value1, value2, "bindWalletTime");
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