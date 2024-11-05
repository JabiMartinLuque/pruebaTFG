package com.example.furbo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
@Table(name = "positions")  
public class Position {
    private String ref;  
    @Id
    private String id;
    private String name;
    private String displayName;
    private String abbreviation;
    private boolean leaf;  // Indica si es una posición final

    @ManyToOne
    private Position parent; // Relación con la posición padre
}
