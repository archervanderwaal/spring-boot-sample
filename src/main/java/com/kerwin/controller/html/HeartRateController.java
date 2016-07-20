package com.kerwin.controller.html;

import com.kerwin.condition.Condition;
import com.kerwin.model.HeartRate;
import com.kerwin.service.HeartRateService;
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
 * Created by Vega on 2016/5/8 0008.
 * HeartRate Controller
 */
@Controller
@RequestMapping()
public class HeartRateController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private HeartRateService heartRateService;

    //搜索指定用户的心率信息
    @RequestMapping(value = "heart_rate_test.html")
    public ModelAndView heartRateByNameAndImei(Condition condition, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView();
        Page<HeartRate> heartRatePage = heartRateService.findBySpecification(condition, pageable);
//        Page<Map<String, Object>> heartRatePage = heartRateService.findBySpecification(condition, pageable);
//        log.info("=============================={}=====================================", heartRatePage.getContent().size());
//        mv.addObject("heartRate", heartRatePage);
        mv.setViewName("heart_rate_test");
        mv.addObject("heartRate", heartRatePage);
        log.info("==============com.moneylike.controller.html.HeartRateController=================");
        return mv;
    }
}