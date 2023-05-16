package com.geekster.studentportal.services;

import com.geekster.studentportal.models.Course;
import com.geekster.studentportal.models.Student;
import com.geekster.studentportal.repositories.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    IStudentDao studentDao;

    @Autowired
    CourseService courseService;

    public void addStudent(Student student) {
        List<Course> course =  student.getCourses();
        studentDao.save(student);
    }

    public Student getStudentById(Long studentId) {
        return studentDao.findStudentByStudentId(studentId);
    }

    public Iterable<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public String modifyStudentById(Long studentId, Long studentPhoneNumber) {
        Boolean isUpdated = studentDao.updateStudentPhoneNumberById(studentId,studentPhoneNumber);
        if(isUpdated) return "Student Updated !!";
        return "id "+studentId+" is not valid !!";
    }


}
