package pers.lance.platform.service.impl;

import com.github.pagehelper.PageInfo;
import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.base.util.CrudUtils;
import pers.lance.platform.base.util.ErrorUtils;
import pers.lance.platform.bean.dto.ShiroPermissionDTO;
import pers.lance.platform.bean.entity.ShiroPermission;
import pers.lance.platform.bean.query.ShiroPermissionQuery;
import pers.lance.platform.bean.vo.ShiroPermissionVO;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import pers.lance.platform.dao.mapper.ShiroPermissionMapper;
import pers.lance.platform.dao.mapper.ShiroRoleMapper;
import pers.lance.platform.dao.repository.ShiroPermissionRepository;
import pers.lance.platform.service.ShiroPermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * shiro 权限许可 Service impl
 *
 * @author lance
 * @date 2019-07-25T10:46:38.217
 */
@Service
public class ShiroPermissionServiceImpl implements ShiroPermissionService {

    @Resource
    private ShiroPermissionMapper shiroPermissionMapper;
    @Resource
    private ShiroRoleMapper shiroRoleMapper;
    @Autowired
    private ShiroPermissionRepository shiroPermissionRepository;

    @Override
    public List<ShiroPermissionVO> listShiroPermissionVOByUserId(String userId) {
        // 仅需要数据权限，菜单权限后台管理前台控制
        return shiroPermissionMapper.listShiroPermissionVOByUserIdAndType(userId, CustomConstant.SHIRO_DATA_PERMISSION);
    }

    @Override
    public PageInfo<ShiroPermissionVO> pageShiroPermissionVO(ShiroPermissionQuery queryParams) {
        CrudUtils.pageBefore(queryParams);
        List<ShiroPermissionVO> list = shiroPermissionMapper.listShiroPermissionVO(queryParams);
        return new PageInfo(list);
    }

    @Override
    public ShiroPermissionVO getShiroPermissionVO(String id) {
        ShiroPermissionVO vo = shiroPermissionMapper.getShiroPermissionVO(id);
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(ShiroPermissionDTO dto) {
        ShiroPermission entity = CrudUtils.save(dto, ShiroPermission.class, shiroPermissionRepository);
        return entity.getId().toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ShiroPermissionDTO dto) {
        CrudUtils.update(dto, ShiroPermission.class, shiroPermissionRepository);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        //  能否删除验证：是否已被使用（角色）
        List<ShiroRoleVO> shiroRoleVOList = shiroRoleMapper.listShiroRoleVOByPermissionId(id.toString());
        if (CollectionUtils.isNotEmpty(shiroRoleVOList)) {
            List<String> roleNameList = new ArrayList<String>();
            for (ShiroRoleVO vo : shiroRoleVOList) {
                roleNameList.add(vo.getName());
            }
            ErrorUtils.message("请先解除与角色的绑定关联，已绑定角色：" + StringUtils.join(roleNameList) + "。");
        }
        // 删除 权限
        shiroPermissionRepository.deleteById(id);
    }
}
