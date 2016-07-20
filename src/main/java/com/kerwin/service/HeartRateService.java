package com.kerwin.service;

import com.kerwin.common.QuerySpecification;
import com.kerwin.condition.Condition;
import com.kerwin.model.HeartRate;
import com.kerwin.model.User;
import com.kerwin.repository.HeartRateRepository;
import com.kerwin.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.kerwin.calculate.UserCalculate.calcUserInfo;

/**
 * Created by Kwok on 2016/4/27.
 * Service implements by HeartRate
 */
@Service
public class HeartRateService {
    private static final Logger log = LoggerFactory.getLogger(HeartRateService.class);
    @Autowired
    private HeartRateRepository heartRateRepository;
    @Autowired
    private UserService userService;

    /**
     * 新增心率监测信息
     *
     * @param heartRate 心率监测信息
     * @return 心率监测信息
     */
    public Integer create(HeartRate heartRate) {

        // 如果不是自动检测的数据, 同时推送到给app用户
        if (!heartRate.getIsAuto())
            userService.pushHeartRateToAppUser(heartRate.getUser().getId(), heartRate.getValue());

        // 如果不是自动监测的数据,并且值为-2或-1，不保存数据
        if (!heartRate.getIsAuto() && (-1 == heartRate.getValue() || -2 == heartRate.getValue()))
            return 1;

        // 保存心率检测信息
        heartRate = heartRateRepository.save(heartRate);

        // 判断是否保存成功
        if (null == heartRate) return 0;
        return 1;
    }

    /**
     * 动态查询心率信息
     *
     * @param condition 查询条件类
     * @param pageable  分页信息
     * @return 心率信息集合
     */
    public Page<HeartRate> findBySpecification(Condition condition, Pageable pageable) {
        // 获取要查询的页数
        int page = pageable.getPageNumber();

        // 页数默认从0开始, 所以非0页数-1
        if (page != 0) pageable = new PageRequest(page - 1, pageable.getPageSize(), pageable.getSort());

        // 构建动态查询条件
        QuerySpecification<HeartRate> spec = new QuerySpecification<>(condition);

        // 查询
        Page<HeartRate> heartRates = heartRateRepository.findAll(spec.getSpecification(), pageable);

        // 遍历查询到的睡眠信息, 设置相关user信息
        for (HeartRate heartRate : heartRates) {

            // 获取心率信息中的用户信息
            User user = heartRate.getUser();

            // 设置用户信息
            heartRate.setUser(calcUserInfo(user));
        }
        return heartRates;
    }

    /**
     * 通过用户编号、 统计时间、 是否自动监测查询心率记录
     *
     * @param userId 用户编号
     * @param isAuto 是否自动检测
     * @param sDate  统计日期(开始)
     * @return 心率检测记录
     */
    public List<HeartRate> findByUserIdAndIsAutoAndDate(Integer userId, Boolean isAuto, Date sDate) {
        return heartRateRepository.findByUserIdAndIsAutoAndTimeBetween(userId, isAuto, sDate, DateUtils.addDay(sDate, 1));
    }
}