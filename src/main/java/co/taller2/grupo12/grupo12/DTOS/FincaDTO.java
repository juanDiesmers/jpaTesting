package co.taller2.grupo12.grupo12.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FincaDTO {
    private Long id_finca;
    private String nombre;
    private double precio; 
    private Long id_arrendatario;
    private Long id_arrendador; 
}