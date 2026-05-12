package com.perfecto.Comentarios.Repositorios;

import com.perfecto.Comentarios.Entidades.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepositorio extends MongoRepository<Comentario, String> {

    Optional<Comentario> findByUsuarioIdAndHotelIdAndReservaId(Integer usuarioId, Integer hotelId, Integer reservaId);

    List<Comentario> findByHotelId(Integer hotelId);

    List<Comentario> findByUsuarioId(Integer usuarioId);

    List<Comentario> findByReservaId(Integer reservaId);
}

