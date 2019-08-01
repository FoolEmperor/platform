package pers.lance.platform.service;

import com.github.pagehelper.PageInfo;
import pers.lance.platform.bean.dto.ShiroRoleDTO;
import pers.lance.platform.bean.query.ShiroRoleQuery;
import pers.lance.platform.bean.vo.ShiroRoleVO;

import java.util.List;

/**
 * 角色 Service
 *
 * @author lance
 * @date 2019-07-25T11:04:34.531
 */
public interface ShiroRoleService {

    /**
     * 通过 userId 列表查询获取 ShiroRoleVO 结果集
     *
     * @param userId 用户id
     * @return 列表返回 ShiroRoleVO
     */
    List<ShiroRoleVO> listShiroRoleVOByUserId(String userId);

    /**
     * 分页查询获取 ShiroRoleVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 ShiroRoleVO
     */
    PageInfo<ShiroRoleVO> pageShiroRoleVO(ShiroRoleQuery queryParams);

    /**
     * 通过id获取DemoVO
     *
     * @param id 对象id
     * @return 对应的 ShiroRoleVO 记录
     */
    ShiroRoleVO getShiroRoleVO(String id);

    /**
     * 保存实体
     *
     * @param dto 数据传输对象
     * @return 存储id
     */
    String save(ShiroRoleDTO dto);

    /**
     * 更新实体
     *
     * @param dto 数据传输对象
     */
    void update(ShiroRoleDTO dto);

    /**
     * 通过id删除实体
     *
     * @param id 对象id
     */
    void delete(Long id);
}
