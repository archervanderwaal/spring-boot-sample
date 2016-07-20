package com.kerwin.model;

import com.kerwin.constant.DateFormatPattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kerwin.calculate.UserCalculate.calcUserInfo;

/**
 * Created by Kerwin on 2016/5/11.
 * 诉求
 */
@Entity
@Table(name = "m_appeal")
@Data
@RequiredArgsConstructor
public class Appeal implements Serializable {
    private static final long serialVersionUID = -6884235519098106553L;

    /**
     * 编号
     */
    @Id
    @Column(name = "appeal_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 诉求时间
     */
    @Column(name = "appeal_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date startTime = new Date();

    /**
     * 结束时间
     */
    @Column(name = "appeal_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date endTime;

    /**
     * 订单金额
     */
    @Column(name = "appeal_amount", precision = 8, scale = 2)
    BigDecimal amount;

    /**
     * 物资消耗
     */
    @Column(name = "appeal_consume")
    String consume;

    /**
     * 诉求地址
     */
    @Column(name = "appeal_address")
    String address;

    /**
     * 诉求内容
     */
    @Column(name = "appeal_content")
    String content;

    /**
     * 救助视屏网盘地址
     */
    @Column(name = "video_url")
    String videoUrl;

    /**
     * 用户Id
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "userId")
    User user;

    /**
     * 客服人员id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "service_empId")
    Employee serviceEmployee;

    /**
     * 调度人员id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "control_empId")
    Employee controlEmployee;

    /**
     * 救援人员id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "help_empId")
    Employee helpEmployee;

    /**
     * 回访人员id
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "return_empId")
    Employee returnEmployee;

    /**
     * 站长
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "master_empId")
    Employee masterEmployee;

    /**
     * 诉求状态
     */
    @Column(name = "appeal_status")
    Boolean status;

    /**
     * 是否紧急状态
     */
    @Column(name = "appeal_urgent")
    Boolean urgent;

    /**
     * 是否调度
     */
    @Column(name = "appeal_scheduling")
    Boolean scheduling;

    /**
     * 是否回访
     */
    @Column(name = "appeal_return")
    Boolean isReturn;

    public Map<String, Object> toMap() {
        this.user = calcUserInfo(user);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", user.getName());
        map.put("gender", user.getGender());
        map.put("age", user.getAge());
        map.put("imei", user.getImei());
        map.put("phone", user.getPhone());
        map.put("videoUrl", videoUrl);
        map.put("content", content);
        map.put("consume", consume);
        map.put("contactName", user.getContactName());
        map.put("contactPhone", user.getContactPhone());
        map.put("address", address);
        map.put("status", status);
        map.put("urgent", urgent);
        map.put("scheduling", scheduling);
        map.put("isReturn", isReturn);
        map.put("contactsName", user.getContactName());
        map.put("contactsPhone", user.getContactPhone());
        map.put("helpId", helpEmployee == null ? null : helpEmployee.getId());
        map.put("help", helpEmployee == null ? null : helpEmployee.getName());
        map.put("service", serviceEmployee == null ? null : serviceEmployee.getName());
        map.put("control", controlEmployee == null ? null : controlEmployee.getName());
        map.put("master", masterEmployee == null ? null : masterEmployee.getName());
        map.put("return", returnEmployee == null ? null : returnEmployee.getName());
        map.put("startTime", new DateTime(startTime).toString(DateFormatPattern.YEAR_MONTH_DAY_HOUR_MIN));
        map.put("endTime", new DateTime(endTime).toString(DateFormatPattern.YEAR_MONTH_DAY_HOUR_MIN));
        return map;
    }
}