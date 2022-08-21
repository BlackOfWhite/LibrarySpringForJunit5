package com.officelibrary.library;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Scope("prototype")
@Component
@Lazy
public class Planet {

    @PostConstruct
    public void onCreated() {
        System.out.println("Created!");
    }

    @PreDestroy
    public void onDestroyed() {
        System.out.println("Destoryed!");
    }
}
