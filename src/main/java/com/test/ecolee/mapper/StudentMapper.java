package com.test.ecolee.mapper;

import com.test.ecolee.dto.StudentDto;
import com.test.ecolee.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ClassMapper.class})
public interface StudentMapper {

    StudentDto toDto(Student student);

    Student toStudent(StudentDto studentDto);
}