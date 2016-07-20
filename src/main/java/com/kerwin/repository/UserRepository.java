package com.kerwin.repository;

import com.kerwin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Kwok on 2016/4/26.
 * Repository by User
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    /**
     * 通过IMEI获取用户信息
     *
     * @param imei 手表IMEI
     * @return 用户信息
     */
    User findByImei(String imei);

    /**
     * 通过app用户编号查询用户信息
     *
     * @param appUserId app用户编号
     * @return 用户信息
     */
    List<User> findByAppUserId(Integer appUserId);

    /**
     * 通过手表编号修改手表电量信息
     *
     * @param battery 手表电量
     * @param id      手表编号
     * @return .
     */
    @Modifying
    @Query(value = "UPDATE User u SET u.battery = ?1 WHERE u.id = ?2")
    Integer updateBattery(Integer battery, Integer id);

    /**
     * 性别统计
     *
     * @param gender 性别
     * @return .
     */
    @Query(value = "SELECT COUNT(u) FROM User u WHERE u.gender = ?1")
    Long findByGender(String gender);

    /**
     * 设置用户当前所在经纬度
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param id        用户编号
     * @return .
     */
    @Modifying
    @Query(value = "UPDATE User u SET u.longitude = ?1, u.latitude = ?2 WHERE u.id = ?3")
    Integer updateLocation(String longitude, String latitude, Integer id);

    /**
     * 修改绑定app用户
     *
     * @param appUserId app用户id
     * @param id        用户id
     * @return .
     */
    @Modifying
    @Query(value = "UPDATE User u SET u.appUserId = ?1 WHERE u.id = ?2")
    Integer updateAppUser(Integer appUserId, Integer id);

    /**
     * 年龄统计
     *
     * @param begin 开始年龄值
     * @param end   结束年龄值
     * @return .
     */
    @Query(value = "SELECT COUNT(*) FROM m_user WHERE to_char(sysdate , 'yyyy') - to_char(user_birthday, 'yyyy') between ?1 AND ?2", nativeQuery = true)
    Long findByAge(Integer begin, Integer end);
}