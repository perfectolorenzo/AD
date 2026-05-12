package com.perfecto.Reservas.DTO;

import lombok.Data;

@Data
public class HotelDTO {
    private int id;             // solo para actualizar
    private String nombre;
    private String direccion;
}
