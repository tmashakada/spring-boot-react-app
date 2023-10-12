package com.mashsoft.springbootreact.controller;

import com.mashsoft.springbootreact.model.Student;
import com.mashsoft.springbootreact.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>>getStudents(){
        return  new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);

    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student,@PathVariable Long id){
        return studentService.updateStudent(student,id);
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);

    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }



}
