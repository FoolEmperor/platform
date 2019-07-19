package pers.lance.platform.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.lance.platform.bean.vo.ShiroPermissionVO;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.bean.entity.ShiroUser;
import pers.lance.platform.bean.vo.UserLoginVO;
import pers.lance.platform.dao.mapper.ShiroUserMapper;
import pers.lance.platform.dao.repository.ShiroRoleRepository;
import pers.lance.platform.dao.repository.ShiroUserRepository;
import pers.lance.platform.service.ShiroUserService;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public ShiroUser findByUsername(String username) {
        return shiroUserRepository.findFirstByUsername(username);
    }

    @Override
    public UserLoginVO getUserLoginVO(String id) {
        return shiroUserMapper.getUserLoginVO(id);
    }

    @Override
    public List<ShiroRoleVO> findAuthorization(String id) {
        List<ShiroRoleVO> result = shiroUserMapper.listRoleVO(id);
        for (ShiroRoleVO e : result) {
            // 这里仅返回数据权限，菜单权限由前台控制（或者交由后台菜单模块返回前台）
            List<ShiroPermissionVO> permissions = shiroUserMapper.listShiroPermissionVO(e.getId(), CustomConstant.SHIRO_DATA_PERMISSION);
            e.setPermissions(permissions);
        }
        return result;
    }



//    @Transactional(rollbackFor = Exception.class)
//    public void save(RegisterVO vo) {
//        validate(vo.getUsername(), vo.getName());
//        String password = encrypt(vo.getPassword(), vo.getUsername());
//        vo.setPassword(password);
//        ShiroUser user = CrudUtils.save(vo, ShiroUser.class, shiroUserRepository);
//        List<ShiroRole> roleList = shiroRoleRepository.findByCode(CustomConstant.SHIRO_ROLE_TYPE_CORPORATE);
//        user.setRoles(roleList);
//    }


    private String encrypt(String password, String salt) {
        return new SimpleHash(CustomConstant.SHIRO_ENCRYPT_ALGORITHM, password, salt, CustomConstant.SHIRO_ENCRYPT_ITERATIONS).toHex();
    }
}
