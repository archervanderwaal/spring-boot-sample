package com.kerwin.service;

import com.kerwin.condition.ComplaintCondition;
import com.kerwin.model.Complaint;
import com.kerwin.repository.CompliantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vega on 2016/5/21 0021.
 * Complaint service
 */
@Service
public class ComplaintService {
    @Autowired
    private CompliantRepository compliantRepository;

    public Page<Complaint> findBySpecification(ComplaintCondition condition, Pageable pageable) {
        int number = pageable.getPageNumber();
        if (number != 0) pageable = new PageRequest(number - 1, pageable.getPageSize(), pageable.getSort());
        return compliantRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != condition.getId())
                predicates.add(cb.equal(root.join("employee").get("id").as(Long.class), condition.getId()));
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageable);
    }
}
