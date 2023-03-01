package com.studentmicroservice.studentmicroservice.repositories;

import com.studentmicroservice.studentmicroservice.entities.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student,Integer>{
    
    Optional<Student> findByDni(String dni);
    
    @Query("SELECT s FROM Student s WHERE CONCAT(s.nombre,' ',s.apellido) LIKE %:fullName%")
    List<Student> findByFullName(@Param("fullName") String fullName);
    
}
