package pers.lance.platform.base.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pers.lance.platform.base.bean.CustomResult;
import pers.lance.platform.base.bean.CustomSymbol;
import pers.lance.platform.base.util.CustomResultUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 响应返回json内容封装
 *
 * @author: lance
 * @date: 2018-05-06
 */
@ControllerAdvice
public class ResultHandler implements ResponseBodyAdvice<Object> {
    private static final String SUFFIX = "/**";

    @Value("${result.except-paths}")
    private String exceptPaths;

    @Autowired
    private HttpServletRequest request;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        String requestUri = request.getRequestURI();
        if (requestUri.startsWith(request.getContextPath())) {
            requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
        }
        // 放行exceptPaths中配置的path
        String[] paths = exceptPaths.split(CustomSymbol.COMMA);
        for (String url : paths) {
            if (url.endsWith(SUFFIX)) {
                if (requestUri.startsWith(url.substring(0, url.length() - 3))) {
                    return false;
                }
            } else if (requestUri.equals(url)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object result, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        serverHttpResponse.getHeaders().setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        if (result instanceof CustomResult) {
            return result;
        }
        return CustomResultUtils.success(result);
    }
}