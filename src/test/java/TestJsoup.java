import utils.HttpUtil;
import utils.RegexUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestJsoup {

    public static void main(String[] args) throws URISyntaxException, IOException {
        String url = "https://www.runoob.com/java/java-map-interface.html";
        String html = HttpUtil.getHtml(url, null, null, 6000, true);

        // 从字符串中解析 HTML
        Document htmlDoc = Jsoup.parse(html);
        System.out.println(htmlDoc.title());
        Elements elements = htmlDoc.select("div[class=\"article-body\"]");
        for (Element element : elements) {
            System.out.println(RegexUtil.filterContent(element.html()));
        }

        // 从链接中解析 HTML
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc.title());
    }

}
