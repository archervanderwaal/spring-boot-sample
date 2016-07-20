package com.kerwin.service;

import com.kerwin.common.QuerySpecification;
import com.kerwin.condition.Condition;
import com.kerwin.model.Play;
import com.kerwin.model.User;
import com.kerwin.repository.PlayRepository;
import com.kerwin.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kerwin.calculate.UserCalculate.calcUserInfo;

/**
 * Created by Kerwin on 2016-04-27.
 * 运动检测业务逻辑
 */
@Service
public class PlayService {
    private static final Logger log = LoggerFactory.getLogger(PlayService.class);

    @Autowired
    private PlayRepository playRepository;
    @Autowired
    private UserService userService;

    /**
     * 增加运动信息
     *
     * @param play 运动信息
     * @return 0 => 增加失败 & 1 => 增加成功
     */
    public Integer create(Play play) {

        // 通过用户编号查询用户当天的运动信息
        Play temp = playRepository.findByUserAndToday(play.getUser().getId());

        // 如果当天存在运动信息的记录, 则修改
        if (null != temp) play.setId(temp.getId());

        // 保存运动信息
        temp = playRepository.save(play);

        // 判断是否保存成功
        if (null == temp) return 0;
        return 1;
    }

    /**
     * 查询运动信息
     *
     * @param userId   用户编号
     * @param pageable 分页信息
     * @return 符合条件的运动检测信息
     */
    public Page<Play> playShow(Integer userId, Pageable pageable) {
        // 通过用户编号查询用户的运动信息
        Page<Play> plays = playRepository.findByUserId(userId, pageable);

        for (Play play : plays) {
            // 设置运动信息周几
            play.setWeek(DateUtils.getDayOfWeek(play.getDate()));
        }

        // 返回运动信息集合
        return plays;
    }

    /**
     * 通过用户编号查询用户当天的运动信息
     *
     * @param id 用户编号
     * @return 用户当前的运动信息
     */
    public Play fundByUserAndToday(Integer id) {
        return playRepository.findByUserAndToday(id);
    }

    /**
     * 动态查询运动信息
     *
     * @param condition 查询条件类
     * @param pageable  分页信息
     * @return 运动信息集合
     */
    public Page<Play> findAll(Condition condition, Pageable pageable) {

        // 获取用户用户要查询的页数
        int page = pageable.getPageNumber();

        // 由于SpringData 的限制, 页数默认从0开始, 将非0页数-1
        if (page != 0) pageable = new PageRequest(page - 1, pageable.getPageSize(), pageable.getSort());

        // 构建动态查询条件对象
        QuerySpecification<Play> spec = new QuerySpecification<>(condition);

        // 查询用户的运动信息
        Page<Play> plays = playRepository.findAll(spec.getSpecification(), pageable);

        // 遍历查询到的运动信息, 设置相关user信息
        for (Play play : plays) {

            // 获取运动信息中的用户信息
            User user = play.getUser();

            // 设置用户信息
            play.setUser(calcUserInfo(user));
        }
        return plays;
    }

    /**
     * 通过用户编号查询运动信息
     *
     * @param userId 用户编号
     * @return 运动信息
     */
    public List<Play> findByUser(Integer userId) {
        return playRepository.findByUserId(userId);
    }
}
