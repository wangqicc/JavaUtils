import algorithm.HashAlgorithm;

public class TestHashAlgorithm {

    public static void main(String[] args) {
        String str = "apple";
        System.out.println((int) str.charAt(0));
        long res = HashAlgorithm.stringHash(str);
        System.out.println(res);
    }

}
