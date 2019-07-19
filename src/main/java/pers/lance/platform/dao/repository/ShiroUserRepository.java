package pers.lance.platform.dao.repository;

import pers.lance.platform.bean.entity.ShiroUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Shiro User Repository
 *
 * @author lance
 * @date 2018-05-06
 */
@Repository
public interface ShiroUserRepository extends JpaRepository<ShiroUser, Long> {

    /**
     * 通过username获取用户
     *
     * @param username
     * @return
     */
    ShiroUser findFirstByUsername(String username);

}
