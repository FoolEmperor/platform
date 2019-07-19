package pers.lance.platform.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.lance.platform.base.bean.CustomConstant;
import pers.lance.platform.base.bean.CustomResult;
import pers.lance.platform.base.bean.group.Save;
import pers.lance.platform.base.bean.group.Update;
import pers.lance.platform.bean.dto.CodeTemplateDTO;
import pers.lance.platform.bean.query.CodeTemplateQuery;
import pers.lance.platform.bean.vo.CodeTemplateVO;
import pers.lance.platform.service.CodeTemplateService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * ${module} Controller
 *
 * @author ${author}
 * @date ${date}
 */
@ApiIgnore
@Api(tags = "999", description = "代码生成器代码非对接接口代码", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CodeTemplateController {

    @Autowired
    private CodeTemplateService codeTemplateService;


    @ApiOperation(value = "page", notes = "分页查询返回", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = CodeTemplateVO.class, responseContainer = "List")
    })
    @GetMapping(value = "codeTemplate/page")
    public PageInfo<CodeTemplateVO> pageCodeTemplateVO(CodeTemplateQuery queryParams) {
        return codeTemplateService.pageCodeTemplateVO(queryParams);
    }

    @ApiOperation(value = "getOneById", notes = "通过Id获取单条记录", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值", response = CodeTemplateVO.class)
    })
    @GetMapping(value = "codeTemplate/{id}")
    public CodeTemplateVO getCodeTemplateVO(@PathVariable("id") String id) {
        return codeTemplateService.getCodeTemplateVO(id);
    }


    @ApiOperation(value = "save", notes = "保存实体", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @PostMapping(value = "codeTemplate/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@Validated(Save.class) @RequestBody CodeTemplateDTO dto) {
        return codeTemplateService.save(dto);
    }

    @ApiOperation(value = "update", notes = "更新实体", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CustomResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = CustomConstant.HTTP_HEADERS_SET_COOKIE, value = "token", paramType = "header", required = true, dataType = "String", example = "8efdc2eb-0514-4f10-8365-279cdc08707d")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功data值")
    })
    @PostMapping(value = "codeTemplate/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(Update.class) @RequestBody CodeTemplateDTO dto) {
        codeTemplateService.update(dto);
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
    @PostMapping(value = "codeTemplate/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        codeTemplateService.delete(Long.parseLong(id));
    }

}
