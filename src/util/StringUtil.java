package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zihao on 2017/5/3.
 */
public class StringUtil {
    public static MessageDigest md;

    public static String getMD5(String input) {
        if (md == null) {
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        if (input == null || input.isEmpty())
            return "";
        md.update(input.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }
}
