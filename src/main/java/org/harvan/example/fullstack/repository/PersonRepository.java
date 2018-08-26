package org.harvan.example.fullstack.repository;

import org.harvan.example.fullstack.entity.Person;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
public interface PersonRepository extends DefaultRepository<Person, Long>, PersonCustomRepository {
}