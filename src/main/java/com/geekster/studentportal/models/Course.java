package com.geekster.studentportal.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Course.class,property = "courseId")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String title;
    private String description;
    private Double duration;

    @ManyToMany(cascade=CascadeType.ALL)
    List<Student> students;

}
