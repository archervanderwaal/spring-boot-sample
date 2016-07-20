package com.kerwin.controller.html;

import com.kerwin.utils.CSVUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kerwin on 2016-05-12.
 * Watch controller
 */
@Controller
@RequestMapping
public class WatchController {
    private List<Map<String, Object>> list = null;

//    @Autowired
//    private WatchService watchService;

//    @RequestMapping(value = "device_management.html")
//    public ModelAndView device(WatchCondition watchCondition, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
//        ModelAndView mv = new ModelAndView("device_management");
//        Page<Watch> page = watchService.findAll(watchCondition, pageable);
//        list = new ArrayList<>();
//        for (Watch watch : page) {
//            list.add(watch.toSimpleMap());
//        }
//        return mv.addObject("watchInfo", new PageImpl<>(list, pageable, page.getTotalElements()));
//    }


    /**
     * 新增当前页数据批量导出为CVS数据
     *
     * @param response .
     * @return .
     */
    @RequestMapping(value = "/ajax_outputdata")
    public String ajaxOutputData(HttpServletResponse response) {
        //循环向加载CVS加载数据 数据来源于分页存储的List中
        List<Map<String, Object>> exportData = new ArrayList<>();
        for (Map<String, Object> watch : list) {
            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("1", watch.get("name"));
            row1.put("2", watch.get("phone"));
            row1.put("3", watch.get("imei"));
            row1.put("4", watch.get("serviceStatus"));
            exportData.add(row1);
        }
        //CVS文件头
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1", "姓名");
        map.put("2", "手机号");
        map.put("3", "手表标识");
        map.put("4", "服务状态");

        CSVUtils.createCVSAndOutput(exportData, map, response);

        return "device_management";
    }
}
