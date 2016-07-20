package com.kerwin.controller.html;

import com.kerwin.common.SimpleData;
import com.kerwin.condition.UserCondition;
import com.kerwin.model.User;
import com.kerwin.service.AppUserService;
import com.kerwin.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.kerwin.calculate.UserCalculate.calcUserInfo;

/**
 * Created by Kerwin on 2016-04-18.
 * User Controller
 */
@Controller
@RequestMapping(method = RequestMethod.POST)
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AppUserService appUserService;

    /**
     * 查看用户详细资料
     *
     * @param userId 用户编号
     * @return 用户详细信息
     */
    @RequestMapping(value = "user_details.html", method = RequestMethod.GET)
    public ModelAndView userDetails(Integer userId) {
        ModelAndView mv = new ModelAndView("userDetails");

        // 通过用户编号查询用户详细信息
        User user = userService.findById(userId);

        // 设置用户临时属性, 如年龄、服务状态等, 并放入model
        mv.addObject("user", calcUserInfo(user));
        return mv;
    }

    /**
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "user_update.html", method = RequestMethod.GET)
    public ModelAndView userupdate(Integer userId) {
        ModelAndView mv = new ModelAndView("userupdate");

        // 通过用户编号查询用户详细信息
        User user = userService.findById(userId);

        // 设置用户临时属性, 如年龄、服务状态等, 并放入model
        mv.addObject("user", calcUserInfo(user));
        return mv;
    }


    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return .
     */

    @RequestMapping(value = "create_user.html", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createUser(@RequestBody User user) {
        SimpleData data = SimpleData.newItem();

        // 执行新增操作
        user = userService.create(user);

        // 判断是否增加成功
        if (null == user) data.setCode(0);
        else data.setCode(1);

        // 设置结果
        return new JSONObject(data).toString();
    }


    /**
     * 筛选用户信息
     *
     * @param condition 筛选条件
     * @param pageable  分页信息
     * @return 用户基本信息
     */
    @RequestMapping(value = "watch_user.html", method = RequestMethod.GET)
    public ModelAndView watchUserSearch(UserCondition condition, @PageableDefault(sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("watch_user");
        Page<User> users = userService.findAll(condition, pageable);
        mv.addObject("users", users);
        return mv;
    }

    /**
     * 性别比例统计
     *
     * @return 性别比例
     */
    @RequestMapping(value = "sex_ratio_statistics.html", method = RequestMethod.GET)
    public ModelAndView sex_ratio_statistic(Map<String, Object> map) {
        ModelAndView mv = new ModelAndView("sex_ratio_statistics");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(appUserService.sex_ratio_statistic(map));
        list.add(userService.sex_ratio_statistic(map));
        mv.addObject("sexRatio", map);
        return mv;
    }

    /**
     * 年龄比例统计
     *
     * @return 年龄比例
     */
    @RequestMapping(value = "statistics_population.html", method = RequestMethod.GET)
    public ModelAndView age_statistic() {
        ModelAndView mv = new ModelAndView("statistics_population");
        Map<String, Object> map = userService.age_statistic();
        mv.addObject("ages", map);
        return mv;
    }
}
