package com.microservices.usermicroservice.service;

import com.microservices.usermicroservice.entities.Users;
import com.microservices.usermicroservice.models.Student;
import com.microservices.usermicroservice.models.Teacher;
import java.util.List;

public interface IUserService {
    
    public List<Users> list();
    public Users findById(int userId);
    public Users save(Users user);
    public void delete(int userId);
    
    //RestTemplate
    public Teacher getTeacher(String dni);
    public Student getStudent(String dni);

    public List<Teacher> findTeachersByEspecialidad(String especialidad);
    public List<Student> findStudentsByFullName(String fullName);
    
    //FeignClient
    public Student saveStudentFC (Student student);
    public void deleteStudentFC (int studentId); 
    
    public Teacher saveTeacherFC (Teacher teacher);
    public void deleteTeacherFC (int teacherId);
}
