package com.kerwin.model;

import com.kerwin.constant.YesOrNo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/4/25.
 * 投诉信息
 */
@Entity
@Table(name = "m_complaint")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint implements Serializable {
    private static final long serialVersionUID = -8392446791595031056L;
    /**
     * 投诉ID
     */
    @Id
    @Column(name = "cmp_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 用户id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    User user;

    /**
     * 员工id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "emp_id")
    Employee employee;

    /**
     * 投诉内容
     */
    @Column(name = "cmp_content")
    String content;

    /**
     * 投诉时间
     */
    @Column(name = "cmp_date")
    @Temporal(TemporalType.TIMESTAMP)
    Date date = new Date();

    /**
     * 处理状态
     */
    @Column(name = "cmp_status")
    Integer status = YesOrNo.NO;
}