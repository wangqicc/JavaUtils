import utils.SecurityUtil;

import java.security.NoSuchAlgorithmException;

public class TestSecurityUtil {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "xsl";

        System.out.println(SecurityUtil.getMD5(str));
        System.out.println(SecurityUtil.getSHA1(str));
    }

}
