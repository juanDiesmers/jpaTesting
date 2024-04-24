package co.taller2.grupo12.grupo12.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arrendatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arrendatario")
    private Long id_arrendatario;

    @Column(length = 255)
    private String nombre;

    @Column(length = 255)
    private String apellido;

    @Column(length = 255)
    private String correo;

    @Column(length = 255)
    private String contrasena;
}