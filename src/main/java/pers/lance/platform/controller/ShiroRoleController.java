package pers.lance.platform.controller;


import com.github.pagehelper.PageInfo;
import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.base.bean.CustomResult;
import pers.lance.platform.base.bean.group.Save;
import pers.lance.platform.base.bean.group.Update;
import pers.lance.platform.base.util.ValidatorUtils;
import pers.lance.platform.bean.dto.ShiroRoleDTO;
import pers.lance.platform.bean.query.ShiroRoleQuery;
import pers.lance.platform.bean.vo.ShiroRoleVO;
import pers.lance.platform.service.ShiroRoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * shiro 角色 Controller
 *
 * @author lance
 * @date 2019-07-25T11:04:34.531
 */
@Api(tags = "97", description = " 角色", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ShiroRoleController {

    @Autowired
    private ShiroRoleService shiroRoleService;


    @ApiOperation(value = "page", notes = "分页查询返回", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = ShiroRoleVO.class, responseContainer = "List")
    })
    @GetMapping(value = "shiroRole/page")
    public PageInfo<ShiroRoleVO> pageShiroRoleVO(ShiroRoleQuery queryParams) {
        ValidatorUtils.validate(queryParams);
        return shiroRoleService.pageShiroRoleVO(queryParams);
    }

    @ApiOperation(value = "getOneById", notes = "通过Id获取单条记录", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = ShiroRoleVO.class)
    })
    @GetMapping(value = "shiroRole/{id}")
    public ShiroRoleVO getShiroRoleVO(@PathVariable("id") String id) {
        return shiroRoleService.getShiroRoleVO(id);
    }


    @ApiOperation(value = "save", notes = "保存实体", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @PostMapping(value = "shiroRole/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@Validated(Save.class) @RequestBody ShiroRoleDTO dto) {
        return shiroRoleService.save(dto);
    }

    @ApiOperation(value = "update", notes = "更新实体", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @PostMapping(value = "shiroRole/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(Update.class) @RequestBody ShiroRoleDTO dto) {
        shiroRoleService.update(dto);
    }


    @ApiOperation(value = "delete/{id}", notes = "删除实体", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d"),
            @ApiImplicitParam(name = "id", value = "id", paramType = "path", required = true, dataType = "String", example = "123456789")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @PostMapping(value = "shiroRole/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        shiroRoleService.delete(Long.parseLong(id));
    }

}
