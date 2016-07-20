package com.kerwin.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/5/24.
 * 血脂
 */
@Entity
@Table(name = "m_blood_lipids")
@Data
@RequiredArgsConstructor
public class BloodLipids implements Serializable {
    private static final long serialVersionUID = -8504404530409223127L;

    /**
     * 编号
     */
    @Id
    @Column(name = "bl_id")
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
    @Column(name = "bl_value")
    Integer value;

    /**
     * 血脂测试时间
     */
    @Column(name = "bl_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date time = new Date();

    /**
     * 等级：
     */
    @Column(name = "bl_level", length = 4)
    String level;

    /**
     * 分析、建议
     */
    @Column(name = "bl_suggest")
    String suggest;
}
