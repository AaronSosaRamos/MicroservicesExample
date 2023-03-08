package com.microservices.usermicroservice.feignclients;

import com.microservices.usermicroservice.models.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "teacher-service", path="/teacher")
public interface ITeacherFeignClient {
    
    @PostMapping("/save")
    public Teacher save(@RequestBody Teacher teacher);
    
    @DeleteMapping("/delete")
    public void delete(@RequestParam(name="teacherId",defaultValue="0") int teacherId);
    
}
