package com.kerwin.controller.json;

import com.kerwin.common.SimpleData;
import com.kerwin.model.HeartRate;
import com.kerwin.model.User;
import com.kerwin.service.HeartRateService;
import com.kerwin.service.UserService;
import com.kerwin.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Kerwin on 2016/4/27.
 * HeartRate Controller
 */
@RestController
@RequestMapping(value = "/heartRate", method = RequestMethod.POST)
public class HeartRateJsonController {
    private static final Logger log = LoggerFactory.getLogger(HeartRateJsonController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private HeartRateService heartRateService;

    /**
     * 心率信息增加
     *
     * @param value 心率值
     * @param imei  手表标识
     * @return Result: 0 => 失败
     */
    @RequestMapping(value = "add.json")
    public SimpleData add(Integer value, String imei) {
        log.info("heartRate.add.json?imei={}&value={}", imei, value);
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == value || StringUtils.isBlank(imei))
            return data.setCode(-1).setMessage("HeartRate value and watches imei can not be empty");

        // 检查imei是否正确
        if (15 != imei.length())
            return data.setCode(-1).setMessage("The user's watch imei length must be 15!");

        // 判断用户是否存在
        User user = userService.findByImei(imei);
        if (null == user) return data.setCode(0).setMessage("User information does not exist!");

        // 保存心率测试结果
        return create(value, user, true);
    }

    /**
     * 手机app用户手动测试心率
     *
     * @param value 心率值
     * @param imei  手表标识
     * @return .
     */
    @RequestMapping(value = "save.json")
    public SimpleData test(Integer value, String imei) {
        log.info("heartRate.save.json?imei={}&value={}", imei, value);
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == value || StringUtils.isBlank(imei))
            return data.setCode(-1).setMessage("HeartRate value and watches imei can not be empty");

        // 检查imei是否正确
        if (15 != imei.length()) return data.setCode(-1).setMessage("The user's watch imei must be 15!");

        // 通过imei查询用户信息
        User user = userService.findByImei(imei);

        // 判断用户是否存在
        if (null == user) return data.setCode(0).setMessage("The user info not exists!");

        // 保存用户信息
        return create(value, user, false);
    }

    /**
     * 创建心率对象, 设置必要的值并保存
     *
     * @param value  心率值
     * @param user   用户信息
     * @param isAuto 是否为自动提交
     * @return .
     */
    private SimpleData create(Integer value, User user, Boolean isAuto) {
        SimpleData data = SimpleData.newItem();

        // 创建新的心率检测记录对象
        HeartRate heartRate = new HeartRate();

        // 设置必要的值
        heartRate.setValue(value);
        heartRate.setIsAuto(isAuto);
        heartRate.setUser(user);

        // 保存心率信息
        int result = heartRateService.create(heartRate);

        // 设置结果
        return data.setCode(result);
    }

    /**
     * 查询当天的心率数据
     *
     * @param userId 用户编号
     * @return .
     */
    @RequestMapping(value = "show.json")
    public SimpleData show(Integer userId, String date) {
        SimpleData data = SimpleData.newItem();
        // 判断参数是否合法
        if (null == userId) return data.setCode(-1).setMessage("The userId can not be empty!");

        // 判断用户是否存在
        if (!userService.exists(userId)) return data.setCode(0).setMessage("The user info not exists!");

        // 创建查询条件(日期)
        Date sDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (null != date)
                sDate = format.parse(date);
            else {
                String dateStr = format.format(new Date());
                sDate = format.parse(dateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<HeartRate> heartRates = heartRateService.findByUserIdAndIsAutoAndDate(userId, false, sDate);

        // 将心率数据中的用户信息制空， 避免出现栈溢出等异常
        for (HeartRate heartRate : heartRates) {

            try {
                // 截取日期字符串, 时分秒
                String tempDate = DateUtils.DateSplit(heartRate.getTime());
                heartRate.setTestTime(tempDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            heartRate.setUser(null);
        }

        data.setCode(1).setMessage("Success!").setData(heartRates);
        return data;
    }
}
