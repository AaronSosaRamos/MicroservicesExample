package com.microservices.usermicroservice.feignclients;

import com.microservices.usermicroservice.models.Teacher;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "teacher-service", path="/teacher")
public interface ITeacherFeignClient {
    
    @GetMapping
    public List<Teacher> list();
    
    @GetMapping("/find")
    public Teacher findTeacher(@RequestParam(name="teacherId",defaultValue="0") int teacherId);
    
    @GetMapping("/findByDni")
    public Teacher findByDni(@RequestParam(name="teacherDni",defaultValue="0") String teacherDni);
    
    @GetMapping("/listByEspecialidad")
    public List<Teacher> listByEspecialidad(@RequestParam(name="especialidad",defaultValue="0") String especialidad);
            
    @PostMapping("/save")
    public Teacher save(@RequestBody Teacher teacher);
    
    @DeleteMapping("/delete")
    public void delete(@RequestParam(name="teacherId",defaultValue="0") int teacherId);
    
}
