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
    
    public List<Teacher> listTeachers();
    public List<Student> listStudents();
    
    public Teacher getTeacherById(int teacherId);
    public Student getStudentById(int studentId);
    
    public Teacher getTeacherByDni(String dni);
    public Student getStudentByDni(String dni);

    public List<Teacher> findTeachersByEspecialidad(String especialidad);
    public List<Student> findStudentsByFullName(String fullName);
    
    public Student saveStudent (Student student);
    public void deleteStudent (int studentId); 
    
    public Teacher saveTeacher (Teacher teacher);
    public void deleteTeacher (int teacherId);
}
