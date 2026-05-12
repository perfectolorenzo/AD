package com.perfecto.Reservas.DTO;

import lombok.Data;

@Data
public class CambiarEstadoDTO {
    private int reservaId;
    private String estado;
}

