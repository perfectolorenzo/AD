package com.perfecto.Comentarios;



import com.perfecto.Comentarios.DTO.ComentarioOutput;
import com.perfecto.Comentarios.Servicios.ComentariosServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ComentariosQueryResolver {

    private final ComentariosServicio comentariosServicio;

    @QueryMapping
    public List<ComentarioOutput> listarComentariosHotel(@Argument String nombreHotel) {
        return comentariosServicio.listarComentariosHotel(nombreHotel);
    }

    @QueryMapping
    public List<ComentarioOutput> listarComentariosUsuario(
            @Argument String usuario,
            @Argument String password
    ) {
        return comentariosServicio.listarComentariosUsuario(usuario, password);
    }

    @QueryMapping
    public List<ComentarioOutput> mostrarComentarioUsuarioReserva(
            @Argument String usuario,
            @Argument String password,
            @Argument Integer reservaId
    ) {
        return comentariosServicio.mostrarComentarioUsuarioReserva(usuario, password, reservaId);
    }

    @QueryMapping
    public Double puntuacionMediaHotel(@Argument String nombreHotel) {
        return comentariosServicio.puntuacionMediaHotel(nombreHotel);
    }

    @QueryMapping
    public Double puntuacionesMediasUsuario(
            @Argument String usuario,
            @Argument String password
    ) {
        return comentariosServicio.puntuacionesMediasUsuario(usuario, password);
    }
}

