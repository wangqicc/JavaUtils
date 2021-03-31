package Utils;

import java.util.regex.Pattern;

public class RegexUtil {

    // 匹配中文字符
    private static final Pattern pattern1 = Pattern.compile("^[\u4e00-\u9fa5]+$");
    // 匹配双字节字符
    private static final Pattern pattern2 = Pattern.compile("^[^\\x00-\\xff]+$");

    /**
     * 判断字符串是否符合正则表达式
     * @param str        字符串
     * @param pattern    正则表达式
     * @return           返回是否匹配的布尔值
     */
    public static boolean isMatch(String str, String pattern) {
        return Pattern.compile(pattern).matcher(str).find();
    }

    /**
     * 判断字符串是否是中文
     * @param str        字符串
     * @return           返回是否匹配的布尔值
     */
    public static boolean isChinese(String str) {
        return pattern1.matcher(str).matches();
    }

    /**
     * 判断字符串是否是双字节字符（占两个字节的字符）
     * @param str        字符串
     * @return           返回是否匹配的布尔值
     */
    public static boolean isDoubleByte(String str) {
        return pattern2.matcher(str).matches();
    }

}
