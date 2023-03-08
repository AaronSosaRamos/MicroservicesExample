package com.microservices.usermicroservice.feignclients;

import com.microservices.usermicroservice.models.Student;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "student-service", path="/student")
public interface IStudentFeignClient {
    
    @GetMapping
    public List<Student> list();
    
    @GetMapping("/find")
    public Student findStudent(@RequestParam(name="studentId",defaultValue="0") int studentId);
    
    @GetMapping("/findByDni")
    public Student findByDni(@RequestParam(name="studentDni",defaultValue="0") String studentDni);
    
    @GetMapping("/listByFullName")
    public List<Student> listByFullName(@RequestParam(name="fullName",defaultValue="0") String fullName);
    
    @PostMapping("/save")
    public Student save(@RequestBody Student student);
    
    @DeleteMapping("/delete")
    public void delete(@RequestParam(name="studentId",defaultValue="0") int studentId);
    
}
