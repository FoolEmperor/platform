package pers.lance.platform.dao.mapper;

import pers.lance.platform.bean.query.ShiroRoleQuery;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色 Mapper
 *
 * @author lance
 * @date 2019-07-25T11:04:34.531
 */
@Mapper
public interface ShiroRoleMapper {

    /**
     * 通过 权限id 列表 获取 ShiroRoleVO
     *
     * @param permissionId 权限id
     * @return 列表返回 ShiroRoleVO
     */
    List<ShiroRoleVO> listShiroRoleVOByPermissionId(@Param("permissionId") String permissionId);

    /**
     * 通过 userId 列表查询获取 ShiroRoleVO 结果集
     *
     * @param userId 用户id
     * @return 列表返回 ShiroRoleVO
     */
    List<ShiroRoleVO> listShiroRoleVOByUserId(@Param("userId") String userId);

    /**
     * 列表查询获取 ShiroRoleVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 ShiroRoleVO
     */
    List<ShiroRoleVO> listShiroRoleVO(ShiroRoleQuery queryParams);

    /**
     * 通过id获取 ShiroRoleVO
     *
     * @param id 对象id
     * @return 对应的 ShiroRoleVO 记录
     */
    ShiroRoleVO getShiroRoleVO(@Param("id") String id);
}
