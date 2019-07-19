package pers.lance.platform.dao.mapper;

import pers.lance.platform.bean.query.DemoQuery;
import pers.lance.platform.bean.vo.DemoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * demo Mapper
 *
 * @author lance
 * @date 2018-05-06
 */
@Mapper
public interface DemoMapper {


    /**
     * 列表查询获取 DemoVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 DemoVO
     */
    List<DemoVO> list(DemoQuery queryParams);

    /**
     * 通过id获取 DemoVO
     *
     * @param id 对象id
     * @return 对应的 DemoVO 记录
     */
    DemoVO getDemoVO(@Param("id") String id);

}
