package com.kerwin.controller.html;

import com.kerwin.common.SimpleData;
import com.kerwin.model.Admin;
import com.kerwin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kerwin on 2016/4/20.
 * Controller by Admin
 */
@Controller
@RequestMapping()
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "admin_login.html")
    public String adminLogin(HttpServletRequest request, Model model, Admin admin) {
        SimpleData data = SimpleData.newItem();
        String name;
        log.info("========================================Admin Login Start=============================================");
        Admin temp = adminService.login(request, admin);
        if (null != temp) {
            model.addAttribute("adminInfo", temp);
            data.setCode(1).setMessage("登录成功！");
            name = "index";
            log.info("Admin {} login success! ==================== Login firstTime is {},==================== Login ip is {}!====================", temp.getName(), new DateTime(temp.getLastLoginTime()), temp.getLastLoginIP());
        } else {
            data.setCode(-1).setMessage("用户名或密码错误！");
            name = "extra_404_option1";
            log.info("Admin {} login failure! ======================= username or password error!=======================", admin.getName());
        }
        //JSONObject  json = new JSONObject(data.setData(temp));
        return name;
    }

    @RequestMapping(value = "admin_add.html")
    public ModelAndView adminAdd(HttpServletRequest request, Admin admin) {
        ModelAndView mv = new ModelAndView();

        // 检查参数是否合法
        if (StringUtils.isBlank(admin.getName()) || StringUtils.isBlank(admin.getPassword()) || StringUtils.isBlank(admin.getEmail()))
            return null;
        int result = adminService.addAdmin(request, admin);
        switch (result) {
            case -2:
                /**
                 * 用户名已经存在
                 */
                mv.setViewName("extra_404_option1");
                break;
            case -1:
                /**
                 * 出现错误
                 */
                mv.setViewName("extra_404_option2");
                break;
            case 0:
                /**
                 * 失败
                 */
                mv.setViewName("extra_404_option3");
                break;
            case 1:
                /**
                 * 增加成功
                 */
                mv.setViewName("login");
                break;
        }
        return mv;
    }

    @RequestMapping(value = "user_login_monitoring.html")
    public ModelAndView showLogRe(String name, @PageableDefault(sort = {"lastLoginTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("user_login_monitoring");
        Page<Admin> admins = adminService.findAll(name, pageable);
        mv.addObject("admins", admins);
        return mv;
    }
}