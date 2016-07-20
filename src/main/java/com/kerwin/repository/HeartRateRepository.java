package com.kerwin.repository;

import com.kerwin.model.HeartRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

/**
 * Created by Kerwin on 2016/5/7.
 * 心率
 */
public interface HeartRateRepository extends JpaRepository<HeartRate, Integer>, JpaSpecificationExecutor<HeartRate> {
    /**
     *
     *
     * @param userId
     * @param isAuto
     * @param date
     * @return 心率检测记录
     */
    /**
     * 通过用户编号、 统计时间、 是否自动监测查询心率记录
     *
     * @param userId 用户编号
     * @param isAuto 是否自动检测
     * @param sDate  统计日期(开始)
     * @param eDate  统计日期(结束)
     * @return .
     */
    List<HeartRate> findByUserIdAndIsAutoAndTimeBetween(Integer userId, Boolean isAuto, Date sDate, Date eDate);
}