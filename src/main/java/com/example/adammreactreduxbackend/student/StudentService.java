package com.example.adammreactreduxbackend.student;

import com.example.adammreactreduxbackend.AdamMReactReduxBackEndApplication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//tells Spring boot that this class is injectable (is a "Spring Bean")
@Service
public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                AdamMReactReduxBackEndApplication.getStudent(1L, "Mariam", "mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 5), 21)
        );
    }
}
