package com.test.ecolee.mapper;

import com.test.ecolee.dto.ClassDto;
import com.test.ecolee.model.Class;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface ClassMapper {
    ClassDto toDto(Class classe);

    Class toClasse(ClassDto classDto);
}
