package com.perfecto.Usuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ValidarUsuarioDTO {



    private String nombre;

    private String contrasena;
}
