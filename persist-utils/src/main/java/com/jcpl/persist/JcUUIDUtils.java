package com.jcpl.persist;

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
}
