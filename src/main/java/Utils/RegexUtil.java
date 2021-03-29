package Utils;

import java.util.regex.Pattern;

public class RegexUtil {

    // 匹配中文字符
    private static final Pattern pattern1 = Pattern.compile("^[\u4e00-\u9fa5]+$");
    // 匹配双字节字符
    private static final Pattern pattern2 = Pattern.compile("^[^\\x00-\\xff]+$");

    /**
     * 检测字符串中是否含有指定模式
     * @param str        字符串
     * @param pattern    模式
     * @return           是否含有指定模式
     */
    public static boolean isMatch(String str, String pattern) {
        return Pattern.compile(pattern).matcher(str).find();
    }

    /**
     * 检测是否是中文字符
     * @param str        字符串
     * @return           是否为中文字符
     */
    public static boolean isChinese(String str) {
        return pattern1.matcher(str).matches();
    }

    /**
     * 检测是否是双字节字符
     * @param str        字符串
     * @return           是否为双字节字符（占两个字节）
     */
    public static boolean isDoubleByte(String str) {
        return pattern2.matcher(str).matches();
    }

}
