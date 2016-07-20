package com.kerwin.repository;

import com.kerwin.model.JPushInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kerwin on 2016/4/30.
 * JPush Info
 */
public interface JPushRepository extends JpaRepository<JPushInfo, String> {
}
