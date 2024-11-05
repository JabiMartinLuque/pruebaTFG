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
@Table(name = "leagues")
public class League {

    @Id
    private Long id;

    private String guid;
    private String uid;
    private String alternateId;
    private String name;
    private String displayName;
    private String abbreviation;
    private String shortName;
    private String midsizeName;
    private String slug;
    private boolean isTournament;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    private List<Season> seasons;

}
