package com.example.adammreactreduxbackend.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//first type 'Student' corresponds to the type of the object this respository works with
//second type 'Long' corresponds to the type of the first type's Id
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //this query is what is run when this function is called and uses JPQL syntax
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    //named parameter using @Param (JPQL and native)
    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Optional<Student> findStudentByEmailNamedParameter(
            @Param("email") String email
    );

    //using native SQL syntax
    @Query(
            value = "SELECT * FROM Student s WHERE s.email = ?1",
            nativeQuery = true
    )
    Optional<Student> findStudentByEmailNative(String email);

    //named parameter using native SQL syntax
    @Query(
            value = "SELECT * FROM Student s WHERE s.email = :email",
            nativeQuery = true
    )
    Optional<Student> findStudentByEmailNativeNamedParameter(
            @Param("email") String email
    );
}
