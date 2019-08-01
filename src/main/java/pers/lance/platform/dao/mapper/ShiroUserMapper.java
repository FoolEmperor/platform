package pers.lance.platform.dao.mapper;

import pers.lance.platform.bean.query.ShiroUserQuery;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import pers.lance.platform.bean.vo.ShiroUserVO;
import pers.lance.platform.bean.vo.UserLoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 列表查询获取 ShiroUserVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 ShiroUserVO
     */
    List<ShiroUserVO> listShiroUserVO(ShiroUserQuery queryParams);

    /**
     * 通过id获取 ShiroUserVO
     *
     * @param id 对象id
     * @return 对应的 ShiroUserVO 记录
     */
    ShiroUserVO getShiroUserVO(@Param("id") String id);


}
