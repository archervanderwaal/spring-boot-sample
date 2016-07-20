package com.kerwin.service;

import com.kerwin.model.HealthAnalysis;
import com.kerwin.model.User;
import com.kerwin.repository.HealthAnalysisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Kersin on 2016/5/24.
 * Health analysis service
 */
@Service
public class HealthAnalysisService {
    private static final Logger log = LoggerFactory.getLogger(HealthAnalysisService.class);
    @Autowired
    private HealthAnalysisRepository healthAnalysisRepository;

    /**
     * 通过用户信息及日期查询用户的健康分析信息
     *
     * @param user 用户信息
     * @param date 日期
     * @return 健康分析
     */
    public HealthAnalysis findByUserAndDate(User user, Date sDate, Date eDate) {
        return healthAnalysisRepository.findByUserAndDateBetween(user, sDate, eDate);
    }
}
