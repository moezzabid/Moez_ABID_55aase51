package com.test.ecolee.mapper;

import com.test.ecolee.dto.TeacherDto;
import com.test.ecolee.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudentMapper.class,ClassMapper.class})
public interface TeacherMapper {

    TeacherDto toDto(Teacher teacher);
    Teacher toTeacher(TeacherDto teacherDto);
}
