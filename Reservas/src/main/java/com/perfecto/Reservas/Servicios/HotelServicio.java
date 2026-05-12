package com.perfecto.Reservas.Servicios;

import com.perfecto.Reservas.DTO.CredencialesDTO;
import com.perfecto.Reservas.DTO.HotelDTO;
import com.perfecto.Reservas.Entidades.Hotel;
import com.perfecto.Reservas.Repositorios.HotelRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServicio {

    private final HotelRepositorio hotelRepositorio;
    private final UsuarioCliente usuarioCliente;

    // Crear hotel
    public String crearHotel(HotelDTO dto, CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return "ERROR: Usuario o contraseña incorrectos";
        }

        try {
            Hotel hotel = new Hotel();
            hotel.setNombre(dto.getNombre());
            hotel.setDireccion(dto.getDireccion());

            hotelRepositorio.save(hotel);
            return "Hotel creado correctamente";

        } catch (Exception e) {
            return "ERROR al crear hotel: " + e.getMessage();
        }
    }

    // Actualizar hotel
    public String actualizarHotel(HotelDTO dto, CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return "ERROR: Usuario o contraseña incorrectos";
        }

        Optional<Hotel> opt = hotelRepositorio.findById(dto.getId());

        if (opt.isEmpty()) {
            return "ERROR: No existe un hotel con ese ID";
        }

        try {
            Hotel hotel = opt.get();
            hotel.setNombre(dto.getNombre());
            hotel.setDireccion(dto.getDireccion());

            hotelRepositorio.save(hotel);
            return "Hotel actualizado correctamente";

        } catch (Exception e) {
            return "ERROR al actualizar hotel: " + e.getMessage();
        }
    }

    // Eliminar hotel
    public String eliminarHotel(int id, CredencialesDTO credenciales) {

        if (!usuarioCliente.validarUsuario(credenciales)) {
            return "ERROR: Usuario o contraseña incorrectos";
        }

        if (!hotelRepositorio.existsById(id)) {
            return "ERROR: No existe un hotel con ese ID";
        }

        try {
            hotelRepositorio.deleteById(id);
            return "Hotel eliminado correctamente";

        } catch (Exception e) {
            return "ERROR al eliminar hotel: " + e.getMessage();
        }
    }

    // Obtener ID a partir del nombre
    public String obtenerIdApartirNombre(String nombre) {

        Hotel hotel = hotelRepositorio.findByNombre(nombre);

        if (hotel == null) {
            return "ERROR: No existe un hotel con ese nombre";
        }

        return String.valueOf(hotel.getId());
    }

    // Obtener nombre a partir del ID
    public String obtenerNombreAPartirId(int id) {

        Optional<Hotel> opt = hotelRepositorio.findById(id);

        if (opt.isEmpty()) {
            return "ERROR: No existe un hotel con ese ID";
        }

        return opt.get().getNombre();
    }
}

