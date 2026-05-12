package com.perfecto.Usuarios.Entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")

    private int id;

    private String nombre;

    private String correoElectronico;

    private String direccion;

    private String contrasena;

    public Usuario(String nombre, String correo_Electronico, String direccion, String contrasena) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.contrasena = contrasena;
    }
}
