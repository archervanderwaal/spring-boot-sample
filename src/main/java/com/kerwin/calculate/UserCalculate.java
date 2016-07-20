package com.kerwin.calculate;

import com.kerwin.constant.ServiceStatus;
import com.kerwin.model.User;
import com.kerwin.utils.DateUtils;

import java.util.Date;

/**
 * Created by kerwin on 16-4-25.
 * 用户相关计算
 */
public class UserCalculate {

    /**
     * 计算用户年龄、 服务状态等信息
     *
     * @param user 用户资料
     * @return 用户信息
     */
    public static User calcUserInfo(User user) {
        if (user == null) return null;
        user.setAge(calcUserAge(user));
        user.setServiceStatus(calcUserServiceStatus(user));
        return user;
    }

    /**
     * 计算用户年龄
     *
     * @param user 用户信息
     * @return 用户年龄
     */
    private static Integer calcUserAge(User user) {
        if (null == user || null == user.getBirthday())
            return 0;
        return DateUtils.yearOfDate(user.getBirthday(), new Date(System.currentTimeMillis()));
    }

    /**
     * 计算用户服务状态
     *
     * @param user 用户信息
     * @return 服务状态
     */
    private static Integer calcUserServiceStatus(User user) {
        if (null == user || null == user.getServiceBeginTime() || null == user.getServiceEndTime() || user.getServiceBeginTime().getTime() > System.currentTimeMillis())
            return ServiceStatus.FREEZE;
        if (user.getServiceEndTime().getTime() > System.currentTimeMillis() && user.getServiceEndTime().getTime() > user.getServiceBeginTime().getTime())
            return ServiceStatus.ACTIVATED;
        return ServiceStatus.NON_ACTIVATED;
    }
}
