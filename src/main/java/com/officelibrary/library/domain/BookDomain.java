package com.officelibrary.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDomain {

    private String title;
    private AuthorDomain author;
}
