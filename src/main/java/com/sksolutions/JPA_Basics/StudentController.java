package com.sksolutions.JPA_Basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add-student")
    public ResponseEntity addStudent(@RequestBody Student student){
        String response = studentService.addStudent(student);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-student-info")
    public ResponseEntity getStudentById(@RequestParam("id") int rollNo){
        Student student = studentService.getStudentById(rollNo);
        if(student == null){
            return new ResponseEntity("Invalid Roll No",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(student,HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-student")
    public ResponseEntity deleteStudentById(@RequestParam("id") int rollNo){
        String response = studentService.deleteStudentById(rollNo);
        return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update-age")
    public ResponseEntity updateStudentAge(@RequestParam("id") int rollNo,
                                           @RequestParam("age") int age){
        Student student = studentService.updateStudentAge(rollNo,age);
        if(student == null){
            return new ResponseEntity("Invalid Roll No",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("student age updated successfully"+student,HttpStatus.FOUND);
    }

    @GetMapping("/get-students-age-greater-than")
    public ResponseEntity getStudentsAgeGreaterThan(@RequestParam("age") int age){
        List<String> list = studentService.getStudentsAgeGreaterThan(age);
        if(list.isEmpty()){
            return new ResponseEntity("all students are younger than "+age,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(list,HttpStatus.FOUND);
    }

    @GetMapping("/find-students-by-name")
    public ResponseEntity findAllStudentsByName(@RequestParam("name") String name){
        List<Student> list = studentService.getAllStudentsByName(name);
        if(list.isEmpty()){
            return new ResponseEntity("there are no students with name : "+name,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(list,HttpStatus.FOUND);
    }

}
