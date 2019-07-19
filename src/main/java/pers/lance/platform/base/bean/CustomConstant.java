package pers.lance.platform.base.bean;


/**
 * 公用常数
 *
 * @author lance
 * @date 2018-05-06
 */
public class CustomConstant {

    /**
     * shiro加密算法
     */
    public static final String SHIRO_ENCRYPT_ALGORITHM = "MD5";

    /**
     * shiro加密迭代次数
     */
    public static final Integer SHIRO_ENCRYPT_ITERATIONS = 2;

    /**
     * 数据权限
     */
    public static final String SHIRO_DATA_PERMISSION = "DATA";

    /**
     * 菜单权限
     */
    public static final String SHIRO_MENU_PERMISSION = "MENU";

    /**
     * 角色类型：用户，USER
     */
    public static final String SHIRO_ROLE_TYPE_USER = "USER";

    /**
     * 角色类型：管理员，ADMIN
     */
    public static final String SHIRO_ROLE_TYPE_ADMIN = "ADMIN";


    /**
     * 请求头
     */
    public static final String HTTP_HEADERS_SET_COOKIE = "x-auth-token";
    /**
     * 请求头
     */
    public static final String HTTP_HEADERS_SET_COOKIE_EXAMPLE = "8efdc2eb-0514-4f10-8365-279cdc08707d";


}
