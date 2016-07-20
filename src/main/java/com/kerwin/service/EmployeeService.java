package com.kerwin.service;

import com.kerwin.common.SimpleData;
import com.kerwin.condition.EmployeeCondition;
import com.kerwin.model.Employee;
import com.kerwin.repository.EmployeeRepository;
import com.kerwin.utils.ValidataUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.kerwin.calculate.EmployeeCalculate.calcEmpInfo;

/**
 * Created by Vega on 2016/5/18 0018.
 * 员工
 */
@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * 新增员工（救援人员，志愿者）信息
     *
     * @param employee 员工信息
     */
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * 修改员工（救援人员，志愿者）信息
     *
     * @param employee 员工（救援人员，志愿者）信息
     * @return 修改结果 -1
     */
    public SimpleData update(Employee employee) {
        SimpleData data = SimpleData.newItem();
        Employee emp;
        emp = employeeRepository.findOne(employee.getId());
        if (null == emp) return data.setCode(-1).setMessage("Employee info not exists!");

        //<editor-fold desc="判断实体的每一个值, 不为NULL或空串的修改">
        if (!StringUtils.isBlank(employee.getJurisdiction())) emp.setJurisdiction(employee.getJurisdiction());
        if (!StringUtils.isBlank(employee.getPhone()) && ValidataUtils.isPhone(employee.getPhone()))
            emp.setPhone(employee.getPhone());
        if (!StringUtils.isBlank(employee.getAddress())) emp.setAddress(employee.getAddress());
        if (!StringUtils.isBlank(employee.getName())) emp.setName(employee.getName());
        if (null != employee.getGender()) emp.setGender(employee.getGender());
        if (null != employee.getBirthday()) emp.setBirthday(employee.getBirthday());
//        if (null != employee.getContact()) emp.setContact(employee.getContact());
        //</editor-fold>

        emp = employeeRepository.save(calcEmpInfo(employee));
        if (null == emp) data.setCode(0).setMessage("Update user info Failure!");
        else data.setCode(1).setMessage("保存成功！");
        return data;
    }

    /**
     * 通过员工（救援人员，志愿者）ID，来删除员工（救援人员，志愿者）信息
     *
     * @param id 员工（救援人员，志愿者）编号
     */
    public void delete(Integer id) {
        employeeRepository.delete(id);
    }

    /**
     * 查询全部或通过条件查询诉求信息
     *
     * @param employeeCondition 条件
     * @param pageable          分页信息
     * @return 员工信息
     */
    public Page<Employee> findAll(final EmployeeCondition employeeCondition, Pageable pageable) {
        int page = pageable.getPageNumber();
        if (page != 0) pageable = new PageRequest(page - 1, pageable.getPageSize(), pageable.getSort());
        return employeeRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isBlank(employeeCondition.getName()))
                predicates.add(cb.like(root.get("name").as(String.class), "%" + employeeCondition.getName() + "%"));
            if (!StringUtils.isBlank(employeeCondition.getPhone()))
                predicates.add(cb.equal(root.get("phone").as(String.class), employeeCondition.getPhone()));
            if (null != employeeCondition.getId())
                predicates.add(cb.equal(root.get("id").as(Long.class), employeeCondition.getId()));
            if (!StringUtils.isBlank(employeeCondition.getJurisdiction()))
                predicates.add(cb.like(root.get("jurisdiction").as(String.class), "%" + employeeCondition.getJurisdiction() + "%"));
            if (null != employeeCondition.getDeptId() && -1 != employeeCondition.getDeptId())
                predicates.add(cb.equal(root.join("dept").get("id").as(Long.class), employeeCondition.getDeptId()));
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageable);
    }
}