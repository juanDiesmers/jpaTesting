package co.taller2.grupo12.grupo12.services;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.DTOS.FincaDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Finca;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArrendadorService {
    
    private final ArrendadorRepository arrendadorRepository;
    private final ModelMapper modelMapper;
    
    public ArrendadorService(ArrendadorRepository arrendadorRepository, ModelMapper modelMapper) {
        this.arrendadorRepository = arrendadorRepository;
        this.modelMapper = modelMapper;
    }

    public List<ArrendadorDTO> getArrendadores() {
        Iterable<Arrendador> arrendadores = arrendadorRepository.findAll();
        return StreamSupport.stream(arrendadores.spliterator(), false)
            .map(arrendador -> {
                ArrendadorDTO arrendadorDTO = modelMapper.map(arrendador, ArrendadorDTO.class);
                List<String> nombresFincas = arrendador.getFincas().stream()
                    .map(Finca::getNombre)
                    .collect(Collectors.toList());
                arrendadorDTO.setNombresFincas(nombresFincas);
                return arrendadorDTO;
            })
            .collect(Collectors.toList());
    }
    

    public Optional<Arrendador> obtenerArrendadorPorId(Long id) {
        return arrendadorRepository.findById(id);
    }

    public Arrendador actualizarArrendador(Long id, ArrendadorDTO arrendadorDTO) {
        Optional<Arrendador> arrendadorOp = arrendadorRepository.findById(id);
        if (arrendadorOp.isPresent()) {
            Arrendador arrendador = arrendadorOp.get();
            arrendador.setNombre(arrendadorDTO.getNombre());
            arrendador.setApellido(arrendadorDTO.getApellido());
            arrendador.setCorreo(arrendadorDTO.getCorreo());
            arrendador.setContrasena(arrendadorDTO.getContrasena());
            return arrendadorRepository.save(arrendador);
        }
        return null;
    }


    public ArrendadorDTO getArrendadorById(Long id) {
        Optional<Arrendador> arrendadorOptional = arrendadorRepository.findById(id);
        return arrendadorOptional.map(arrendador -> modelMapper.map(arrendador, ArrendadorDTO.class)).orElse(null);
    }

    public ArrendadorDTO createArrendador(ArrendadorDTO arrendadorDTO) {
        Arrendador arrendador = modelMapper.map(arrendadorDTO, Arrendador.class);
        Arrendador savedArrendador = arrendadorRepository.save(arrendador);
        return modelMapper.map(savedArrendador, ArrendadorDTO.class);
    }


    public Arrendador crearArrendadorSIN(ArrendadorDTO arrendadorDTO) {
        Arrendador arrendador = new Arrendador();
        arrendador.setNombre(arrendadorDTO.getNombre());
        arrendador.setApellido(arrendadorDTO.getApellido());
        arrendador.setCorreo(arrendadorDTO.getCorreo());
        arrendador.setContrasena(arrendadorDTO.getContrasena());
        return arrendadorRepository.save(arrendador);
    }

    public List<Arrendador> obtenerTodosLosArrendadores() {
        return (List<Arrendador>) arrendadorRepository.findAll();
    }

    public Arrendador guardarArrendador(ArrendadorDTO arrendadorDTO) {
        // Crear una instancia de Arrendador a partir de los datos del DTO
        Arrendador arrendador = new Arrendador();
        arrendador.setNombre(arrendadorDTO.getNombre());
        arrendador.setApellido(arrendadorDTO.getApellido());
        
        // Guardar el arrendador utilizando el repositorio
        return arrendadorRepository.save(arrendador);
    }


    public void deleteArrendador(Long id) {
        arrendadorRepository.deleteById(id);
    }
}

