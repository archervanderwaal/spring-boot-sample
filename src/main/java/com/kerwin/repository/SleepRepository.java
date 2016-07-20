package com.kerwin.repository;

import com.kerwin.model.Sleep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Kwok on 2016/4/26.
 * Repository by Sleep
 */
public interface SleepRepository extends JpaRepository<Sleep, Integer>, JpaSpecificationExecutor<Sleep> {
    /**
     * 通过用户信息查询睡眠信息
     *
     * @param userId   用户信息
     * @param pageable 分页信息
     * @return 睡眠信息
     */
    Page<Sleep> findByUserId(Integer userId, Pageable pageable);
}
