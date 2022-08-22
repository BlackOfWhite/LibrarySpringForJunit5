package com.officelibrary.library.domain;

import java.time.LocalDate;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AuthorDomain {
    private LocalDate birthDate;
    private String name;
    private String lastName;
    private String alias;
    private Optional<LocalDate> deathDate;
}
