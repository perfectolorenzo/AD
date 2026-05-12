package com.perfecto.Reservas.Servicios;

import com.perfecto.Reservas.DTO.CredencialesDTO;
import com.perfecto.Reservas.DTO.HabitacionDTO;
import com.perfecto.Reservas.Entidades.Habitacion;
import com.perfecto.Reservas.Repositorios.HabitacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitacionServicio {

    private final HabitacionRepositorio habitacionRepositorio;
    private final UsuarioCliente usuarioCliente;

    // Crear habitación
    public String crearHabitacion(HabitacionDTO dto, CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return "ERROR: Usuario o contraseña incorrectos";
        }

        try {
            Habitacion habitacion = new Habitacion();
            habitacion.setHotelId(dto.getHotelId());
            habitacion.setNumeroHabitacion(dto.getNumeroHabitacion());
            habitacion.setTipo(dto.getTipo());
            habitacion.setPrecio(dto.getPrecio());
            habitacion.setDisponible(true); // por defecto disponible

            habitacionRepositorio.save(habitacion);
            return "Habitación creada correctamente";

        } catch (Exception e) {
            return "ERROR al crear habitación: " + e.getMessage();
        }
    }

    // Actualizar habitación
    public String actualizarHabitacion(HabitacionDTO dto, CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return "ERROR: Usuario o contraseña incorrectos";
        }

        Optional<Habitacion> opt = habitacionRepositorio.findById(dto.getId());

        if (opt.isEmpty()) {
            return "ERROR: No existe una habitación con ese ID";
        }

        try {
            Habitacion habitacion = opt.get();
            habitacion.setHotelId(dto.getHotelId());
            habitacion.setNumeroHabitacion(dto.getNumeroHabitacion());
            habitacion.setTipo(dto.getTipo());
            habitacion.setPrecio(dto.getPrecio());
            habitacion.setDisponible(dto.isDisponible());

            habitacionRepositorio.save(habitacion);
            return "Habitación actualizada correctamente";

        } catch (Exception e) {
            return "ERROR al actualizar habitación: " + e.getMessage();
        }
    }

    // Eliminar habitación
    public String eliminarHabitacion(int id, CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return "ERROR: Usuario o contraseña incorrectos";
        }

        if (!habitacionRepositorio.existsById(id)) {
            return "ERROR: No existe una habitación con ese ID";
        }

        try {
            habitacionRepositorio.deleteById(id);
            return "Habitación eliminada correctamente";

        } catch (Exception e) {
            return "ERROR al eliminar habitación: " + e.getMessage();
        }
    }
}

