package com.kerwin.condition;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by Vega on 2016/5/18 0018.
 * Employee condition
 */
@Data
@RequiredArgsConstructor
public class EmployeeCondition implements Serializable {
    private static final long serialVersionUID = 1446371330667161527L;

    /**
     * 姓名
     */
    String name;
    /**
     * 手机号
     */
    String phone;
    /**
     * 员工编号
     */
    Integer id;
    /**
     * 部门编号
     */
    Integer deptId;
    /**
     * 管辖区域
     */
    String jurisdiction;
}
