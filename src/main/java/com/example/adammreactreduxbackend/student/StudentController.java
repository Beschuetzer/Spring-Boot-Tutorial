package com.example.adammreactreduxbackend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    //Autowired auto-magically injects the objects specified in the constructor's param list via Spring boot dependency injection
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    @PostMapping
    //@RequestBody tells springframework.web to map the body of the request to a Student object
    public void addStudent(@RequestBody Student student) {
        this.studentService.addStudent(student);
    }
}
