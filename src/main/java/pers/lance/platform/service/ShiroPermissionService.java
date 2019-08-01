package pers.lance.platform.service;

import com.github.pagehelper.PageInfo;
import pers.lance.platform.bean.dto.ShiroPermissionDTO;
import pers.lance.platform.bean.query.ShiroPermissionQuery;
import pers.lance.platform.bean.vo.ShiroPermissionVO;

import java.util.List;

/**
 * shiro 权限许可 Service
 *
 * @author lance
 * @date 2019-07-25T10:46:38.217
 */
public interface ShiroPermissionService {

    /**
     * 通过 userId 列表查询获取 ShiroPermissionVO 结果集
     *
     * @param userId 用户id
     * @return 列表返回 ShiroPermissionVO
     */
    List<ShiroPermissionVO> listShiroPermissionVOByUserId(String userId);

    /**
     * 分页查询获取 ShiroPermissionVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 ShiroPermissionVO
     */
    PageInfo<ShiroPermissionVO> pageShiroPermissionVO(ShiroPermissionQuery queryParams);

    /**
     * 通过id获取DemoVO
     *
     * @param id 对象id
     * @return 对应的 ShiroPermissionVO 记录
     */
    ShiroPermissionVO getShiroPermissionVO(String id);

    /**
     * 保存实体
     *
     * @param dto 数据传输对象
     * @return 存储id
     */
    String save(ShiroPermissionDTO dto);

    /**
     * 更新实体
     *
     * @param dto 数据传输对象
     */
    void update(ShiroPermissionDTO dto);

    /**
     * 通过id删除实体
     *
     * @param id 对象id
     */
    void delete(Long id);
}
