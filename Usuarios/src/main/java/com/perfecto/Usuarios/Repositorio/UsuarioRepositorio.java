package com.perfecto.Usuarios.Repositorio;

import com.perfecto.Usuarios.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.contrasena = :contrasena")
    List<Usuario> findByNombrePassword(@Param("nombre") String nombre,
                                       @Param("contrasena") String contrasena);
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.contrasena = :contrasena")
    Usuario findByNombreAndContrasena(@Param("nombre") String nombre,
                                      @Param("contrasena") String contrasena);


    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    List<Usuario> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.nombre = :nombre AND u.contrasena = :contrasena")
    boolean existsNombreYPassword(@Param("nombre") String nombre,
                                  @Param("contrasena") String contrasena);

    @Query("SELECT u FROM Usuario u WHERE u.correoElectronico = :correo")
    List<Usuario> findByCorreo(@Param("correo") String correo);

    @Query("SELECT u.nombre FROM Usuario u WHERE u.id = :id")
    String findNombreById(@Param("id") Integer id);

    @Query("SELECT u.id FROM Usuario u WHERE u.nombre = :nombre")
    Integer findIdByNombre(@Param("nombre") String nombre);

    boolean existsById(Integer id);
}
