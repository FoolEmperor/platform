package pers.lance.platform.base.config;

import pers.lance.platform.base.bean.CustomConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Mvc Config
 *
 * @author: lance
 * @create: 2018-11-16 18:03
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 1 设置访问源地址
                .allowedOrigins("*")
                // 2 设置访问源请求头
                .allowedHeaders("*")
                // 3 设置访问源请求方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 是否允许证书 不再默认开启
                .allowCredentials(false)
                // 跨域允许时间
                .maxAge(3600L)
                // 允许跨域获取请求头信息
                .exposedHeaders(CustomConstant.HTTP_HEADERS_SET_COOKIE);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        // 默认注入
        converters.add(new MappingJackson2HttpMessageConverter());
        // 解决中文乱码
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        // 解决 添加解决中文乱码后 上述配置之后，返回json数据直接报错 500：no convertter for return value of type
        converters.add(new GsonHttpMessageConverter());
    }

}
