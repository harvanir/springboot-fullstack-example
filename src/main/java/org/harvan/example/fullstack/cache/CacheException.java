package org.harvan.example.fullstack.cache;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
public class CacheException extends RuntimeException {
    public CacheException(Throwable cause) {
        super(cause);
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }
}