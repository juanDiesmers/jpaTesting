package co.taller2.grupo12.grupo12.DTOS;

import java.util.ArrayList;
import java.util.List;

import co.taller2.grupo12.grupo12.entity.Comentario;
import co.taller2.grupo12.grupo12.entity.Pago;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArrendatarioDTO {
    private Long id_arrendatario;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private List<Solicitud> solicitudes = new ArrayList<>();
    private List<Pago> pagos = new ArrayList<>();
    private List<Comentario> comentarios = new ArrayList<>();
}
