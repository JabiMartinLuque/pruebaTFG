package com.example.furbo.entidades;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
@Table(name = "usuarios")  // Nombre de la tabla en la base de datos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
   
}