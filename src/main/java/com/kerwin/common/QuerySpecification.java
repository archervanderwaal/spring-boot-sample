package com.kerwin.common;

import com.kerwin.condition.Condition;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kerwin on 2016/5/9.
 * Query sleep/play/heartRate Specification
 */
public class QuerySpecification<T> {

    private Specification<T> specification;

    public QuerySpecification(Condition condition) {
        this.specification = setSpecification(condition);
    }

    public Specification<T> getSpecification() {
        return this.specification;
    }

    private Specification<T> setSpecification(Condition condition) {
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();

//            Join<T, User> userJoin = root.join(root.getModel().getSingularAttribute("user", User.class), JoinType.LEFT);

//            SetJoin<T,User> userJoin = root.join(root.getModel().getSet("userId",User.class) , JoinType.LEFT);

            if (!StringUtils.isBlank(condition.getName())) {
//                list.add(cb.like(userJoin.get("name").as(String.class), "%" + condition.getName() + "%"));
                list.add(cb.like(root.join("user").get("name").as(String.class), "%" + condition.getName() + "%"));
            }
            if (!StringUtils.isBlank(condition.getImei())) {
//                list.add(cb.equal(userJoin.get("imei").as(String.class), condition.getImei()));
                list.add(cb.equal(root.join("user").get("imei").as(String.class), condition.getImei()));
            }
            query.where(list.toArray(new Predicate[list.size()]));
            return null;
        };
    }
}
