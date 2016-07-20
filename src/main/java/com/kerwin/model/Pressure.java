package com.kerwin.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/5/24.
 * 血压
 */
@Entity
@Table(name = "m_pressure")
@Data
@RequiredArgsConstructor
public class Pressure implements Serializable {
    private static final long serialVersionUID = -8590013859737923580L;

    /**
     * 编号
     */
    @Id
    @Column(name = "pressure_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    Integer userId;

    /**
     * 血压(收缩)
     */
    @Column(name = "pressure_systolic", length = 6)
    String systolic;

    /**
     * 血压(舒张)
     */
    @Column(name = "pressure_diastolic", length = 6)
    String diastolic;

    /**
     * 血压测试时间
     */
    @Column(name = "pressure_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date time = new Date();

    /**
     * 等级：
     */
    @Column(name = "pressure_level", length = 4)
    String level;

    /**
     * 分析、建议
     */
    @Column(name = "pressure_suggest")
    String suggest;

}
