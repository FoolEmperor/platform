package pers.lance.platform.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.lance.platform.bean.query.CodeTemplateQuery;
import pers.lance.platform.bean.vo.CodeTemplateVO;

import java.util.List;

/**
 * ${module} Mapper
 *
 * @author ${author}
 * @date ${date}
 */
@Mapper
public interface CodeTemplateMapper {


    /**
     * 列表查询获取 CodeTemplateVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 CodeTemplateVO
     */
    List<CodeTemplateVO> listCodeTemplateVO(CodeTemplateQuery queryParams);

    /**
     * 通过id获取 CodeTemplateVO
     *
     * @param id 对象id
     * @return 对应的 CodeTemplateVO 记录
     */
    CodeTemplateVO getCodeTemplateVO(@Param("id") String id);


}
