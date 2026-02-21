package com.sksolutions.JPA_Basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String addStudent(Student student){
        studentRepository.save(student);
        return "student added successfully";
    }

    public Student getStudentById(int rollNo){
        Optional<Student> optionalStudent = studentRepository.findById(rollNo);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        return null;
    }

    public String deleteStudentById(int rollNo){
        if(studentRepository.existsById(rollNo)){
            studentRepository.deleteById(rollNo);
            return rollNo+" deleted successfully";
        }
        return "student not found";
    }

    public Student updateStudentAge(int rollNo,int age){
        Optional<Student> optionalStudent = studentRepository.findById(rollNo);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            student.setAge(age);
            studentRepository.save(student);
            return student;
        }
        return null;
    }

    public List<String> getStudentsAgeGreaterThan(int age){
        return studentRepository.findByAgeGreaterThan(age);
    }

    public List<Student>getAllStudentsByName(String name){
        return studentRepository.findByName(name);
    }



}
