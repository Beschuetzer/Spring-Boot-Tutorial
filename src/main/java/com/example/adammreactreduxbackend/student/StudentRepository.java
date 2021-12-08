package com.example.adammreactreduxbackend.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//first type 'Student' corresponds to the type of the object this respository works with
//second type 'Long' corresponds to the type of the first type's Id
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
