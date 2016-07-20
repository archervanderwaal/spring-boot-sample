package com.kerwin.calculate;

import com.kerwin.constant.Qualities;
import com.kerwin.model.Sleep;
import com.kerwin.utils.DateUtils;

import java.util.Date;

/**
 * Created by Kerwin on 2016/4/23.
 * 睡眠相关计算
 */
public class SleepCalculate {

    /**
     * 计算睡眠相关的数据
     *
     * @param sleep 睡眠质量
     * @return 睡眠质量对象
     */
    public static Sleep calcSleepInfo(Sleep sleep) {
        sleep.setQuality(calcQuality(sleep));                   // 睡眠质量
        sleep.setSleepTime(calcSleepTime(sleep, "firstTime"));  // 睡眠时长
        sleep.setSleepDeep(calcSleepTime(sleep, "deep"));       // 深度睡眠时长
        sleep.setSleepLight(calcSleepTime(sleep, "light"));     // 浅度睡眠时长
        return sleep;
    }

    /**
     * 计算睡眠时长
     *
     * @param sleep 睡眠质量
     * @param type  计算类型
     * @return 睡眠时长
     */
    private static String calcSleepTime(Sleep sleep, String type) {
        StringBuilder sb = new StringBuilder();
        if (null == sleep || null == sleep.getStartTime())
            return sb.append("0时0分").toString();
        Date fromDate = sleep.getStartTime();
        Date toDate = null;
        switch (type) {
            case "firstTime":
                toDate = null != sleep.getEndTime() ? sleep.getEndTime() : fromDate;
                break;
            case "deep":
                toDate = null != sleep.getFirstTime() ? sleep.getFirstTime() : null != sleep.getEndTime() ? sleep.getEndTime() : fromDate;
                break;
            case "light":
                fromDate = null != sleep.getFirstTime() ? sleep.getFirstTime() : fromDate;
                toDate = null != sleep.getEndTime() ? sleep.getEndTime() : fromDate;
                break;
        }
        int minute = DateUtils.minuteOfDate(fromDate, toDate);
        return sb.append(minute / 60).append("时").append(minute % 60).append("分").toString();
    }

    /**
     * 计算睡眠质量
     *
     * @param sleep 睡眠信息
     * @return 睡眠质量
     */
    private static Integer calcQuality(Sleep sleep) {
        int quality = 1;
        if (null == sleep || null == sleep.getCount())
            return quality;
        int count = sleep.getCount();
        if (0 <= count && count < 5)
            quality = Qualities.GOOD;
        else if (5 <= count && count < 10)
            quality = Qualities.GENERAL;
        else
            quality = Qualities.POOR;
        return quality;
    }
}