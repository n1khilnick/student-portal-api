package com.geekster.studentportal.controllers;

import com.geekster.studentportal.models.Student;
import com.geekster.studentportal.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping("/")
    public void addStudent(@RequestBody Student student){
         studentService.addStudent(student);
    }

    @GetMapping("/id/{studentId}")
        public ResponseEntity<Student> getStudentById(@PathVariable Long studentId){
        HttpStatus status = null;
        Student  student = studentService.getStudentById(studentId);
        if(student ==null){
            status = HttpStatus.BAD_REQUEST;
        }
        else{
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Student>(student,status);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Student>> getAllStudents(){
        Iterable<Student> students = studentService.getAllStudents();
        HttpStatus status;
        if(students == null){
            status = HttpStatus.NO_CONTENT;
        }
        else{
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Iterable<Student>>(students,status);
    }


    @PutMapping(value = "/")
    public ResponseEntity<String> updateStudentNameById(@RequestParam Long studentId,@RequestParam Long studentPhoneNumber){

        String message = studentService.modifyStudentById(studentId,studentPhoneNumber);
        HttpStatus status;
        if(!message.equals("Student Updated !!")){
            status = HttpStatus.NO_CONTENT;
        }
        else{
            status = HttpStatus.OK;
        }
        return new ResponseEntity<String>(message,status);
    }

//    @DeleteMapping("/")
//    public ResponseEntity<Student> deleteStudentById(@RequestParam Long studentId){
//        HttpStatus status = null;
//        studentService.deleteStudentById(studentId);
//        if(student ==null){
//            status = HttpStatus.BAD_REQUEST;
//        }
//        else{
//            status = HttpStatus.OK;
//        }
//        return new ResponseEntity<Student>(student,status);
//    }



}
