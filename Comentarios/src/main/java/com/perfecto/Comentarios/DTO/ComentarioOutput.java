package com.perfecto.Comentarios.DTO;

import lombok.Data;

@Data
public class ComentarioOutput {
    private String nombreHotel;
    private Integer reservaId;
    private Double puntuacion;
    private String comentario;
}

