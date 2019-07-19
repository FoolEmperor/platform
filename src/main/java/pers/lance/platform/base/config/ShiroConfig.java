package pers.lance.platform.base.config;

import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.base.bean.CustomSymbol;
import pers.lance.platform.base.shiro.CustomSessionManager;
import pers.lance.platform.base.shiro.CustomShiroFormAuthenticationFilter;
import pers.lance.platform.base.shiro.CustomShiroRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro Config
 *
 * @author lance
 * @date 2018-05-06
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private String timeoutUnit;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${shiro.session.expire}")
    private int expire;
    @Value("${shiro.filter.anon-paths}")
    private String anonPaths;
    @Value("${shiro.filter.authc-paths}")
    private String authcPaths;
    @Value("${shiro.filter.login-url}")
    private String loginUrl;

    private static final String ANON = "anon";
    private static final String AUTHC = "authc";
    private static final String USER= "user";
    private static final String CUSTEM_FILER = "customShiroFormAuthenticationFilter";

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 注意过滤器配置顺序 不能颠倒,配置不会被拦截的链接 顺序判断
        // anonPaths
        filterSetting(filterChainDefinitionMap, anonPaths, ANON);
        // authcPaths
        filterSetting(filterChainDefinitionMap, authcPaths, USER);
        filterChainDefinitionMap.put("/**", CUSTEM_FILER);


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        // 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl(loginUrl);

        LinkedHashMap<String, Filter> filtsMap = new LinkedHashMap<String, Filter>();
        filtsMap.put(CUSTEM_FILER, new CustomShiroFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filtsMap);

        return shiroFilterFactoryBean;
    }

    private void filterSetting(Map<String, String> filterMap, String paths, String auth) {
        if (StringUtils.isNotBlank(paths)) {
            String[] pathArr = paths.split(CustomSymbol.COMMA);
            for (String path : pathArr) {
                filterMap.put(path, auth);
            }
        }
    }

    /**
     * 凭证匹配器 shiro散列配置
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法
        hashedCredentialsMatcher.setHashAlgorithmName(CustomConstant.SHIRO_ENCRYPT_ALGORITHM);
        // 散列的次数
        hashedCredentialsMatcher.setHashIterations(CustomConstant.SHIRO_ENCRYPT_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    /**
     * Custom Shiro Realm
     *
     * @return
     */
    @Bean
    public CustomShiroRealm customShiroRealm() {
        CustomShiroRealm customShiroRealm = new CustomShiroRealm();
        customShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customShiroRealm;
    }

    /**
     * Security Manager
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(redisCacheManager());
        /*注入cookie管理器*/
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    /**
     * Custom Session Manager
     */
    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        customSessionManager.setSessionDAO(redisSessionDAO());
        return customSessionManager;
    }

    /**
     * redisCacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        // 配置缓存过期时间（单位/秒）
        redisManager.setExpire(expire);
        int timeout = Integer.parseInt(timeoutUnit.substring(0, timeoutUnit.length() - 1)) * 1000;
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    /**
     * 开启shiro aop注解支持.
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * cookie管理对象
     * @return
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }
    @Bean
    public SimpleCookie rememberMeCookie(){
        /*这个参数是cookie的名称，对应前端页面的checkbox的name=remremberMe*/
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        /*cookie的有效时间为30天，单位秒*/
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }
}
