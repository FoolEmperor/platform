package pers.lance.platform.controller;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.base.bean.CustomResult;
import pers.lance.platform.base.bean.CustomResultCodeEnum;
import pers.lance.platform.base.util.ErrorUtils;
import pers.lance.platform.bean.vo.LoginVO;
import pers.lance.platform.bean.vo.UserLoginVO;

/**
 * Login Controller
 *
 * @author lance
 * @date 2018-05-06
 */
@RestController
@Api(tags = "99", description = "用户登录退出模块", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    /**
     * 登陆有效性验证
     */
    @ApiOperation(value = "默认登录页面地址", notes = "前后端分离情况直接抛出'未登录'异常", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @GetMapping(value = "login")
    public void login() {
        Subject subject = SecurityUtils.getSubject();
        // 已记住 或者 已认证（登陆）
        boolean isLogin = subject.isRemembered() || subject.isAuthenticated();
        ErrorUtils.isFalse(isLogin, CustomResultCodeEnum.UNLOGIN);
    }

    /**
     * 前台登陆登录
     *
     * @return
     * @ param vo
     */
    @ApiOperation(value = "前台登陆登录", notes = "用户登录请求提交地址", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = UserLoginVO.class)
    })
    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserLoginVO login(@Validated @RequestBody LoginVO vo) {
        return basicLoginLogic(vo, CustomConstant.SHIRO_ROLE_TYPE_USER);
    }

    /**
     * 后台登陆接口
     *
     * @return
     * @ param vo
     */
    @ApiOperation(value = "后台登陆接口", notes = "后台用户登录请求提交地址", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = UserLoginVO.class)
    })
    @PostMapping(value = "adminLogin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserLoginVO adminLogin(@Validated @RequestBody LoginVO vo) {
        return basicLoginLogic(vo, CustomConstant.SHIRO_ROLE_TYPE_ADMIN);
    }

    /**
     * logout
     */
    @ApiOperation(value = "用户退出", notes = "空返回格式", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @GetMapping(value = "logout")
    public void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 基本登陆逻辑
     *
     * @param vo
     * @param exceptRoleCodeArray
     * @return
     */
    public UserLoginVO basicLoginLogic(LoginVO vo, String... exceptRoleCodeArray) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(vo.getUsername(), vo.getPassword(), vo.getRememberMe());
        subject.login(token);
        UserLoginVO user = (UserLoginVO) SecurityUtils.getSubject().getPrincipal();
        if (!StringUtils.equalsAnyIgnoreCase(user.getRoleCode(), exceptRoleCodeArray)) {
            // 前后台用户分离：
            SecurityUtils.getSubject().logout();
            throw new UnknownAccountException();
        }
        return user;
    }

}
