package com.kerwin.repository;

import com.kerwin.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Kerwin on 2016/4/26.
 * AppUser Repository
 */
public interface AppUserRepository extends JpaRepository<AppUser, Integer>, JpaSpecificationExecutor<AppUser> {
    /**
     * 使用手机号判断APP用户是否存在
     *
     * @param phone 手机号
     * @return App用户
     */
    AppUser findByPhone(String phone);
    /**
     * 找回用户密码
     *
     * @param password 密码
     * @param phone    手机号码
     * @return .
     */
    @Modifying
    @Query(value = "UPDATE AppUser a SET a.password = ?1 WHERE a.phone = ?2")
    Integer updatePassword(String password, String phone);

    /**
     * 修改用户头像
     *
     * @param avatarPath 头像路径
     * @param id         用户编号
     * @return .
     */
    @Modifying
    @Query(value = "UPDATE AppUser a SET a.avatar = ?1 WHERE a.id = ?2")
    Integer updateAvatar(String avatarPath, Integer id);

    /**
     * 通过app用户编号修改用户信息
     *
     * @param id     手机用户编号
     * @param userId 手表用户编号
     * @return .
     */
    @Modifying
    @Query(value = "UPDATE AppUser a SET a.userId = :userId WHERE a.id = :id")
    Integer updateUserIdById(@Param("id") Integer id, @Param("userId") Integer userId);

    /**
     * 性别统计
     *
     * @param gender 性别
     * @return .
     */
    @Query(value = "SELECT COUNT(a) FROM AppUser a WHERE a.gender = ?1")
    Long findByGender(String gender);


}
