package com.teachermicroservice.teachermicroservice.service;

import com.teachermicroservice.teachermicroservice.entities.Teacher;
import java.util.List;

public interface ITeacherService {
    
    public List<Teacher> list();
    public Teacher findById(int teacherId);
    public Teacher save(Teacher teacher);
    public void delete(int teacherId);
    
    public Teacher findByDni(String dni);
    public List<Teacher> findByEspecialidad(String especialidad);
    
}
