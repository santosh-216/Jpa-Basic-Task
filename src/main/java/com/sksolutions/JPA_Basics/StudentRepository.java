package com.sksolutions.JPA_Basics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("select s.name from Student s where s.age > :age")
    List<String> findByAgeGreaterThan(@Param("age") int age);
    List<Student> findByName(String name);
}