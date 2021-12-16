package com.example.adammreactreduxbackend;

import com.example.adammreactreduxbackend.student.StudentTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class AdamMReactReduxBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdamMReactReduxBackEndApplication.class, args);
    }

    public static StudentTest getStudent(Long id, String name, String email, LocalDate dob) {
        return new StudentTest(
                id,
                name,
                email,
                dob
        );
    }
}
