package com.example.furbo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
@Table(name = "athletes")  // Nombre de la tabla en la base de datos
public class Athlete {
    @Id
    private String id;  // Identificador único del atleta
    private String uid;
    private String type;  // Tipo de deporte, por ejemplo, "soccer"
    private String guid;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String displayName;
    private String shortName;
    private int weight;  // Peso en libras
    private String displayWeight;  // Peso en formato legible
    private int height;  // Altura en pulgadas
    private String displayHeight;  // Altura en formato legible
    private int age;
    private String dateOfBirth;  // Fecha de nacimiento en formato ISO 8601
    private String gender;  // Género del atleta
    private String birthPlace;  // Lugar de nacimiento
    private String citizenship;  // Ciudadanía
    private String jersey;  // Número de camiseta
    private boolean active;  // Estado activo del atleta

    @ManyToOne  // Relación muchos a uno con la clase Position
    @JoinColumn(name = "position_id", referencedColumnName = "id")  // Relación con la tabla Position
    private Position position;  // Posición del atleta

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;  // País del atleta
}
