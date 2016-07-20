package com.kerwin.repository;

import com.kerwin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Kerwin on 2016/4/22.
 * Repository by Admin
 */
public interface AdminRepository extends JpaRepository<Admin, Integer>, JpaSpecificationExecutor<Admin> {
    /**
     * 通过ID查找管理员信息
     *
     * @param name 管理员编号
     * @return 管理员信息
     */
    Admin findByName(String name);
}