package pers.lance.platform.service;

import pers.lance.platform.bean.dto.DemoDTO;
import pers.lance.platform.bean.query.DemoQuery;
import pers.lance.platform.bean.vo.DemoVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * demo service
 *
 * @author lance
 * @date 2018-05-06
 */
public interface DemoService {

    /**
     * 列表查询获取 DemoVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 DemoVO
     */
    List<DemoVO> list(DemoQuery queryParams);

    /**
     * 分页查询获取 DemoVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 DemoVO
     */
    PageInfo<DemoVO> pageDemoVO(DemoQuery queryParams);


    /**
     * 保存实体
     *
     * @param dto 数据传输对象
     * @return 存储id
     */
    String save(DemoDTO dto);

    /**
     * 更新实体
     *
     * @param dto 数据传输对象
     */
    void update(DemoDTO dto);

    /**
     * 通过id获取DemoVO
     *
     * @param id 对象id
     * @return 对应的 DemoVO 记录
     */
    DemoVO getDemoVO(String id);

    /**
     * 通过id删除实体
     *
     * @param id 对象id
     */
    void delete(Long id);
}
