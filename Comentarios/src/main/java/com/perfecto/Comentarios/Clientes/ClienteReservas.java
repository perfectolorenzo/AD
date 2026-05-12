package com.perfecto.Comentarios.Clientes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ClienteReservas {

    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8502/reservas")
            .build();

    public Integer obtenerIdHotel(String nombreHotel) {
        try {
            Map respuesta = restClient.get()
                    .uri("/idHotel?nombreHotel={nombreHotel}", nombreHotel)
                    .retrieve()
                    .body(Map.class);

            return respuesta != null ? (Integer) respuesta.get("idHotel") : null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean checkReserva(Integer idUsuario, Integer idHotel, Integer idReserva) {
        try {
            Boolean respuesta = restClient.get()
                    .uri("/checkReserva?idUsuario={idUsuario}&idHotel={idHotel}&idReserva={idReserva}",
                            idUsuario, idHotel, idReserva)
                    .retrieve()
                    .body(Boolean.class);

            return respuesta != null && respuesta;
        } catch (Exception e) {
            return false;
        }
    }
}

