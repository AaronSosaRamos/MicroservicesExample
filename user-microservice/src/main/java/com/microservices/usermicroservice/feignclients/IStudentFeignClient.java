package com.microservices.usermicroservice.feignclients;

import com.microservices.usermicroservice.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "student-service", path="/student")
public interface IStudentFeignClient {
    
    @PostMapping("/save")
    public Student save(@RequestBody Student student);
    
    @DeleteMapping("/delete")
    public void delete(@RequestParam(name="studentId",defaultValue="0") int studentId);
    
}
