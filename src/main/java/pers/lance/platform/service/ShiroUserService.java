package pers.lance.platform.service;

import com.github.pagehelper.PageInfo;
import pers.lance.platform.bean.dto.ShiroUserDTO;
import pers.lance.platform.bean.entity.ShiroUser;
import pers.lance.platform.bean.query.ShiroUserQuery;
import pers.lance.platform.bean.vo.ShiroUserVO;
import pers.lance.platform.bean.vo.UserLoginVO;

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
     * 分页查询获取 ShiroUserVO 结果集
     *
     * @param queryParams 查询参数对象
     * @return 分页返回 ShiroUserVO
     */
    PageInfo<ShiroUserVO> pageShiroUserVO(ShiroUserQuery queryParams);

    /**
     * 通过id获取DemoVO
     *
     * @param id 对象id
     * @return 对应的 ShiroUserVO 记录
     */
    ShiroUserVO getShiroUserVO(String id);

    /**
     * 保存实体
     *
     * @param dto 数据传输对象
     * @return 存储id
     */
    String save(ShiroUserDTO dto);

    /**
     * 更新实体
     *
     * @param dto 数据传输对象
     */
    void update(ShiroUserDTO dto);

    /**
     * 通过id删除实体
     *
     * @param id 对象id
     */
    void delete(Long id);
}
