package com.kerwin.repository;

import com.kerwin.model.Ordering;
import com.kerwin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

/**
 * Created by Kerwin on 2016/5/27.
 * Ordering repository
 */
public interface OrderingRepository extends JpaRepository<Ordering, Integer>, JpaSpecificationExecutor<Ordering> {

    /**
     * 通过用户及日期查询当天订餐信息
     *
     * @param user  用户
     * @param sDate 开始时间
     * @param eDate 结束时间
     * @return 订餐信息
     */
    Ordering findByUserAndDateBetween(User user, Date sDate, Date eDate);
}
