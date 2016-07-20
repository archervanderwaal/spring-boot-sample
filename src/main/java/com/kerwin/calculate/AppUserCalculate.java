package com.kerwin.calculate;

import com.kerwin.constant.FilialScores;
import com.kerwin.model.AppUser;
import com.kerwin.utils.DateUtils;

import java.util.Date;

/**
 * Created by kerwin on 16-4-25.
 * App用户相关计算
 */
public class AppUserCalculate {
    public static AppUser calcAppUserInfo(AppUser user) {
        user.setFilialScore(calcAppUserFilialScore(user));
        user.setAge(calcUserAge(user));
        return user;
    }

    /**
     * 计算用户年龄
     *
     * @param user 用户信息
     * @return 用户年龄
     */
    private static Integer calcUserAge(AppUser user) {
        if (null == user || null == user.getBirthday())
            return 0;
        return DateUtils.yearOfDate(user.getBirthday(), new Date(System.currentTimeMillis()));
    }

    /**
     * 计算孝心指数
     *
     * @param appUser App用户
     * @return 孝心指数
     */
    private static Integer calcAppUserFilialScore(AppUser appUser) {
        if (null == appUser || null == appUser.getLoginCount()) return FilialScores.ONE;
        int count = appUser.getLoginCount();

        // 达到0次  1级
        if (0 <= count && 100 < count) return FilialScores.ONE;

        // 达到100次  2级
        if (100 <= count && 200 < count) return FilialScores.TWO;

        // 达到200次  3级
        if (200 <= count && 500 < count) return FilialScores.THREE;

        // 达到500次 4级
        if (500 <= count && 1000 < count) return FilialScores.FOUR;

        // 超过1000次 5级
        if (1000 <= count) return FilialScores.FIVE;
        return FilialScores.ONE;
    }
}
