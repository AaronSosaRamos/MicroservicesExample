package com.studentmicroservice.studentmicroservice.controllers;

import com.studentmicroservice.studentmicroservice.entities.Student;
import com.studentmicroservice.studentmicroservice.service.IStudentService;
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
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private IStudentService studentService;
    
    @GetMapping
    public ResponseEntity<List<Student>> list(){
        List<Student> studentList = studentService.list();
        if(studentList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(studentList);
    }
    
    @GetMapping("/find")
    public ResponseEntity<Student> findStudent(@RequestParam(name="studentId",defaultValue="0") int studentId){
        Student student = studentService.findById(studentId);
        
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(student);
    }
    
    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student){
        Student savedStudent = studentService.save(student);
        
        if(savedStudent==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(savedStudent);
    }
    
    @DeleteMapping("/delete")
    public String delete(@RequestParam(name="studentId",defaultValue="0") int studentId){
        studentService.delete(studentId);
        return "Se ha eliminado al estudiante satisfactoriamente";
    }
    
    @GetMapping("/findByDni")
    public ResponseEntity<Student> findByDni(@RequestParam(name="studentDni",defaultValue="0") String studentDni){
        Student student = studentService.findByDni(studentDni);
        
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(student);
    }
    
    @GetMapping("/listByFullName")
    public ResponseEntity<List<Student>> listByFullName(@RequestParam(name="fullName",defaultValue="0") String fullName){
        List<Student> studentList = studentService.findByFullName(fullName);
        if(studentList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(studentList);
    }
}
