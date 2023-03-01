package com.studentmicroservice.studentmicroservice.service;

import com.studentmicroservice.studentmicroservice.entities.Student;
import com.studentmicroservice.studentmicroservice.repositories.IStudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService{
    
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public List<Student> list() {
        return (ArrayList<Student>) studentRepository.findAll();
    }

    @Override
    public Student findById(int studentId) {
        return studentRepository.findById(studentId)
                .orElse(null);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(int studentId) {
        studentRepository.delete(this.findById(studentId));
    }

    @Override
    public Student findByDni(String dni) {
        return studentRepository.findByDni(dni)
                .orElse(null);
    }

    @Override
    public List<Student> findByFullName(String fullName) {
        return (ArrayList<Student>) studentRepository.findByFullName(fullName);
    }
    
}
