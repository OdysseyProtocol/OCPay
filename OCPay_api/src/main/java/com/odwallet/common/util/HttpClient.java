package com.odwallet.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangwei on 2017/9/5.
 * httpClient
 */
public class HttpClient {
    /**
     * 请求类型  get/post
     */
    public String METHOD_NAME = "GET";
    /**
     * 请求的地址 url
     */
    private String URL = "";
    /**
     * HttpHost 代理对象
     */
    private HttpHost httpHost = null;
    /**
     * HTTP身份认证 用户凭证
     */
    private CredentialsProvider credsProvider = null;
    /**
     * clinet上下文对象
     * 用来应用程序经常需要通过一些逻辑相关的请求-响应交换来保持状态信息。
     * 为了使应用程序能够维持一个过程状态，
     * HttpClient允许HTTP请求在一个特定的执行上下文中来执行--称为HTTP上下文
     */
    private HttpClientContext httpClientContext = null;
    /**
     * httpclient的请求对象  线程池
     */
    private CloseableHttpClient httpclient = null;
    /**
     * 用回来获取请求响应的对象
     */
    private CloseableHttpResponse closeableHttpResponse = null;
    /**
     * get请求对象
     */
    private HttpGet httpGet = null;
    /**
     * post请求对象
     */
    private HttpPost httpPost = null;
    /**
     * 服务器响应超时 单位毫秒
     */
    private int socketTimeout = 20000;
    /**
     * 连接被阻塞超时 单位毫秒
     */
    private int connectTimeout = 20000;
    /**
     * 请求超时的时间 单位毫秒
     */
    private int connectionRequestTimeout = 20000;
    /**
     * 最大不要超过1000
     */
    private static int maxConnTotal = 10;
    /**
     * 实际的单个连接池大小，如tps定为50，那就配置50
     */
    private static int maxConnPerRoute = 20;
    private String accept = "application/json";
    private String referer = "";
    private String contentType = "application/json";
    private String origin = "";
    private String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
    private boolean autoSaveReferer;
 
    /**
     * 自动保存上一个请求源 true 自动保存/false 不自动保存（默认）
     */
    public void setAutoSaveReferer(boolean autoSaveReferer) {
        this.autoSaveReferer = autoSaveReferer;
    }
 
    /**
     * 先前网页的地址
     */
    public void setReferer(String referer) {
        this.referer = referer;
    }
 
    /**
     * 客户端能够接收的内容类型
     */
    public void setAccept(String accept) {
        this.accept = accept;
    }
 
    /**
     * 请求的与实体对应的MIME信息
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
 
    /**
     * 请求的来源-post使用，等同于Referer头
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
 
    /**
     * 注明客户端请求的触发器版本
     *
     * @param userAgent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
 
    /**
     * 服务器响应超时 单位毫秒
     *
     * @param socketTimeout
     */
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
 
 
    /**
     * 连接被阻塞超时 单位毫秒
     *
     * @param connectTimeout
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
 
    /**
     * 请求超时的时间 单位毫秒
     *
     * @param connectionRequestTimeout
     */
    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }
 
 
    /**
     * 最大的请求连接数 注意:最大不要超过1000
     *
     * @param maxConnTotal
     */
    public static void setMaxConnTotal(int maxConnTotal) {
        HttpClient.maxConnTotal = maxConnTotal;
    }
 
 
    /**
     * 实际的单个连接池大小，如tps定为50，那就配置50
     *
     * @param maxConnPerRoute
     */
    public static void setMaxConnPerRoute(int maxConnPerRoute) {
        HttpClient.maxConnPerRoute = maxConnPerRoute;
    }
 
    private boolean isAutoSaveReferer() {
        return autoSaveReferer;
    }
 
 
    /**
     * 构造函数 无参
     */
    public HttpClient() {
        initHttpRequest();
    }
 
    /**
     * 构造函数 初始化包含UR
     *
     * @param URL
     */
    public HttpClient(String URL) {
        this.URL = URL;
        initHttpRequest();
    }
 
    /**
     * 构造函数 初始化包含URL和请求类型
     *
     * @param URL
     * @param METHOD_NAME
     */
    public HttpClient(String URL, String METHOD_NAME) {
        this.URL = URL;
        this.METHOD_NAME = METHOD_NAME;
        initHttpRequest();
    }
 
    /**
     * 初始化htpclinet的请求对象
     */
    public void initHttpRequest() {
        if (METHOD_NAME.equalsIgnoreCase("get")) {
            if (httpGet == null) {
                if (StringUtil.isNotEmpty(URL)) {
                    httpGet = new HttpGet(URL);
                } else {
                    httpGet = new HttpGet();
                }
            } else {
                try {
                    httpGet.setURI(new java.net.URI(URL));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            httpGet.setHeader("Accept", accept);
            httpGet.setHeader("User-Agent", userAgent);
            if (StringUtil.isNotEmpty(contentType)) {
                httpGet.setHeader("Content-Type", contentType);
            }
            if (StringUtil.isNotEmpty(referer)) {
                httpGet.setHeader("Referer", referer);
            }
        } else if (METHOD_NAME.equalsIgnoreCase("post")) {
            if (httpPost == null) {
                if (StringUtil.isNotEmpty(URL)) {
                    httpPost = new HttpPost(URL);
                } else {
                    httpPost = new HttpPost();
                }
            } else {
                try {
                    httpPost.setURI(new java.net.URI(URL));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            httpPost.setHeader("Accept", accept);
            if (StringUtil.isNotEmpty(contentType)) {
                httpPost.setHeader("Content-Type", contentType);
            }
            httpPost.setHeader("Origin", origin);
            httpPost.setHeader("User-Agent", userAgent);
            if (StringUtil.isNotEmpty(referer)) {
                httpPost.setHeader("Referer", referer);
            }
        }
        initHttpClinet();
    }
 
    /**
     * 设置提交方式 get/post
     *
     * @param method
     * @throws Exception
     */
    public void setMethod(String method) throws Exception {
        try {
            if (StringUtil.isNotEmpty(method)) {
                METHOD_NAME = method;
                initHttpRequest();
            } else {
                throw new Exception("参数不能为空,method is null");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
 
    /**
     * 关闭client连接对象 释放对象 并重新初始化
     *
     * @throws Exception
     */
    public void closeClient(boolean isInit) {
        if (httpclient != null) {
            try {
                // 关闭请求
                httpclient.getConnectionManager().shutdown();
                HttpClientUtils.closeQuietly(httpclient);
                httpclient = null;
                if (closeableHttpResponse != null) {
                    closeableHttpResponse.close();//防止线程池一直占用,无法释放内存,导致后面的请求响应堵塞
                }
                if (httpGet != null) {
                    httpGet = null;
                }
                if (httpPost != null) {
                    httpPost = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (isInit) {//如果是异常关的httpclient对象一定要重新初始化对象,以免访问出现空指针
                    initHttpClinet();
                }
            }
        }
    }
 
    /**
     * 初始化clinet请求对象-并初始化HTTP/HTTPS两种协议
     */
    public void initHttpClinet() {
        if (httpclient == null) {
            try {
//                System.setProperty("jsse.enableSNIExtension", "false");
                //采用绕过验证的方式处理https请求
                SSLContext sslcontext = createIgnoreVerifySSL();
                // 设置协议http和https对应的处理socket链接工厂的对象
                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.INSTANCE)
                        .register("https", new SSLConnectionSocketFactory(sslcontext))
                        .build();
                Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
                Protocol.registerProtocol("https", myhttps);
                PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                RequestConfig defaultRequestConfig = RequestConfig.custom()
                        .setSocketTimeout(socketTimeout)
                        .setConnectTimeout(connectTimeout)
                        .setConnectionRequestTimeout(connectionRequestTimeout)
                        .setStaleConnectionCheckEnabled(true)
                        .build();
 
                httpclient = HttpClients.custom()
                        .setDefaultRequestConfig(defaultRequestConfig).setMaxConnTotal(maxConnTotal)
                        .setMaxConnPerRoute(maxConnPerRoute).setConnectionManager(connManager)
                        .build();
               /* .setSSLSocketFactory(new SSLConnectionSocketFactory(sslcontext))*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 请求数据并返回字符串类型
     *
     * @return
     * @throws Exception
     */
    public String request() throws Exception {
        try {
            HttpEntity entity = request(URL);
            InputStream inputStream = entity.getContent();
            if (inputStream != null) {
                return readLineInputStream("utf-8", inputStream);
            } else {
                return "";
            }
        } catch (Exception e) {
            throw new Exception(msgException(e));
        } finally {
            closeHttpResponse();
        }
    }
 
    /**
     * 请求数据并返回一个数据实体
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpEntity request(String url) throws Exception {
        try {
            if (StringUtil.isNotEmpty(url)) {
                URL = url;
            } else {
                throw new Exception("参数不能为空,url is null");
            }
            if (StringUtil.isEmpty(origin)) {
                origin = URL;
            }
            initHttpRequest();
            if (httpHost != null) {
                closeableHttpResponse = httpclient.execute(httpHost, METHOD_NAME.equalsIgnoreCase("get") ? httpGet : httpPost);
            } else if (httpHost != null && httpClientContext != null) {
                closeableHttpResponse = httpclient.execute(httpHost, METHOD_NAME.equalsIgnoreCase("get") ? httpGet : httpPost, httpClientContext);
            } else {
                closeableHttpResponse = httpclient.execute(METHOD_NAME.equalsIgnoreCase("get") ? httpGet : httpPost);
            }
            if (isAutoSaveReferer()) {
                setReferer(URL);
            }
            return closeableHttpResponse.getEntity();
        } catch (Exception e) {
            closeClient(true); //如果出现异常了就进行请求释放,防止异常未捕获导致回收失败
            e.printStackTrace();
            throw new Exception(msgException(e));
        } finally {
            if (httpPost != null)
                httpPost.setEntity(null);
        }
    }
 
 
    /**
     * 请求数据并返回数据 自定义流转文本编码格式
     *
     * @param url
     * @param utf
     * @return
     * @throws Exception
     */
    public String request(String url, String utf) throws Exception {
        try {
            utf = StringUtil.isEmpty(utf) ? "utf-8" : utf;
            HttpEntity entity = request(url);
            InputStream inputStream = entity.getContent();
            if (inputStream != null) {
                return readLineInputStream(utf, inputStream);
            } else {
                return "";
            }
        } catch (Exception e) {
            throw new Exception(msgException(e));
        } finally {
            closeHttpResponse();
        }
    }
 
    /**
     * 请求并返回byte数组数据
     *
     * @param url
     * @param numBer
     * @return
     * @throws Exception
     */
    public byte[] request(String url, Integer numBer) throws Exception {
        try {
            return toByteArrays(request(url).getContent(), numBer);
        } catch (EOFException e) {
            throw new Exception(e.getMessage());
        } finally {
            closeHttpResponse();
        }
    }
 
    /**
     * 文件流转byte数组
     *
     * @param in
     * @param numBer
     * @return
     * @throws Exception
     */
    public byte[] toByteArrays(InputStream in, Integer numBer) throws Exception {
        numBer = numBer == null ? 8 : numBer;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * numBer];
        int i = 0;
        while ((i = in.read(buffer)) != -1) {
            out.write(buffer, 0, i);
        }
        return out.toByteArray();
    }
 
    /**
     * 重载读取流
     *
     * @param utf
     * @param httpEntity
     * @return
     * @throws IOException
     */
    public String readLineInputStream(String utf, HttpEntity httpEntity) throws IOException {
        return readLineInputStream(utf, httpEntity.getContent());
    }
 
    /**
     * 重载读取流
     *
     * @param utf
     * @param inputStream
     * @return
     * @throws IOException
     */
    public String readLineInputStream(String utf, InputStream inputStream) throws IOException {
        BufferedReader bf = null;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(inputStream,
                    utf);
            bf = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = bf.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bf.close();
            isr.close();
            inputStream.close();
        }
        return "";
    }
 
    /**
     * 重载读取流
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public String readLineInputStream(InputStream inputStream) throws IOException {
        return readLineInputStream("utf-8", inputStream);
    }
 
    /**
     * 重载读取流
     *
     * @param httpEntity
     * @return
     * @throws IOException
     */
    public String readLineInputStream(HttpEntity httpEntity) throws Exception {
        try {
            if (httpEntity != null) {
                return readLineInputStream("utf-8", httpEntity.getContent());
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("读取文件流异常:" + e.getMessage());
        }
    }
 
    /**
     * 设置cookie
     *
     * @param cookie
     */
    public void setCookie(String cookie) {
        if (METHOD_NAME.equalsIgnoreCase("get")) {
            httpGet.setHeader("Cookie", cookie);
        } else if (METHOD_NAME.equalsIgnoreCase("post")) {
            httpPost.setHeader("Cookie", cookie);
        }
    }
 
    public void setHeader(String headerName, String headerVal) throws Exception {
        setHeader(new CommonMap<String, String>(headerName, headerVal));
    }
 
    /**
     * 设置Header
     *
     * @param header
     */
    public void setHeader(Map<String, String> header) throws Exception {
        try {
            if (header != null) {
                for (String hd : header.keySet()) {
                    if (METHOD_NAME.equalsIgnoreCase("get")) {
                        httpGet.setHeader(hd, header.get(hd));
                    } else if (METHOD_NAME.equalsIgnoreCase("post")) {
                        httpPost.setHeader(hd, header.get(hd));
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("setHeader 异常:" + e.getMessage());
        }
    }
 
    /**
     * 设置post请求参数 string
     *
     * @param params
     * @throws Exception
     */
    public void setEntity(String params) throws Exception {
        setEntity(getUrlParams(params));
    }
 
    /**
     * 设置post请求参数 map
     *
     * @param params
     * @throws Exception
     */
    public void setEntity(Map<String, String> params) throws Exception {
        try {
            if (METHOD_NAME.equalsIgnoreCase("post")) {
                httpPost.setEntity(new ByteArrayEntity(JSONObject.toJSON(params).toString().getBytes("utf-8")));
            }
        } catch (Exception e) {
            throw new Exception("setEntity 异常:" + e.getMessage());
        }
    }
 
    /**
     * 设置post请求参数 map
     *
     * @param json
     * @throws Exception
     */
    public void setEntity(StringEntity json) throws Exception {
        try {
            if (METHOD_NAME.equalsIgnoreCase("post")) {
                httpPost.setEntity(json);
 
            }
        } catch (Exception e) {
            throw new Exception("setEntity 异常:" + e.getMessage());
        }
    }
 
    /**
     * 获取返回值的类型
     *
     * @return
     */
    public Header getContentType() throws Exception {
        if (closeableHttpResponse == null || closeableHttpResponse.getEntity() == null) {
            throw new Exception("java.lang.NullPointerException: closeableHttpResponse 为空,请先初始化对象。");
        }
        return closeableHttpResponse.getEntity().getContentType();
    }
 
    /**
     * 获取响应的实体类型 并获取具体类型:jpeg/html/png/gif......
     *
     * @param isFormat
     * @return
     */
    public String getContentType(boolean isFormat) {
        String contentType = "";
        try {
            if (isFormat) {
                contentType = getContentType().toString();
                if (StringUtil.isNotEmpty(contentType)) {
                    int endIndex = contentType.length();
                    if (contentType.indexOf(";") != -1) {
                        endIndex = contentType.indexOf(";");
                    }
                    contentType = StringUtil.isNotEmpty(contentType) ? contentType.substring(contentType.indexOf("/") + 1, endIndex) : "";
                }
            } else {
                return getContentType().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentType;
    }
 
    /**
     * 获取返回值的状态
     * 100  客户端可以继续
     * 101  指示服务器正根据 Upgrade 头切换协议
     * 200  请求正常成功
     * 201  指示请求成功并在服务器上创建了一个新资源
     * 202  指示已接受请求进行处理但处理尚未完成
     * 203  指示客户端呈现的元信息并不源自服务器
     * 204  指示请求成功但没有返回新信息
     * 205  指示代理应该 重置导致请求被发送的文档视图
     * 206  指示服务器已完成对资源的部分 GET 请求
     * 300  请求的资源对应于表示形式集合中的某种表示形式，每种表示形式都有自己的特定位置
     * 301  指示已经将资源永久地移动到了某个新位置，并且将来的引用应将新 URI 用于其请求
     * 302  指示已经将资源暂时地移动到了另一个位置，但将来的引用仍应使用原来的 URI 访问该资源。 保留此定义是为了向后兼容。SC_FOUND 现在是首选定义
     * 303  指示可在另一个 URI 之下找到该请求的响应
     * 304  指示条件 GET 操作发现资源可用但不可修改
     * 305  指示必须 通过 Location 字段给定的代理访问请求资源
     * 307  指示请求的资源暂时驻留在另一个 URI 之下。临时 URI 应该 通过响应中的 Location 字段提供
     * 400  指示客户端发送的请求在语法上不正确
     * 401  指示请求需要进行 HTTP 验证
     * 402  保留此代码以备将来使用
     * 403  指示服务器理解请求但拒绝完成它
     * 404  指示请求的资源不可用
     * 405  指示 Request-Line 中指定的方法不支持 Request-URI 标识的资源
     * 406  指示请求标识的资源只能生成响应实体，根据请求中发送的 accept 头，这些响应实体具有不可接受的内容特征
     * 407  指示客户端必须 首先通过代理验证其自身
     * 408  指示客户端没有在服务器准备等待的时间内生成请求
     * 409  指示由于与当前资源状态冲突请求无法完成
     * 410  指示资源在服务器上不再可用并且不知道转发地址。应该 认为此条件是永久性的
     * 411  指示在没有定义 Content-Length 的情况下无法处理请求
     * 412  指示在服务器上测试一个或多个请求头字段中给出的前提时，该前提被求值为 false
     * 413  指示因为请求实体大于服务器愿意或能够处理的实体，所以服务器拒绝处理请求
     * 414  指示因为 Request-URI 的长度大于服务器愿意解释的 Request-URI 长度，所以服务器拒绝为请求提供服务
     * 415  指示因为请求实体的格式不受请求方法的请求资源支持，所以服务器拒绝为请求提供服务
     * 416  指示服务器无法服务于请求的字节范围
     * 417  指示服务器无法服务于请求的字节范围
     * 500  指示 HTTP 服务器内存在错误使服务器无法完成请求
     * 501  指示 HTTP 服务器不支持完成请求所需的功能
     * 502  指示 HTTP 服务器在充当代理或网关时从它参考的服务器接收到一个无效响应
     * 503  指示 HTTP 服务器暂时过载，并且无法处理请求
     * 504  指示服务器在充当网关或代理时没有从上游服务器接收到及时的响应
     * 505  指示服务器不支持或拒绝支持请求消息中使用的 HTTP 协议版本
     *
     * @return
     */
    public int getStatusCode() throws Exception {
        if (closeableHttpResponse == null || closeableHttpResponse.getStatusLine() == null) {
            throw new Exception("java.lang.NullPointerException: closeableHttpResponse 为空,请先初始化对象。");
        }
        try {
            return closeableHttpResponse.getStatusLine().getStatusCode();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
 
    public CloseableHttpResponse getHttpResponse() throws Exception {
        try {
            return closeableHttpResponse;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
 
    /**
     * 关闭线程池，防止堵塞（这里需要手动调用,由于这里吧响应和请求封装在一个类了所以只能在外面手动调用释放）
     *
     * @throws IOException
     */
    public void closeHttpResponse() throws IOException {
        if (closeableHttpResponse != null) {
            closeableHttpResponse.close();//防止线程池一直占用,无法释放内存,导致后面的请求响应堵塞
        }
    }
 
    /**
     * 关闭线程池，防止堵塞（这里需要手动调用,由于这里吧响应和请求封装在一个类了所以只能在外面手动调用释放）
     * 如果出现发送httpclient请求后一直无响应,应该是线程池占用完了,所以每次调用request后记得在finally手动调用本方法CloseHttpResponse
     *
     * @param entity 访问流结束后需要传递实体流进来并释放掉
     * @throws IOException
     */
    public void closeHttpResponse(HttpEntity entity) throws IOException {
        if (closeableHttpResponse != null) {
            EntityUtils.consume(entity);
            closeableHttpResponse.close();//防止线程池一直占用,无法释放内存,导致后面的请求响应堵塞
        }
    }
 
    /**
     * 服务器认证信息
     *
     * @param userName 登录信息的用户名
     * @param userPwd  登录信息的密码
     * @throws Exception
     */
    public void setCredentials(String userName, String userPwd) throws Exception {
        try {
            if (httpHost == null) {
                throw new Exception("java.lang.NullPointerException: httpHost 为空,请先初始化对象。");
            }
            credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope(httpHost.getHostName(), httpHost.getPort()),
                    new UsernamePasswordCredentials(userName, userPwd));
            // 创建 AuthCache 对象
            AuthCache authCache = new BasicAuthCache();
            //创建 BasicScheme，并把它添加到 auth cache中
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(httpHost, basicAuth);
            // 把AutoCache添加到上下文中
            httpClientContext = HttpClientContext.create();
            httpClientContext.setCredentialsProvider(credsProvider);
            httpClientContext.setAuthCache(authCache);
        } catch (Exception e) {
            throw new Exception(msgException(e));
        }
    }
 
 
    /**
     * 设置HTTP请求代理模式
     * 格式:127.0.0.0:8888
     *
     * @param hostVal
     * @throws Exception
     */
    public void setHttpHost(String hostVal) throws Exception {
        if (StringUtil.isNotEmpty(hostVal) && hostVal.indexOf(":") != -1) {
            try {
                String ip = hostVal.substring(0, hostVal.indexOf(":"));
                String host = hostVal.substring(hostVal.indexOf(":") + 1);
                if (StringUtil.isNotEmpty(ip) && isIP(ip) && StringUtil.isNotEmpty(host)) {
                    httpHost = new HttpHost(ip, Integer.parseInt(host));
                }
            } catch (Exception e) {
                throw new Exception(msgException(e));
            }
        } else {
            throw new Exception("httphost 参数内容有误,正确示范:127.0.1.1:8888");
        }
    }
 
 
    /**
     * 通过map对象转换为数据实体
     *
     * @param params
     * @return
     */
    private static List<NameValuePair> getNameValuePairs(Map<String, String> params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null && params.keySet().size() > 0) {
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                nvps.add(new BasicNameValuePair((String) entry.getKey(),
                        (String) entry.getValue()));
            }
        }
        return nvps;
    }
 
    /**
     * json url Transformation map json参数标准化为map对象
     *
     * @param param
     * @return
     */
    public static Map<String, String> getUrlParams(String param) {
        Map<String, String> map = new HashMap<String, String>(0);
        if (param == null || param.length() <= 0) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length != 0) {
                map.put(p[0], p.length == 2 && p[1] != null && p[1].length() != 0 ? p[1] : "");
            }
        }
        return map;
    }
 
    /**
     * 判断是否为IP对象
     *
     * @param addr
     * @return
     */
    public boolean isIP(String addr) throws Exception {
        try {
            if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
                return false;
            }
            /**
             * 判断IP格式和范围
             */
            String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
 
            Pattern pat = Pattern.compile(rexp);
 
            Matcher mat = pat.matcher(addr);
 
            boolean ipAddress = mat.find();
 
            return ipAddress;
        } catch (Exception e) {
            throw new Exception("isIP exception 验证IP失败....." + msgException(e));
        }
    }
 
    /**
     * 判断是否是图片类型的文件
     *
     * @param html
     */
    public boolean isImage(String html) {
        if (StringUtil.isNotEmpty(html)) {
            if (ImageType.BMP.toString().equalsIgnoreCase(html)
                    || ImageType.gif.toString().equalsIgnoreCase(html)
                    || ImageType.jpeg.toString().equalsIgnoreCase(html)
                    || ImageType.png.toString().equalsIgnoreCase(html)) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
 
    /**
     * 判断是否是图片类型的文件
     */
    public boolean isImage() {
        try {
            String requestType = getContentType(true);
            if (StringUtil.isNotEmpty(requestType)) {
                return isImage(requestType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
    /**
     * 图片类型的枚举
     */
    public enum ImageType {
        png, gif, jpg, jpeg, BMP,
    }
 
    /**
     * 绕过验证-HTTPS
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sc = SSLContext.getInstance("SSLv3");
 
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
 
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
 
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
 
        sc.init(null, new TrustManager[]{trustManager}, null);
 
        return sc;
    }
 
    /**
     * 打印错误的异常详情
     *
     * @param e
     * @return
     */
    public String msgException(Exception e) {
        String msg = "";
        if (e != null && e.getStackTrace().length != 0) {
            StackTraceElement stackTraceElement = e.getStackTrace()[0];// 得到异常棧的首个元素
            msg = String.format("出现异常的类文件:%s,错误行数:%s,错误方法体:%s", stackTraceElement.getFileName(), stackTraceElement.getLineNumber(), stackTraceElement.getMethodName());
          /*  System.out.println("File="+stackTraceElement.getFileName());// 打印文件名
            System.out.println("Line="+stackTraceElement.getLineNumber());// 打印出错行号
            System.out.println("Method="+stackTraceElement.getMethodName());// 打印出错方法*/
        }
        return msg;
    }
 
 
}