package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/4/25.
 * 运动信息
 */
@Entity
@Table(name = "m_play")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Play implements Serializable {
    private static final long serialVersionUID = -4087865654122894389L;

    /**
     * 运动编号
     */
    @Id
    @Column(name = "play_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 用户id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    User user;

    /**
     * 统计日期
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "play_time")
    Date date = new Date();

    /**
     * 运动类型
     */
    @Column(name = "play_type", length = 10)
    String type;

    /**
     * 步数
     */
    @Column(name = "play_count")
    Integer count;

    /**
     * 距离
     */
    @Column(name = "play_distance")
    Integer distance;

    /**
     * 运动消耗卡路里
     */
    @Column(name = "play_calories")
    Integer calories;

    /**
     * 等级：
     */
    @Column(name = "play_level")
    String level;

    /**
     * 分析、建议
     */
    @Column(name = "play_suggest")
    String suggest;

    /**
     * 周几
     */
    @Transient
    String week;
}