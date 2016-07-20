package com.kerwin.calculate;

import com.kerwin.model.Employee;
import com.kerwin.utils.DateUtils;

import java.util.Date;

/**
 * Created by kerwin on 16-4-25.
 * 员工相关计算
 */
public class EmployeeCalculate {
    /**
     * 计算员工年龄
     *
     * @param employee 员工信息
     * @return 员工年龄
     */
    private static Integer calcEmpAge(Employee employee) {
        if (null == employee || null == employee.getBirthday())
            return 0;
        return DateUtils.yearOfDate(employee.getBirthday(), new Date(System.currentTimeMillis()));
    }

    /**
     * 计算员工救援(出任务)次数
     *
     * @param employee 员工信息
     * @return 总次数
     */
    private static Integer calcEmployeeHelpCount(Employee employee) {
        int num;
        if (null == employee) return 0;
        num = null == employee.getSuccessCount() ? 0 : employee.getSuccessCount();
        num = null == employee.getFailureCount() ? num : num + employee.getFailureCount();
        return num;
    }

    /**
     * 计算员工资料
     *
     * @return 员工信息
     */
    public static Employee calcEmpInfo(Employee employee) {
        employee.setAge(calcEmpAge(employee));
        employee.setHelpCount(calcEmployeeHelpCount(employee));
        return employee;
    }

}
