package com.perfecto.Reservas.Servicios;

import com.perfecto.Reservas.DTO.CredencialesDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UsuarioCliente {

    private final RestClient restClient = RestClient.create();

    public boolean validarUsuario(CredencialesDTO credenciales) {

        String url = "http://localhost:8502/usuarios/validar";

        try {
            Boolean respuesta = restClient
                    .post()
                    .uri(url)
                    .body(credenciales)
                    .retrieve()
                    .body(Boolean.class);

            return respuesta != null && respuesta;

        } catch (Exception e) {
            return false;
        }
    }
}

