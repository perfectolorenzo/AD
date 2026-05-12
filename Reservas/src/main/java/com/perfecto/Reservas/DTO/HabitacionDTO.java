package com.perfecto.Reservas.DTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class HabitacionDTO {
    private int id;                 // solo para actualizar
    private int hotelId;
    private int numeroHabitacion;
    private String tipo;
    private BigDecimal precio;
    private boolean disponible;     // solo para actualizar
}

