package com.kerwin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiamo on 2016/4/21.
 * 判断是否是手机
 */
public class MobileUtils {
    public static boolean isMobile(String userAgent) {
        if (null == userAgent) {
            userAgent = "";
        }

        String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i|windows (phone|ce)|blackberry|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp|laystation portable)|nokia|fennec|htc[-_]|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
        String tableReg = "\\b(ipad|tablet|(nexus 7)|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
        Pattern phonePat = Pattern.compile(phoneReg, 2);
        Pattern tablePat = Pattern.compile(tableReg, 2);
        Matcher matcherPhone = phonePat.matcher(userAgent);
        Matcher matcherTable = tablePat.matcher(userAgent);
        return !matcherPhone.find() && !matcherTable.find() ? true : true;
    }

    public static int getCode(int length) {
        if (length < 1) {
            length = 1;
        }

        if (length > 10) {
            length = 10;
        }

        return (int) ((Math.random() * 9.0D + 1.0D) * Math.pow(10.0D, (double) (length - 1)));
    }
}
