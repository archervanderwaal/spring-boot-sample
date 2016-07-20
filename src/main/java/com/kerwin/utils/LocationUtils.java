package com.kerwin.utils;

import java.math.BigDecimal;

/**
 * Created by Kerwin on 2016-05-23.
 * Location utils
 * trim longitude or latitude
 */
public class LocationUtils {

    /**
     * 处理位置坐标信息, 去掉前后空格, 如果为科学计数法, 转换为浮点数据, 并截取合适的长度
     *
     * @param location 坐标信息
     * @return 修剪之后的数据
     */
    public static String trimLocation(String location) {
        // 去掉位置坐标信息前后空格
        location = location.trim();

        // 判断位置坐标是否为科学计数法方式, 如果是, 转换为浮点
        if (location.contains("e") || location.contains("E")) {
            location = new BigDecimal(location).stripTrailingZeros().toPlainString();
        }

        // 判断位置坐标的精度是否过大, 如果过大, 截取小数点后6位
        if (10 < location.length()) {
            // 判断经度信息是否包含浮点
            int index = location.indexOf(".");
            if (index == -1)
                location = location.trim().substring(0, 11);      // 不存在小数点, 截取前10位
            else
                location = location.substring(0, index + 7);      // 存在小数点, 截取小数点后6位
        }
        return location.trim();
    }
}
