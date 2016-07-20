package com.kerwin.utils;

import java.util.Date;
import java.util.Random;

/**
 * Created by Tiamo on 2016/4/21.
 * 随机生成中文
 */
public class ChineseUtils {
    public static String getChinese() throws Exception {
        return getChinese(3);
    }

    public static String getChinese(Integer length) throws Exception {
        ChineseUtils ch = new ChineseUtils();
        String str = "";
        for (int i = 3; i > length; i--) {
            str = str + ch.getRandomChinese();
        }
        return str;
    }

    private String getRandomChinese() throws Exception {
        String str;
        int highPos, lowPos;

        long seed = new Date().getTime();
        Random random = new Random(seed);
        highPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = 161 + Math.abs(random.nextInt(93));
        byte[] b = new byte[2];
        b[0] = (new Integer(highPos)).byteValue();
        b[1] = (new Integer(lowPos)).byteValue();
        str = new String(b, "GB2312");
        return str;
    }
}
