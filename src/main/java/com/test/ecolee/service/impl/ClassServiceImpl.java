package com.test.ecolee.service.impl;

import com.test.ecolee.exception.ConflictException;
import com.test.ecolee.model.Class;
import com.test.ecolee.model.Student;
import com.test.ecolee.model.Teacher;
import com.test.ecolee.repository.ClassRepository;
import com.test.ecolee.repository.StudentRepository;
import com.test.ecolee.repository.TeacherRepository;
import com.test.ecolee.service.IClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements IClassService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public Page<Student> getAllStudentByClass(Pageable pageable, String name) {
            return studentRepository.findAllByClasse(pageable, name);
    }

    @Override
    public Page<Student> getAllStudentByTeacher(Pageable pageable, String firstName, String lastName) {
        Optional<Teacher> teacherOptional = teacherRepository.findByFirstNameAndLastName(
                firstName, lastName);

        teacherOptional.ifPresent(teacherDb -> {
            studentRepository.findAllByFirstNameAndLastNameTeacher(pageable, teacherDb.getFirstName(), teacherDb.getLastName());
        });
         return  getAllStudent(pageable);
    }

    @Override
    public Page<Student> getAllStudentByTeacherAndClass(Pageable pageable, String firstName, String lastName, String name) {
        if (firstName != null && lastName != null && name != null) {
            return studentRepository.findAllByClasseAndAndFirstNameAndLastNameTeacher(pageable, firstName, lastName, name);
        }
        return getAllStudent(pageable);
    }

    @Override
    public Page<Student> getAllStudent(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student saveStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findByFirstNameAndLastName(
                student.getFirstName(),student.getLastName());

        studentOptional.ifPresent(studentDb -> {
            throw new ConflictException(String.format("name student is already exist"));
        });
        student.setCreatedBy("moez");
        student.setLastModifiedBy("moez");
        return studentRepository.save(student);
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findByFirstNameAndLastName(
                teacher.getFirstName(),teacher.getLastName());

        teacherOptional.ifPresent(teacherDb -> {
            throw new ConflictException(String.format("name teacher is already exist"));
        });
        teacher.setCreatedBy("moez");
        teacher.setLastModifiedBy("moez");
        return teacherRepository.save(teacher);
    }

    @Override
    public Class saveClass(Class classe) {
        System.out.println(classe.getTeacher());
        classe.setCreatedBy("moez");
        classe.setLastModifiedBy("moez");
        return classRepository.save(classe);
    }

}
