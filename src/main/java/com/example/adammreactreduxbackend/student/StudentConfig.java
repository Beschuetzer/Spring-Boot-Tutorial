package com.example.adammreactreduxbackend.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//Seeding the Student table in the db via JPA
@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            StudentTest s1 = new StudentTest("Mariam", "mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));
            StudentTest s2 = new StudentTest("Alex", "alex.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));

            repository.saveAll(List.of(s1, s2));
        };
    }
}


