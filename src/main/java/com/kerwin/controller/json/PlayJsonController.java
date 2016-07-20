package com.kerwin.controller.json;

import com.kerwin.common.SimpleData;
import com.kerwin.model.Play;
import com.kerwin.model.User;
import com.kerwin.service.PlayService;
import com.kerwin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kerwin on 2016-04-26.
 * Play Controller
 */
@RestController
@RequestMapping(value = "/play", method = RequestMethod.POST)
public class PlayJsonController {
    private static final Logger log = LoggerFactory.getLogger(PlayJsonController.class);
    @Autowired
    private PlayService playService;
    @Autowired
    private UserService userService;

    /**
     * 新增用户运动信息
     *
     * @param play 运动信息
     * @param imei 用户手表标识
     * @return .
     */
    @RequestMapping(value = "add.json")
    public SimpleData add(Play play, String imei) {
        SimpleData data = SimpleData.newItem();

        // 判断参数是否合法
        if (StringUtils.isBlank(imei) || 15 != imei.length())
            return data.setCode(-1).setMessage("The IMEI is empty or length is not 15!");

        // 判断运动类型是否为空, 如果为空, 默认设置为走路
        if (StringUtils.isBlank(play.getType())) play.setType("走路");

        // 通过imei查询用户信息
        User user = userService.findByImei(imei);

        // 判断用户信息是否存在
        if (null == user) return data.setCode(0).setMessage("The User info not exists!");

        // 给运动信息设置用户
        play.setUser(user);

        // 保存运动信息
        int result = playService.create(play);

        // 设置结果
        return data.setCode(result);
    }

    /**
     * 查询指定用户的近期天的运动信息
     *
     * @param userId   用户编号
     * @param pageable 分页信息
     * @return .
     */
    @RequestMapping(value = "show.json")
    public SimpleData show(Integer userId, @PageableDefault(value = 7, sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable) {
        SimpleData data = SimpleData.newItem();

        // 检查参数是否合法
        if (null == userId) return data.setCode(-1).setMessage("The UserId can not empty!");

        // 判断用户是否存在
        if (!userService.exists(userId)) data.setCode(0).setMessage("The User info no exists!");

        // 查询
        Page<Play> plays = playService.playShow(userId, pageable);

        // 将运动数据中的用户信息制空， 避免出现栈溢出等异常
        for (Play play : plays) {
            play.setUser(null);
        }

        // 设置查询结果
        return data.setCode(1).setMessage("Success").setData(plays);
    }

    /**
     * 通过用户编号查询用户当天的运动信息
     *
     * @param userId 用户编号
     * @return 用户当前的运动信息
     */
    @RequestMapping(value = "showToday.json")
    public SimpleData showToday(Integer userId) {
        SimpleData data = SimpleData.newItem();

        // 检查参数是否合法
        if (null == userId) return data.setCode(-1).setMessage("The userId can not empty!");

        // 判断用户是否存在
        if (!userService.exists(userId)) return data.setCode(0).setMessage("The User info not exists!");

        // 查询用户当天的运动信息
        Play play = playService.fundByUserAndToday(userId);

        // 判断用户当天是否存在运动数据
        if (null == play) return data.setCode(0).setMessage("Not find any corresponding data!");

        // 设置查询结果
        return data.setCode(1).setMessage("Success").setData(play);
    }
}
