package pers.lance.platform.base.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Custom Shiro Form Authentication Filter
 *
 * @author: lance
 * @create: 2018-11-22 17:59
 */
@Slf4j
public class CustomShiroFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final String OPTIONS = "OPTIONS";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean allowed = super.isAccessAllowed(request, response, mappedValue);
        if (!allowed) {
            // 判断请求是否是options请求
            String method = WebUtils.toHttp(request).getMethod();
            if (StringUtils.equalsIgnoreCase(OPTIONS, method)) {
                return true;
            }
        }
        return allowed;
    }

}
