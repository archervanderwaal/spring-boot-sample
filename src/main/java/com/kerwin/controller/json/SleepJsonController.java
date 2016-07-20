package com.kerwin.controller.json;

import com.kerwin.calculate.SleepCalculate;
import com.kerwin.common.SimpleData;
import com.kerwin.model.Sleep;
import com.kerwin.model.User;
import com.kerwin.service.SleepService;
import com.kerwin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Kerwin on 2016-04-26.
 * Sleep Quality Controller
 */
@RestController
@RequestMapping(value = "/sleep", method = RequestMethod.POST)
public class SleepJsonController {
    private static final Logger log = LoggerFactory.getLogger(SleepCalculate.class);

    @Autowired
    private SleepService sleepService;
    @Autowired
    private UserService userService;

    /**
     * 新增睡眠质量
     *
     * @param sleep 睡眠信息
     * @param sDate 开始时间
     * @param eDate 结束时间
     * @param fDate 第一次动的时间
     * @param imei  设备标识
     * @return .
     */
    @RequestMapping(value = "add.json")
    public SimpleData save(Sleep sleep, Long sDate, Long eDate, Long fDate, String imei) {
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (null == sleep.getCount() || StringUtils.isBlank(imei))
            return data.setCode(-1).setMessage("Incomplete data, check data integrity!");

        // 检测日期数据是否正确
        if (null == sDate || null == eDate || null == fDate)
            return data.setCode(-1).setMessage("The start and endDate and firstDate can not by empty!");

        // 检查imei是否正确
        if (15 != imei.length())
            return data.setCode(-1).setMessage("The user's watch imei length must be 15");

        // 通过imei查询用户信息
        User user = userService.findByImei(imei);

        if (null == user)
            return data.setCode(0).setMessage("The user info not exists!");

        // 设置用户信息
        sleep.setUser(user);

        // 设置开始睡眠时间、结束睡眠时间、第一次动的时间
        sleep.setStartTime(new Date(sDate));
        sleep.setEndTime(new Date(eDate));
        sleep.setFirstTime(new Date(fDate));

        // 保存睡眠信息
        int result = sleepService.create(sleep);

        // 设置结果
        return data.setCode(result);
    }

    /**
     * 查询用户近期天的睡眠信息
     *
     * @param userId   用户编号
     * @param pageable 分页信息
     * @return .
     */
    @RequestMapping(value = "show.json")
    public SimpleData show(Integer userId, @PageableDefault(value = 7, sort = {"statistical"}, direction = Sort.Direction.DESC) Pageable pageable) {
        SimpleData data = SimpleData.newItem();
        // 判断参数是否合法
        if (null == userId) return data.setCode(-1).setMessage("The user id can not be Empty!");

        // 判断用户是否存在
        if (!userService.exists(userId)) return data.setCode(0).setMessage("The user info not exists!");

        // 查询
        Page<Sleep> sleeps = sleepService.sleepQualityShow(userId, pageable);

        // 将睡眠数据中的用户信息制空， 避免出现栈溢出等异常
        for (Sleep sleep : sleeps) {
            sleep.setUser(null);
        }

        // 设置结果
        return data.setCode(1).setData(sleeps);
    }
}
