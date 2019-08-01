package pers.lance.platform.service.impl;

import com.github.pagehelper.PageInfo;
import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.base.util.CrudUtils;
import pers.lance.platform.base.util.CustomCommonUtils;
import pers.lance.platform.bean.dto.ShiroUserDTO;
import pers.lance.platform.bean.entity.ShiroRole;
import pers.lance.platform.bean.entity.ShiroUser;
import pers.lance.platform.bean.query.ShiroUserQuery;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import pers.lance.platform.bean.vo.ShiroUserVO;
import pers.lance.platform.bean.vo.UserLoginVO;
import pers.lance.platform.dao.mapper.ShiroRoleMapper;
import pers.lance.platform.dao.mapper.ShiroUserMapper;
import pers.lance.platform.dao.repository.ShiroRoleRepository;
import pers.lance.platform.dao.repository.ShiroUserRepository;
import pers.lance.platform.service.ShiroUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * Shiro User Service impl
 *
 * @author lance
 * @date 2018-05-06
 */
@Service
public class ShiroUserServiceImpl implements ShiroUserService {

    @Autowired
    private ShiroUserRepository shiroUserRepository;
    @Autowired
    private ShiroRoleRepository shiroRoleRepository;
    @Resource
    private ShiroUserMapper shiroUserMapper;
    @Resource
    private ShiroRoleMapper shiroRoleMapper;

    @Override
    public ShiroUser findByUsername(String username) {
        return shiroUserRepository.findFirstByUsername(username);
    }

    @Override
    public UserLoginVO getUserLoginVO(String id) {
        return shiroUserMapper.getUserLoginVO(id);
    }

    @Override
    public PageInfo<ShiroUserVO> pageShiroUserVO(ShiroUserQuery queryParams) {
        CrudUtils.pageBefore(queryParams);
        List<ShiroUserVO> list = shiroUserMapper.listShiroUserVO(queryParams);
        return new PageInfo(list);
    }

    @Override
    public ShiroUserVO getShiroUserVO(String id) {
        ShiroUserVO vo = shiroUserMapper.getShiroUserVO(id);
        if (Objects.nonNull(vo)) {
            List<ShiroRoleVO> roleVOList = shiroRoleMapper.listShiroRoleVOByUserId(vo.getId());
            vo.setRoleVOList(roleVOList);
        }
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(ShiroUserDTO dto) {
        return saveOrUpdate(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ShiroUserDTO dto) {
        saveOrUpdate(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        shiroUserRepository.deleteById(id);
    }

    private String saveOrUpdate(ShiroUserDTO dto) {
        ShiroUser entity = new ShiroUser();
        if (Objects.nonNull(dto.getId())) {
            entity = shiroUserRepository.getOne(dto.getId());
        }
        // 基本信息
        CustomCommonUtils.copyNonNullProperties(dto, entity);
        // 密码
        String password = encrypt(dto.getPassword(), dto.getUsername());
        entity.setPassword(password);
        // 角色列表
        List<ShiroRole> roleList = shiroRoleRepository.findAllById(dto.getRoleIdList());
        entity.setRoleList(roleList);
        return entity.getId().toString();
    }

    private String encrypt(String password, String salt) {
        return new SimpleHash(CustomConstant.SHIRO_ENCRYPT_ALGORITHM, password, salt, CustomConstant.SHIRO_ENCRYPT_ITERATIONS).toHex();
    }
}
