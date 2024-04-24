package co.taller2.grupo12.grupo12.DTOS;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {
    private Long id_solicitud;
    private double calificacion;
    private String comentario;
    private Date fecha;
    private Long idArrendador;
    private Long idArrendatario;
    
}
