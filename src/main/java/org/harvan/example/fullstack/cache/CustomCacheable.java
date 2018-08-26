package org.harvan.example.fullstack.cache;

import java.lang.annotation.*;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (29 Jul 2018)
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomCacheable {
    String key() default "";
}