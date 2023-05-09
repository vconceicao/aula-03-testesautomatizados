package com.example.aula03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Aula03Application {

    public static void main(String[] args) {
        SpringApplication.run(Aula03Application.class, args);
    }

}
