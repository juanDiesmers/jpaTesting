package co.taller2.grupo12.grupo12.services;

import java.sql.Date;
import java.util.List;

import co.taller2.grupo12.grupo12.DTOS.PagoDTO;
import co.taller2.grupo12.grupo12.entity.Pago;
import co.taller2.grupo12.grupo12.ApplicationRepository.PagoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PagoService(PagoRepository pagoRepository, ModelMapper modelMapper) {
        this.pagoRepository = pagoRepository;
        this.modelMapper = modelMapper;
    }

    public List<PagoDTO> getAllPagos() {
        Iterable<Pago> pagos = pagoRepository.findAll();
        return StreamSupport.stream(pagos.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PagoDTO getPagoById(Long id) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        return pagoOptional.map(this::convertToDTO).orElse(null);
    }

    public PagoDTO createPago(PagoDTO pagoDTO) {
        Pago pago = convertToEntity(pagoDTO);
        return convertToDTO(pagoRepository.save(pago));
    }

    public PagoDTO updatePago(Long id, PagoDTO pagoDTO) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if (pagoOptional.isPresent()) {
            Pago existingPago = pagoOptional.get();
            existingPago.setFecha((Date) pagoDTO.getFecha());
            existingPago.setValor(pagoDTO.getValor());
            return convertToDTO(pagoRepository.save(existingPago));
        } else {
            return null;
        }
    }

    public void deletePago(Long id) {
        pagoRepository.deleteById(id);
    }

    private PagoDTO convertToDTO(Pago pago) {
        PagoDTO pagoDTO = modelMapper.map(pago, PagoDTO.class);
        // Set IDs directly to DTO from entity
        pagoDTO.setId_arrendatario(pago.getArrendatario().getId_arrendatario());
        pagoDTO.setId_solicitud(pago.getSolicitud().getId_solicitud());
        return pagoDTO;
    }

    private Pago convertToEntity(PagoDTO pagoDTO) {
        Pago pago = modelMapper.map(pagoDTO, Pago.class);
        return pago;
    }
}
