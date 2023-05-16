package com.geekster.studentportal.repositories;

import com.geekster.studentportal.models.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseDao extends CrudRepository<Course,Long> {

    Course findCourseByCourseId(Long courseId);

    @Modifying
    @Query(value = "update courses set duration =:duration where course_id =:courseId",nativeQuery = true)
    void updateCourseDurationByCourseId(Long courseId, Double duration);
}
