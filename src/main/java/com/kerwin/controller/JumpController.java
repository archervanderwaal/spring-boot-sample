package com.kerwin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class JumpController {
    // 首页
    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }

    // 救援人员管理
    @RequestMapping("/rescue_personnel_supervise.html")
    public String rescue_personnel_supervise() {
        return "rescue_personnel_supervise";
    }

    // 救援人员地图
    @RequestMapping("/rescue_workers_map.html")
    public String rescue_workers_map() {
        return "rescue_workers_map";
    }

    // 救援物品登记
    @RequestMapping("/registration_supplies.html")
    public String registration_supplies() {
        return "registration_supplies";
    }

//    // 评价存档
//    @RequestMapping("/evaluate_file.html")
//    public String evaluate_file() {
//        return "evaluate_file";
//    }

    // 运动测试
    @RequestMapping("/test_play.html")
    public String test_play() {
        return "test_play";
    }

    //骑车
    @RequestMapping("ride_bike.html")
    public String ride_bike() {
        return "ride_bike";
    }

    //跑步
    @RequestMapping("run.html")
    public String run() {
        return "run";
    }

    //登山
    @RequestMapping("mountaineering.html")
    public String mountaineering() {
        return "mountaineering";
    }

    // 围栏设置
    @RequestMapping("/set_fence.html")
    public String set_fence() {
        return "set_fence";
    }

    // 订单处理
    @RequestMapping("/order_processing.html")
    public String order_processing() {
        return "order_processing";
    }

    // 充电提醒
    @RequestMapping("/charging_reminder.html")
    public String charging_reminder() {
        return "charging_reminder";
    }

    // 饮食建议
    @RequestMapping("/dietary_advice.html")
    public String dietary_advice() {
        return "dietary_advice";
    }

    // 生日提醒
    @RequestMapping("/birthday_reminder.html")
    public String birthday_reminder() {
        return "birthday_reminder";
    }

    // 健康建议
    @RequestMapping("/health_advice.html")
    public String health_advice() {
        return "health_advice";
    }

    // 用药提醒
    @RequestMapping("/medication_reminders.html")
    public String medication_reminders() {
        return "medication_reminders";
    }

    // 天气播报
    @RequestMapping("/weather_broadcast.html")
    public String weather_broadcast() {
        return "weather_broadcast";
    }

    // 呼叫用户管理
    @RequestMapping("/call_user_management.html")
    public String call_user_management() {
        return "call_user_management";
    }

    // 电话呼叫记录
    @RequestMapping("/telephone_call_record.html")
    public String telephone_call_record() {
        return "telephone_call_record";
    }

    // 电话诉求体系统计
    @RequestMapping("/telephone_appeals.html")
    public String telephone_appeals() {
        return "telephone_appeals";
    }

//    // 性别比例统计
//    @RequestMapping("/sex_ratio_statistics.html")
//    public String sex_ratio_statistics() {
//        return "sex_ratio_statistics";
//    }

    // 健康比例统计
    @RequestMapping("/health.html")
    public String health() {
        return "health";
    }

//    // 各年龄段人数统计
//    @RequestMapping("/statistics_population.html")
//    public String statistics_population() {
//        return "statistics_population";
//    }

    // 志愿者信息管理
    @RequestMapping("/volunteer.html")
    public String volunteer() {
        return "volunteer";
    }
}
