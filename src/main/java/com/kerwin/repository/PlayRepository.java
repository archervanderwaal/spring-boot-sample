package com.kerwin.repository;

import com.kerwin.model.Play;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Kerwin on 2016/4/30.
 * 运动信息
 */
public interface PlayRepository extends JpaRepository<Play, Integer>, JpaSpecificationExecutor<Play> {
    /**
     * 通过用户信息查询运动信息
     *
     * @param userId   用户信息
     * @param pageable 分页信息
     * @return 用户信息集合
     */
    Page<Play> findByUserId(Integer userId, Pageable pageable);

    /**
     * 通过用户编号查询运动信息
     *
     * @param userId 用户信息
     * @return 运动信息
     */
    List<Play> findByUserId(Integer userId);

    /**
     * 查询用户当天的运动信息
     *
     * @param userId 用户编号
     * @return 运动信息集合
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM m_play p WHERE p.user_id = ?1 AND play_time > TO_DATE(TO_CHAR(sysdate, 'yyyy-MM-dd'), 'yyyy-MM-dd')")
    Play findByUserAndToday(Integer userId);
}
