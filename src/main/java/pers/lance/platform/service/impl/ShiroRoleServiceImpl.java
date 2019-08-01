package pers.lance.platform.service.impl;

import com.github.pagehelper.PageInfo;
import pers.lance.platform.base.util.CrudUtils;
import pers.lance.platform.base.util.CustomCommonUtils;
import pers.lance.platform.bean.dto.ShiroRoleDTO;
import pers.lance.platform.bean.entity.ShiroPermission;
import pers.lance.platform.bean.entity.ShiroRole;
import pers.lance.platform.bean.query.ShiroRoleQuery;
import pers.lance.platform.bean.vo.ShiroPermissionVO;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import pers.lance.platform.dao.mapper.ShiroPermissionMapper;
import pers.lance.platform.dao.mapper.ShiroRoleMapper;
import pers.lance.platform.dao.mapper.ShiroUserMapper;
import pers.lance.platform.dao.repository.ShiroPermissionRepository;
import pers.lance.platform.dao.repository.ShiroRoleRepository;
import pers.lance.platform.service.ShiroRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * 角色 Service impl
 *
 * @author lance
 * @date 2019-07-25T11:04:34.531
 */
@Service
public class ShiroRoleServiceImpl implements ShiroRoleService {

    @Resource
    private ShiroRoleMapper shiroRoleMapper;
    @Resource
    private ShiroPermissionMapper shiroPermissionMapper;
    @Resource
    private ShiroUserMapper shiroUserMapper;
    @Autowired
    private ShiroRoleRepository shiroRoleRepository;
    @Autowired
    private ShiroPermissionRepository shiroPermissionRepository;

    @Override
    public List<ShiroRoleVO> listShiroRoleVOByUserId(String userId) {
        return shiroRoleMapper.listShiroRoleVOByUserId(userId);
    }

    @Override
    public PageInfo<ShiroRoleVO> pageShiroRoleVO(ShiroRoleQuery queryParams) {
        CrudUtils.pageBefore(queryParams);
        List<ShiroRoleVO> list = shiroRoleMapper.listShiroRoleVO(queryParams);
        return new PageInfo(list);
    }

    @Override
    public ShiroRoleVO getShiroRoleVO(String id) {
        ShiroRoleVO vo = shiroRoleMapper.getShiroRoleVO(id);
        if (Objects.nonNull(vo)) {
            List<ShiroPermissionVO> listShiroPermission = shiroPermissionMapper.listShiroPermissionVOByRoleId(vo.getId());
            vo.setPermissionVOList(listShiroPermission);
        }
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(ShiroRoleDTO dto) {
        ShiroRole entity = new ShiroRole();
        BeanUtils.copyProperties(dto, entity);
        List<ShiroPermission> permissionIdList = shiroPermissionRepository.findAllById(dto.getPermissionIdList());
        entity.setPermissionList(permissionIdList);
        shiroRoleRepository.save(entity);
        return entity.getId().toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ShiroRoleDTO dto) {
        ShiroRole entity = shiroRoleRepository.getOne(dto.getId());
        CustomCommonUtils.copyNonNullProperties(dto, entity);
        List<ShiroPermission> permissionIdList = shiroPermissionRepository.findAllById(dto.getPermissionIdList());
        entity.setPermissionList(permissionIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        // TODO 能否删除验证：是否已被使用（用户）
        shiroRoleRepository.deleteById(id);
    }
}
