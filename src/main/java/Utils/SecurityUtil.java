package Utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    /**
     * 获取字符串的 MD5 值
     * @param str        字符串
     * @return           MD5 值
     * @throws NoSuchAlgorithmException        没有该算法异常
     */
    public static String getMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes());
        return DatatypeConverter.printHexBinary(md5.digest()).toUpperCase();
    }

}
