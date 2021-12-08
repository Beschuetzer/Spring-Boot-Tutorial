package com.example.adammreactreduxbackend;

import com.example.adammreactreduxbackend.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class AdamMReactReduxBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdamMReactReduxBackEndApplication.class, args);
    }

    public static Student getStudent(Long id, String name, String email, LocalDate dob) {
        return new Student(
                id,
                name,
                email,
                dob
        );
    }
}
