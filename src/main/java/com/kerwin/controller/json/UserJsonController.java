package com.kerwin.controller.json;

import com.kerwin.common.SimpleData;
import com.kerwin.model.HealthAnalysis;
import com.kerwin.model.Ordering;
import com.kerwin.model.User;
import com.kerwin.service.OrderingService;
import com.kerwin.service.UserService;
import com.kerwin.utils.LocationUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kerwin.calculate.UserCalculate.calcUserInfo;

/**
 * Created by Kerwin on 2016/5/16.
 * User Controller
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserJsonController {
    private static final Logger log = LoggerFactory.getLogger(UserJsonController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private OrderingService orderingService;

    /**
     * 通过用户编号获取用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    @RequestMapping(value = "getDetailInfo.json")
    public SimpleData getUserInfo(Integer id) {
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == id) return data.setCode(-1).setMessage("User ID can not be empty!");

        // 查询用户信息
        User user = userService.findById(id);

        // 判断用户是否存在
        if (null == user) return data.setCode(0).setMessage("User information does not exist!");
        return data.setCode(1).setMessage("Success").setData(calcUserInfo(user));
    }

    /**
     * 获取用户历史健康状态
     *
     * @param id      用户编号
     * @param dateStr 日期字符串
     * @return 健康分析数据
     */
    @RequestMapping(value = "getUserHistory.json")
    public SimpleData getUserHistory(Integer id, @RequestParam(name = "date") String dateStr) {
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == id || StringUtils.isBlank(dateStr))
            return data.setCode(-1).setMessage("The user's id and date can not be empty!");

        // 判断传入的日期字符串参数是否正确
        if (!dateStr.contains("-") || 10 < dateStr.length())
            return data.setCode(-1).setMessage("The date input illegal!");

        User user = userService.findById(id);

        // 转换用户生日格式为java.util.Date;
        Date date = null;
        try {
            // 转换日期
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 执行查询操作
        HealthAnalysis analysis = userService.getUserHistory(user, date);

        // 判断是否查询到数据, 并设置结果
        if (null == analysis) return data.setCode(0).setMessage("No query history info!");

        // 避免信息过多导致解析麻烦, 将用户信息至空
        analysis.setUser(null);

        // 设置结果
        return data.setCode(1).setMessage("Query success!").setData(analysis);
    }

    /**
     * 通过用户信息及日期查询当天的订餐信息
     *
     * @param userId  用户id
     * @param dateStr 日期字符串
     * @return 订餐信息
     */
    @RequestMapping(value = "getOrdering.json")
    public SimpleData findByUserIdAndDate(Integer userId, @RequestParam(name = "date") String dateStr) {
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == userId || StringUtils.isBlank(dateStr))
            return data.setCode(-1).setMessage("The userId and date can not be empty!");

        // 判断输入的日期是否正确
        if (!dateStr.contains("-")) return data.setCode(-1).setMessage("Please enter the correct date!");

        // 通过用户id查询用户信息
        User user = userService.findById(userId);

        // 判断用户信息是否存在
        if (null == user) return data.setCode(0).setMessage("The user info not exists!");

        // 转换用户生日格式为java.util.Date;
        Date date = null;
        try {
            // 转换日期
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 查询订餐信息
        Ordering ordering = orderingService.findByUserAndDate(user, date);
        if (null == ordering) return data.setCode(0).setMessage("No query ordering info!");

        // 避免信息过多导致解析麻烦, 将用户信息至空
        ordering.setUser(null);

        // 设置结果
        return data.setCode(1).setMessage("Success!").setData(ordering);
    }

    /**
     * 修改用户信息
     *
     * @param user         用户信息
     * @param userBirthday 用户生日
     * @return .
     */
    @RequestMapping(value = "update.json")
    public SimpleData updateUserInfo(User user, String userBirthday) {
        SimpleData data = SimpleData.newItem();

        // 判断用户是否存在
        if (!userService.exists(user.getId())) return data.setCode(0).setMessage("User info not exists!");

        // 转换用户生日类型, 将字符串转换为 java.util.Date;
        if (!StringUtils.isBlank(userBirthday)) {
            try {
                Date time = new SimpleDateFormat("yyyy-MM-dd").parse(userBirthday);
                user.setBirthday(time);
            } catch (ParseException e) {
                log.error("The user birthday parse failure, userBirthday = {}", userBirthday);
                e.printStackTrace();
            }
        }
        user = userService.update(user); // 修改用户信息
        if (null == user) data.setCode(0).setMessage("Update user info Failure!");
        else data.setCode(1).setMessage("Success!");
        return data;
    }

    /**
     * 通过用户编号获取对应手表用户的位置、电量、运动等信息
     *
     * @param id     用户编号
     * @param option 信息类型
     *               watcher      播报天气
     *               battery      获取电量
     *               location     获取位置
     *               heartRate    心率
     *               play         获取运动信息
     * @return .
     */
    @RequestMapping(value = "pullWatchInfo.json")
    public SimpleData pullWatchInfo(Integer id, String option) {
        SimpleData data = SimpleData.newItem();
        log.info("pullWatchInfo.json?id = {}&option = {}", id, option);

        // 判断参数是否合法
        if (null == id || StringUtils.isBlank(option))
            return data.setCode(-1).setMessage("User ID and Option can not be empty!");

        User user = userService.findById(id); // 查询用户信息

        // 判断用户是否存在
        if (null == user) return data.setCode(0).setMessage("User information does not exist!");

        // 给对应的用户手表推送对应的信息
        return userService.pullWatchInfo(user.getImei(), option);
    }

    /**
     * 通过用户编号给对应的手表设置闹钟
     *
     * @param userId 用户编号
     * @param key    key
     * @param value  value
     * @param type   type
     * @return 结果：-1 失败（数据不完整）
     */
    @RequestMapping(value = "setClock.json")
    public SimpleData setClock(Integer userId, String key, String value, String type) {
        SimpleData data = SimpleData.newItem();
        log.info("setClock.json?userId={}&key={}&value={}&type={}", userId, key, value, type);

        // 判断参数是否合法
        if (null == userId || StringUtils.isBlank(key) || StringUtils.isBlank(value) || StringUtils.isBlank(type))
            return data.setCode(-1).setMessage("Setting failed! userId, key, value, type can not be empty! Please check data integrity!");

        // 判断用户是否存在
        User user = userService.findById(userId);
        if (null == user) return data.setCode(0).setMessage("User information does not exist!");

        // 打包请求信息
        Map<String, String> extras = new HashMap<>();
        extras.put("key", key);
        extras.put("value", value);
        extras.put("type", type);

        // 给用户推送请求
        return userService.setClock(user.getImei(), extras);
    }

    /**
     * 通过手表标识给对应的手表设置位置信息
     *
     * @param imei      手表标识
     * @param longitude 经度
     * @param latitude  纬度
     * @return .
     */
    @RequestMapping("setLocation.json")
    public Integer setLocation(String imei, String longitude, String latitude) {
        log.info("setLocation.json?imei={}&longitude={}&latitude={}", imei, longitude, latitude);

        // 检查参数是否合法
        if (StringUtils.isBlank(imei) || StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude))
            return -1;

        // 判断imei长度是否正确
        if (15 != imei.length())
            return -1;

        // 查询用户信息
        User user = userService.findByImei(imei);

        // 判断用户是否存在
        if (null == user)
            return -1;

        // 处理精度信息
        longitude = LocationUtils.trimLocation(longitude);

        // 处理纬度信息
        latitude = LocationUtils.trimLocation(latitude);

        // 保存用户位置信息
        return userService.setLocation(user.getId(), longitude, latitude);
    }

    /**
     * 修改用户手表电量
     *
     * @param imei    手表标识
     * @param battery 电量
     * @return .
     */
    @RequestMapping(value = "setBattery.json")
    public Integer setBattery(String imei, Integer battery) {
        log.info("setBattery.json?imei={}&value={}", imei, battery);

        // 检查参数是否合法
        if (StringUtils.isBlank(imei) || null == battery)
            return -1;

        // 判断imei长度是否正确
        if (15 != imei.length())
            return -1;

        // 查询用户信息
        User user = userService.findByImei(imei);

        // 判断用户是否存在
        if (null == user)
            return 0;

        // 保存用户电量信息
        return userService.setBattery(user.getId(), battery);
    }
}
