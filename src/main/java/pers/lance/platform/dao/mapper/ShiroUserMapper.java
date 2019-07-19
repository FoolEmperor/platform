package pers.lance.platform.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.lance.platform.bean.vo.ShiroPermissionVO;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import pers.lance.platform.bean.vo.UserLoginVO;

import java.util.List;


/**
 * Shiro User Mapper
 *
 * @author lance
 * @date 2018-05-06
 */
@Mapper
public interface ShiroUserMapper {

    /**
     * 通过id获取用户登录信息VO
     *
     * @param id
     * @return
     */
    UserLoginVO getUserLoginVO(@Param("id") String id);

    /**
     * 通过userId 获取角色VO
     *
     * @param userId
     * @return
     */
    List<ShiroRoleVO> listRoleVO(@Param("userId") String userId);

    /**
     * 列表获取权限许可
     *
     * @param roleId
     * @param type
     * @return
     */
    List<ShiroPermissionVO> listShiroPermissionVO(@Param("roleId") String roleId, @Param("type") String type);


}
