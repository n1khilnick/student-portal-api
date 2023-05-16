package com.geekster.studentportal.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.geekster.studentportal.models.enums.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Student.class,property = "studentId")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String studentName;

    private Integer studentAge;

    private Long studentPhoneNumber;

    private String studentPassword;

    private String branch;

    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToOne
    private Address address;

    @OneToOne
    private Laptop laptop;

    @ManyToMany
    private List<Course> courses;

    @OneToMany
    private List<Book> books;
}
