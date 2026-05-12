package com.perfecto.Reservas.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private int id;

    private String nombre;
    private String direccion;
}
