package org.harvan.example.fullstack.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@Getter
@Setter
public class PersonDtoRequest {
    private String name;
    private Date dateOfBirth;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonDtoRequest{");
        sb.append("name='").append(name).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append('}');
        return sb.toString();
    }
}