package com.test.ecolee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.ecolee.exception.NotFoundException;
import com.test.ecolee.model.Class;
import com.test.ecolee.model.Student;
import com.test.ecolee.model.Teacher;
import com.test.ecolee.service.IClassService;
import com.test.ecolee.utils.ModelsMockGenrator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class StudentControllerTest {

    @MockBean
    private IClassService iClassService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_create_teacher_then_return_created() throws Exception {
        // GIVEN
        Teacher teacher = ModelsMockGenrator.createTeacher();
        Mockito.doReturn(teacher).when(iClassService)
                .saveTeacher(Mockito.any(Teacher.class));
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/teacher").content(objectMapper.writeValueAsString(teacher))
                        .contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2V6eiIsImlhdCI6MTY4NDEwNDAzNSwiZXhwIjoxNjg0MTkwNDM1fQ.JGhGpWY0gjRwEEhn2dyTh5GsQR3UIPG3OB6RsS1RbSVhJk4gP0_l43okSar3-Jz_iZYNqZK-eRzE_Fpgf9qTbw"))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        Matchers.is("moez")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        Matchers.is("abid")));
    }

    @Test
    void should_create_student_then_return_unauthorized() throws Exception {
        // GIVEN
        Mockito.doThrow(new NotFoundException(
                        String.format("you need a access token2")))
                .when(iClassService).saveStudent(Mockito.any(Student.class));
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student"))
        // THEN
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void should_create_teacher_then_return_unauthorized() throws Exception {
        // GIVEN
        Mockito.doThrow(new NotFoundException(
                        String.format("you need a access token2")))
                .when(iClassService).saveTeacher(Mockito.any(Teacher.class));
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher"))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void should_create_class_then_return_unauthorized() throws Exception {
        // GIVEN
        Mockito.doThrow(new NotFoundException(
                        String.format("you need a access token")))
                .when(iClassService).saveClass(Mockito.any(Class.class));
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/class"))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void should_create_class_then_return_created() throws Exception {
        // GIVEN
        Class classe = ModelsMockGenrator.createClass();

        Mockito.doReturn(classe).when(iClassService)
                .saveClass(Mockito.any(Class.class));
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/class").content(objectMapper.writeValueAsString(classe))
                        .contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2V6eiIsImlhdCI6MTY4NDEwNDAzNSwiZXhwIjoxNjg0MTkwNDM1fQ.JGhGpWY0gjRwEEhn2dyTh5GsQR3UIPG3OB6RsS1RbSVhJk4gP0_l43okSar3-Jz_iZYNqZK-eRzE_Fpgf9qTbw"))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        Matchers.is("9eme")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher.firstName",
                        Matchers.is("moez")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher.lastName",
                        Matchers.is("abid")));
    }

    @Test
    void should_create_student_then_return_created() throws Exception {
        // GIVEN
        Student student = ModelsMockGenrator.createStudent();

        Mockito.doReturn(student).when(iClassService)
                .saveStudent(Mockito.any(Student.class));
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/student").content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2V6eiIsImlhdCI6MTY4NDEwNDAzNSwiZXhwIjoxNjg0MTkwNDM1fQ.JGhGpWY0gjRwEEhn2dyTh5GsQR3UIPG3OB6RsS1RbSVhJk4gP0_l43okSar3-Jz_iZYNqZK-eRzE_Fpgf9qTbw"))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        Matchers.is("ahmed")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.class.name",
                        Matchers.is("9eme")));
    }
}
