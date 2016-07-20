package com.kerwin.service;

import com.kerwin.model.Dept;
import com.kerwin.repository.DeptRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Vega on 2016/5/11 0011.
 * 部门管理业务逻辑
 */
@Service
public class DeptService {
    private static final Logger log = LoggerFactory.getLogger(DeptService.class);

    @Autowired
    private DeptRepository deptRepository;

    public Page<Dept> findAll(String name, Pageable pageable) {
        int page = pageable.getPageNumber();
        if (0 != page) pageable = new PageRequest(page - 1, pageable.getPageSize(), pageable.getSort());
        return deptRepository.findAll((root, query, cb) -> {
            if (!StringUtils.isBlank(name))
                query.where(cb.like(root.get("name").as(String.class), "%" + name + "%"));
            return null;
        }, pageable);
    }

    /**
     * 新增部门信息
     *
     * @param dept 部门信息
     * @return 部门信息
     */
    /*public Dept save(Dept dept) {
       Dept temp = null;
        try {
            if (!StringUtils.isBlank(dept.getName()))
                temp = deptRepository.save(dept);
        } catch (Exception ex) {
            log.error("", ex);
            ex.printStackTrace();
        }
        return temp;
    }*/
    public Dept save(Dept dept) {
        return null == dept ? null : deptRepository.saveAndFlush(dept);
    }
    /**
     * 通过部门编号删除部门信息
     *
     * @param id 部门编号
     */
    public void delete(Integer id) {
        try {
            deptRepository.delete(id);
        } catch (Exception ex) {
            log.error("======================================delete(id={})=========================================", ex);
        }
    }

    /**
     * 修改部门名称
     *
     * @param dept 部门信息
     * @return 修改结果 -1
     */
    @Transactional
    public Integer updateDeptName(Dept dept) {
        Dept temp;
        int result = 0;
        try {
            if (StringUtils.isBlank(dept.getName()) || null == dept.getId())
                result = -1;
            else {
                temp = deptRepository.getOne(dept.getId());
                if (null == temp) result = -2;
                else result = deptRepository.updateName(dept.getName(), dept.getId());
            }
        } catch (Exception ex) {
            log.error("=========================updateName(name={}, id={})", dept.getName(), dept.getId(), ex);
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }
}