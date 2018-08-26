package org.harvan.example.fullstack.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.harvan.example.fullstack.cache.CustomCacheable;
import org.harvan.example.fullstack.dto.PersonDtoRequest;
import org.harvan.example.fullstack.dto.PersonDtoResponse;
import org.harvan.example.fullstack.entity.Person;
import org.harvan.example.fullstack.library.BeanMapper;
import org.harvan.example.fullstack.library.Mapper;
import org.harvan.example.fullstack.repository.PersonRepository;
import org.harvan.example.fullstack.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@Service
public class PersonServiceImpl implements PersonService {
    private static final String KEY_PREFIX = "org.harvan.example.fullstack.dto.PersonDtoResponse";
    private Logger logger = LogManager.getLogger(getClass());
    private BeanMapper mapper = Mapper.INSTANCE;
    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @CustomCacheable(key = KEY_PREFIX + "FindAll")
    @Override
    public Flux<PersonDtoResponse> findAll() {
        return Flux.fromIterable(mapper.map(personRepository.findAll()));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Mono<PersonDtoResponse> save(PersonDtoRequest personDtoRequest) {
        return Mono.defer(() -> {
            Person person = mapper.map(personDtoRequest);
            Person personSaved = personRepository.save(person);
            PersonDtoResponse personDtoResponse = mapper.map(personSaved);

            if (logger.isDebugEnabled()) {
                logger.debug("person new: {}", person);
                logger.debug("person saved: {}", personSaved);
                logger.debug("personDtoResponse: {}", personDtoResponse);
            }

            return Mono.just(personDtoResponse);
        });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @CustomCacheable(key = KEY_PREFIX)
    @Override
    public Mono<PersonDtoResponse> findById(String id) {
        return Mono.defer(() ->
                findByIdValue(id)
        );
    }

    private Mono<PersonDtoResponse> findByIdValue(String id) {
        return personRepository.findById(Long.valueOf(id))
                .map(person -> Mono.just(mapper.map(person)))
                .orElseGet(Mono::empty);
    }

    @Override
    public Mono<PersonDtoResponse> findByName(String name) {
        return personRepository.findByName(name
        ).map(person -> {
            if (logger.isDebugEnabled()) {
                logger.debug("Logging service...");
            }
            return mapper.map(person);
        });
    }
}