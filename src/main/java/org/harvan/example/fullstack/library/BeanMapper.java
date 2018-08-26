package org.harvan.example.fullstack.library;

import org.harvan.example.fullstack.dto.PersonDtoRequest;
import org.harvan.example.fullstack.dto.PersonDtoResponse;
import org.harvan.example.fullstack.entity.Person;
import org.mapstruct.Mapper;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@Mapper
public interface BeanMapper {

    Person map(PersonDtoRequest personDtoRequest);

    PersonDtoResponse map(Person person);

    Iterable<PersonDtoResponse> map(Iterable<Person> personIterable);
}