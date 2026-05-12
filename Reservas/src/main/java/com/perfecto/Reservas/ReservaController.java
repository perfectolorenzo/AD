package com.perfecto.Reservas;


import com.perfecto.Reservas.DTO.CambiarEstadoDTO;
import com.perfecto.Reservas.DTO.CredencialesDTO;
import com.perfecto.Reservas.DTO.ReservaDTO;
import com.perfecto.Reservas.Entidades.Reserva;
import com.perfecto.Reservas.Servicios.ReservaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaServicio reservaServicio;

    // Crear reserva
    @PostMapping
    public String crearReserva(@RequestBody ReservaDTO dto,
                               @RequestHeader("nombre") String nombre,
                               @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return reservaServicio.crearReserva(dto, cred);
    }

    // Cambiar estado
    @PatchMapping
    public String cambiarEstado(@RequestBody CambiarEstadoDTO dto,
                                @RequestHeader("nombre") String nombre,
                                @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return reservaServicio.cambiarEstado(dto, cred);
    }

    // Listar reservas del usuario
    @GetMapping
    public List<Reserva> listarReservasUsuario(@RequestHeader("nombre") String nombre,
                                               @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return reservaServicio.listarReservasUsuario(cred);
    }

    // Listar reservas según estado
    @GetMapping("/{estado}")
    public List<Reserva> listarReservasSegunEstado(@PathVariable String estado,
                                                   @RequestHeader("nombre") String nombre,
                                                   @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return reservaServicio.listarReservasSegunEstado(estado, cred);
    }

    // Check reserva (sin credenciales)
    @GetMapping("/check/{idUsuario}/{idHotel}/{idReserva}")
    public boolean checkReserva(@PathVariable int idUsuario,
                                @PathVariable int idHotel,
                                @PathVariable int idReserva) {

        return reservaServicio.checkReserva(idUsuario, idHotel, idReserva);
    }
}

