package algorithm;

public class HashAlgorithm {

    /**
     * 计算字符串的 hash 值
     * hash(s) = \sum_{i=0}^{n-1}{s[i]*p^i} mod m
     * reference: https://cp-algorithms.com/string/string-hashing.html
     * https://jorgechavez.dev/2020/11/12/string-hashing/
     * p 质数（只能被 1 和其本身整除的数），需要大于所有不同字符的数量
     * m 质数，这个数字越大，碰撞的几率也就越小
     * @param str 字符串
     * @return hash 值
     */
    public static long stringHash(String str) {
        int p = 31;
        int m = 1000000009;
        long res = 0;
        for (int i = 0; i < str.length(); ++i) {
            res += (int) str.charAt(i) * Math.pow(p, i);
        }
        return res % m;
    }

}
