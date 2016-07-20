package com.kerwin.controller.html;

import com.kerwin.condition.ComplaintCondition;
import com.kerwin.model.Complaint;
import com.kerwin.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vega on 2016/5/21 0021.
 * complaint controller
 */
@Controller
@RequestMapping
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    /**
     * 筛选用户信息
     *
     * @param complaintCondition 筛选条件
     * @param pageable           分页信息
     * @return 员工信息
     */
    @RequestMapping(value = "evaluate_file.html")
    public ModelAndView getEmployee(ComplaintCondition complaintCondition, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        ModelAndView mv = new ModelAndView("evaluate_file");
        Page<Complaint> complaintPage = complaintService.findBySpecification(complaintCondition, pageable);
        System.err.println(complaintPage.getContent().size());
        mv.addObject("complaints", complaintPage);
        return mv;
    }
}
