package org.harvan.example.fullstack.cache.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.function.Supplier;

import static reactor.core.scheduler.Schedulers.elastic;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
public class MonoCacheInterceptor extends ReactorCacheInterceptor<Mono<Object>, Mono<Boolean>> {
    private Logger logger = LogManager.getLogger(getClass());

    public MonoCacheInterceptor(ReactiveRedisTemplate<String, Object> reactiveRedisTemplate) {
        super(reactiveRedisTemplate);
    }

    public boolean isResponsible(Method method) {
        return Mono.class.isAssignableFrom(method.getReturnType());
    }

    private Mono<Object> getCache(String key, Object... args) {
        return Mono.defer(() -> reactiveRedisTemplate.execute(connection -> {
            String parsedKey = getKey(key, args);

            if (logger.isDebugEnabled()) {
                logger.debug("Read from redis, key: {}", parsedKey);
            }

            return connection.stringCommands().get(ByteBuffer.wrap(parsedKey.getBytes()));
        }).subscribeOn(elastic()).publishOn(elastic()).next().map(
                byteBuffer -> reactiveRedisTemplate.getSerializationContext().getValueSerializationPair()
                        .read(byteBuffer)
        )).onErrorResume(throwable -> {
            if (throwable instanceof SerializationException) {
                return Mono.empty();
            }
            return Mono.error(throwable);
        });
    }

    @Override
    public Mono<Object> getCache(Supplier<Mono<Object>> supplier, String key, Object... args) {
        return getCache(key, args
        ).switchIfEmpty(Mono.defer(() ->
                put(supplier, key, args)
        ));
    }

    private Mono<Object> put(Supplier<Mono<Object>> supplier, String key, Object... args) {
        return supplier.get().flatMap(value ->
                reactiveRedisTemplate.opsForValue().set(getKey(key, args), value
                ).map(aBoolean ->
                        value
                )
        );
    }

    @Override
    public Mono<Object> getCache(Supplier<Mono<Object>> supplier, String key, Duration timeout, Object... args) {
        return getCache(key, args
        ).switchIfEmpty(Mono.defer(() ->
                put(supplier, key, timeout, args)
        ));
    }

    private Mono<Object> put(Supplier<Mono<Object>> supplier, String key, Duration timeout, Object... args) {
        return supplier.get().flatMap(value ->
                reactiveRedisTemplate.opsForValue().set(getKey(key, args), value, timeout
                ).map(aBoolean ->
                        value
                )
        );
    }

    @Override
    public Mono<Boolean> evict(String value, Object... args) {
        return reactiveRedisTemplate.opsForValue().delete(getKey(value, args)).subscribeOn(elastic())
                .publishOn(elastic());
    }
}