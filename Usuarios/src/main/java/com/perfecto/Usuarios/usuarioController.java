package com.perfecto.Usuarios;

import com.perfecto.Usuarios.Entidades.Usuario;
import com.perfecto.Usuarios.Servicios.UsuarioServicio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class usuarioController {

    private final UsuarioServicio servicio;

    public usuarioController(UsuarioServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/registrar")
    public String crearUsuario(@RequestBody Usuario u) {
        return servicio.crearUsuario(u);
    }

    @PutMapping("/registrar")
    public String actualizarUsuario(@RequestBody Usuario u) {
        return servicio.actualizarUsuario(u);
    }

    @DeleteMapping("/")
    public String eliminarUsuario(@RequestBody Usuario u) {
        return servicio.eliminarUsuario(u.getNombre(), u.getContrasena());
    }

    @PostMapping("/validar")
    public boolean validarUsuario(@RequestBody Usuario u) {
        return servicio.validarUsuario(u.getNombre(), u.getContrasena());
    }

    //@PostMapping("/validar")
    //public boolean validar(@RequestBody CredencialesDTO dto) {
        //return usuarioServicio.validarUsuario(dto.getNombre(), dto.getContrasena());
    //}


    @GetMapping("/info/id/{id}")
    public String obtenerNombre(@PathVariable Integer id) {
        return servicio.obtenerNombrePorId(id);
    }

    @GetMapping("/info/nombre/{nombre}")
    public String obtenerId(@PathVariable String nombre) {
        return servicio.obtenerIdPorNombre(nombre);
    }

    @GetMapping("/checkIfExist/{id}")
    public boolean checkIfExist(@PathVariable Integer id) {
        return servicio.checkIfExist(id);
    }
}
