package com.kerwin.repository;

import com.kerwin.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Kerwin on 2016/5/10.
 * Dept Repository
 */
public interface DeptRepository extends JpaRepository<Dept, Integer>, JpaSpecificationExecutor<Dept> {
    @Modifying
    @Query(value = "UPDATE Dept d SET d.name = ?1 WHERE d.id = ?2")
    Integer updateName(String name, Integer id);
}
