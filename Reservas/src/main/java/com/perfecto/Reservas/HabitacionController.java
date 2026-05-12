package com.perfecto.Reservas;


import com.perfecto.Reservas.DTO.CredencialesDTO;
import com.perfecto.Reservas.DTO.HabitacionDTO;
import com.perfecto.Reservas.Servicios.HabitacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas/habitacion")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionServicio habitacionServicio;

    // Crear habitación
    @PostMapping
    public String crearHabitacion(@RequestBody HabitacionDTO dto,
                                  @RequestHeader("nombre") String nombre,
                                  @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return habitacionServicio.crearHabitacion(dto, cred);
    }

    // Actualizar habitación
    @PatchMapping
    public String actualizarHabitacion(@RequestBody HabitacionDTO dto,
                                       @RequestHeader("nombre") String nombre,
                                       @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return habitacionServicio.actualizarHabitacion(dto, cred);
    }

    // Eliminar habitación
    @DeleteMapping("/{id}")
    public String eliminarHabitacion(@PathVariable int id,
                                     @RequestHeader("nombre") String nombre,
                                     @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return habitacionServicio.eliminarHabitacion(id, cred);
    }
}

