package com.test.ecolee.controller;


import com.test.ecolee.dto.ClassDto;
import com.test.ecolee.dto.StudentDto;
import com.test.ecolee.dto.TeacherDto;
import com.test.ecolee.exception.ConflictException;
import com.test.ecolee.exception.NotFoundException;
import com.test.ecolee.mapper.ClassMapper;
import com.test.ecolee.mapper.StudentMapper;
import com.test.ecolee.mapper.TeacherMapper;
import com.test.ecolee.model.Student;
import com.test.ecolee.service.IClassService;
import com.test.ecolee.utils.PaginationUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class StudentController {

    private final IClassService iClassService;
    private final ClassMapper classMapper;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    @PostMapping("/class")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "please pass sso token in the format Beare ", dataType = "String", paramType = "header", required = true),
    })
    public ResponseEntity<ClassDto> createClass(@Valid @RequestBody ClassDto classe) {
        try {
            ClassDto classDto = classMapper.toDto(
                    iClassService.saveClass(
                            classMapper.toClasse(classe)
                    ));

            return ResponseEntity.ok(classDto);
        } catch (NotFoundException | ConflictException e) {
            if (e instanceof NotFoundException) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/teacher")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "please pass sso token in the format Beare ", dataType = "String", paramType = "header", required = true),
    })
    public ResponseEntity<TeacherDto> createTeacher(@Valid @RequestBody TeacherDto teacher) {
        try {
            TeacherDto teacherDto = teacherMapper.toDto(
                    iClassService.saveTeacher(
                            teacherMapper.toTeacher(teacher)
                    ));
            return ResponseEntity.ok(teacherDto);
        } catch (NotFoundException | ConflictException e) {
            if (e instanceof NotFoundException) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/student")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "please pass sso token in the format Beare XXASDACS", dataType = "String", paramType = "header", required = true),
    })
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto student) {
        try {
            StudentDto studentDto = studentMapper.toDto(
                    iClassService.saveStudent(
                            studentMapper.toStudent(student)
                    ));
            return ResponseEntity.ok(studentDto);
        } catch (NotFoundException | ConflictException e) {
            if (e instanceof NotFoundException) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAllStudentByClass")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "please pass sso token in the format Beare ", dataType = "String", paramType = "header", required = true),
    })
    public ResponseEntity<Map<String, Object>> getAllStudentByClass(@RequestParam(value = "name",required = false) String name, @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        try {
            List<Student> studentList = new ArrayList<Student>();
            Pageable paging = PageRequest.of(page, size);
            Page<Student> students;
            if (name == null)
                students = iClassService.getAllStudent(paging);
            else
                students = iClassService.getAllStudentByClass(paging, name);
            studentList = students.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("Students", studentList);
            response.put("currentPage", students.getNumber());
            response.put("totalItems", students.getTotalElements());
            response.put("totalPages", students.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllStudentByNameTeacherAndClass")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "please pass sso token in the format Beare ", dataType = "String", paramType = "header", required = true),
    })
    public ResponseEntity<List<Student>> getAllStudentByNameTeacherAndClassName(@RequestParam(value = "firstName",required = false ) String firstName, @RequestParam(value = "lastName",required = false ) String lastName,@RequestParam(value = "className",required = false ) String name, Pageable pageable) {
        final Page<Student> page = iClassService.getAllStudentByTeacherAndClass(pageable, firstName, lastName,name);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getAllStudentByNameTeacherAndClass");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}

