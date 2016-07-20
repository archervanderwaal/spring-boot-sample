package com.kerwin.controller.html;

import com.kerwin.common.SimpleData;
import com.kerwin.condition.AppealCondition;
import com.kerwin.model.Appeal;
import com.kerwin.model.User;
import com.kerwin.service.AppealService;
import com.kerwin.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.kerwin.calculate.UserCalculate.calcUserInfo;

/**
 * Created by Kerwin on 2016/5/11.
 * 诉求管理
 */
@Controller
@RequestMapping
public class AppealController {
    private static final Logger log = LoggerFactory.getLogger(AppealController.class);

    @Autowired
    private AppealService appealService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "rescue_details.html")
    public ModelAndView showAppealInfo(AppealCondition appealCondition, @PageableDefault(sort = {"startTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("rescue_details");
        Page<Appeal> page = appealService.findAll(appealCondition, "SaveRescue", pageable);
        log.info("==============================showAppealInfo(page={})==================================", page.getContent().size());
        List<Map<String, Object>> list = new ArrayList<>();
        for (Appeal appeal : page) {
            list.add(appeal.toMap());
        }
        log.info("================================showAppealInfo(list.size={})=============================", list.size());
        mv.addObject("appeals", new PageImpl<>(list, pageable, page.getTotalElements()));
        return mv;
    }

    /**
     * 修改诉求状态
     *
     * @param id .
     * @return .
     */
    @RequestMapping(value = "update_appeal_status", method = RequestMethod.POST)
    @ResponseBody
    public String updateAppealStatus(Integer id) {
        log.info("====================================update_appeal_status(id={})====================================", id);
        SimpleData data = appealService.updateAppealStatus(id);
        JSONObject json = new JSONObject(data);
        log.info(json.toString());
        return json.toString();
    }

    /**
     * @param appealCondition 救援统计条件查询类 条件为Null时默认查询全部
     * @param pageable        分页和默认分页信息(PageableDefault)
     * @return .
     */
    @RequestMapping("rescue_details1.html")
    public ModelAndView implement_statistics(AppealCondition appealCondition, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("rescue_details1");
        Page<Appeal> page = appealService.findAll(appealCondition, "rescue_details1", pageable);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Appeal appeal : page) {
            list.add(appeal.toMap());
        }
        mv.addObject("appeals", new PageImpl<>(list, pageable, page.getTotalElements()));
        return mv;
    }

    /**
     * 诉求状态跟踪
     *
     * @param appealCondition 条件类
     * @param pageable        .
     * @return .
     */
    @RequestMapping("demand_status_track.html")
    public ModelAndView demand_statusUpdata(AppealCondition appealCondition, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("demand_status_track");
        Page<Appeal> page = appealService.findAll(appealCondition, "appealStatusTrack", pageable);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Appeal appeal : page) {
            list.add(appeal.toMap());
        }
        mv.addObject("demandInfo", new PageImpl<>(list, pageable, page.getTotalElements()));
        return mv;
    }

    /**
     * 新增诉求订单根据IMEI查询出当前用户信息
     *
     * @param watchImei 手表标识
     * @return .
     */
    @RequestMapping("ajax_searchByImei.html")
    @ResponseBody
    public String ajaxSeach(String watchImei) {
        User user = calcUserInfo(userService.findByImei(watchImei));
        return new JSONObject(user).toString();
    }

    /**
     * 新增诉求订单
     *
     * @param appeal 订单
     * @param userId 订单用户编号
     * @return .
     */
    @RequestMapping("save_post.html")
    @ResponseBody
    public String save(Appeal appeal, Integer userId) {
        User user = userService.findById(userId);
        appeal.setUser(user);
        appealService.save(appeal);
        if (appeal.getUrgent()) {
            //启动报警器 调度救援人员
        }
        return "增加订单成功";
    }

    /**
     * 历史订单查询
     *
     * @param appealCondition 条件类
     * @param pageable        .
     * @return .
     */
    @RequestMapping(value = "appeal_historical_record.html")
    public ModelAndView appealHistorical(AppealCondition appealCondition, @PageableDefault(sort = {"startTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("appeal_historical_record");
        Page<Appeal> page = appealService.findAll(appealCondition, "rescue_details1", pageable);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Appeal appeal : page) {
            list.add(appeal.toMap());
        }
        mv.addObject("appeals", new PageImpl<>(list, pageable, page.getTotalElements()));
        return mv;
    }
}
