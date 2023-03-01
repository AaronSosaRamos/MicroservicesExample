package com.microservices.usermicroservice.controllers;

import com.microservices.usermicroservice.entities.Users;
import com.microservices.usermicroservice.models.Student;
import com.microservices.usermicroservice.models.Teacher;
import com.microservices.usermicroservice.service.IUserService;
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
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private IUserService userService;
    
    @GetMapping
    public ResponseEntity<List<Users>> list(){
        List<Users> userList = userService.list();
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(userList);
    }
    
    @GetMapping("/find")
    public ResponseEntity<Users> findUser(@RequestParam(name="userId",defaultValue="0") int userId){
        Users user = userService.findById(userId);
        
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/save")
    public ResponseEntity<Users> save(@RequestBody Users user){
        Users savedUser = userService.save(user);
        
        if(savedUser==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(savedUser);
    }
    
    @DeleteMapping("/delete")
    public String delete(@RequestParam(name="userId",defaultValue="0") int userId){
        userService.delete(userId);
        return "Se ha eliminado al usuario satisfactoriamente";
    }
    
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(@RequestParam(name="dni",defaultValue="0") String dni){
        Student student = userService.getStudent(dni);
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(student);
    }
    
    @GetMapping("/student/listByFullName")
    public ResponseEntity<List<Student>> getStudentsByFullName(@RequestParam(name="fullName",defaultValue="0") String fullName){
        List<Student> students = userService.findStudentsByFullName(fullName);
        if(students==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(students);
    }
    
    @PostMapping("/student/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        Student newStudent = userService.saveStudentFC(student);
        return ResponseEntity.ok(newStudent);
    }
    
    @DeleteMapping("student/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam(name="studentId",defaultValue="0") int studentId){
        userService.deleteStudentFC(studentId);
        return ResponseEntity.ok("Se ha eliminado al estudiante satisfactoriamente");
    }
    
    @GetMapping("/teacher")
    public ResponseEntity<Teacher> getTeacher(@RequestParam(name="dni",defaultValue="0") String dni){
        Teacher teacher = userService.getTeacher(dni);
        if(teacher==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(teacher);
    }
    
    @GetMapping("/teacher/listByEspecialidad")
    public ResponseEntity<List<Teacher>> getTeachersByEspecialidad(@RequestParam(name="especialidad",defaultValue="0") String especialidad){
        List<Teacher> teachers = userService.findTeachersByEspecialidad(especialidad);
        if(teachers==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(teachers);
    }
    
    @PostMapping("/teacher/save")
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher){
        Teacher newTeacher = userService.saveTeacherFC(teacher);
        return ResponseEntity.ok(newTeacher);
    }
    
    @DeleteMapping("teacher/delete")
    public ResponseEntity<String> deleteTeacher(@RequestParam(name="teacherId",defaultValue="0") int teacherId){
        userService.deleteTeacherFC(teacherId);
        return ResponseEntity.ok("Se ha eliminado al docente satisfactoriamente");
    }
}
