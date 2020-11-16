package com.jcpl.persist;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * @author Administrator
 */
public interface JcUUIDUtils {

    /**
     * 取得一个UUID
     * @return
     */
    static String generate32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /**
     * 取得一个UUID
     * @return
     */
    static long generateLongUUID() {
        long time = System.currentTimeMillis();
        Random r = new Random(time);
        int no = r.nextInt(100);
        return Long.valueOf(no + "" + time);
    }

    /**
     * md5 加密
     * @param data
     * @return
     */
    static String md5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = data.getBytes();
            md.update(b);
            byte[] b2 = md.digest();
            int len = b2.length;
            String str = "0123456789abcdef";
            char[] ch = str.toCharArray();
            char[] chs = new char[len * 2];
            for (int i = 0, k = 0; i < len; i++) {
                byte b3 = b2[i];
                chs[k++] = ch[b3 >>> 4 & 0xf];
                chs[k++] = ch[b3 & 0xf];
            }
            return new String(chs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
