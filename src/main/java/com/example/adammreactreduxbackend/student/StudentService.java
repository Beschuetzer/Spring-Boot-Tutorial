package com.example.adammreactreduxbackend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional studentFound = studentRepository.findStudentByEmail(student.getEmail());
        if (studentFound.isPresent()) {
            throw new IllegalStateException("Email taken");
        }

        studentRepository.save(student);
    }

    public String deleteStudent(Long idToDelete) {
        System.out.println("idToDelete = " + idToDelete);
        Boolean studentExistsInDB = studentRepository.existsById(idToDelete);
        if (!studentExistsInDB) {
            throw new IllegalStateException(String.format("No entry for the id %s found", idToDelete));
        };
        studentRepository.deleteById(idToDelete);
        return String.format("Successfully deleted student with id of %s", idToDelete);
    }

    //allows you not to have to write JDBA query to update the db record/row
    //basically allows you to use the repository's .findById() returned Student object (JPA object)
    //to call .setName(newName) and .setEmail(newEmail) setters to update the new values
    @Transactional
    public String updateStudent(String name, String email) {
        System.out.println("name = " + name);
        System.out.println("email = " + email);

        return "Successfully updated student!";
    }
}
