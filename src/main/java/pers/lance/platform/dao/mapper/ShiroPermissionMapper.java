package pers.lance.platform.dao.mapper;

import pers.lance.platform.bean.query.ShiroPermissionQuery;
import pers.lance.platform.bean.vo.ShiroPermissionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * shiro 权限许可 Mapper
 *
 * @author lance
 * @date 2019-07-25T10:46:38.217
 */
@Mapper
public interface ShiroPermissionMapper {

    /**
     * 通过 userId 列表查询获取 ShiroPermissionVO 结果集
     *
     * @param userId 用户id
     * @param type   类型
     * @return 列表返回 ShiroPermissionVO
     */
    List<ShiroPermissionVO> listShiroPermissionVOByUserIdAndType(@Param("userId") String userId, @Param("type") String type);

    /**
     * 列表查询获取 ShiroPermissionVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 列表返回 ShiroPermissionVO
     */
    List<ShiroPermissionVO> listShiroPermissionVO(ShiroPermissionQuery queryParams);

    /**
     * 通过 roleId 列表查询获取 ShiroPermissionVO 结果集
     *
     * @param roleId 角色id
     * @return 列表返回 ShiroPermissionVO
     */
    List<ShiroPermissionVO> listShiroPermissionVOByRoleId(@Param("roleId") String roleId);

    /**
     * 通过id获取 ShiroPermissionVO
     *
     * @param id 对象id
     * @return 对应的 ShiroPermissionVO 记录
     */
    ShiroPermissionVO getShiroPermissionVO(@Param("id") String id);


}
