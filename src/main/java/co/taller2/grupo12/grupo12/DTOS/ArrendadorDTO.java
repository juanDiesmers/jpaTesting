package co.taller2.grupo12.grupo12.DTOS;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArrendadorDTO {
    private Long id_arrendador;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contrasena;
    private List<String> nombresFincas; 
    private List<ComentarioDTO> comentarios = new ArrayList<>();
    
}
