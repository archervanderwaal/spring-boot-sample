package com.kerwin.controller.html;

import com.kerwin.common.SimpleData;
import com.kerwin.model.Dept;
import com.kerwin.service.DeptService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kerwin on 2016/5/11 0011.
 * dept controller
 */
@Controller
@RequestMapping
public class DeptController {
    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

//    List<Map<String, Object>> list = null;

    @Autowired
    private DeptService deptService;

    //搜索指定部门信息
    @RequestMapping(value = "department.html", method = RequestMethod.GET)
    public ModelAndView findAll(String name, @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("department");
        Page<Dept> deptPage = deptService.findAll(name, pageable);
//        list = new ArrayList<>();
//        for (Dept dept : deptPage) {
//            list.add(dept.toMap());
//        }
//        mv.addObject("dept", new PageImpl<>(list, pageable, deptPage.getTotalElements()));
        mv.addObject("dept", deptPage);
        log.info("==============com.moneyLike.controller.html.DeptController(name={})=================", name);
        return mv;
    }

    /**
     * 新增部门信息
     *
     * @param dept
     * @return
     */
    @RequestMapping(value = "save_department.html", method = RequestMethod.GET)
    public SimpleData addDept(Dept dept) {
        SimpleData data = SimpleData.newItem();
        dept = deptService.save(dept);
        if (null == dept) data.setCode(0);
        else data.setCode(1);
        JSONObject json = new JSONObject();
        return null;
    }

    /**
     * 修改部门信息
     *
     * @param dept
     * @return
     */
    @RequestMapping(value = "update_department.html", method = RequestMethod.GET)
    @ResponseBody
    public SimpleData updateDept(Dept dept) {
        SimpleData data = SimpleData.newItem();
        deptService.updateDeptName(dept);
        if (null == dept) data.setCode(0);
        else data.setCode(1);
        //JSONObject json = new JSONObject();
        return data;
    }
//    @RequestMapping(value = "create_user.html")
//    @ResponseBody
//    public String createUser(User user) {
//        SimpleData data = SimpleData.newItem();
//        user = userService.create(user);
//        if (null == user) data.setCode(0);
//        else data.setCode(1);
//        return new JSONObject(data).toString();
//    }
}
