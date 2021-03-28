import Utils.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class TestHttpUtil {

    public static void main(String[] args) throws URISyntaxException, UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("key1", "value1");
        params.put("key2", "value2");

        // 测试 GET 方法
        String getUrl = "https://httpbin.org/get";

        String html = HttpUtil.getHtml(getUrl, params, null, 6000, false);
        System.out.println(html);

        // 测试 POST 方法
        String postUrl = "https://httpbin.org/post";

        html = HttpUtil.postHtml(postUrl, params, null, 6000, false);
        System.out.println(html);

    }

}
