package com.example.furbo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
public class Team {
    @Id
    private String id;
    private String guid;
    private String location;
    private String name;
    private String abbreviation;
    private String displayName;
    private String shortDisplayName;
    private String color;
    private String alternateColor;
    private boolean isActive;
    private String form;
    private String logoUrl;
    private String venueName;
    private String venueCity;
    private String venueCountry;
    private String clubhouseUrl;    
}
