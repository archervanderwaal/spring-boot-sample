package com.kerwin.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kerwin on 2016/4/21.
 * IP
 */
public class IPUtils {

    /**
     * 通过Request取得IP地址
     *
     * @param request request
     * @return IP
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (isInvalidIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            if (isInvalidIP(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (isInvalidIP(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (isInvalidIP(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (isInvalidIP(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String ip1 : ips) {
                if (!("unknown".equalsIgnoreCase(ip1))) {
                    ip = ip1;
                    break;
                }
            }
        }
        ip = ip.trim();
        if ("0:0:0:0:0:0:0:1".equals(ip))
            ip = "127.0.0.1";
        return ip;
    }

    /**
     * 判断IP地址是否合法
     *
     * @param ip IP地址
     * @return true 合法 & false 非法
     */
    private static boolean isInvalidIP(String ip) {
        return StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip);
    }
}
