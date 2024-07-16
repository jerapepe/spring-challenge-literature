package com.jera.Literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAutor(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer anioNacimiento,
        @JsonAlias("death_year") Integer anioFallecimiento

) {
}