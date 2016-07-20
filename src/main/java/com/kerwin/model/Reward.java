package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/4/25.
 * 奖励记录, 员工不为空时App用户空，APP用户不为空时员工空
 */
@Entity
@Table(name = "m_reward")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward implements Serializable {

    private static final long serialVersionUID = -8161767172115836360L;
    /**
     * 奖励记录编号
     */
    @Id
    @Column(name = "reward_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 员工id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "emp_id")
    Employee employee;

    /**
     * 员工id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "au_id")
    AppUser appUser;

    /**
     * 奖励时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reward_time")
    Date time;

    /**
     * 奖励内容
     */
    @Column(name = "reward_content")
    String content;
}