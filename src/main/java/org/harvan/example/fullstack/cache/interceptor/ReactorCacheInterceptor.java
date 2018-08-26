package org.harvan.example.fullstack.cache.interceptor;

import org.springframework.data.redis.core.ReactiveRedisTemplate;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
abstract class ReactorCacheInterceptor<T, E> implements AbstractCacheInterceptor<T, E> {
    ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    ReactorCacheInterceptor(ReactiveRedisTemplate<String, Object> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }
}