package com.test.ecolee.dto;

import com.test.ecolee.model.Class;
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
public class StudentDto {

    private Long id;
    @NotNull(message = "First Name should be not null")
    @Size(max = 50)
    private String firstName;
    @NotNull(message = "Last Name should be not null")
    @Size(max = 50)
    private String lastName;
    private ClassDto classe;
}
