package com.teachermicroservice.teachermicroservice.service;

import com.teachermicroservice.teachermicroservice.entities.Teacher;
import com.teachermicroservice.teachermicroservice.repositories.ITeacherRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements ITeacherService{

    @Autowired
    private ITeacherRepository teacherRepository;
    
    @Override
    public List<Teacher> list() {
        return (ArrayList<Teacher>) teacherRepository.findAll();
    }

    @Override
    public Teacher findById(int teacherId) {
        return teacherRepository.findById(teacherId)
                .orElse(null);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(int teacherId) {
        teacherRepository.delete(this.findById(teacherId));
    }

    @Override
    public Teacher findByDni(String dni) {
        return teacherRepository.findByDni(dni)
                .orElse(null);
    }

    @Override
    public List<Teacher> findByEspecialidad(String especialidad) {
        return teacherRepository.findByEspecialidad(especialidad);
    }
    
}
