package com.officelibrary.library.qualifer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("json")
@Primary
public class JsonFormatter implements FormatterService {
    @Override
    public void format(String text) {
        System.out.println("JSON");
    }
}
