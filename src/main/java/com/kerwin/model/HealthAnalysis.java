package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/4/25.
 * 健康状态分析表
 */
@Entity
@Table(name = "m_health_analysis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthAnalysis implements Serializable {
    private static final long serialVersionUID = 7183100354114952771L;
    /**
     * 健康状态分析编号
     */
    @Id
    @Column(name = "ha_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 用户id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    User user;

    /**
     * 血压
     */
    @Column(name = "ha_pressure", length = 6)
    String pressure;

    /**
     * 血糖
     */
    @Column(name = "ha_blood_sugar", length = 6)
    String bloodSugar;

    /**
     * 血脂
     */
    @Column(name = "ha_blood_lipids", length = 6)
    String lipids;

    /**
     * 心率
     */
    @Column(name = "ha_heart_rate", length = 4)
    String heartRate;

    /**
     * 睡眠
     */
    @Column(name = "ha_sleep", length = 4)
    String sleep;

    /**
     * 运动
     */
    @Column(name = "ha_play", length = 4)
    String play;

    /**
     * 健康建议
     */
    @Column(name = "ha_suggest")
    String suggest;

    /**
     * 统计日期
     */
    @Column(name = "ha_date")
    @Temporal(TemporalType.DATE)
    Date date;
}