package com.example.sdmisoback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.example.sdmisoback.security"})
public class BackendServerApp {

    public static void main(String[] args) {
        SpringApplication.run(BackendServerApp.class, args);
    }
}