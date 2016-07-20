package com.kerwin.service;

import com.kerwin.common.QuerySpecification;
import com.kerwin.condition.Condition;
import com.kerwin.model.Sleep;
import com.kerwin.model.User;
import com.kerwin.repository.SleepRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.kerwin.calculate.SleepCalculate.calcSleepInfo;
import static com.kerwin.calculate.UserCalculate.calcUserInfo;

/**
 * Created by Kerwin on 2016/4/25.
 * 运动信息
 */
@Service
public class SleepService {
    private static final Logger log = LoggerFactory.getLogger(SleepService.class);

    @Autowired
    private SleepRepository sleepRepository;
    @Autowired
    private UserService userService;

    /**
     * 新增睡眠质量信息
     *
     * @param sleep 睡眠质量信息
     * @return 0 => 增加失败 & 1 => 增加成功
     */
    public Integer create(Sleep sleep) {
        sleep = sleepRepository.save(calcSleepInfo(sleep));

        // 判断是否保存成功
        if (null == sleep) return 0;
        return 1;
    }

    /**
     * 通过用户编号，分页查询睡眠质量信息
     *
     * @param id       用户编号
     * @param pageable 分页信息
     * @return 符合条件的睡眠质量信息
     */
    public Page<Sleep> sleepQualityShow(Integer id, Pageable pageable) {
        return sleepRepository.findByUserId(id, pageable);
    }

    /**
     * 睡眠质量分页查询
     *
     * @param condition 查询睡眠指令条件类
     * @param pageable  分页信息
     * @return 睡眠质量信息
     */
    public Page<Sleep> findBySpecification(Condition condition, Pageable pageable) {

        // 获取用户要查询的页数
        int page = pageable.getPageNumber();

        // 由于分页是从0页开始, 不为0时-1
        if (page != 0) pageable = new PageRequest(page - 1, pageable.getPageSize(), pageable.getSort());

        // 构建动态查询条件对象
        QuerySpecification<Sleep> spec = new QuerySpecification<>(condition);

        // 查询
        Page<Sleep> sleeps = sleepRepository.findAll(spec.getSpecification(), pageable);

        // 遍历查询到的睡眠信息, 设置相关user信息
        for (Sleep sleep : sleeps) {

            // 获取睡眠信息中的用户信息
            User user = sleep.getUser();

            // 设置用户信息
            sleep.setUser(calcUserInfo(user));
        }
        return sleeps;
    }

}
