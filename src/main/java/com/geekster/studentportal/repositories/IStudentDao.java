package com.geekster.studentportal.repositories;

import com.geekster.studentportal.models.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentDao extends CrudRepository<Student,Long> {
    Student findFirstByStudentPhoneNumber(Long studentPhoneNumber);

    Student findStudentByStudentId(Long studentId);

    @Modifying
    @Query(value = "update user_table set user_phone_number = :userPhoneNumber where user_id = :userId",nativeQuery = true)
    Boolean updateStudentPhoneNumberById(Long userId, Long userPhoneNumber);

}
