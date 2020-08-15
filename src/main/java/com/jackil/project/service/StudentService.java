package com.jackil.project.service;

import com.jackil.project.dao.StudentDAO;
import com.jackil.project.exception.StudentNotFoundException;
import com.jackil.project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class StudentService {


    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(@Qualifier("fake") StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }


    public int persistNewStudent(UUID studentID, Student student) {
        UUID studentUUID = studentID == null ? UUID.randomUUID() : studentID;
        student.setId(studentID);
        return studentDAO.insertStudent(studentUUID, student);
    }

    public Optional<Student> getStudentById(UUID studentID) throws StudentNotFoundException {
        Optional<Student> student = studentDAO.selectStudentById(studentID);
        if (student.isPresent()) {
            return student;
        }
        throw new StudentNotFoundException("Student not found");
    }

    public List<Student> getAllStudents() {
        return studentDAO.selectAllStudents();
    }

    public int updateStudentById(UUID studentID, Student studentToUpdate) throws StudentNotFoundException {
        return studentDAO.updateStudentById(getStudentById(studentID).get().getId(), studentToUpdate);
    }

    public int deleteStudentById(UUID studentID) throws StudentNotFoundException {
        return studentDAO.deleteStudentById(getStudentById(studentID).get().getId());
    }
}
