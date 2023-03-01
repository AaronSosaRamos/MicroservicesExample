package com.microservices.usermicroservice.service;

import com.microservices.usermicroservice.entities.Users;
import com.microservices.usermicroservice.models.Student;
import com.microservices.usermicroservice.models.Teacher;
import com.microservices.usermicroservice.repositories.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.microservices.usermicroservice.feignclients.IStudentFeignClient;
import com.microservices.usermicroservice.feignclients.ITeacherFeignClient;

@Service
public class UserService implements IUserService{
    
    @Autowired
    private RestTemplate restTemplate; 
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private IStudentFeignClient studentFeignClient;
    
    @Autowired
    private ITeacherFeignClient teacherFeignClient;
    
    //RestTemplate
    
    @Override
    public Teacher getTeacher(String dni) {
        Teacher teacher = restTemplate.getForObject("http://localhost:8003/teacher/findByDni?teacherDni={teacherDni}", Teacher.class, dni);
        return teacher;
    }

    @Override
    public Student getStudent(String dni) {
        Student student = restTemplate.getForObject("http://localhost:8002/student/findByDni?studentDni={studentDni}", Student.class, dni);
        return student;
    }
    
    @Override
    public List<Teacher> findTeachersByEspecialidad(String especialidad) {
        List<Teacher> teachers = restTemplate.getForObject("http://localhost:8003/teacher/listByEspecialidad?especialidad={especialidad}", List.class, especialidad);
        return teachers;
    }

    @Override
    public List<Student> findStudentsByFullName(String fullName) {
        List<Student> students = restTemplate.getForObject("http://localhost:8002/student/listByFullName?fullName={fullName}", List.class, fullName);
        return students;
    }
    
    //FeignClient
    @Override
    public Student saveStudentFC(Student student) {
        return studentFeignClient.save(student);
    }

    @Override
    public void deleteStudentFC(int studentId) {
        studentFeignClient.delete(studentId);
    }
    
    @Override
    public Teacher saveTeacherFC(Teacher teacher) {
        return teacherFeignClient.save(teacher);
    }

    @Override
    public void deleteTeacherFC(int teacherId) {
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
    
}
