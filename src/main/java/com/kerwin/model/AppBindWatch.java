package com.kerwin.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/5/28.
 * app user and watch user bind info
 */
@Entity
@Table(name = "m_app_bind_watch")
@Data
@RequiredArgsConstructor
public class AppBindWatch implements Serializable {
    private static final long serialVersionUID = 8963799985343563484L;

    /**
     * 编号
     */
    @Id
    @Column(name = "bind_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 用户信息
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    User user;

    /**
     * app用户信息
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "au_id")
    AppUser appUser;

    /**
     * 用户昵称
     */
    @Column(name = "user_name")
    String nickName;

    /**
     * 绑定日期
     */
    @Column(name = "bind_date")
    @Temporal(TemporalType.TIMESTAMP)
    Date date;
}
