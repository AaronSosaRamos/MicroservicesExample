package com.studentmicroservice.studentmicroservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int studentId;
    
    private String dni;
    private int edad;
    private String nombre;
    private String apellido;
    private String telefono;
    
}
