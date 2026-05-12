package com.perfecto.Reservas.Repositorios;

import com.perfecto.Reservas.Entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepositorio extends JpaRepository<Hotel, Integer> {

    // Para obtener ID a partir del nombre
    Hotel findByNombre(String nombre);
}

