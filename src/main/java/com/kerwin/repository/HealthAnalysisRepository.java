package com.kerwin.repository;

import com.kerwin.model.HealthAnalysis;
import com.kerwin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

/**
 * Created by Kerwin on 2016/5/24.
 * Health analysis repository
 */
public interface HealthAnalysisRepository extends JpaRepository<HealthAnalysis, Integer>, JpaSpecificationExecutor<HealthAnalysis> {

    /**
     *
     *
     * @param user
     * @param date
     * @return 健康信息
     */
    /**
     * 通过用户及日期查询用户健康分析信息
     *
     * @param user  用户信息
     * @param sDate 开始日期
     * @param eDate 结束日期
     * @return .
     */
    HealthAnalysis findByUserAndDateBetween(User user, Date sDate, Date eDate);
}
