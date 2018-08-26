package org.harvan.example.fullstack.cache.interceptor;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
interface AbstractCacheInterceptor<T, E> extends CacheInterceptor<T, E> {
    default String getKey(String value, Object... args) {
        return KeyUtils.getKey(value, args);
    }
}
