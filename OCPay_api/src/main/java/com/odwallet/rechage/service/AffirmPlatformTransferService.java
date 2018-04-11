package com.odwallet.rechage.service;

import com.alibaba.fastjson.JSONObject;
import com.odwallet.common.util.Constants;
import com.odwallet.rechage.dao.PlatformTransferMapper;
import com.odwallet.rechage.entity.MerchantInfo;
import com.odwallet.rechage.entity.PlatformTransfer;
import com.odwallet.rechage.entity.PlatformTransferExample;
import com.odwallet.rechage.entity.TransactionOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by fly on 18/3/24.
 */
@Service
public class AffirmPlatformTransferService {

    Logger logger = LoggerFactory.getLogger(AffirmPlatformTransferService.class);

    @Autowired
    private CheckSuccessRechargeOrderService checkSuccessRechargeOrderService;

    @Autowired
    private PlatformTransferMapper platformTransferMapper;

    @Autowired
    private MerchantInfoService merchantInfoService;


    /**
     * change order status
     * call back
     * log order
     *
     * @param transactionOrder
     */
    public void affirmPlatformTransfer(TransactionOrder transactionOrder) {
        int res = checkSuccessRechargeOrderService.changeTransactionSuccess(transactionOrder);
        if (res > 0) {
            PlatformTransferExample transferExample = new PlatformTransferExample();
            PlatformTransferExample.Criteria criteria = transferExample.createCriteria();
            criteria.andOrderTxHashEqualTo(transactionOrder.getTxHash());
            List<PlatformTransfer> platformTransfers = platformTransferMapper.selectByExample(transferExample);
            if (platformTransfers != null && platformTransfers.size() > 0) {
                PlatformTransfer platformTransfer = new PlatformTransfer();
                PlatformTransfer oldPlatformTransfer = platformTransfers.get(0);
                platformTransfer.setId(oldPlatformTransfer.getId());
                platformTransfer.setStatus(Constants.ORDER_STATUS_SUCCESS);
                platformTransfer.setUpdatedAt(new Date());
                platformTransferMapper.updateByPrimaryKeySelective(platformTransfer);
                MerchantInfo merchantInfo = merchantInfoService.getMerchantInfoById(oldPlatformTransfer.getMerchantId());
                JSONObject callBackJSONObject = new JSONObject();
                callBackJSONObject.put("from", transactionOrder.getFromAddress());
                callBackJSONObject.put("to", transactionOrder.getToAddress());
                callBackJSONObject.put("orderId", oldPlatformTransfer.getOrderId());
                callBackJSONObject.put("coinNum", oldPlatformTransfer.getAmount());
                callBackJSONObject.put("fee", transactionOrder.getFee());
                callBackJSONObject.put("tradingTime", transactionOrder.getTradingTime() == null ? transactionOrder.getCreatedTime() : transactionOrder.getTradingTime());
                callBackJSONObject.put("txHash", transactionOrder.getTxHash());
                try {
                    checkSuccessRechargeOrderService.toCallBack(transactionOrder, merchantInfo, merchantInfo.getTransferCallBackUrl(), callBackJSONObject);
                } catch (Exception e) {
                    logger.error(e.getMessage(), "call back error:" + e);
                }
            }
        }


    }
}
