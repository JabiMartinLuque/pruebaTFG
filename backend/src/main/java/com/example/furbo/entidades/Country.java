package com.example.furbo.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
class Country {
    @Id
    private Long id; // Puedes usar el tipo de dato que desees para el ID

    private String slug;
    private String name;
    private String abbreviation;

    private String flagHref; // Suponiendo que Flag es otra clase que crees para almacenar la información de la bandera
}
