package org.harvan.example.fullstack.cache.config;

import org.harvan.example.fullstack.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (12 Aug 2018)
 */
@Configuration
public class CacheConfiguration {

    @Bean
    public CacheInterceptor<Mono<Object>, Mono<Boolean>> monoCacheInterceptor(ReactiveRedisTemplate<String, Object> reactiveRedisTemplate) {
        return new MonoCacheInterceptor(reactiveRedisTemplate);
    }

    @Bean
    public CacheInterceptor<Flux<Object>, Mono<Boolean>> fluxCacheInterceptor(ReactiveRedisTemplate<String, Object> reactiveRedisTemplate) {
        return new FluxCacheInterceptor(reactiveRedisTemplate);
    }

    @Bean
    public CacheDelegator reactorCacheDelegator(
            CacheInterceptor<Mono<Object>, Mono<Boolean>> monoCacheInterceptor
            , CacheInterceptor<Flux<Object>, Mono<Boolean>> fluxCacheInterceptor
    ) {
        return new ReactorCacheDelegator(monoCacheInterceptor, fluxCacheInterceptor);
    }

    @Bean
    public CacheAspect cacheAspect(CacheDelegator reactorCacheDelegator) {
        return new CacheAspect(reactorCacheDelegator);
    }
}