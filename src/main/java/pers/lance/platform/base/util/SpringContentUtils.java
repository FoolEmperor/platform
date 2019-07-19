package pers.lance.platform.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * spring 上下文获取工具
 *
 * @author lance
 * @date 2018-05-06
 */
@Component
public class SpringContentUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContentUtils.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getContext().getBean(name);

    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getContext().getBean(name, clazz);
    }

    /**
     * 通过注解获取bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> clazz) {
        return getContext().getBeansWithAnnotation(clazz);
    }
}