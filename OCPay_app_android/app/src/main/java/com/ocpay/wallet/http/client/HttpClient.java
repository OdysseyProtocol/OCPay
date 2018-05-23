package com.ocpay.wallet.http.client;

import com.ocpay.wallet.http.utils.BuildFactory;

import static com.ocpay.wallet.Constans.HTTP.API_DATA_BLOCK;
import static com.ocpay.wallet.Constans.HTTP.API_ETHSCAN;


public interface HttpClient {

    class Builder {
        public static EthScanHttpClient getEthScanServer() {
            return BuildFactory.getInstance().create(EthScanHttpClient.class, API_ETHSCAN);
        }

        public static DataBlockClient getDataBlockServer() {
            return BuildFactory.getInstance().create(DataBlockClient.class, API_DATA_BLOCK);
        }

    }
}