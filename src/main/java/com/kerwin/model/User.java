package com.kerwin.model;

import com.kerwin.constant.ServiceStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kerwin on 2016/4/22.
 * 手表用户
 */
@Entity
@Table(name = "m_user")
@Data
@RequiredArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -270436088269307242L;
    /**
     * 用户编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id")
    Integer id;

    /**
     * app用户编号
     */
    @Column(name = "au_id")
    Integer appUserId;

    /**
     * 用户名称
     */
    @Column(name = "user_name", length = 10)
    String name;

    /**
     * 昵称
     */
    @Column(name = "user_nick_name", length = 10)
    String nickName;

    /**
     * 身份证
     */
    @Column(name = "user_card", length = 18)
    String idCard;

    /**
     * 手机号
     */
    @Column(name = "user_phone", length = 11)
    String phone;

    /**
     * 性别
     */
    @Column(name = "user_gender", length = 2)
    String gender;

    /**
     * 紧急联系人名称
     */
    @Column(name = "user_contact_name", length = 20)
    String contactName;

    /**
     * 紧急联系人电话
     */
    @Column(name = "user_contact_phone", length = 11)
    String contactPhone;

    /**
     * 民族
     */
    @Column(name = "user_nationality", length = 50)
    String nationality;

    /**
     * 头像
     */
    @Column(name = "user_avatar", length = 100)
    String avatar;

    /**
     * 自理状况
     */
    @Column(name = "user_themselves", length = 2)
    String themselves;

    /**
     * 是否有医保
     */
    @Column(name = "user_medicare", length = 2)
    String medicare;

    /**
     * 视力
     */
    @Column(name = "user_vision", length = 4)
    String vision;

    /**
     * 听力
     */
    @Column(name = "user_hearing", length = 4)
    String hearing;

    /**
     * 记忆力
     */
    @Column(name = "user_memory", length = 4)
    String memory;

    /**
     * 语言表达能力
     */
    @Column(name = "user_language", length = 4)
    String language;

    /**
     * 血型
     */
    @Column(name = "user_blood", length = 4)
    String blood;

    /**
     * 居住状况
     */
    @Column(name = "user_living", length = 20)
    String living;

    /**
     * 用户身高
     */
    @Column(name = "user_height")
    String height;

    /**
     * 用户体重
     */
    @Column(name = "user_weight")
    String weight;

    /**
     * 详细地址
     */
    @Column(name = "user_address")
    String address;

    /**
     * 喜好
     */
    @Column(name = "user_hobby")
    String hobby;

    /**
     * 备注
     */
    @Column(name = "user_remark")
    String remark;

    /**
     * 过敏药物
     */
    @Column(name = "user_allergy_medications")
    String allergyMedications;

    /**
     * 饮食习惯
     */
    @Column(name = "user_eating_habits")
    String eatingHabits;

    /**
     * 病史
     */
    @Column(name = "user_medical")
    String medical;

    /**
     * 用户生日
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "user_birthday")
    Date birthday;

    /**
     * 注册时间
     */
    @Column(name = "user_register_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date registerTime;

    /**
     * 服务开通时间
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "user_service_begin_time")
    Date serviceBeginTime;

    /**
     * 服务结束时间
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "user_service_end_time")
    Date serviceEndTime;

    /**
     * 经度
     */
    @Column(name = "user_longitude", length = 10)
    String longitude;

    /**
     * 纬度
     */
    @Column(name = "user_latitude", length = 10)
    String latitude;

    /**
     * 设备识别码
     */
    @Column(name = "watch_imei", length = 15)
    String imei;

    /**
     * 电量
     */
    @Column(name = "watch_battery")
    Integer battery = 0;

    /**
     * 年龄
     */
    @Transient
    Integer age;

    /**
     * 服务状态
     */
    @Transient
    Integer serviceStatus = ServiceStatus.NON_ACTIVATED;

    public User(String imei) {
        this.imei = imei;
    }

    public Map<String, Object> toSimpleMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);                          // 用户编号
        map.put("name", name);                      // 用户名称
        map.put("age", age);                        // 用户年龄
        map.put("hobby", hobby);                    // 用户爱好
        map.put("avatar", avatar);                  // 用户头像
        map.put("eatingHabits", eatingHabits);      // 饮食习惯
        if (null != birthday)                       // 用户生日
            map.put("birthday", new SimpleDateFormat("MM-dd").format(birthday));
        return map;
    }
}