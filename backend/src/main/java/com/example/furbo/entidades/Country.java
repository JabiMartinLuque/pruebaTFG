package com.example.furbo.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class Country {
    @Id
    private Long id; // Puedes usar el tipo de dato que desees para el ID

    private String slug;
    private String name;
    private String abbreviation;

    private String flagHref; // Suponiendo que Flag es otra clase que crees para almacenar la información de la bandera

    @OneToMany(mappedBy = "country")
    private List<Athlete> athletes;
}
