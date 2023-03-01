package com.teachermicroservice.teachermicroservice.controller;

import com.teachermicroservice.teachermicroservice.entities.Teacher;
import com.teachermicroservice.teachermicroservice.service.ITeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    
    @Autowired
    private ITeacherService teacherService;
    
    @GetMapping
    public ResponseEntity<List<Teacher>> list(){
        List<Teacher> teacherList = teacherService.list();
        if(teacherList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(teacherList);
    }
    
    @GetMapping("/find")
    public ResponseEntity<Teacher> findTeacher(@RequestParam(name="teacherId",defaultValue="0") int teacherId){
        Teacher teacher = teacherService.findById(teacherId);
        
        if(teacher==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(teacher);
    }
    
    @PostMapping("/save")
    public ResponseEntity<Teacher> save(@RequestBody Teacher teacher){
        Teacher savedTeacher = teacherService.save(teacher);
        
        if(savedTeacher==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(savedTeacher);
    }
    
    @DeleteMapping("/delete")
    public String delete(@RequestParam(name="teacherId",defaultValue="0") int teacherId){
        teacherService.delete(teacherId);
        return "Se ha eliminado al docente satisfactoriamente";
    }
    
    @GetMapping("/findByDni")
    public ResponseEntity<Teacher> findByDni(@RequestParam(name="teacherDni",defaultValue="0") String teacherDni){
        Teacher teacher = teacherService.findByDni(teacherDni);
        
        if(teacher==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(teacher);
    }
    
    @GetMapping("/listByEspecialidad")
    public ResponseEntity<List<Teacher>> listByEspecialidad(@RequestParam(name="especialidad",defaultValue="0") String especialidad){
        List<Teacher> teacherList = teacherService.findByEspecialidad(especialidad);
        if(teacherList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(teacherList);
    }
    
}
