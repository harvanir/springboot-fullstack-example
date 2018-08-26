package org.harvan.example.fullstack.cache.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
public interface CacheDelegator {
    Object delegate(Annotation annotation, Object realObject, Method method, Object... args);
}