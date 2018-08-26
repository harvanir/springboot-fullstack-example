package org.harvan.example.fullstack.cache;

import java.lang.annotation.*;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (10 Aug 2018)
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomEvict {
    String key() default "";
}