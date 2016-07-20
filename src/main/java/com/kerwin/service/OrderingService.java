package com.kerwin.service;

import com.kerwin.model.Ordering;
import com.kerwin.model.User;
import com.kerwin.repository.OrderingRepository;
import com.kerwin.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Kersin on 2016/5/27.
 * Ordering service
 */
@Service
public class OrderingService {
    @Autowired
    private OrderingRepository orderingRepository;

    /**
     * 通过用户信息及日期查询当天的订餐信息
     *
     * @param user 用户信息
     * @param date 日期
     * @return 订餐信息
     */
    public Ordering findByUserAndDate(User user, Date date) {
        return orderingRepository.findByUserAndDateBetween(user, date, DateUtils.addDay(date, 1));
    }
}
