package com.kerwin.utils;

import com.kerwin.model.Admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiamo on 2016/4/21.
 * Cookie
 */
public class CookieUtils {
    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    String value = cookie.getValue();
                    return value;
                }
            }
        }
        return null;
    }

    public List<Cookie> addCookie(Admin admin) {
        Cookie cookieU = new Cookie("admin_name", admin.getName());
        Cookie cookieP = new Cookie("admin_pwd", admin.getPassword());
        cookieU.setMaxAge(60 * 60 * 24 * 14);
        cookieP.setMaxAge(60 * 60 * 24 * 14);
        cookieU.setPath("/");
        cookieP.setPath("/");
        List<Cookie> list = new ArrayList<>();
        list.add(cookieP);
        list.add(cookieU);
        return list;
    }
}
