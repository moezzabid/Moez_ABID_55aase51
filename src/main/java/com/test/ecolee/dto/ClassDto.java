package com.test.ecolee.dto;

import com.test.ecolee.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassDto {

    private Long id;
    @NotNull(message = "name should be not null")
    @Size(max = 50)
    private String name;
    private TeacherDto teacher;
}
