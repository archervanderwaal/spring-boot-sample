package com.kerwin.model;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kerwin on 2016/5/22.
 * employee level
 * 员工职位
 */
@Entity
@Table(name = "m_employee_level")
@RequiredArgsConstructor
public class EmpLevel implements Serializable {
    private static final long serialVersionUID = -7272731705966793553L;

    /**
     * 编号
     */
    @Id
    @Column(name = "level_id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    /**
     * 职位名称
     */
    @Column(name = "level_name")
    String name;
}
