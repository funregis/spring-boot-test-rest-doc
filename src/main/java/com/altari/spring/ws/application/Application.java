package com.altari.spring.ws.application;

import org.springframework.boot.ResourceBanner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

//auto-configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.altari.spring.ws" })
@EnableConfigurationProperties
@EnableCaching
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .banner(new ResourceBanner(new ClassPathResource("config/banner.txt")))
                .run(args);

    }

}
