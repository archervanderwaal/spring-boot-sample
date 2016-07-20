package com.kerwin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kerwin on 2016/4/25.
 * 部门信息
 */
@Entity
@Table(name = "m_dept")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept implements Serializable {
    private static final long serialVersionUID = -6266662242287355686L;
    /**
     * 部门编号
     */
    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 部门名称
     */
    @Column(name = "dept_name", length = 10)
    String name;

    /**
     * 员工信息
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dept")
    List<Employee> employees;
}