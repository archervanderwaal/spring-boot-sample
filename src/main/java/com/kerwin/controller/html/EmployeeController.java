package com.kerwin.controller.html;

import com.kerwin.common.SimpleData;
import com.kerwin.condition.EmployeeCondition;
import com.kerwin.model.Employee;
import com.kerwin.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vega on 2016/5/19 0019.
 * Employee Controller
 */

@Controller
@RequestMapping(method = RequestMethod.GET)
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * 筛选志愿者信息
     *
     * @param employeeCondition 筛选条件
     * @param pageable          分页信息
     * @return 志愿者基本信息
     */
    @RequestMapping(value = "volunteer.html")
    public ModelAndView getVolunteerEmployee(EmployeeCondition employeeCondition, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("volunteer");
        employeeCondition.setDeptId(5);
        Page<Employee> employeePage = employeeService.findAll(employeeCondition, pageable);
        mv.addObject("employees", employeePage);
        return mv;
    }

    /**
     * 筛选救援人员信息
     *
     * @param employeeCondition 筛选条件
     * @param pageable          分页信息
     * @return 救援人员信息
     */
    @RequestMapping(value = "rescue_personnel_supervise.html")
    public ModelAndView getRescueWorkersEmployee(EmployeeCondition employeeCondition, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("rescue_personnel_supervise");
        employeeCondition.setDeptId(6);
        Page<Employee> employeePage = employeeService.findAll(employeeCondition, pageable);
        mv.addObject("employees", employeePage);
        return mv;
    }
    /**
     * 添加员工（救援人员）基本信息
     *
     * @param employee .
     * @return .
     */
    @RequestMapping(value = "create_rescue.html", method = RequestMethod.GET)
    @ResponseBody
    public String save(Employee employee) {
        SimpleData data = SimpleData.newItem();
        employee = employeeService.create(employee);
        log.info("========================create_rescue(id={})=======================", employee);
        JSONObject json = new JSONObject(data);
        log.info("==========json===================", json.toString());
        return json.toString();
    }

    /**
     * 新增员工（救援人员，志愿者）基本信息
     *
     * @param employee 员工（救援人员，志愿者）
     * @return .
     */
    @RequestMapping(value = "createVolunteer.html", method = RequestMethod.GET)
    @ResponseBody
    public String create(Employee employee, String conName, String conPhone) {
        SimpleData data = SimpleData.newItem();
        Employee temp;
        if (StringUtils.isBlank(conName) || StringUtils.isBlank(conPhone) || StringUtils.isBlank(employee.getIdentity()) || StringUtils.isBlank(employee.getName()) || StringUtils.isBlank(employee.getPhone()))
            data.setCode(-1).setMessage("The name, phone, identity, contactName, contactPhone can not empty!");
        else {
            temp = employeeService.create(employee);
            if (null == temp) data.setCode(0).setMessage("Failure!");
            else data.setCode(1).setMessage("Success!").setData(temp);
        }
        System.err.println(new JSONObject(data));
        return new JSONObject(data).toString();
    }

    /**
     * 修改员工（救援人员，志愿者）基本信息
     *
     * @param employee .
     * @return .
     */
    @RequestMapping(value = "updatVolunteer.html", method = RequestMethod.GET)
    @ResponseBody
    public String update(Employee employee) {
        SimpleData data = employeeService.update(employee);
        log.info("========================updatVolunteer(id={})=======================", employee);
        JSONObject json = new JSONObject(data);
        log.info("==========json===================", json.toString());
        return json.toString();
    }
}
/**
 * 职位：
 * <p>
 * 救援人员
 * 志愿者
 * 客服
 * 调度
 * 营养师
 * 站长
 * 部门经理
 */
