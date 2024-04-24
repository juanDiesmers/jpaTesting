package co.taller2.grupo12.grupo12.services;

import java.util.List;

import co.taller2.grupo12.grupo12.DTOS.ComentarioDTO;
import co.taller2.grupo12.grupo12.entity.Comentario;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.ApplicationRepository.ComentarioRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ArrendatarioRepository arrendatarioRepository;
    private final ArrendadorRepository arrendadorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository, ArrendatarioRepository arrendatarioRepository, ArrendadorRepository arrendadorRepository, ModelMapper modelMapper) {
        this.comentarioRepository = comentarioRepository;
        this.arrendatarioRepository = arrendatarioRepository;
        this.arrendadorRepository = arrendadorRepository;
        this.modelMapper = modelMapper;
    }

    public List<ComentarioDTO> getAllComentarios() {
        Iterable<Comentario> comentarios = comentarioRepository.findAll();
        return StreamSupport.stream(comentarios.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ComentarioDTO getComentarioById(Long id) {
        Optional<Comentario> comentarioOptional = comentarioRepository.findById(id);
        return comentarioOptional.map(this::convertToDTO).orElse(null);
    }

    public ComentarioDTO createComentario(ComentarioDTO comentarioDTO) {
        Comentario comentario = convertToEntity(comentarioDTO);
        if (comentario.getArrendador() != null && comentario.getArrendatario() != null) {
            return convertToDTO(comentarioRepository.save(comentario));
        } else {
            throw new IllegalArgumentException("Arrendador o arrendatario no pueden ser nulos.");
        }
    }

    public ComentarioDTO updateComentario(Long id, ComentarioDTO comentarioDTO) {
        Optional<Comentario> comentarioOptional = comentarioRepository.findById(id);
        if (comentarioOptional.isPresent()) {
            Comentario existingComentario = comentarioOptional.get();
            existingComentario.setFecha(comentarioDTO.getFecha());
            existingComentario.setCalificacion(comentarioDTO.getCalificacion());
            existingComentario.setComentario(comentarioDTO.getComentario());
            return convertToDTO(comentarioRepository.save(existingComentario));
        } else {
            return null;
        }
    }

    public void deleteComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    private ComentarioDTO convertToDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
        return comentarioDTO;
    }

    private Comentario convertToEntity(ComentarioDTO comentarioDTO) {
        Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
        
        // Obtener el arrendador y arrendatario a partir de sus IDs
        Arrendador arrendador = arrendadorRepository.findById(comentarioDTO.getIdArrendador())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró ningún arrendador con el ID proporcionado."));
        Arrendatario arrendatario = arrendatarioRepository.findById(comentarioDTO.getIdArrendatario())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró ningún arrendatario con el ID proporcionado."));
        
        // Asignar los arrendadores y arrendatarios al comentario
        comentario.setArrendador(arrendador);
        comentario.setArrendatario(arrendatario);
        
        return comentario;
    }
}