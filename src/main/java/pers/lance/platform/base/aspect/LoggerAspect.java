package pers.lance.platform.base.aspect;

import pers.lance.platform.base.bean.CustomBusinessException;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

/**
 * 日志记录统一处理AOP切面：生产环境上为提升效率可以删除
 *
 * @author lance
 * @date 2018-05-06
 */
@Component
@Aspect
@Log4j2
public class LoggerAspect {

    /**
     * 请求切点
     */
    @Pointcut(value = "execution(@org.springframework.web.bind.annotation.*Mapping * com..*.controller.*Controller.*(..))")
    public void requestPoint() {
    }

    /**
     * 请求结束切点
     */
    @Pointcut(value = "execution(@org.springframework.web.bind.annotation.*Mapping * com..*.controller.*Controller.*(..))")
    public void resultPoint() {
    }

    /**
     * 操作记录
     *
     * @param joinPoint
     */
    @Before(value = "requestPoint()")
    public void requestRecord(JoinPoint joinPoint) {
        StringBuilder message = new StringBuilder("request log：");
        commonInfomation(joinPoint, message);
        log.warn(message.toString());
    }

    /**
     * 异常记录
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "resultPoint()", throwing = "e")
    public void exceptionRecord(JoinPoint joinPoint, Throwable e) {
        StringBuilder message = new StringBuilder("exception log：");
        commonInfomation(joinPoint, message);
        if (e instanceof CustomBusinessException) {
            message.append("发生业务异常，异常信息为：");
            message.append(e.toString());
        } else {
            message.append("发现未知异常。");
        }
        log.error(message.toString(), e);
    }

    /**
     * 业务操作成功记录
     */
    @AfterReturning(value = "resultPoint()", returning = "result")
    public void successRecord(JoinPoint joinPoint, Object result) {
        StringBuilder message = new StringBuilder("response log：");
        commonInfomation(joinPoint, message);
        if (Objects.nonNull(result)) {
            message.append(" result value:  ");
            message.append(result.toString());
        }
        log.warn(message.toString());
    }

    /**
     * 公共信息部分
     */
    private void commonInfomation(JoinPoint joinPoint, StringBuilder message) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        message.append(className);
        message.append(".");
        message.append(methodName);
        message.append("(");
        Arrays.asList(args).forEach(e -> {
            message.append(" parameter: ");
            if (Objects.nonNull(e)) {
                message.append(e.toString());
            } else {
                message.append(" null ");
            }
        });
        message.append("）");
    }

}
