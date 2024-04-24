package co.taller2.grupo12.grupo12.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.taller2.grupo12.grupo12.DTOS.SolicitudDTO;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Finca;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.FincaRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.SolicitudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ArrendatarioRepository arrendatarioRepository;
    private final FincaRepository fincaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository, ArrendatarioRepository arrendatarioRepository, FincaRepository fincaRepository, ModelMapper modelMapper) {
        this.solicitudRepository = solicitudRepository;
        this.arrendatarioRepository = arrendatarioRepository;
        this.fincaRepository = fincaRepository;
        this.modelMapper = modelMapper;
    }

    public List<SolicitudDTO> getAllSolicitudes() {
        Iterable<Solicitud> solicitudes = solicitudRepository.findAll();
        return StreamSupport.stream(solicitudes.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SolicitudDTO getSolicitudById(Long id) {
        Optional<Solicitud> solicitudOptional = solicitudRepository.findById(id);
        return solicitudOptional.map(this::convertToDTO).orElse(null);
    }

    public SolicitudDTO createSolicitud(SolicitudDTO solicitudDTO) {
        Solicitud solicitud = convertToEntity(solicitudDTO);
        if (solicitud.getArrendatario() != null)  {
            return convertToDTO(solicitudRepository.save(solicitud));
        } else {
            throw new IllegalArgumentException("El arrendatario no puede ser nulo.");
        }
    }

    public SolicitudDTO updateSolicitud(Long id, SolicitudDTO solicitudDTO) {
        Optional<Solicitud> solicitudOptional = solicitudRepository.findById(id);
        if (solicitudOptional.isPresent()) {
            Solicitud existingSolicitud = solicitudOptional.get();
            existingSolicitud.setFecha(solicitudDTO.getFecha());
            existingSolicitud.setEstado(solicitudDTO.getEstado());
            // Actualizar otros campos según sea necesario
            return convertToDTO(solicitudRepository.save(existingSolicitud));
        } else {
            return null;
        }
    }

    public void deleteSolicitud(Long id) {
        solicitudRepository.deleteById(id);
    }

    private SolicitudDTO convertToDTO(Solicitud solicitud) {
        SolicitudDTO solicitudDTO = modelMapper.map(solicitud, SolicitudDTO.class);
        return solicitudDTO;
    }

    private Solicitud convertToEntity(SolicitudDTO solicitudDTO) {
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        
        // Obtener el arrendatario a partir del ID
        Arrendatario arrendatario = arrendatarioRepository.findById(solicitudDTO.getIdArrendatario())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró ningún arrendatario con el ID proporcionado."));
        // Obtener la finca a partir del ID
        Finca finca = fincaRepository.findById(solicitudDTO.getIdFinca())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró ninguna finca con el ID proporcionado."));
    
        solicitud.setArrendatario(arrendatario);
        solicitud.setFinca(finca);
    
        return solicitud;
    }
}