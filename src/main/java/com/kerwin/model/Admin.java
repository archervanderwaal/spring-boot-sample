package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kerwin on 2016/4/20.
 * 管理员
 */
@Entity(name = "m_admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
    private static final long serialVersionUID = -4525499305059101894L;

    /**
     * 管理员编号
     */
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 管理员名称
     */
    @Column(name = "admin_name", length = 20)
    String name;

    /**
     * 登录密码
     */
    @Column(name = "admin_password", length = 50)
    String password;

    /**
     * 邮箱地址
     */
    @Column(name = "admin_email", length = 50)
    String email;

    /**
     * 管理员级别
     */
    @Column(name = "admin_level")
    Integer level;

    /**
     * 上次登录时间
     */
    @Column(name = "admin_last_login_time")
    DateTime lastLoginTime = new DateTime();
    /**
     * 上次登录IP
     */
    @Column(name = "admin_last_login_ip", length = 15)
    String lastLoginIP;

    /**
     * 登录次数
     */
    @Column(name = "admin_login_count")
    Integer loginCount;

    public Admin(String name, String password, String email) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
