package com.perfecto.Usuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegistroUsuarioDTO {

    private String nombre;

    private String correoElectronico;

    private String direccion;

    private String contrasena;

}
