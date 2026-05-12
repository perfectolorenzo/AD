package com.perfecto.Comentarios.Clientes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ClienteUsuarios {

    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8501/usuarios")
            .build();

    public boolean validarUsuario(String nombre, String contrasena) {
        try {
            Boolean respuesta = restClient.get()
                    .uri("/validar?nombre={nombre}&contrasena={contrasena}", nombre, contrasena)
                    .retrieve()
                    .body(Boolean.class);

            return respuesta != null && respuesta;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer obtenerIdUsuario(String nombre) {
        try {
            Map respuesta = restClient.get()
                    .uri("/idUsuario?nombre={nombre}", nombre)
                    .retrieve()
                    .body(Map.class);

            return respuesta != null ? (Integer) respuesta.get("idUsuario") : null;
        } catch (Exception e) {
            return null;
        }
    }
}

