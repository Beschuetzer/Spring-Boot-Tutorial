package com.example.adammreactreduxbackend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
        }
        ;
        studentRepository.deleteById(idToDelete);
        return String.format("Successfully deleted student with id of %s", idToDelete);
    }

    //@Transactional causes the entity (the returned value from 'studentRepository.findById(id)') to go
    //into a 'managed state', meaning the setters for the Object (.setName(newName) and .setEmail(newEmail)) will update
    //the entity in the DB too.
    //The way to trigger a rollback is to throw an exception
    @Transactional
    public String updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findStudentByEmailNativeNamedParameter(email)
                .orElseThrow(() -> {
                    System.out.println("id = " + id);
                    return new IllegalStateException(String.format("There is no student with the id of %s.", id));
                });

        if (!name.equals(null) && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (!email.equals(null) && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException(String.format("The email %s is already taken.  Try another.", email));
            }
            student.setEmail(email);
        }

        System.out.println("name = " + name);
        System.out.println("email = " + email);

        return "Successfully updated student!";
    }
}
