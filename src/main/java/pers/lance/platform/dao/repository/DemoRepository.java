package pers.lance.platform.dao.repository;

import org.springframework.data.jpa.repository.Query;
import pers.lance.platform.bean.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * demo repository
 *
 * @author lance
 * @date 2018-05-06
 */
@Repository
public interface DemoRepository extends JpaRepository<Demo, Long> {

}
