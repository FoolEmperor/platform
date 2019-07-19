package pers.lance.platform.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.lance.platform.base.util.CrudUtils;
import pers.lance.platform.bean.dto.CodeTemplateDTO;
import pers.lance.platform.bean.entity.CodeTemplate;
import pers.lance.platform.bean.query.CodeTemplateQuery;
import pers.lance.platform.bean.vo.CodeTemplateVO;
import pers.lance.platform.dao.mapper.CodeTemplateMapper;
import pers.lance.platform.dao.repository.CodeTemplateRepository;
import pers.lance.platform.service.CodeTemplateService;

import javax.annotation.Resource;
import java.util.List;


/**
 * ${module} Service impl
 *
 * @author ${author}
 * @date ${date}
 */
@Service
public class CodeTemplateServiceImpl implements CodeTemplateService {

    @Resource
    private CodeTemplateMapper codeTemplateMapper;
    @Autowired
    private CodeTemplateRepository codeTemplateRepository;

    @Override
    public PageInfo<CodeTemplateVO> pageCodeTemplateVO(CodeTemplateQuery queryParams) {
        CrudUtils.pageBefore(queryParams);
        List<CodeTemplateVO> list = codeTemplateMapper.listCodeTemplateVO(queryParams);
        return new PageInfo(list);
    }

    @Override
    public CodeTemplateVO getCodeTemplateVO(String id) {
        CodeTemplateVO vo = codeTemplateMapper.getCodeTemplateVO(id);
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(CodeTemplateDTO dto) {
        CodeTemplate entity = CrudUtils.save(dto, CodeTemplate.class, codeTemplateRepository);
        return entity.getId().toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CodeTemplateDTO dto) {
        CrudUtils.update(dto, CodeTemplate.class, codeTemplateRepository);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        codeTemplateRepository.deleteById(id);
    }
}
