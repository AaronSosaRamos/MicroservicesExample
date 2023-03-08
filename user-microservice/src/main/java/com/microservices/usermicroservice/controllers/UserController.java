package com.microservices.usermicroservice.controllers;

import com.microservices.usermicroservice.entities.Users;
import com.microservices.usermicroservice.models.Student;
import com.microservices.usermicroservice.models.Teacher;
import com.microservices.usermicroservice.service.IUserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    
    @CircuitBreaker(name = "studentCB",fallbackMethod = "fallBackGetStudent")
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(@RequestParam(name="dni",defaultValue="0") String dni){
        Student student = userService.getStudent(dni);
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(student);
    }
    
    @CircuitBreaker(name = "studentCB",fallbackMethod = "fallBackListStudentByFullName")
    @GetMapping("/student/listByFullName")
    public ResponseEntity<List<Student>> getStudentsByFullName(@RequestParam(name="fullName",defaultValue="0") String fullName){
        List<Student> students = userService.findStudentsByFullName(fullName);
        if(students==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(students);
    }
    
    @CircuitBreaker(name = "studentCB",fallbackMethod = "fallBackSaveStudent")
    @PostMapping("/student/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        Student newStudent = userService.saveStudentFC(student);
        return ResponseEntity.ok(newStudent);
    }
    
    @CircuitBreaker(name = "studentCB",fallbackMethod = "fallBackDeleteStudent")
    @DeleteMapping("student/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam(name="studentId",defaultValue="0") int studentId){
        userService.deleteStudentFC(studentId);
        return ResponseEntity.ok("Se ha eliminado al estudiante satisfactoriamente");
    }
    
    @CircuitBreaker(name = "teacherCB",fallbackMethod = "fallBackGetTeacher")
    @GetMapping("/teacher")
    public ResponseEntity<Teacher> getTeacher(@RequestParam(name="dni",defaultValue="0") String dni){
        Teacher teacher = userService.getTeacher(dni);
        if(teacher==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(teacher);
    }
    
    @CircuitBreaker(name = "teacherCB",fallbackMethod = "fallBackListTeacherByEspecialidad")
    @GetMapping("/teacher/listByEspecialidad")
    public ResponseEntity<List<Teacher>> getTeachersByEspecialidad(@RequestParam(name="especialidad",defaultValue="0") String especialidad){
        List<Teacher> teachers = userService.findTeachersByEspecialidad(especialidad);
        if(teachers==null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(teachers);
    }
    
    @CircuitBreaker(name = "teacherCB",fallbackMethod = "fallBackSaveTeacher")
    @PostMapping("/teacher/save")
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher){
        Teacher newTeacher = userService.saveTeacherFC(teacher);
        return ResponseEntity.ok(newTeacher);
    }
    
    @CircuitBreaker(name = "teacherCB",fallbackMethod = "fallBackDeleteTeacher")
    @DeleteMapping("teacher/delete")
    public ResponseEntity<String> deleteTeacher(@RequestParam(name="teacherId",defaultValue="0") int teacherId){
        userService.deleteTeacherFC(teacherId);
        return ResponseEntity.ok("Se ha eliminado al docente satisfactoriamente");
    }
    
    private ResponseEntity<Student> fallBackGetStudent(@RequestParam(name="dni",defaultValue="0") String dni, RuntimeException exception){
        return new ResponseEntity("La solicitud de obtención de estudiante por DNI no se pudo desarrollar debido a errores internos con el microservicio - Student"
                , HttpStatus.OK);
    }
    
    private ResponseEntity<List<Student>> fallBackListStudentByFullName(@RequestParam(name="fullName",defaultValue="0") String fullName, RuntimeException exception){
        return new ResponseEntity("La solicitud de listado de estudiantes por nombre completo no se pudo desarrollar debido a errores internos con el microservicio - Student"
                , HttpStatus.OK);
    }
    
    private ResponseEntity<Student> fallBackSaveStudent(@RequestBody Student student, RuntimeException exception){
        return new ResponseEntity("La solicitud de guardar estudiante por nombre completo no se pudo desarrollar debido a errores internos con el microservicio - Student"
                , HttpStatus.OK);
    }
    
    private ResponseEntity<String> fallBackDeleteStudent(@RequestParam(name="studentId",defaultValue="0") int studentId, RuntimeException exception){
        return new ResponseEntity("La solicitud de eliminar estudiante por nombre completo no se pudo desarrollar debido a errores internos con el microservicio - Student"
                , HttpStatus.OK);
    }
    
    private ResponseEntity<Teacher> fallBackGetTeacher(@RequestParam(name="dni",defaultValue="0") String dni, RuntimeException exception){
        return new ResponseEntity("La solicitud de obtención de docente por DNI no se pudo desarrollar debido a errores internos con el microservicio - Teacher"
                , HttpStatus.OK);
    }
    
    private ResponseEntity<List<Teacher>> fallBackListTeacherByEspecialidad(@RequestParam(name="especialidad",defaultValue="0") String especialidad, RuntimeException exception){
        return new ResponseEntity("La solicitud de listado de docentes por especialidad no se pudo desarrollar debido a errores internos con el microservicio - Teacher"
                , HttpStatus.OK);
    }
    
    private ResponseEntity<Teacher> fallBackSaveTeacher(@RequestBody Teacher teacher, RuntimeException exception){
        return new ResponseEntity("La solicitud de guardar docente no se pudo desarrollar debido a errores internos con el microservicio - Teacher"
                , HttpStatus.OK);
    }

    private ResponseEntity<String> fallBackDeleteTeacher(@RequestParam(name="teacherId",defaultValue="0") int teacherId, RuntimeException exception){
        return new ResponseEntity("La solicitud de eliminar docente no se pudo desarrollar debido a errores internos con el microservicio - Teacher"
                , HttpStatus.OK);
    }
}
