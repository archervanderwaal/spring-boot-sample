package com.kerwin.repository;

import com.kerwin.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Vega on 2016/5/21 0021.
 * compliant repository
 */
public interface CompliantRepository extends JpaRepository<Complaint, Integer>, JpaSpecificationExecutor<Complaint> {
}
