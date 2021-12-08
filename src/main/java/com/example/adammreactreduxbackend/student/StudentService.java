package com.example.adammreactreduxbackend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//tells Spring boot that this class is injectable (is a "Spring Bean")
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    //using dependency injection to auto-inject student repository into service
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        //this is an interface that extends JpaRepository
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return List.of(null);
    }
}
