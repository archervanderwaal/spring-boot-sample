package com.kerwin.controller.html;

import com.kerwin.condition.Condition;
import com.kerwin.model.Sleep;
import com.kerwin.service.SleepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kerwin on 2016-05-06.
 * Sleep quality controller
 */
@Controller
@RequestMapping
public class SleepController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private SleepService sleepService;

    @RequestMapping(value = "/sleep_quality.html")
    public ModelAndView sleepQualityByNameAndImei(Condition condition, @PageableDefault(sort = {"statistical"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("sleep_quality");
        Page<Sleep> sleeps = sleepService.findBySpecification(condition, pageable);
        mv.addObject("sleeps", sleeps);
        return mv;
    }
}
