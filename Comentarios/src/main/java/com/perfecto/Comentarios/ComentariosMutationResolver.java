package com.perfecto.Comentarios;



import com.perfecto.Comentarios.DTO.ComentarioOutput;
import com.perfecto.Comentarios.DTO.CrearComentarioInput;
import com.perfecto.Comentarios.Servicios.ComentariosServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ComentariosMutationResolver {

    private final ComentariosServicio comentariosServicio;

    @MutationMapping
    public ComentarioOutput crearComentario(
            @Argument String usuario,
            @Argument String password,
            @Argument CrearComentarioInput input
    ) {
        return comentariosServicio.crearComentario(usuario, password, input);
    }

    @MutationMapping
    public String eliminarComentarios() {
        return comentariosServicio.eliminarComentarios();
    }

    @MutationMapping
    public String eliminarComentarioDeUsuario(@Argument String idComentario) {
        return comentariosServicio.eliminarComentarioDeUsuario(idComentario);
    }
}

