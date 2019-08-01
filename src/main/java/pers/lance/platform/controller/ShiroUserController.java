package pers.lance.platform.controller;


import com.github.pagehelper.PageInfo;
import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.base.bean.CustomResult;
import pers.lance.platform.base.bean.group.Save;
import pers.lance.platform.base.bean.group.Update;
import pers.lance.platform.base.util.ValidatorUtils;
import pers.lance.platform.bean.dto.ShiroUserDTO;
import pers.lance.platform.bean.query.ShiroUserQuery;
import pers.lance.platform.bean.vo.ShiroUserVO;
import pers.lance.platform.service.ShiroUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * shiro 用户 Controller
 *
 * @author lance
 * @date 2019-07-25T16:03:54.821
 */
@Api(tags = "96", description = "用户", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ShiroUserController {

    @Autowired
    private ShiroUserService shiroUserService;


    @ApiOperation(value = "page", notes = "分页查询返回", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = ShiroUserVO.class, responseContainer = "List")
    })
    @GetMapping(value = "shiroUser/page")
    public PageInfo<ShiroUserVO> pageShiroUserVO(ShiroUserQuery queryParams) {
        ValidatorUtils.validate(queryParams);
        return shiroUserService.pageShiroUserVO(queryParams);
    }

    @ApiOperation(value = "getOneById", notes = "通过Id获取单条记录", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = ShiroUserVO.class)
    })
    @GetMapping(value = "shiroUser/{id}")
    public ShiroUserVO getShiroUserVO(@PathVariable("id") String id) {
        return shiroUserService.getShiroUserVO(id);
    }


    @ApiOperation(value = "save", notes = "保存实体", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @PostMapping(value = "shiroUser/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@Validated(Save.class) @RequestBody ShiroUserDTO dto) {
        return shiroUserService.save(dto);
    }

    @ApiOperation(value = "update", notes = "更新实体", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @PostMapping(value = "shiroUser/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(Update.class) @RequestBody ShiroUserDTO dto) {
        shiroUserService.update(dto);
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
    @PostMapping(value = "shiroUser/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        shiroUserService.delete(Long.parseLong(id));
    }

}
