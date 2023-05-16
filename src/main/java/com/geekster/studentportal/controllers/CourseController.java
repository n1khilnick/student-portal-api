package com.geekster.studentportal.controllers;

import com.geekster.studentportal.models.Course;
import com.geekster.studentportal.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    CourseService courseService;


    @PostMapping(value = "/")
    public void addCourse(@RequestBody Course Course){
        courseService.createCourse(Course);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Iterable<Course>> getAllCourses(){
        Iterable<Course> courses = courseService.getAllCourses();
        HttpStatus status;
        if(courses == null){
            status = HttpStatus.NO_CONTENT;
        }
        else{
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Iterable<Course>>(courses,status);
    }

    @GetMapping(value = "/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable    Long courseId) {
        HttpStatus status = null;
        Course Course = courseService.getCourseById(courseId);
        if (Course == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Course>(Course, status);

    }

    @PutMapping(value = "/update/id/{courseId}/duration/{duration}")
    public ResponseEntity<String> updateCourseDurationById(@PathVariable Long courseId,@PathVariable Double duration){

        Course Course = courseService.getCourseById(courseId);
        String message = null;
        HttpStatus status;

        if(Course == null){

            status = HttpStatus.NOT_MODIFIED;
            message = "id "+courseId+" is not valid";
        }
        else{
            status = HttpStatus.OK;
            courseService.modifyCourseDurationById(courseId,duration);

        }
        return new ResponseEntity<String>(message,status);
    }

    @DeleteMapping("/id/{courseId}")
    public ResponseEntity<String>removeAddress(@PathVariable Long addressId){
        HttpStatus status;
        String message = null;
        try{
            courseService.deleteCourseById(addressId);
            status = HttpStatus.OK;
            message = "Address deleted successfully !!!";

        }
        catch (Exception ex){
            status = HttpStatus.BAD_REQUEST;
            message = "AddressId not exist";
            System.out.println(ex);
        }
        return new ResponseEntity<String>(message,status);
    }


}
