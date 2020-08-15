package com.jackil.project.controller;

import com.jackil.project.exception.StudentNotFoundException;
import com.jackil.project.model.Student;
import com.jackil.project.service.StudentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(
            value = "insertStudent",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addStudent(@Valid @NonNull @RequestBody Student student) {
        this.studentService.persistNewStudent(UUID.randomUUID(), student);
    }

    @RequestMapping(
            value = "getStudents",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(
            path = "getStudent/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Optional<Student> getStudent(@PathVariable("id")UUID studentID) throws StudentNotFoundException {
        return studentService.getStudentById(studentID);
    }

    @SneakyThrows
    @RequestMapping(
            path = "deleteStudent/{id}",
            method = RequestMethod.DELETE
    )
    public void deleteStudent(@PathVariable("id") UUID studentID) {
        studentService.deleteStudentById(studentID);
    }

    @SneakyThrows
    @RequestMapping(
            path = "updateStudent/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void updateStudent(@PathVariable("id") UUID studentID, @Valid @NonNull @RequestBody  Student student) {
        studentService.updateStudentById(studentID, student);
    }


}
