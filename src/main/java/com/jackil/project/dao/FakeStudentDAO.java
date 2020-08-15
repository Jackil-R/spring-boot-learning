package com.jackil.project.dao;

import com.jackil.project.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fake")
public class FakeStudentDAO implements StudentDAO {

    private final Map<UUID,Student> database;

    public FakeStudentDAO() {
        this.database = new HashMap<>();
    }

    @Override
    public int insertStudent(UUID studentID, Student student) {
       database.put(studentID, student);
       return 1;
    }

    @Override
    public Optional<Student> selectStudentById(UUID studentID) {
       return Optional.ofNullable(database.get(studentID));
    }

    @Override
    public List<Student> selectAllStudents() {
        return new ArrayList<>(database.values());
    }

    @Override
    public int updateStudentById(UUID studentID, Student student) {
        database.put(studentID, student);
        return 1;
    }

    @Override
    public int deleteStudentById(UUID studentID) {
        database.remove(studentID);
        return 1;
    }
}
