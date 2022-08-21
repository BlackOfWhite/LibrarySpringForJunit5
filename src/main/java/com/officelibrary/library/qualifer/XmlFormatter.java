package com.officelibrary.library.qualifer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("xml")
public class XmlFormatter implements FormatterService {
    @Override
    public void format(String text) {
        System.out.println("XML");
    }
}
