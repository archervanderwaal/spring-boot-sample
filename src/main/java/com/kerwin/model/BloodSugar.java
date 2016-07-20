package com.kerwin.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/5/24.
 * 血糖
 */
@Entity
@Table(name = "m_blood_sugar")
@Data
@RequiredArgsConstructor
public class BloodSugar implements Serializable {
    private static final long serialVersionUID = 5283387982529905138L;

    /**
     * 编号
     */
    @Id
    @Column(name = "bs_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 用户id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    User user;

    /**
     * 测试值
     */
    @Column(name = "bs_value")
    Integer value;

    /**
     * 血糖测试时间
     */
    @Column(name = "bs_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date time = new Date();

    /**
     * 等级：
     */
    @Column(name = "bs_level", length = 4)
    String level;

    /**
     * 分析、建议
     */
    @Column(name = "bs_suggest")
    String suggest;
}
