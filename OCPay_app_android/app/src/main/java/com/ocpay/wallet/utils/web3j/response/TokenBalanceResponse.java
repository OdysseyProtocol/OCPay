package com.ocpay.wallet.utils.web3j.response;

public class TokenBalanceResponse {

    private String jsonrpc;
    private String result;
    private int id;

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}