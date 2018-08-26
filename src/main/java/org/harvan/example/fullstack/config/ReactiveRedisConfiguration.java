package org.harvan.example.fullstack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (23 Jul 2018)
 */
@Configuration
public class ReactiveRedisConfiguration {

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory,
            ResourceLoader resourceLoader) {
        JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(
                Objects.requireNonNull(resourceLoader.getClassLoader()));

        RedisSerializationContext<String, Object> serializationContext = RedisSerializationContext
                .<String, Object>newSerializationContext().key(new StringRedisSerializer()).value(jdkSerializer)
                .hashKey(jdkSerializer).hashValue(jdkSerializer).build();

        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory,
                serializationContext);
    }
}