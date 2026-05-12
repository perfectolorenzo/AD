package com.perfecto.Reservas.Repositorios;

import com.perfecto.Reservas.Entidades.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepositorio extends JpaRepository<Habitacion, Integer> {

    // Para obtener habitaciones por hotel
    java.util.List<Habitacion> findByHotelId(int hotelId);
}

