package pers.lance.platform.dao.repository;

import pers.lance.platform.bean.entity.ShiroPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * shiro 权限许可 Repository
 *
 * @author lance
 * @date 2019-07-25T10:46:38.217
 */
@Repository
public interface ShiroPermissionRepository extends JpaRepository<ShiroPermission, Long> {

}
