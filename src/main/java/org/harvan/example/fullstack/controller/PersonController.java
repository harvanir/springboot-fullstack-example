package org.harvan.example.fullstack.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.harvan.example.fullstack.dto.PersonDtoRequest;
import org.harvan.example.fullstack.dto.PersonDtoResponse;
import org.harvan.example.fullstack.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.elastic;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    private Logger logger = LogManager.getLogger(getClass());
    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/ping")
    public Mono<String> ping() {
        return Mono.just("pinged...");
    }

    @GetMapping()
    public Flux<PersonDtoResponse> findAll() {
        return personService.findAll().subscribeOn(elastic()).publishOn(elastic());
    }


    @PostMapping("/")
    public Mono<PersonDtoResponse> create(@RequestBody PersonDtoRequest personDtoRequest) {
        return personService.save(personDtoRequest).map(dto -> {
            if (logger.isDebugEnabled()) {
                logger.debug("personDtoRequest: {}", personDtoRequest);
                logger.debug("returned save: {}", dto);
            }

            return dto;
        }).subscribeOn(elastic()).publishOn(elastic());
    }

    @GetMapping("/{id}")
    public Mono<PersonDtoResponse> findById(@PathVariable String id) {
        return personService.findById(id).subscribeOn(elastic()).publishOn(elastic());
    }

    @GetMapping("/findByName/{name}")
    public Mono<PersonDtoResponse> findByName(@PathVariable String name) {
        return Mono.defer(() -> {
            if (logger.isDebugEnabled()) {
                logger.debug("Logging controller...");
            }

            return personService.findByName(name);
        }).subscribeOn(elastic()).publishOn(elastic());
    }
}