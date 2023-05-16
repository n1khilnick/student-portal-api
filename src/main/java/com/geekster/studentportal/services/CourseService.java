package com.geekster.studentportal.services;

import com.geekster.studentportal.models.Course;
import com.geekster.studentportal.models.Student;
import com.geekster.studentportal.repositories.ICourseDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    ICourseDao courseDao;

    List<Student> students;

    public void saveCourse(Course course) {
        courseDao.save(course);
    }

    public void createCourse(Course course) {
        course.setStudents(students);
        courseDao.save(course);
    }

    public Iterable<Course> getAllCourses() {
        return courseDao.findAll();
    }

    public Course getCourseById(Long courseId) {
        return courseDao.findCourseByCourseId(courseId);
    }

    @Transactional
    public void modifyCourseDurationById(Long courseId, Double duration) {
        courseDao.updateCourseDurationByCourseId(courseId,duration);
    }

    @Transactional
    public void deleteCourseById(Long courseId) {
        courseDao.deleteById(courseId);
    }
}
