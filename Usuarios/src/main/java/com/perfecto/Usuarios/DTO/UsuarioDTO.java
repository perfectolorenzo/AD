package com.perfecto.Usuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {

    private int id;

    private String nombre;

    private String correoElectronico;

    private String direccion;

    private String contrasena;
}
