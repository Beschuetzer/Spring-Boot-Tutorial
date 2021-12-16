package com.example.adammreactreduxbackend.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

//first type 'Student' corresponds to the type of the object this respository works with
//second type 'Long' corresponds to the type of the first type's Id
@Repository
public interface StudentRepository extends JpaRepository<StudentTest, Long> {

    //this query is what is run when this function is called and uses JPQL syntax
    @Query("SELECT s FROM StudentTest s WHERE s.email = ?1")
    Optional<StudentTest> findStudentByEmail(String email);

    //named parameter using @Param (JPQL and native)
    @Query("SELECT s FROM StudentTest s WHERE s.email = :email")
    Optional<StudentTest> findStudentByEmailNamedParameter(
            @Param("email") String email
    );

    //using native SQL syntax
    @Query(
            value = "SELECT * FROM Student s WHERE s.email = ?1",
            nativeQuery = true
    )
    Optional<StudentTest> findStudentByEmailNative(String email);

    //named parameter using native SQL syntax
    @Query(
            value = "SELECT * FROM Student s WHERE s.email = :email",
            nativeQuery = true
    )
    Optional<StudentTest> findStudentByEmailNativeNamedParameter(
            @Param("email") String email
    );

    //using Collection<String> to specify the '("name1", "name2", ...)' part of the query
    @Query(
            value = "SELECT * FROM Student s WHERE s.name IN :names",
            nativeQuery = true
    )
    Optional<StudentTest> findStudentByCollectionParameter(
            @Param("names") Collection<String> names
    );

    //@Modifying is necessary for queries that modify the DB
    @Modifying
    @Query(
            value = "UPDATE student SET email = :email WHERE name = :name",
            nativeQuery = true
    )
    int updateStudentSetEmailForName(
            @Param("name") String name,
            @Param("email") String email
    );

    //Insert queries must be written using native syntax
    @Modifying
    @Query(
            value = "INSERT into student (id, dob, email, name) VALUES(-1, :dob, :email, :name)",
            nativeQuery = true
    )
    int insertStudent(
            @Param("name") String name,
            @Param("email") String email,
            @Param("dob") LocalDate dob
    );

}
