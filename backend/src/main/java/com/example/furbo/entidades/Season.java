package com.example.furbo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
public class Season {
    @Id
    private String ref;  // Enlace a la referencia única de la temporada
    private int year;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String displayName;
    private String shortDisplayName;
    private String abbreviation;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    @OneToMany
    private List<Athlete> athletes;  // Lista de atletas asociados a la temporada

}
