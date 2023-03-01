package com.microservices.usermicroservice.models;

import lombok.Data;

@Data
public class Student {
    
    private String dni;
    private int edad;
    private String nombre;
    private String apellido;
    private String telefono;
    
}
