package com.tohacks2020.textmemaybe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//this annotation to the configuration class will allow Spring Boot to auto-configure your Spring app upon deployment.
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication

public class AppConfig {
    public static void main(String[] args){
        SpringApplication.run(AppConfig.class, args);
    }
}
