package com.perfecto.Usuarios.Servicios;

import com.perfecto.Usuarios.Entidades.Usuario;
import com.perfecto.Usuarios.Repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio {

    private final UsuarioRepositorio repo;

    public UsuarioServicio(UsuarioRepositorio repo) {
        this.repo = repo;
    }

    public String crearUsuario(Usuario u) {
        repo.save(u);
        return "Usuario registrado correctamente";
    }

    public String actualizarUsuario(Usuario u) {
        if (!repo.existsById(u.getId())) {
            return "El usuario no existe";
        }
        repo.save(u);
        return "Usuario actualizado correctamente";
    }

    public String eliminarUsuario(String nombre, String contrasena) {
        Usuario u = repo.findByNombreAndContrasena(nombre, contrasena);
        if (u == null) {
            return "Usuario no encontrado o contraseña incorrecta";
        }
        repo.delete(u);
        return "Usuario eliminado correctamente";

    }

    public boolean validarUsuario(String nombre, String contrasena) {
        return repo.findByNombreAndContrasena(nombre, contrasena) != null;

    }

    //public boolean validarUsuario(String nombre, String contrasena) {
      //  return repo.findByNombreAndContrasena(nombre, contrasena).isPresent();
    //}


    public String obtenerNombrePorId(Integer id) {
        return repo.findById(id)
                .map(Usuario::getNombre)
                .orElse("Usuario no encontrado");
    }

    public String obtenerIdPorNombre(String nombre) {
        Integer id = repo.findIdByNombre(nombre);
        return id != null ? id.toString() : "Usuario no encontrado";
    }


    public boolean checkIfExist(Integer id) {
        return repo.existsById(id);
    }
}
