package com.perfecto.Reservas.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservaDTO {
    private int habitacionId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}

