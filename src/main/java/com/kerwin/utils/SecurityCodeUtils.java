package com.kerwin.utils;

import java.util.Arrays;

/**
 * Created by Kerwin on 2016/4/21.
 * 生成验证码
 */
public class SecurityCodeUtils {
    /**
     * 获取验证码
     *
     * @return 随机生成的6位简单验证码
     */
    public static String getSecurityCode() {
        return getSecurityCode(6, SecurityCodeLevel.Simple, false);
    }

    /**
     * 获取验证码
     *
     * @param length      验证码长度
     * @param level       验证码难度
     * @param isCanRepeat 是否可以重复
     * @return 根据需要生成的验证码
     */
    public static String getSecurityCode(int length, SecurityCodeLevel level, boolean isCanRepeat) {
        char[] codes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z'};
        if (level == SecurityCodeLevel.Simple) {
            codes = Arrays.copyOfRange(codes, 0, 10);
        } else if (level == SecurityCodeLevel.Medium) {
            codes = Arrays.copyOfRange(codes, 0, 36);
        }
        int n = codes.length;
        if (length > n && !isCanRepeat) throw new RuntimeException("SecurityCode.getSecurityCode()");
        char[] result = new char[length];
        if (isCanRepeat) {
            for (int i = 0; i < result.length; i++) {
                int r = (int) (Math.random() * n);
                result[i] = codes[r];
            }
        } else {
            for (int i = 0; i < result.length; i++) {
                int r = (int) (Math.random() * n);
                result[i] = codes[r];
                codes[r] = codes[n - 1];
                n--;
            }
        }
        return String.valueOf(result);
    }


    public enum SecurityCodeLevel {
        /**
         * 简单
         */
        Simple,
        /**
         * 中等
         */
        Medium,
        /**
         * 困难
         */
        Hard
    }
}
