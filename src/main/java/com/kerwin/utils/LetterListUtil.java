package com.kerwin.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tiamo on 2016/4/21.
 * 字母列表
 */
public class LetterListUtil {
    private static String lettertable = "abcdefghijklmnopqrstuvwxyz";

    public static List<Map<String, Object>> letters() throws Exception {
        List<Map<String, Object>> letterlists = new ArrayList<>();
        for (int i = 0; i < lettertable.length(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put(lettertable.substring(i, i + 1), null);
            letterlists.add(map);
        }
        return letterlists;
    }
}
