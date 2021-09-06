package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HeroConfig {

    @Bean
    CommandLineRunner commandLineRunner(HeroRepository repository) {
        return args -> {
            Hero Hero1 = new Hero("hero1");
            Hero Hero2 = new Hero("hero2");

            repository.saveAll(
                    List.of(Hero1, Hero2)
            );
        };
    }
}
