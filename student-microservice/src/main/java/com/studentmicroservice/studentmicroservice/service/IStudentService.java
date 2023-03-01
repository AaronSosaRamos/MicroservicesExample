package com.studentmicroservice.studentmicroservice.service;

import com.studentmicroservice.studentmicroservice.entities.Student;
import java.util.List;

public interface IStudentService {
    
    public List<Student> list();
    public Student findById(int studentId);
    public Student save(Student student);
    public void delete(int studentId);
    
    public Student findByDni(String dni);
    public List<Student> findByFullName(String fullName);
    
}
