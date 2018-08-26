package org.harvan.example.fullstack.repository;

import org.harvan.example.fullstack.entity.Person;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (12 Aug 2018)
 */
public interface PersonCustomRepository {
    Mono<Person> findByName(String name);
}