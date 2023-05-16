package com.test.ecolee.service;

import com.test.ecolee.model.Class;
import com.test.ecolee.model.Student;
import com.test.ecolee.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClassService {

    Page<Student> getAllStudentByClass(Pageable pageable, String name);

    Page<Student> getAllStudentByTeacher(Pageable pageable, String firstName, String lastName);
    Page<Student> getAllStudentByTeacherAndClass(Pageable pageable, String firstName, String lastName,String name);

    Page<Student> getAllStudent(Pageable pageable);

    Student saveStudent(Student student);

    Teacher saveTeacher(Teacher teacher);

    Class saveClass(Class classe);
}
