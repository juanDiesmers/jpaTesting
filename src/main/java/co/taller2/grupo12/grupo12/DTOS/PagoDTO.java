package co.taller2.grupo12.grupo12.DTOS;

import java.util.Date;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private long id_pago;
    private Date fecha;
    private double valor;
    private long id_arrendatario; // changed to long from Arrendatario object
    private long id_solicitud; // changed to long from Solicitud object
}
