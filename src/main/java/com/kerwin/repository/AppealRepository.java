package com.kerwin.repository;

import com.kerwin.model.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Kerwin on 2016/5/11.
 * Appeal repository
 */
public interface AppealRepository extends JpaRepository<Appeal, Integer>, JpaSpecificationExecutor<Appeal> {
    @Modifying
    @Query(value = "UPDATE Appeal a SET a.status = 0 WHERE a.id =?1")
    Integer updateAppealStatus(Integer id);
}
