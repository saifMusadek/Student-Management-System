package com.studentmanagement.service;

import com.studentmanagement.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();
    Student saveStudent(Student student);

    Student getStudentbyId(Long id);
    Student updateStudent(Student student);
    void deleteStudentbyId(Long id);
}
