package com.kerwin.repository;

import com.kerwin.model.AppBindWatch;
import com.kerwin.model.AppUser;
import com.kerwin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Kerwin on 2016/5/28.
 * app user and watch user bind info repository
 */
public interface AppBindWatchRepository extends JpaRepository<AppBindWatch, Integer>, JpaSpecificationExecutor<AppBindWatch> {

    /**
     * 通过手表用户信息查询已经绑定的app用户
     *
     * @param user 手表用户信息
     * @return 与当前手机用户绑定的信息
     */
    List<AppBindWatch> findByUser(User user);

    /**
     * 通过app用户信息查询已经绑定的说表用户信息
     *
     * @param appUser app用户信息
     * @return 与当前app用户绑定的信息
     */
    List<AppBindWatch> findByAppUser(AppUser appUser);

    /**
     * 通过app用户信息及watch用户信息查询绑定信息
     *
     * @param user    watch用户信息
     * @param appUser app用户信息
     * @return 绑定信息
     */
    AppBindWatch findByUserAndAppUser(User user, AppUser appUser);
}
