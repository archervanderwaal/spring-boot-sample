package com.kerwin.controller;

import com.kerwin.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Tiamo on 2016/4/20.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("admin", new Admin());
        mv.setViewName("login");
        return mv;
    }


    @RequestMapping(value = "login.html")
    public ModelAndView logout() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("admin", new Admin());
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "jurisdiction.html")
    public ModelAndView jurisdiction() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jurisdiction");
        return mv;
    }
}
