package pers.lance.platform.service;

import com.github.pagehelper.PageInfo;
import pers.lance.platform.bean.dto.CodeTemplateDTO;
import pers.lance.platform.bean.query.CodeTemplateQuery;
import pers.lance.platform.bean.vo.CodeTemplateVO;

/**
 * ${module} Service
 *
 * @author ${author}
 * @date ${date}
 */
public interface CodeTemplateService {

    /**
     * 分页查询获取 CodeTemplateVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 CodeTemplateVO
     */
    PageInfo<CodeTemplateVO> pageCodeTemplateVO(CodeTemplateQuery queryParams);

    /**
     * 通过id获取DemoVO
     *
     * @param id 对象id
     * @return 对应的 CodeTemplateVO 记录
     */
    CodeTemplateVO getCodeTemplateVO(String id);

    /**
     * 保存实体
     *
     * @param dto 数据传输对象
     * @return 存储id
     */
    String save(CodeTemplateDTO dto);

    /**
     * 更新实体
     *
     * @param dto 数据传输对象
     */
    void update(CodeTemplateDTO dto);

    /**
     * 通过id删除实体
     *
     * @param id 对象id
     */
    void delete(Long id);
}
