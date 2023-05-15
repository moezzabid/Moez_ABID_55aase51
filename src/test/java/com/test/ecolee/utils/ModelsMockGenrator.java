package com.test.ecolee.utils;

import com.test.ecolee.model.Class;
import com.test.ecolee.model.Student;
import com.test.ecolee.model.Teacher;

import java.time.Instant;

public class ModelsMockGenrator {

    public static Teacher createTeacher(){

        Teacher teacher = new Teacher();
        teacher.setFirstName("moez");
        teacher.setLastName("abid");
        teacher.setCreatedBy("moezz");
        teacher.setLastModifiedBy("test");
        teacher.setCreatedDate(Instant.now());
        teacher.setLastModifiedDate(Instant.now());
        return teacher ;

    }

    public static Class createClass(){

        Class classe = new Class();
        classe.setName("9eme");
        classe.setTeacher(createTeacher());
        classe.setCreatedBy("moezz");
        classe.setLastModifiedBy("test");
        classe.setCreatedDate(Instant.now());
        classe.setLastModifiedDate(Instant.now());
        return classe ;
    }


    public static Student createStudent(){

        Student student = new Student();
        student.setFirstName("ahmed");
        student.setLastName("test");
        student.setClasse(createClass());
        student.setCreatedBy("moezz");
        student.setLastModifiedBy("test");
        student.setCreatedDate(Instant.now());
        student.setLastModifiedDate(Instant.now());
        return student ;
    }
}
