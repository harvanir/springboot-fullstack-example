package org.harvan.example.fullstack.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.harvan.example.fullstack.entity.Person;
import org.harvan.example.fullstack.repository.PersonCustomRepository;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Random;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (12 Aug 2018)
 */
public class PersonCustomRepositoryImpl implements PersonCustomRepository {
    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public Mono<Person> findByName(String name) {
        return Mono.defer(() -> {
            if (logger.isDebugEnabled()) {
                logger.debug("Logging repository...");
            }

            long random = new Random().nextLong();

            Person person = new Person();
            person.setId(random);
            person.setName("Name : " + random);
            person.setDateOfBirth(new Date());

            return Mono.just(person);
        });
    }
}