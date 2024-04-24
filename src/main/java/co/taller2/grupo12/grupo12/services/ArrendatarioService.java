package co.taller2.grupo12.grupo12.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ArrendatarioService {

    @Autowired
    private ArrendatarioRepository arrendatarioRepository;

    public Arrendatario crearArrendatario(ArrendatarioDTO arrendatarioDTO) {
        Arrendatario arrendatario = new Arrendatario();
        arrendatario.setNombre(arrendatarioDTO.getNombre());
        arrendatario.setApellido(arrendatarioDTO.getApellido());
        arrendatario.setCorreo(arrendatarioDTO.getCorreo());
        arrendatario.setContrasena(arrendatarioDTO.getContrasena());
        return arrendatarioRepository.save(arrendatario);
    }

    public List<Arrendatario> obtenerTodosLosArrendatarios() {
        return (List<Arrendatario>) arrendatarioRepository.findAll();
    }

    public Optional<Arrendatario> obtenerArrendatarioPorId(Long id) {
        return arrendatarioRepository.findById(id);
    }

    public Arrendatario actualizarArrendatario(Long id, ArrendatarioDTO arrendatarioDTO) {
        Optional<Arrendatario> arrendatarioOptional = arrendatarioRepository.findById(id);
        if (arrendatarioOptional.isPresent()) {
            Arrendatario arrendatario = arrendatarioOptional.get();
            arrendatario.setNombre(arrendatarioDTO.getNombre());
            arrendatario.setApellido(arrendatarioDTO.getApellido());
            arrendatario.setCorreo(arrendatarioDTO.getCorreo());
            arrendatario.setContrasena(arrendatarioDTO.getContrasena());
            return arrendatarioRepository.save(arrendatario);
        }
        return null;
    }

    public void eliminarArrendatarioPorId(Long id) {
        arrendatarioRepository.deleteById(id);
    }
}

