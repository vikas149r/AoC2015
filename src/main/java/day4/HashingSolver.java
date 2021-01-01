package day4;

import java.math.BigInteger;
import java.security.MessageDigest;

public class HashingSolver {
    public static void main(String[] args) throws Exception {
        final String secretKey = "yzbqklnj";

        MessageDigest md = MessageDigest.getInstance("MD5");
        int numberOfZeroes = 5;

        for (long i = 0; i < Long.MAX_VALUE; i++) {
            String input = secretKey + i;
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashText = no.toString(16);

            if (hashText.length() <= (32 - numberOfZeroes)) {
                System.out.println(i);
                break;
            }
        }
    }
}
