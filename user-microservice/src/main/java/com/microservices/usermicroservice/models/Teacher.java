package com.microservices.usermicroservice.models;

import lombok.Data;

@Data
public class Teacher {
    
    private String dni;
    private int edad;
    private String nombre;
    private String apellido;
    private String telefono;
    private String especialidad;
    
}
