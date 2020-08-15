package com.jackil.project.dao;

import com.jackil.project.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface StudentDAO {

    int insertStudent(UUID studentID, Student student);

    Optional<Student> selectStudentById(UUID studentID);

    List<Student> selectAllStudents();

    int updateStudentById(UUID studentID, Student student);

    int deleteStudentById(UUID studentID);

}
