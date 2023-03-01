package com.teachermicroservice.teachermicroservice.repositories;

import com.teachermicroservice.teachermicroservice.entities.Teacher;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher,Integer>{
    
    Optional<Teacher> findByDni(String dni);
    
    @Query("SELECT t FROM Teacher t WHERE t.especialidad LIKE %:especialidad%")
    List<Teacher> findByEspecialidad(@Param("especialidad") String especialidad);
    
}
