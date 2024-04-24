package co.taller2.grupo12.grupo12.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arrendador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_arrendador;
    
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contrasena;

    @OneToMany(mappedBy = "arrendador", cascade = CascadeType.ALL)
    private List<Finca> fincas = new ArrayList<>();
    
    @OneToMany(mappedBy = "arrendador")
    private List<Comentario> comentarios = new ArrayList<Comentario>();
}
