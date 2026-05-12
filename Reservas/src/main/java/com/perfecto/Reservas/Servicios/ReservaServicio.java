package com.perfecto.Reservas.Servicios;

import com.perfecto.Reservas.DTO.CambiarEstadoDTO;
import com.perfecto.Reservas.DTO.CredencialesDTO;
import com.perfecto.Reservas.DTO.ReservaDTO;
import com.perfecto.Reservas.Entidades.Reserva;
import com.perfecto.Reservas.Repositorios.ReservaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServicio {

    private final ReservaRepositorio reservaRepositorio;
    private final UsuarioCliente usuarioCliente;

    // Crear reserva
    public String crearReserva(ReservaDTO dto, CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return "ERROR: Usuario o contraseña incorrectos";
        }

        try {
            Reserva reserva = new Reserva();
            reserva.setUsuarioId(credenciales.getNombre().hashCode()); // TEMPORAL si no tienes ID real
            reserva.setHabitacionId(dto.getHabitacionId());
            reserva.setFechaInicio(dto.getFechaInicio());
            reserva.setFechaFin(dto.getFechaFin());
            reserva.setEstado("Pendiente");

            reservaRepositorio.save(reserva);
            return "Reserva creada correctamente";

        } catch (Exception e) {
            return "ERROR al crear reserva: " + e.getMessage();
        }
    }

    // Cambiar estado de una reserva
    public String cambiarEstado(CambiarEstadoDTO dto, CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return "ERROR: Usuario o contraseña incorrectos";
        }

        Optional<Reserva> opt = reservaRepositorio.findById(dto.getReservaId());

        if (opt.isEmpty()) {
            return "ERROR: No existe una reserva con ese ID";
        }

        try {
            Reserva reserva = opt.get();
            reserva.setEstado(dto.getEstado());
            reservaRepositorio.save(reserva);

            return "Estado actualizado correctamente";

        } catch (Exception e) {
            return "ERROR al actualizar estado: " + e.getMessage();
        }
    }

    // Listar reservas del usuario
    public List<Reserva> listarReservasUsuario(CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return List.of();
        }

        int usuarioId = credenciales.getNombre().hashCode(); // TEMPORAL si no tienes ID real

        return reservaRepositorio.findByUsuarioId(usuarioId);
    }

    // Listar reservas según estado
    public List<Reserva> listarReservasSegunEstado(String estado, CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return List.of();
        }

        return reservaRepositorio.findByEstado(estado);
    }

    // Check reserva (sin validación de usuario)
    public boolean checkReserva(int idUsuario, int idHotel, int idReserva) {

        Optional<Reserva> opt = reservaRepositorio.findById(idReserva);

        if (opt.isEmpty()) {
            return false;
        }

        Reserva reserva = opt.get();

        return reserva.getUsuarioId() == idUsuario;
    }
}

