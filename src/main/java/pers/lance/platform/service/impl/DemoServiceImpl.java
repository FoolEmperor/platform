package pers.lance.platform.service.impl;

import pers.lance.platform.base.util.CrudUtils;
import pers.lance.platform.bean.dto.DemoDTO;
import pers.lance.platform.bean.entity.Demo;
import pers.lance.platform.bean.query.DemoQuery;
import pers.lance.platform.bean.vo.DemoVO;
import pers.lance.platform.dao.mapper.DemoMapper;
import pers.lance.platform.dao.repository.DemoRepository;
import pers.lance.platform.service.DemoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * demo service impl
 *
 * @author lance
 * @date 2018-05-06
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoMapper demoMapper;
    @Autowired
    private DemoRepository demoRepository;


    @Override
    public List<DemoVO> list(DemoQuery queryParams) {
        return demoMapper.list(queryParams);
    }

    @Override
    public PageInfo<DemoVO> pageDemoVO(DemoQuery queryParams) {
        CrudUtils.pageBefore(queryParams);
        List<DemoVO> list = demoMapper.list(queryParams);
        return new PageInfo(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(DemoDTO dto) {
        Demo entity=CrudUtils.save(dto, Demo.class, demoRepository);
        return entity.getId().toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DemoDTO dto) {
        CrudUtils.update(dto, Demo.class, demoRepository);
    }

    @Override
    public DemoVO getDemoVO(String id) {
        return demoMapper.getDemoVO(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        demoRepository.deleteById(id);
    }
}
