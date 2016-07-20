package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kerwin on 2016/4/25.
 * 员工类
 */
@Entity
@Table(name = "m_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = -1051620944748361624L;
    /**
     * 员工ID
     */
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 员工姓名
     */
    @Column(name = "emp_name", length = 10)
    String name;

    /**
     * 密码
     */
    @Column(name = "emp_password", length = 50)
    String password;

    /**
     * 员工手机号
     */
    @Column(name = "emp_phone", length = 11)
    String phone;

    /**
     * 身份证号码
     */
    @Column(name = "emp_identity", length = 18)
    String identity;

    /**
     * 员工性别
     */
    @Column(name = "emp_gender", length = 2)
    String gender;

    /**
     * 员工出生日期
     */
    @Column(name = "emp_birthday")
    @Temporal(TemporalType.DATE)
    Date birthday;

    /**
     * 上次登录时间
     */
    @Column(name = "emp_last_login_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date loginTime;

    /**
     * 上次登录IP
     */
    @Column(name = "emp_last_login_ip", length = 15)
    String loginIp;

    /**
     * 登录次数
     */
    @Column(name = "emp_login_count")
    Integer loginCount;

    /**
     * 详细地址
     */
    @Column(name = "emp_address")
    String address;

    /**
     * 工作状态
     */
    @Column(name = "emp_status")
    Boolean status;

    /**
     * 管辖区域
     */
    @Column(name = "emp_jurisdiction", length = 50)
    String jurisdiction;

    /**
     * 当前所在经度
     */
    @Column(name = "emp_longitude", length = 10)
    String longitude;

    /**
     * 当前所在纬度
     */
    @Column(name = "emp_latitude", length = 10)
    String latitude;

    /**
     * 入职时间
     */
    @Column(name = "emp_entryDate")
    @Temporal(TemporalType.DATE)
    Date entryDate;

    /**
     * 离职日期
     */
    @Column(name = "emp_turnoverDate")
    @Temporal(TemporalType.DATE)
    Date turnoverDate;

    /**
     * 救援成功次数
     */
    @Column(name = "emp_help_success_count")
    Integer successCount;

    /**
     * 救援失败次数
     */
    @Column(name = "emp_help_failure_count")
    Integer failureCount;

    /**
     * 紧急联系人姓名
     */
    @Column(name = "emp_contact_name", length = 10)
    String contactName;

    /**
     * 紧急联系人电话
     */
    @Column(name = "emp_contact_phone", length = 11)
    String contactPhone;

    /**
     * 所在部门
     */
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "dept_id")
    Dept dept;

    /**
     * 职位
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "level_id")
    EmpLevel empLevel;

    /**
     * 年龄
     */
    @Transient
    Integer age;

    /**
     * 救援总次数
     */
    @Transient
    Integer helpCount;

    /**
     * 服务类型 1 ==> 救援 、2 ==> 送货、3 ==> 保洁、4 ==> 回访
     */
    @Column(name = "emp_serviceType")
    Integer serviceType;
}
