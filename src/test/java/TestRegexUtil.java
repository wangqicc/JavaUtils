import utils.RegexUtil;

public class TestRegexUtil {

    public static void main(String[] args) {
        String str = "A你好";
        System.out.println(RegexUtil.isChinese(str));
        System.out.println(RegexUtil.isDoubleByte(str));
        String pattern = "[^\\x00-\\xff]+";
        System.out.println(RegexUtil.isMatch(str, pattern));
    }

}
