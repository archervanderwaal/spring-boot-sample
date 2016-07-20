package com.kerwin.controller.html;

import com.kerwin.common.SimpleData;
import com.kerwin.condition.AppUserCondition;
import com.kerwin.model.AppUser;
import com.kerwin.service.AppUserService;
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

import static com.kerwin.calculate.AppUserCalculate.calcAppUserInfo;

/**
 * Created by Administrator on 2016-05-26.
 */
@Controller
@RequestMapping(value = "", method = RequestMethod.GET)
public class AppUserController {
    @Autowired
    private AppUserService appUserService;


    /**
     * 查看用户详细资料
     *
     * @param id 用户编号
     * @return 用户详细信息
     */
    @RequestMapping(value = "appUser_details.html", method = RequestMethod.POST)
    @ResponseBody
    public String appUserDetails(Integer id) {
        SimpleData data = SimpleData.newItem();
        // 判断参数是否合法
        if (null == id) data.setCode(-1).setMessage("The app user id can not be empty!");
        else {
            // 通过用户id查询用户信息
            AppUser appUser = appUserService.findById(id);
            // 判断用户信息是否存在
            if (null == appUser) data.setCode(0).setMessage("The app user not exists!");
                // 设置结果
            else data.setCode(1).setMessage("Success!").setData(calcAppUserInfo(appUser));
        }
        return new JSONObject(data).toString();
    }


    /**
     * 筛选用户信息
     *
     * @param appUserCondition
     * @param pageable
     * @return
     */
    @RequestMapping(value = "app_user.html")
    public ModelAndView findBySpecification(AppUserCondition appUserCondition, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("app_user");
        Page<AppUser> appUserPage = appUserService.findAll(appUserCondition, pageable);
        for (AppUser appUser : appUserPage) {
            appUser = calcAppUserInfo(appUser);
        }
        mv.addObject("appUsers", appUserPage);
        return mv;
    }


    /**
     * 新增用户信息
     *
     * @param appUser
     * @return
     */
    @RequestMapping(value = "appCreate_user.html", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String appCreateUser(@RequestBody AppUser appUser) {
        SimpleData data = SimpleData.newItem();

        // 执行新增操作
        appUser = appUserService.create(appUser);

        // 判断是否增加成功
        if (null == appUser) data.setCode(0);
        else data.setCode(1);

        // 设置结果
        return new JSONObject(data).toString();
    }
}
