package com.microservices.usermicroservice.service;

import com.microservices.usermicroservice.entities.Users;
import com.microservices.usermicroservice.models.Student;
import com.microservices.usermicroservice.models.Teacher;
import com.microservices.usermicroservice.repositories.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservices.usermicroservice.feignclients.IStudentFeignClient;
import com.microservices.usermicroservice.feignclients.ITeacherFeignClient;

@Service
public class UserService implements IUserService{
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private IStudentFeignClient studentFeignClient;
    
    @Autowired
    private ITeacherFeignClient teacherFeignClient;
        
    @Override
    public Teacher getTeacherByDni(String dni) {
        return teacherFeignClient.findByDni(dni);
    }

    @Override
    public Student getStudentByDni(String dni) {
        return studentFeignClient.findByDni(dni);
    }
    
    @Override
    public List<Teacher> findTeachersByEspecialidad(String especialidad) {
        return teacherFeignClient.listByEspecialidad(especialidad);
    }

    @Override
    public List<Student> findStudentsByFullName(String fullName) {
        return studentFeignClient.listByFullName(fullName);
    }
    
    @Override
    public Student saveStudent(Student student) {
        return studentFeignClient.save(student);
    }

    @Override
    public void deleteStudent(int studentId) {
        studentFeignClient.delete(studentId);
    }
    
    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherFeignClient.save(teacher);
    }

    @Override
    public void deleteTeacher(int teacherId) {
        teacherFeignClient.delete(teacherId);
    }

    @Override
    public List<Users> list() {
        return (ArrayList<Users>) userRepository.findAll();
    }

    @Override
    public Users findById(int userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    @Override
    public Users save(Users user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int userId) {
        userRepository.delete(this.findById(userId));
    }

    @Override
    public List<Teacher> listTeachers() {
        return teacherFeignClient.list();
    }

    @Override
    public List<Student> listStudents() {
        return studentFeignClient.list();
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        return teacherFeignClient.findTeacher(teacherId);
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentFeignClient.findStudent(studentId);
    }
    
}
