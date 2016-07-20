package com.kerwin.utils;

/**
 * Created by Tiamo on 2016/4/21.
 * 身份证替换指定位置字符串
 */
public class ReplaceStrUtil {
    public static String replace(String Strpara) throws Exception {
        StringBuilder stringBuffer = new StringBuilder(Strpara);
        stringBuffer.replace(10, 14, "****");
        return stringBuffer.toString();
    }
}
