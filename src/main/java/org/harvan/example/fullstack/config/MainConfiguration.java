package org.harvan.example.fullstack.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (23 Jul 2018)
 */
@Configuration
public class MainConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}