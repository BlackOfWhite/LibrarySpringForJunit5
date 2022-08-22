package com.officelibrary.library.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.officelibrary.library.domain.BookDomain;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private static final Pattern camelCasePattern = Pattern.compile("([A-Z]+\\w+)+");

    public void validateTitlePattern(BookDomain book) {
        Matcher matcher = camelCasePattern.matcher(book.getTitle());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Incorrect book title");
        }
    }

    public void validateLegalCharacters(BookDomain book) {

    }
}
