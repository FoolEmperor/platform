package pers.lance.platform.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.base.bean.CustomResult;
import pers.lance.platform.base.bean.group.Save;
import pers.lance.platform.base.bean.group.Update;
import pers.lance.platform.base.util.ErrorUtils;
import pers.lance.platform.bean.dto.DemoDTO;
import pers.lance.platform.bean.query.DemoQuery;
import pers.lance.platform.bean.vo.DemoVO;
import pers.lance.platform.service.DemoService;

/**
 * demo
 *
 * @author lance
 * @date 2018-05-06
 */
@Api(tags = "100", description = "例子模块，开发事例用户开发及需求沟通", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;


    @ApiOperation(value = "page", notes = "分页查询返回格式", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header",
                    required = true, dataType = "String", example = CustomConstant.HTTP_HEADERS_SET_COOKIE_EXAMPLE)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = DemoVO.class, responseContainer = "List")
    })
    @RequiresAuthentication
    @GetMapping(value = "demo/page")
    public PageInfo<DemoVO> pageDemoVO(DemoQuery queryParams) {
        return demoService.pageDemoVO(queryParams);
    }

    /**
     * 只有拥有 demo:save 权限的用户才能操作
     *
     * @param vo
     */
    @ApiOperation(value = "save", notes = "保存返回格式", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header",
                    required = true, dataType = "String", example = CustomConstant.HTTP_HEADERS_SET_COOKIE_EXAMPLE)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @RequiresPermissions(value = "demo:save")
    @PostMapping(value = "demo/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@Validated(Save.class) @RequestBody DemoDTO vo) {
        return demoService.save(vo);
    }

    @ApiOperation(value = "getOneById", notes = "通过Id获取单条记录", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header",
                    required = true, dataType = "String", example = CustomConstant.HTTP_HEADERS_SET_COOKIE_EXAMPLE)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = DemoVO.class)
    })
    @GetMapping(value = "demo/{id}")
    public DemoVO getDemoVO(@PathVariable("id") String id) {
        return demoService.getDemoVO(id);
    }

    /**
     * 只有拥有 demo:update 权限的用户才能操作
     *
     * @param dto
     */
    @ApiOperation(value = "update", notes = "更新返回格式", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header",
                    required = true, dataType = "String", example = CustomConstant.HTTP_HEADERS_SET_COOKIE_EXAMPLE)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @RequiresPermissions(value = "demo:update")
    @PostMapping(value = "demo/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(Update.class) @RequestBody DemoDTO dto) {
        demoService.update(dto);
    }

    /**
     * 只有拥有 admin 角色的才能删除
     *
     * @param id
     */
    @ApiOperation(value = "demo/delete/{id}", notes = "删除返回格式", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header",
                    required = true, dataType = "String", example = CustomConstant.HTTP_HEADERS_SET_COOKIE_EXAMPLE),
            @ApiImplicitParam(name = "id", value = "id", paramType = "path", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @RequiresRoles(value = CustomConstant.SHIRO_ROLE_TYPE_ADMIN)
    @PostMapping(value = "demo/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        demoService.delete(Long.parseLong(id));
    }

    @ApiOperation(value = "void", notes = "空返回格式", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @GetMapping("public/void")
    public void voidTest() {
        System.out.println("hello world");
    }

    @ApiOperation(value = "int", notes = "数字返回格式", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = Integer.class)
    })
    @GetMapping("public/int")
    public int intTest() {
        return 1;
    }

    @ApiOperation(value = "object", notes = "对象返回格式", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = Object.class)
    })
    @GetMapping("public/object")
    public Object objectTest() {
        return new Object();
    }

    @ApiOperation(value = "error", notes = "请求失败返回格式", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiResponses({
            @ApiResponse(code = 500, message = "请求失败data值", response = String.class)
    })
    @GetMapping("public/error")
    public void exception() {
        ErrorUtils.message("error");
    }


    @ApiOperation(value = "string", notes = "字符串返回格式demo", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = String.class)
    })
    @GetMapping("public/string")
    public String stringTest() {
        return "hello world!";
    }

    @ApiOperation(value = "boolean", notes = "布尔返回格式demo", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = Boolean.class)
    })
    @GetMapping("public/boolean")
    public boolean booleanTest() {
        return System.currentTimeMillis() % 2 == 0;
    }

}
