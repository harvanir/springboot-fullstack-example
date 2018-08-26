package org.harvan.example.fullstack.cache.interceptor;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * @param <T> Cache return type.
 * @param <R> Evict return type.
 * @author Harvan Irsyadi
 * @version 1.0.0
 */
public interface CacheInterceptor<T, R> {
    boolean isResponsible(Method method);

    T getCache(Supplier<T> supplier, String key, Object... args);

    T getCache(Supplier<T> supplier, String key, Duration timeout, Object... args);

    R evict(String key, Object... args);
}