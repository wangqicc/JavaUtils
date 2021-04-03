package utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    /**
     * 获取字符串的 MD5 值
     * @param str 字符串
     * @return MD5 值
     * @throws NoSuchAlgorithmException 抛出异常，没有该算法
     */
    public static String getMD5(String str) throws NoSuchAlgorithmException {
        return getAlgorithm(str, "MD5");
    }

    /**
     * 获取字符串的 SHA-1 值
     * @param str 字符串
     * @return SHA-1 值
     * @throws NoSuchAlgorithmException 抛出异常，没有该算法
     */
    public static String getSHA1(String str) throws NoSuchAlgorithmException {
        return getAlgorithm(str, "SHA-1");
    }

    /**
     * 根据字符串名和算法名，返回加密后的值
     * @param str 字符串
     * @param algorithm 算法名
     * @return 加密后的值
     * @throws NoSuchAlgorithmException 抛出异常，没有该算法
     */
    private static String getAlgorithm(String str, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(str.getBytes());
        return DatatypeConverter.printHexBinary(digest.digest()).toUpperCase();
    }

}
