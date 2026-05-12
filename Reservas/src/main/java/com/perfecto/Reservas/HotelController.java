package com.perfecto.Reservas;


import com.perfecto.Reservas.DTO.CredencialesDTO;
import com.perfecto.Reservas.DTO.HotelDTO;
import com.perfecto.Reservas.Servicios.HotelServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelServicio hotelServicio;

    // Crear hotel
    @PostMapping
    public String crearHotel(@RequestBody HotelDTO dto,
                             @RequestHeader("nombre") String nombre,
                             @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return hotelServicio.crearHotel(dto, cred);
    }

    // Actualizar hotel
    @PatchMapping
    public String actualizarHotel(@RequestBody HotelDTO dto,
                                  @RequestHeader("nombre") String nombre,
                                  @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return hotelServicio.actualizarHotel(dto, cred);
    }

    // Eliminar hotel
    @DeleteMapping("/{id}")
    public String eliminarHotel(@PathVariable int id,
                                @RequestHeader("nombre") String nombre,
                                @RequestHeader("contrasena") String contrasena) {

        CredencialesDTO cred = new CredencialesDTO();
        cred.setNombre(nombre);
        cred.setContrasena(contrasena);

        return hotelServicio.eliminarHotel(id, cred);
    }

    // Obtener ID a partir del nombre
    @PostMapping("/id/{nombreHotel}")
    public String obtenerId(@PathVariable String nombreHotel) {
        return hotelServicio.obtenerIdApartirNombre(nombreHotel);
    }

    // Obtener nombre a partir del ID
    @PostMapping("/nombre/{idHotel}")
    public String obtenerNombre(@PathVariable int idHotel) {
        return hotelServicio.obtenerNombreAPartirId(idHotel);
    }
}

