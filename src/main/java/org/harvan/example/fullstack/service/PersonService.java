package org.harvan.example.fullstack.service;

import org.harvan.example.fullstack.dto.PersonDtoRequest;
import org.harvan.example.fullstack.dto.PersonDtoResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
public interface PersonService {
    Flux<PersonDtoResponse> findAll();

    Mono<PersonDtoResponse> save(PersonDtoRequest personDtoRequest);

    Mono<PersonDtoResponse> findById(String id);

    Mono<PersonDtoResponse> findByName(String name);
}