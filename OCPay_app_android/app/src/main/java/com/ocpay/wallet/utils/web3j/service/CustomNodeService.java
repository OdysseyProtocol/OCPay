package com.ocpay.wallet.utils.web3j.service;

import org.web3j.protocol.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by y on 2018/3/6.
 */
public class CustomNodeService extends Service {

    public static final MediaType JSON_MEDIA_TYPE
            = MediaType.parse("application/json; charset=utf-8");

    public static final String DEFAULT_URL = "http ://h.stormfives.com:34378";

//    public static final String DEFAULT_URL = Constants.BLOCK_NODE_URL;


    private OkHttpClient httpClient;

    private final String url;

    private final boolean includeRawResponse;

    private HashMap<String, String> headers = new HashMap<>();

    public CustomNodeService(String url, OkHttpClient httpClient, boolean includeRawResponses) {
        super();
        this.url = url;
        this.httpClient = httpClient;
        this.includeRawResponse = includeRawResponses;
    }

    public CustomNodeService(OkHttpClient httpClient, boolean includeRawResponses) {
        this(DEFAULT_URL, httpClient, includeRawResponses);
    }

    private CustomNodeService(String url, OkHttpClient httpClient) {
        this(url, httpClient, false);
    }

    public CustomNodeService(String url) {
        this(url, createOkHttpClient());
    }

    public CustomNodeService(String url, boolean includeRawResponse) {
        this(url, createOkHttpClient(), includeRawResponse);
    }

    public CustomNodeService(OkHttpClient httpClient) {
        this(DEFAULT_URL, httpClient);
    }

    public CustomNodeService(boolean includeRawResponse) {
        this(DEFAULT_URL, includeRawResponse);
    }

    public CustomNodeService() {
        this(DEFAULT_URL);
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        configureLogging(builder);
        return builder.build();
    }

    private static void configureLogging(OkHttpClient.Builder builder) {
        //todo show log
        if (false) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(null);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
    }

    protected InputStream performIO(String request) throws IOException {

        RequestBody requestBody = RequestBody.create(JSON_MEDIA_TYPE, request);
        Headers headers = buildHeaders();

        Request httpRequest = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(requestBody)
                .build();

        Response response = httpClient.newCall(httpRequest).execute();
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                return buildInputStream(responseBody);
            } else {
                return null;
            }
        } else {
            //todo  throw exception
//            throw new ClientConnectionException(
//                    "Invalid response received: " + response.body());
            return null;
        }
    }

    private InputStream buildInputStream(ResponseBody responseBody) throws IOException {
        InputStream inputStream = responseBody.byteStream();

        if (includeRawResponse) {
            // we have to buffer the entire input payload, so that after processing
            // it can be re-read and used to populate the rawResponse field.

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body
            Buffer buffer = source.buffer();

            long size = buffer.size();
            if (size > Integer.MAX_VALUE) {
                throw new UnsupportedOperationException(
                        "Non-integer input buffer size specified: " + size);
            }

            int bufferSize = (int) size;
            BufferedInputStream bufferedinputStream =
                    new BufferedInputStream(inputStream, bufferSize);

            bufferedinputStream.mark(inputStream.available());
            return bufferedinputStream;

        } else {
            return inputStream;
        }
    }

    private Headers buildHeaders() {
        return Headers.of(headers);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void addHeaders(Map<String, String> headersToAdd) {
        headers.putAll(headersToAdd);
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    @Override
    public <T extends org.web3j.protocol.core.Response> T send(org.web3j.protocol.core.Request request, Class<T> responseType) throws IOException {
        return null;
    }
}