package pers.lance.platform.base.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import pers.lance.platform.bean.entity.ShiroUser;
import pers.lance.platform.bean.vo.ShiroPermissionVO;
import pers.lance.platform.bean.vo.UserLoginVO;
import pers.lance.platform.service.ShiroPermissionService;
import pers.lance.platform.service.ShiroRoleService;
import pers.lance.platform.service.ShiroUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * Custom Shiro Realm
 *
 * @author lance
 * @date 2018-05-06
 */
public class CustomShiroRealm extends AuthorizingRealm {

    @Autowired
    private ShiroUserService shiroUserService;
    @Autowired
    private ShiroRoleService shiroRoleService;
    @Autowired
    private ShiroPermissionService shiroPermissionService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取登录缓存
        UserLoginVO user = (UserLoginVO) principals.getPrimaryPrincipal();
        // 获取用户角色集合
        List<ShiroRoleVO> roleVOList = shiroRoleService.listShiroRoleVOByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(roleVOList)) {
            for (ShiroRoleVO role : roleVOList) {
                authorizationInfo.addRole(role.getCode());
            }
        }
        // 获取角色权限集合
        List<ShiroPermissionVO> permissionVOList = shiroPermissionService.listShiroPermissionVOByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(permissionVOList)) {
            for (ShiroPermissionVO p : permissionVOList) {
                authorizationInfo.addStringPermission(p.getCode());
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        // 获取用户的输入的账号.
        String username = token.getPrincipal().toString();
        // 可根据实际情况做缓存，当然Shiro也有默认的时间间隔机制（2分钟内不会重复执行该方法）
        ShiroUser shiroUser = shiroUserService.findByUsername(username);
        if (Objects.isNull(shiroUser)) {
            // 如果用户没未找到.
            throw new UnknownAccountException();
        }
        // 是否有效
        if (!shiroUser.getEnable()) {
            // 无效用户
            throw new DisabledAccountException();
        }
        // 获取登录缓存
        UserLoginVO user = shiroUserService.getUserLoginVO(shiroUser.getId().toString());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                shiroUser.getPassword(),
                ByteSource.Util.bytes(shiroUser.getUsername()),
                getName()
        );
        return authenticationInfo;
    }
}
