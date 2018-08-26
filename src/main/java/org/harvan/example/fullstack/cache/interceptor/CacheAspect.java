package org.harvan.example.fullstack.cache.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.harvan.example.fullstack.cache.CustomCacheable;
import org.harvan.example.fullstack.cache.CustomEvict;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;


/**
 * To ensure this interceptor is highest order than other interceptors, the Order is set to 0 (zero).
 *
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (29 Jul 2018)
 */
@Aspect
@Order(0)
public class CacheAspect {
    private CacheDelegator cacheDelegator;

    public CacheAspect(CacheDelegator cacheDelegator) {
        this.cacheDelegator = cacheDelegator;
    }

    @Around("@annotation(customCacheable)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, CustomCacheable customCacheable) {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

        return cacheDelegator.delegate(customCacheable, proceedingJoinPoint.getTarget(), method, proceedingJoinPoint.getArgs());
    }

    @Around("@annotation(customEvict)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, CustomEvict customEvict) {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

        return cacheDelegator.delegate(customEvict, proceedingJoinPoint.getTarget(), method, proceedingJoinPoint.getArgs());
    }
}