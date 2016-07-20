package com.kerwin.service;

import com.kerwin.common.SimpleData;
import com.kerwin.condition.AppealCondition;
import com.kerwin.model.Appeal;
import com.kerwin.repository.AppealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kerwin on 2016/5/11.
 * Appeal service
 */
@Service
public class AppealService {
    private static final Logger log = LoggerFactory.getLogger(AppealService.class);
    @Autowired
    private AppealRepository appealRepository;

    /**
     * 查询全部或通过条件查询诉求信息
     *
     * @param appealCondition 条件
     * @param pageable        分页信息
     * @return 诉求信息
     */
    public Page<Appeal> findAll(final AppealCondition appealCondition, String option, Pageable pageable) {
        int page = pageable.getPageNumber();
        if (page != 0) pageable = new PageRequest(page - 1, pageable.getPageSize(), pageable.getSort());
        return appealRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //StringUtils.isNotBlank
//            if (!StringUtils.isBlank(appealCondition.getName()))
//                predicates.add(cb.like(root.join("user").get("name").as(String.class), "%" + appealCondition.getName() + "%"));
//
//            if (!StringUtils.isBlank(appealCondition.getPhone()))
//                predicates.add(cb.equal(root.join("user").get("phone").as(String.class), appealCondition.getPhone()));
//
//            if (!StringUtils.isBlank(appealCondition.getEmpId()))
//                predicates.add(cb.equal(root.join("helpEmployee").get("id").as(String.class), appealCondition.getEmpId()));

//            switch (option) {
//                case "SaveRescue":
//                    predicates.add(cb.equal(root.get("status").as(Integer.class), 0));
//                    predicates.add(cb.equal(root.get("scheduling").as(Integer.class), 2));
//                    break;
//                case "rescue_details1":
//                    predicates.add(cb.equal(root.get("status").as(Integer.class), 1));
//                    break;
//                case "appealStatusTrack":
//                    predicates.add(cb.notEqual(root.get("status").as(Integer.class), 1));
//                    predicates.add(cb.notEqual(root.get("scheduling").as(Integer.class), 3));
//                    break;
//            }
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageable);
    }

    /**
     * 新增诉求信息
     *
     * @param appeal 诉求信息
     * @return 诉求信息
     */
    public Appeal save(Appeal appeal) {
        Appeal temp = null;
        try {
            if (null != appeal)
                temp = appealRepository.save(appeal);
        } catch (Exception ex) {
            log.error("", ex);
            ex.printStackTrace();
        }
        return temp;
    }

    /**
     * 修改诉求状态
     *
     * @param id 诉求编号
     * @return 诉求信息
     */
    @Transactional
    public SimpleData updateAppealStatus(Integer id) {
        SimpleData data = SimpleData.newItem();
        Appeal appeal;
        try {
            if (null == id) data.setCode(-1).setMessage("诉求信息编号不能为空！");
            else {
                appeal = appealRepository.findOne(id);
                if (null == appeal) data.setCode(0).setMessage("诉求信息不存在！");
                else {
                    int result = appealRepository.updateAppealStatus(id);
                    if (result == 1) data.setCode(1).setMessage("修改状态成功！");
                }
            }
        } catch (Exception ex) {
            log.error("", ex);
            ex.printStackTrace();
        }
        return data;
    }

    /**
     * 修改诉求内容
     *
     * @param appeal 诉求信息
     * @return 诉求信息
     */
    public Appeal update(Appeal appeal) {
        Appeal temp = null;
        try {
            if (null != appeal && null != appeal.getId() && null != appeal.getUser()) {
                temp = appealRepository.findOne(appeal.getId());
                if (null != temp) {
                    temp.setContent(appeal.getContent());
                    temp = appealRepository.save(appeal);
                }
            }
        } catch (Exception ex) {
            log.error("", ex);
            ex.printStackTrace();
        }
        return temp;
    }
}
