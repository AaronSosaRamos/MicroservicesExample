package com.teachermicroservice.teachermicroservice.entities;

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
@Table(name="teacher")
public class Teacher {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int teacherId;
    
    private String dni;
    private int edad;
    private String nombre;
    private String apellido;
    private String telefono;
    private String especialidad;
    
}
