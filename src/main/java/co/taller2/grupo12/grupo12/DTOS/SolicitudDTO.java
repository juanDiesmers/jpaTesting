package co.taller2.grupo12.grupo12.DTOS;

import java.sql.Date;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Finca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDTO {
    private Long idSolicitud;
    private String estado;
    private Date fecha;
    private Long idArrendatario;
    private Long idFinca;

}