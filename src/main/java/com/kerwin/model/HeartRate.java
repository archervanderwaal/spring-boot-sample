package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/4/25.
 * 心率测试
 */
@Entity
@Table(name = "M_HeartRate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeartRate implements Serializable {
    private static final long serialVersionUID = 8595093527429851800L;
    /**
     * 心率测试ID
     */
    @Id
    @Column(name = "heart_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 用户id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    User user;

    /**
     * 是否为自动检测
     */
    @Column(name = "heart_auto")
    Boolean isAuto;

    /**
     * 心率测试时间
     */
    @Column(name = "heart_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date time = new Date();

    /**
     * 心率测试值
     */
    @Column(name = "heart_value")
    Integer value;

    /**
     * 状态： 过缓、正常、过快
     */
    @Column(name = "heart_level", length = 4)
    String level;

    /**
     * 分析、建议
     */
    @Column(name = "heart_suggest")
    String suggest;

    /**
     * 测试时间
     */
    @Transient
    String testTime;
}