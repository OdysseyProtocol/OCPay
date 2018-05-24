package com.stormfives.ocpay.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
/**
 * http请求工具类
 * @author niuchangqing
 *
 */
public class HttpClientUtils {
	/**
	 * 连接超时时间
	 */
	public static final int CONNECTION_TIMEOUT_MS = 360000;

	/**
	 * 读取数据超时时间
	 */
	public static final int SO_TIMEOUT_MS = 360000;

	public static final String CONTENT_TYPE_JSON_CHARSET = "application/json;charset=gbk";

	public static final String CONTENT_TYPE_XML_CHARSET = "application/xml;charset=gbk";

	/**
	 * httpclient读取内容时使用的字符集
	 */
	public static final String CONTENT_CHARSET = "GBK";

	public static final Charset UTF_8 = Charset.forName("UTF-8");

	public static final Charset GBK = Charset.forName(CONTENT_CHARSET);

	/**
	 * 简单get调用
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String simpleGetInvoke(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException {
		return simpleGetInvoke(url, params,CONTENT_CHARSET);
	}
	/**
	 * 简单get调用
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String simpleGetInvoke(String url, Map<String, String> params,String charset)
			throws ClientProtocolException, IOException, URISyntaxException {

		HttpClient client = buildHttpClient(false);

		HttpGet get = buildHttpGet(url, params);

		HttpResponse response = client.execute(get);
		
		assertStatus(response);

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String returnStr = EntityUtils.toString(entity,charset);
			return returnStr;
		}
		return null;
	}

	/**
	 * 简单post调用
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String simplePostInvoke(String url, Map<String, String> params)
			throws URISyntaxException, ClientProtocolException, IOException {
		return simplePostInvoke(url, params,CONTENT_CHARSET);
	}
	/**
	 * 简单post调用
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String simplePostInvoke(String url, Map<String, String> params,String charset)
			throws URISyntaxException, ClientProtocolException, IOException {

		HttpClient client = buildHttpClient(false);

		HttpPost postMethod = buildHttpPost(url, params);

		HttpResponse response = client.execute(postMethod);

		assertStatus(response);
		
		HttpEntity entity = response.getEntity();

		if (entity != null) {
			String returnStr = EntityUtils.toString(entity, charset);
			return returnStr;
		}

		return null;
	}

	/**
	 * 创建HttpClient
	 * 
	 * @param isMultiThread
	 * @return
	 */
	public static HttpClient buildHttpClient(boolean isMultiThread) {

		CloseableHttpClient client;

		if (isMultiThread)
			client = HttpClientBuilder
					.create()
					.setConnectionManager(
							new PoolingHttpClientConnectionManager()).build();
		else
			client = HttpClientBuilder.create().build();
		// 设置代理服务器地址和端口
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		return client;
	}

	/**
	 * 构建httpPost对象
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 */
	public static HttpPost buildHttpPost(String url, Map<String, String> params)
			throws UnsupportedEncodingException, URISyntaxException {
		Assert.notNull(url, "构建HttpPost时,url不能为null");
		HttpPost post = new HttpPost(url);
		setCommonHttpMethod(post);
		HttpEntity he = null;
		if (params != null) {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				formparams.add(new BasicNameValuePair(key, params.get(key)));
			}
			he = new UrlEncodedFormEntity(formparams, GBK);
			post.setEntity(he);
		}
		// 在RequestContent.process中会自动写入消息体的长度，自己不用写入，写入反而检测报错
		// setContentLength(post, he);
		return post;

	}

	/**
	 * 构建httpGet对象
	 * 
	 * @param url
	 * @return
	 * @throws URISyntaxException
	 */
	public static HttpGet buildHttpGet(String url, Map<String, String> params)
			throws URISyntaxException {
		Assert.notNull(url, "构建HttpGet时,url不能为null");
		HttpGet get = new HttpGet(buildGetUrl(url, params));
		return get;
	}

	/**
	 * build getUrl str
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	private static String buildGetUrl(String url, Map<String, String> params) {
		StringBuffer uriStr = new StringBuffer(url);
		if (params != null) {
			List<NameValuePair> ps = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				ps.add(new BasicNameValuePair(key, params.get(key)));
			}
			uriStr.append("?");
			uriStr.append(URLEncodedUtils.format(ps, UTF_8));
		}
		return uriStr.toString();
	}

	/**
	 * 设置HttpMethod通用配置
	 * 
	 * @param httpMethod
	 */
	public static void setCommonHttpMethod(HttpRequestBase httpMethod) {
		httpMethod.setHeader(HTTP.CONTENT_ENCODING, CONTENT_CHARSET);// setting
																		// contextCoding
//		httpMethod.setHeader(HTTP.CHARSET_PARAM, CONTENT_CHARSET);
		// httpMethod.setHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON_CHARSET);
		// httpMethod.setHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_XML_CHARSET);
	}

	/**
	 * 设置成消息体的长度 setting MessageBody length
	 * 
	 * @param httpMethod
	 * @param he
	 */
	public static void setContentLength(HttpRequestBase httpMethod,
			HttpEntity he) {
		if (he == null) {
			return;
		}
		httpMethod.setHeader(HTTP.CONTENT_LEN, String.valueOf(he.getContentLength()));
	}

	/**
	 * 构建公用RequestConfig
	 * 
	 * @return
	 */
	public static RequestConfig buildRequestConfig() {
		// 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SO_TIMEOUT_MS)
				.setConnectTimeout(CONNECTION_TIMEOUT_MS).build();
		return requestConfig;
	}

	/**
	 * 强验证必须是200状态否则报异常
	 * @param res
	 * @throws HttpException
	 */
 static	void assertStatus(HttpResponse res) throws IOException{
		Assert.notNull(res, "http响应对象为null");
		Assert.notNull(res.getStatusLine(), "http响应对象的状态为null");
		switch (res.getStatusLine().getStatusCode()) {
		case HttpStatus.SC_OK:
			break;
		default:
			throw new IOException("服务器响应状态异常,失败.");
		}
	}
	private HttpClientUtils() {
	}
}
