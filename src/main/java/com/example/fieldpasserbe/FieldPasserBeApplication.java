package com.example.fieldpasserbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class FieldPasserBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FieldPasserBeApplication.class, args);

    }

}
