package com.ocpay.wallet.utils.web3j.response;

import com.ocpay.wallet.utils.TokenUtils;
import com.snow.commonlibrary.utils.StringUtil;

import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static com.ocpay.wallet.utils.CommonUtils.Hex2Decimal;
import static org.web3j.abi.Utils.convert;

public class TokenBalanceResponse {

    public String jsonrpc;
    public String result;
    public String id;
    public ErrorInfo error;
    public String tokenName;


    public BigInteger getDecimalFromDex() {
        if (result.startsWith("0x")) {
            return Hex2Decimal(result);
        }
        if (!StringUtil.isNumber(result) || StringUtil.isEmpty(result) || !result.startsWith("0x"))
            return new BigInteger("0");
        return new BigInteger(result);
    }

    public String getResult() {
        return result;
    }


    public BigDecimal getTokenBalance() {
        if (TokenUtils.ETH.equals(tokenName)) {
            return getEthBalance();
        }
        if (result.startsWith("0x") && result.length() == 66) {
            List<TypeReference<?>> typeReferences = Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
            });
            List<Type> decode = FunctionReturnDecoder.decode(result, convert(typeReferences));
            BigDecimal ethbal = new BigDecimal(decode.get(0).getValue().toString());
            BigDecimal divide = ethbal.divide(new BigDecimal(1000000000000000000d), 4, BigDecimal.ROUND_UP);
            return divide;
        }
        return new BigDecimal(0);
    }


    public BigDecimal getEthBalance() {
        if (!result.startsWith("0x")) {
            return new BigDecimal(result).divide(new BigDecimal(1000000000000000000d), 5, BigDecimal.ROUND_UP);

        }
        return new BigDecimal(0);
    }


    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}