package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/4/25.
 * 睡眠质量表
 */
@Entity
@Table(name = "m_sleep")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sleep implements Serializable {
    private static final long serialVersionUID = 7475378112520324255L;

    /**
     * 睡眠质量id
     */
    @Id
    @Column(name = "sleep_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 用户id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    User user;

    /**
     * 开始时间
     */
    @Column(name = "sleep_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "sleep_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date endTime;

    /**
     * 第一次翻身的时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sleep_first_time")
    Date firstTime;

    /**
     * 翻身次数
     */
    @Column(name = "sleep_count")
    Integer count;

    /**
     * 总睡眠时长
     */
    @Column(name = "sleep_count_time", length = 12)
    String sleepTime;

    /**
     * 深度睡眠时长
     */
    @Column(name = "sleep_deep_time", length = 12)
    String sleepDeep;

    /**
     * 浅度睡眠时长
     */
    @Column(name = "sleep_light_time", length = 12)
    String sleepLight;

    /**
     * 统计日期
     */
    @Column(name = "sleep_statistical")
    @Temporal(TemporalType.DATE)
    Date statistical = new Date();

    /**
     * 睡眠质量
     */
    @Column(name = "sleep_quality")
    Integer quality;

    /**
     * 分析、建议
     */
    @Column(name = "sleep_suggest")
    String suggest;
}