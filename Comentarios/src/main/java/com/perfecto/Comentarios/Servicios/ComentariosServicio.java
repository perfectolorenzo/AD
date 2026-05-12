package com.perfecto.Comentarios.Servicios;

import com.perfecto.Comentarios.Clientes.ClienteReservas;
import com.perfecto.Comentarios.Clientes.ClienteUsuarios;
import com.perfecto.Comentarios.DTO.ComentarioOutput;
import com.perfecto.Comentarios.DTO.CrearComentarioInput;
import com.perfecto.Comentarios.Entidades.Comentario;
import com.perfecto.Comentarios.Repositorios.ComentarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentariosServicio {

    private final ComentarioRepositorio comentarioRepositorio;
    private final ClienteUsuarios clienteUsuarios;
    private final ClienteReservas clienteReservas;

    // ---------------------------------------------------------
    // 1. CREAR COMENTARIO
    // ---------------------------------------------------------
    public ComentarioOutput crearComentario(String usuario, String password, CrearComentarioInput input) {

        // 1. Validar usuario
        if (!clienteUsuarios.validarUsuario(usuario, password)) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        // 2. Obtener idUsuario
        Integer idUsuario = clienteUsuarios.obtenerIdUsuario(usuario);
        if (idUsuario == null) {
            throw new RuntimeException("No se pudo obtener el id del usuario");
        }

        // 3. Obtener idHotel desde nombreHotel
        Integer idHotel = clienteReservas.obtenerIdHotel(input.getNombreHotel());
        if (idHotel == null) {
            throw new RuntimeException("No existe un hotel con ese nombre");
        }

        // 4. Validar checkReserva
        boolean reservaValida = clienteReservas.checkReserva(idUsuario, idHotel, input.getReservaId());
        if (!reservaValida) {
            throw new RuntimeException("La reserva no pertenece al usuario o al hotel indicado");
        }

        // 5. Comprobar si ya existe comentario
        Optional<Comentario> existente =
                comentarioRepositorio.findByUsuarioIdAndHotelIdAndReservaId(idUsuario, idHotel, input.getReservaId());

        if (existente.isPresent()) {
            throw new RuntimeException("El usuario ya ha comentado esta reserva");
        }

        // 6. Crear comentario
        Comentario comentario = Comentario.builder()
                .usuarioId(idUsuario)
                .hotelId(idHotel)
                .reservaId(input.getReservaId())
                .puntuacion(input.getPuntuacion())
                .comentario(input.getComentario())
                .fechaCreacion(Instant.now())
                .build();

        comentarioRepositorio.save(comentario);

        // 7. Devolver DTO
        ComentarioOutput salida = new ComentarioOutput();
        salida.setNombreHotel(input.getNombreHotel());
        salida.setReservaId(input.getReservaId());
        salida.setPuntuacion(input.getPuntuacion());
        salida.setComentario(input.getComentario());

        return salida;
    }

    // ---------------------------------------------------------
    // 2. ELIMINAR TODOS LOS COMENTARIOS
    // ---------------------------------------------------------
    public String eliminarComentarios() {
        try {
            comentarioRepositorio.deleteAll();
            return "Todos los comentarios han sido eliminados correctamente";
        } catch (Exception e) {
            return "Error al eliminar los comentarios";
        }
    }

    // ---------------------------------------------------------
    // 3. ELIMINAR COMENTARIO POR ID
    // ---------------------------------------------------------
    public String eliminarComentarioDeUsuario(String idComentario) {
        if (!comentarioRepositorio.existsById(idComentario)) {
            return "No existe un comentario con ese ID";
        }

        comentarioRepositorio.deleteById(idComentario);
        return "Comentario eliminado correctamente";
    }

    // ---------------------------------------------------------
    // 4. LISTAR COMENTARIOS DE UN HOTEL
    // ---------------------------------------------------------
    public List<ComentarioOutput> listarComentariosHotel(String nombreHotel) {

        Integer idHotel = clienteReservas.obtenerIdHotel(nombreHotel);
        if (idHotel == null) {
            throw new RuntimeException("No existe un hotel con ese nombre");
        }

        return comentarioRepositorio.findByHotelId(idHotel)
                .stream()
                .map(c -> {
                    ComentarioOutput dto = new ComentarioOutput();
                    dto.setNombreHotel(nombreHotel);
                    dto.setReservaId(c.getReservaId());
                    dto.setPuntuacion(c.getPuntuacion());
                    dto.setComentario(c.getComentario());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // 5. LISTAR COMENTARIOS DE UN USUARIO
    // ---------------------------------------------------------
    public List<ComentarioOutput> listarComentariosUsuario(String usuario, String password) {

        if (!clienteUsuarios.validarUsuario(usuario, password)) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        Integer idUsuario = clienteUsuarios.obtenerIdUsuario(usuario);

        return comentarioRepositorio.findByUsuarioId(idUsuario)
                .stream()
                .map(c -> {
                    ComentarioOutput dto = new ComentarioOutput();
                    dto.setNombreHotel("Hotel " + c.getHotelId()); // luego lo mejoramos si quieres
                    dto.setReservaId(c.getReservaId());
                    dto.setPuntuacion(c.getPuntuacion());
                    dto.setComentario(c.getComentario());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // 6. MOSTRAR COMENTARIO DE UN USUARIO EN UNA RESERVA
    // ---------------------------------------------------------
    public List<ComentarioOutput> mostrarComentarioUsuarioReserva(String usuario, String password, Integer reservaId) {

        if (!clienteUsuarios.validarUsuario(usuario, password)) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        Integer idUsuario = clienteUsuarios.obtenerIdUsuario(usuario);

        return comentarioRepositorio.findByReservaId(reservaId)
                .stream()
                .filter(c -> c.getUsuarioId().equals(idUsuario))
                .map(c -> {
                    ComentarioOutput dto = new ComentarioOutput();
                    dto.setNombreHotel("Hotel " + c.getHotelId());
                    dto.setReservaId(c.getReservaId());
                    dto.setPuntuacion(c.getPuntuacion());
                    dto.setComentario(c.getComentario());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // ---------------------------------------------------------
    // 7. PUNTUACIÓN MEDIA DE UN HOTEL
    // ---------------------------------------------------------
    public Double puntuacionMediaHotel(String nombreHotel) {

        Integer idHotel = clienteReservas.obtenerIdHotel(nombreHotel);
        if (idHotel == null) {
            throw new RuntimeException("No existe un hotel con ese nombre");
        }

        List<Comentario> comentarios = comentarioRepositorio.findByHotelId(idHotel);

        return comentarios.stream()
                .mapToDouble(Comentario::getPuntuacion)
                .average()
                .orElse(0.0);
    }

    // ---------------------------------------------------------
    // 8. PUNTUACIÓN MEDIA DE UN USUARIO
    // ---------------------------------------------------------
    public Double puntuacionesMediasUsuario(String usuario, String password) {

        if (!clienteUsuarios.validarUsuario(usuario, password)) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        Integer idUsuario = clienteUsuarios.obtenerIdUsuario(usuario);

        List<Comentario> comentarios = comentarioRepositorio.findByUsuarioId(idUsuario);

        return comentarios.stream()
                .mapToDouble(Comentario::getPuntuacion)
                .average()
                .orElse(0.0);
    }
}
