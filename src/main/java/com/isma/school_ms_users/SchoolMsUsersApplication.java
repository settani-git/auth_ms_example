package com.isma.school_ms_users;

import com.isma.school_ms_users.core.setup.Initializer;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class SchoolMsUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolMsUsersApplication.class, args);
    }

    @Bean
    CommandLineRunner start() {
        return args -> {
            Initializer.initData();
        };
    }

    @Bean
    BCryptPasswordEncoder getBCPE() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}


