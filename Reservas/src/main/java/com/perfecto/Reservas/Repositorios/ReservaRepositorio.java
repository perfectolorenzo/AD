package com.perfecto.Reservas.Repositorios;

import com.perfecto.Reservas.Entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {

    // Listar reservas por usuario
    List<Reserva> findByUsuarioId(int usuarioId);

    // Listar reservas por estado
    List<Reserva> findByEstado(String estado);
}
