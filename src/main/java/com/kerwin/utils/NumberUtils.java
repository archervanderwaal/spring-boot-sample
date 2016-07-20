package com.kerwin.utils;

import java.text.DecimalFormat;

/**
 * Created by Tiamo on 2016/4/21.
 * 规避科学计数法
 */
public class NumberUtils {
    private static DecimalFormat df = new DecimalFormat("#.#");

    public static String trans(Double amount) throws Exception {
        String result;

        result = df.format(amount);

        if (!result.contains(".")) {
            if (result.length() <= 4) {
            } else if (result.length() <= 8 && result.length() > 4) {
                result = result.substring(0, result.length() - 4) + "万";
            } else {
                result = result.substring(0, result.length() - 8) + "亿";
            }
        } else {
            String result1 = result.substring(0, result.indexOf("."));
            if (result1.length() <= 4) {
            } else if (result1.length() <= 8 && result1.length() > 4) {
                result = result1.substring(0, result1.length() - 4) + "万";
            } else {
                result = result1.substring(0, result1.length() - 8) + "亿";
            }
        }
        return result;
    }

    public static String trans1(Double amount) throws Exception {
        String result;

        result = df.format(amount);
        return result;
    }
}
