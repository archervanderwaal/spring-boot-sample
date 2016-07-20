package com.kerwin.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/5/27.
 * 订餐信息
 */
@Entity
@Table(name = "m_ordering")
@Data
@RequiredArgsConstructor
public class Ordering implements Serializable {
    private static final long serialVersionUID = 6557967076244589326L;
    /**
     * 编号
     */
    @Id
    @Column(name = "ordering_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 用户id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    User user;

    /**
     * 早餐
     */
    @Column(name = "ordering_breakfast")
    String breakfast;

    /**
     * 午餐
     */
    @Column(name = "ordering_lunch")
    String lunch;

    /**
     * 晚餐
     */
    @Column(name = "ordering_dinner")
    String dinner;

    /**
     * 日期
     */
    @Column(name = "ordering_date")
    @Temporal(TemporalType.DATE)
    Date date;
}
