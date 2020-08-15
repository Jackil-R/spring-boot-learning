package com.jackil.project.dao;

import com.jackil.project.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("real")
public class RealStudentDAO implements StudentDAO {

    private final JdbcTemplate jdbcTemplate;

    public RealStudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertStudent(UUID studentID, Student student) {
        final String sql = "INSERT INTO Student (id, FirstName, LastName, Course, Age) VALUES (?, ?, ?, ?, ?)";
        Object[] param = {studentID, student.getFirstName(),student.getLastName(), student.getCourse(), student.getAge()};
        return jdbcTemplate.update(sql, param);
    }

    @Override
    public Optional<Student> selectStudentById(UUID studentID) {
        final String sql = "SELECT id, FirstName, LastName, Course, Age FROM Student WHERE id = ?";
        Student student = jdbcTemplate.queryForObject(sql, new Object[]{studentID}, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String course = resultSet.getString("Course");
            Integer age = resultSet.getInt("Age");
            return new Student(id, firstName, lastName, course, age);
        });
        return Optional.ofNullable(student);
    }

    @Override
    public List<Student> selectAllStudents() {
        final String sql = "SELECT id, FirstName, LastName, Course, Age FROM Student";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String course = resultSet.getString("Course");
            Integer age = resultSet.getInt("Age");
            return new Student(id, firstName, lastName, course, age);
        });
    }

    @Override
    public int updateStudentById(UUID studentID, Student student) {
        final String sql = "UPDATE person SET FirstName = ?, LastName = ? , Course = ?, Age = ?  WHERE id = ?";
        Object[] param = {student.getFirstName(),student.getLastName(), student.getCourse(), student.getAge(), studentID};
        return jdbcTemplate.update(sql,param);
    }

    @Override
    public int deleteStudentById(UUID studentID) {
        final String sql = "DELETE FROM Student WHERE id = ?";
        return jdbcTemplate.update(sql, studentID);
    }
}
