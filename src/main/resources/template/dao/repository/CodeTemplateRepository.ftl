package pers.lance.platform.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.lance.platform.bean.entity.CodeTemplate;

/**
 * ${module} Repository
 *
 * @author ${author}
 * @date ${date}
 */
@Repository
public interface CodeTemplateRepository extends JpaRepository<CodeTemplate, Long> {

}
