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
    public List<StudentTest> getStudents() {
        return this.studentService.getStudents();
    }

    @PostMapping
    //@RequestBody tells springframework.web to map the body of the request to a Student object
    public void addStudent(@RequestBody StudentTest studentTest) {
        this.studentService.addStudent(studentTest);
    }

    @DeleteMapping(path = "/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long id) {
        return this.studentService.deleteStudent(id);
    }

    @PutMapping(path = "/{studentId}")
    public String updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        return this.studentService.updateStudent(studentId, name, email);
    }
}
