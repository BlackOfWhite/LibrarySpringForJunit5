package com.officelibrary.library;

import java.util.UUID;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PlanetService {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SimpleConfig.class);
        Planet planet = ctx.getBean(Planet.class);
        System.out.println(planet);

        System.out.println(UUID.randomUUID());

        Planet planet2 = ctx.getBean(Planet.class);
        System.out.println(planet2);
        ctx.close();
    }
}
