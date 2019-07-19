package pers.lance.platform.service;

import pers.lance.platform.bean.entity.ShiroUser;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import pers.lance.platform.bean.vo.UserLoginVO;

import java.util.List;

/**
 * Shiro User Service
 *
 * @author lance
 * @date 2018-05-06
 */
public interface ShiroUserService {

    /**
     * 通过用户名查询用户实体
     *
     * @param username
     * @return
     */
    ShiroUser findByUsername(String username);

    /**
     * 通过id获取登陆用户VO
     *
     * @param id
     * @return
     */
    UserLoginVO getUserLoginVO(String id);

    /**
     * 查询用户角色
     *
     * @param id
     * @return
     */
    List<ShiroRoleVO> findAuthorization(String id);


}
