package pers.lance.platform.controller;


import com.github.pagehelper.PageInfo;
import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.base.bean.CustomResult;
import pers.lance.platform.base.bean.group.Save;
import pers.lance.platform.base.bean.group.Update;
import pers.lance.platform.base.util.ValidatorUtils;
import pers.lance.platform.bean.dto.ShiroPermissionDTO;
import pers.lance.platform.bean.query.ShiroPermissionQuery;
import pers.lance.platform.bean.vo.ShiroPermissionVO;
import pers.lance.platform.service.ShiroPermissionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * shiro 权限许可 Controller
 *
 * @author lance
 * @date 2019-07-25T10:46:38.217
 */
@Api(tags = "98", description = " 权限许可", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ShiroPermissionController {

    @Autowired
    private ShiroPermissionService shiroPermissionService;


    @ApiOperation(value = "page", notes = "分页查询返回", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = ShiroPermissionVO.class, responseContainer = "List")
    })
    @GetMapping(value = "shiroPermission/page")
    public PageInfo<ShiroPermissionVO> pageShiroPermissionVO(ShiroPermissionQuery queryParams) {
        ValidatorUtils.validate(queryParams);
        return shiroPermissionService.pageShiroPermissionVO(queryParams);
    }

    @ApiOperation(value = "getOneById", notes = "通过Id获取单条记录", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = ShiroPermissionVO.class)
    })
    @GetMapping(value = "shiroPermission/{id}")
    public ShiroPermissionVO getShiroPermissionVO(@PathVariable("id") String id) {
        return shiroPermissionService.getShiroPermissionVO(id);
    }


    @ApiOperation(value = "save", notes = "保存实体", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @PostMapping(value = "shiroPermission/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@Validated(Save.class) @RequestBody ShiroPermissionDTO dto) {
        return shiroPermissionService.save(dto);
    }

    @ApiOperation(value = "update", notes = "更新实体", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @PostMapping(value = "shiroPermission/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(Update.class) @RequestBody ShiroPermissionDTO dto) {
        shiroPermissionService.update(dto);
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
    @PostMapping(value = "shiroPermission/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        shiroPermissionService.delete(Long.parseLong(id));
    }

}
