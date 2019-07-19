package pers.lance.platform.dao.repository;

import pers.lance.platform.bean.entity.ShiroRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Shiro Role Repository
 *
 * @author lance
 * @date 2018-05-06
 */
@Repository
public interface ShiroRoleRepository extends JpaRepository<ShiroRole, Long> {

    /**
     * 通过code获取唯一角色
     *
     * @param code
     * @return
     */
    List<ShiroRole> findByCode(String code);

}
