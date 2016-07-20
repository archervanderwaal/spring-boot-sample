package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Kerwin on 2016/4/23.
 * APP用户
 */
@Entity
@Table(name = "m_app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements Serializable {
    private static final long serialVersionUID = -6462183952083759353L;

    /**
     * 用户编号
     */
    @Id
    @Column(name = "au_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 首页默认展示的用户的编号
     */
    @Column(name = "au_default_user")
    Integer userId;

    /**
     * 注册时间
     */
    @Column(name = "au_register_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date registerTime;

    /**
     * 用户名
     */
    @Column(name = "au_name", length = 20)
    String name;

    /**
     * 性别
     */
    @Column(name = "au_gender", length = 4)
    String gender = "保密";

    /**
     * 生日
     */
    @Column(name = "au_birthday")
    @Temporal(TemporalType.DATE)
    Date birthday;

    /**
     * 手机号
     */
    @Column(name = "au_phone", length = 11)
    String phone;

    /**
     * 密码
     */
    @Column(name = "au_password", length = 50)
    String password;

    /**
     * 头像
     */
    @Column(name = "au_avatar", length = 100)
    String avatar;

    /**
     * 余额
     */
    @Column(name = "au_balance", precision = 8, scale = 2)
    BigDecimal balance = new BigDecimal(0);

    /**
     * 上次登录时间
     */
    @Column(name = "au_last_login_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date lastLoginTime = new Date();

    /**
     * 上次登录IP
     */
    @Column(name = "au_last_login_ip", length = 15)
    String lastLoginIp;

    /**
     * 登录次数
     */
    @Column(name = "au_login_count")
    Integer loginCount;

    /**
     * 个性签名
     */
    @Column(name = "au_sign", length = 50)
    String sign;

    /**
     * 自我介绍
     */
    @Column(name = "au_remark")
    String remark;

    /**
     * 用户身高
     */
    @Column(name = "au_height")
    Integer height;

    /**
     * 用户体重
     */
    @Column(name = "au_weight")
    Integer weight;

    /**
     * 当前位置经度
     */
    @Column(name = "au_longitude", length = 10)
    String longitude;

    /**
     * 当前位置纬度
     */
    @Column(name = "au_latitude", length = 10)
    String latitude;

    /**
     * 奖励记录
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "appUser")
    List<Reward> reward = new ArrayList<>();

    /**
     * 年龄
     */
    @Transient
    Integer age;
    /**
     * 孝心指数
     */
    @Transient
    Integer filialScore;

    /**
     * 临时存放首页用户信息
     */
    @Transient
    User user;

    /**
     * app用户登陆后首页展示的数据
     *
     * @return 用户基本资料
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();      // 家庭成员基本资料
        if (null != user) map = user.toSimpleMap();     // 如果设置了默认展示的用户, 则显示
        map.put("filialScore", filialScore);            // 孝心指数
        return map;
    }
}
