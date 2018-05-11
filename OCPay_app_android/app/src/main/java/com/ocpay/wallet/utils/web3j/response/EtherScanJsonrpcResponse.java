package com.ocpay.wallet.utils.web3j.response;

import com.snow.commonlibrary.utils.StringUtil;

import java.math.BigInteger;

import static com.ocpay.wallet.utils.CommonUtils.Hex2Decimal;

public class EtherScanJsonrpcResponse {
    public String jsonrpc;
    public String result;
    public String id;


    public BigInteger getDicemalFromDex() {
        if (!StringUtil.isNumber(result) || StringUtil.isEmpty(result) || !result.startsWith("0x"))
            return new BigInteger("0");
        if (result.startsWith("0x")) {
            return Hex2Decimal(result);
        }
        return new BigInteger(result);
    }
}
