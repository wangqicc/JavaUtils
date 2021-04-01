package Utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class HttpUtil {

    // 连接池管理
    private static final PoolingHttpClientConnectionManager connManager;

    // 编码方式
    private static final String ENCODING = "UTF-8";

    // 报错返回结果
    private static final String RESULT = "-1";

    // 请求头
    private static final Map<String, String> HEADERS;

    static {
        // 设置请求头
        HEADERS = new HashMap<String, String>();
        HEADERS.put("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2224.3 Safari/537.36");
        HEADERS.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        HEADERS.put("Referer", "");
        HEADERS.put("Cookie", "");
        connManager = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        connManager.setMaxTotal(200);
        // 设置每个主机并发数
        connManager.setDefaultMaxPerRoute(10);
    }

    /**
     * 将字典类型的参数转换为 NameValuePair 数组类型的参数列表
     * @param params        字典类型参数
     * @return              NameValuePair 数组
     */
    private static List<NameValuePair> getNameValueParams(Map<String, String> params) {
        List<NameValuePair> paramList = new LinkedList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return paramList;
    }

    /**
     * 建立 HTTP 连接
     * @param timeout        超时时间，单位 ms
     * @return               HTTP 对象
     */
    private static CloseableHttpClient getHttpClient(Integer timeout) {
        // 配置请求参数
        RequestConfig requestConfig = RequestConfig.custom()
                // 设置请求超时时间
                .setConnectTimeout(timeout)
                // 设置连接超时时间
                .setConnectionRequestTimeout(timeout)
                // 设置响应超时时间
                .setSocketTimeout(timeout)
                // 设置缓存格式，建议设置为标准格式 CookieSpecs.STANDARD
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        // 配置超时回调机制
        HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
                return false;
            }
        };
        return HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler(retryHandler)
                .build();
    }

    /**
     * 获取 get 方式的 html 页面内容
     * @param url        页面链接
     * @param params     请求参数
     * @param headers    请求头
     * @param timeout    超时时间
     * @param isStream   是否以文件流形式获取
     * @return           返回的页面内容
     * @throws URISyntaxException        链接格式异常
     */
    public static String getHtml(String url, Map<String, String> params, Map<String, String> headers, Integer timeout, boolean isStream) throws URISyntaxException {

        // 构建 URL
        URIBuilder uriBuilder = new URIBuilder(url);
        // 添加参数
        if (null != params) {
            uriBuilder.addParameters(getNameValueParams(params));
        }
        // 创建 GET 请求
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        // 添加请求头信息
        if (null == headers) {
            headers = HEADERS;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        return getResult(httpGet, timeout, isStream, "GET");
    }

    /**
     * 获取 post 方式的 html 页面内容
     * @param url        页面链接
     * @param params     请求参数
     * @param headers    请求头
     * @param timeout    超时时间
     * @param isStream   是否以文件流形式获取
     * @return           返回页面内容
     * @throws UnsupportedEncodingException        编码异常
     */
    public static String postHtml(String url, Map<String, String> params, Map<String, String> headers, Integer timeout, boolean isStream) throws UnsupportedEncodingException {

        // 创建 POST 请求
        HttpPost httpPost = new HttpPost(url);
        // 添加请求头信息
        if (null == headers) {
            headers = HEADERS;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        // 添加参数
        if (null != params) {
            httpPost.setEntity(new UrlEncodedFormEntity(getNameValueParams(params), ENCODING));
        }
        return getResult(httpPost, timeout, isStream, "POST");
    }

    /**
     * 返回请求结果
     * @param httpRequest        HTTP 请求
     * @param timeout            超时时间
     * @param isStream           是否以文件流形式获取
     * @param method             采用的方式 GET or POST
     * @return                   请求的内容
     */
    private static String getResult(HttpRequestBase httpRequest, Integer timeout, boolean isStream, String method) {
        // 响应结果
        StringBuffer sb = null;

        CloseableHttpResponse response = null;

        try {
            // 获取 HTTP 连接
            CloseableHttpClient httpClient = getHttpClient(timeout);
            // 发起请求
            response = httpClient.execute(httpRequest);
            int responseCode = response.getStatusLine().getStatusCode();
            // 处理重定向
            if (302 == responseCode) {
                String locationUrl = response.getLastHeader("Location").getValue();
                if ("GET".equals(method)) {
                    return getResult(new HttpGet(locationUrl), timeout, isStream, method);
                } else {
                    return getResult(new HttpPost(locationUrl), timeout, isStream, method);
                }
            }
            // 处理成功
            if (200 == responseCode) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                sb = new StringBuffer();
                // 以文件流形式获取
                if (isStream) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), ENCODING));
                    String len;
                    while (null != (len = br.readLine())) {
                        sb.append(len);
                    }
                } else {
                    sb.append(EntityUtils.toString(entity, ENCODING));
                    if (sb.length() < 1) {
                        sb.append("-1");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    System.err.println("关闭响应连接出错");
                    e.printStackTrace();
                }
            }
        }
        return sb == null ? RESULT : ("".equals(sb.toString().trim()) ? "-1" : sb.toString());
    }

}
