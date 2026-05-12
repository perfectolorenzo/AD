package com.perfecto.Reservas.Entidades;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitacion_id")
    private int id;

    @Column(name = "hotel_id")
    private int hotelId;

    @Column(name = "numero_habitacion")
    private int numeroHabitacion;

    private String tipo;

    private BigDecimal precio;

    private boolean disponible;
}
