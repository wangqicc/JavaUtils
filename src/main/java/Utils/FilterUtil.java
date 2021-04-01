package Utils;

import java.util.regex.Pattern;

public class FilterUtil {

    /**
     * 过滤 HTML 标签
     * @param str        字符串
     * @return           过滤后的字符串
     */
    public static String filterContent(String str) {
        // 多个空白字符替换为单个
        str = Pattern.compile("\\s+").matcher(str).replaceAll(" ");
        // 移除标签与其之间的内容
        str = Pattern.compile("<!--.*?-->").matcher(str).replaceAll("");
        str = Pattern.compile("//<!\\[CDATA\\[[^>]*//]]>").matcher(str).replaceAll("");
        str = Pattern.compile("<\\s*xml[^>]*>.*?<\\s*/\\s*xml\\s*>").matcher(str).replaceAll("");
        str = Pattern.compile("<\\s*style[^>]*>.*?<\\s*/\\s*style\\s*>").matcher(str).replaceAll("");
        str = Pattern.compile("<\\s*iframe[^>]*>.*?<\\s*/\\s*iframe\\s*>").matcher(str).replaceAll("");
        str = Pattern.compile("<\\s*script[^>]*>.*?<\\s*/\\s*script\\s*>").matcher(str).replaceAll("");
        str = Pattern.compile("<\\s*object[^>]*>.*?<\\s*/\\s*object\\s*>").matcher(str).replaceAll("");
        // 处理 br 标签
        str = Pattern.compile("</br[^>]*>").matcher(str).replaceAll("<br>");
        str = Pattern.compile("</?bl[^>]*>").matcher(str).replaceAll("<p>");
        str = Pattern.compile("</?bu[^>]*>").matcher(str).replaceAll("");
        str = Pattern.compile("</?b[^>]*>").matcher(str).replaceAll("<br>");
        // 处理 div、p、li 标签
        str = Pattern.compile("</?[dpl][^>]*>").matcher(str).replaceAll("<p>");
        // 处理其余标签
        str = Pattern.compile("</?[acefghijklmnoqrstuvwxyz][^>]*>").matcher(str).replaceAll("");
        // 将多个相同的标签转换为两个 p 标签
        str = Pattern.compile("(\\s*<[a-z!][^>]*>\\s*){2,}").matcher(str).replaceAll("<p><p>");
        // 移除首尾多余 p 标签
        str = Pattern.compile("^(\\s*<p>\\s*)+|(\\s*<p>\\s*)+$").matcher(str).replaceAll("<p>");
        // 去除首尾空白字符
        str = str.trim();
        return str;
    }

    /**
     * 将文件名中的 \"/:*?<>| 替换为 _
     * @param str        字符串
     * @return           替换后的字符串
     */
    public static String filterFilename(String str) {
        return Pattern.compile("[\\\\\"/:*?<>|]").matcher(str).replaceAll("_");
    }

}
