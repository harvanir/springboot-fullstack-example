package org.harvan.example.fullstack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@Getter
@Setter
public class PersonDtoResponse implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("dateOfBirth")
    private Date dateOfBirth;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonDtoResponse{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append('}');
        return sb.toString();
    }
}