package co.taller2.grupo12.grupo12.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.taller2.grupo12.grupo12.DTOS.SolicitudDTO;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import co.taller2.grupo12.grupo12.services.SolicitudService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> getAllSolicitudes() {
        List<SolicitudDTO> solicitudes = solicitudService.getAllSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> getSolicitudById(@PathVariable Long id) {
        SolicitudDTO solicitud = solicitudService.getSolicitudById(id);
        if (solicitud != null) {
            return ResponseEntity.ok(solicitud);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO> createSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
        SolicitudDTO createdSolicitud = solicitudService.createSolicitud(solicitudDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSolicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDTO> updateSolicitud(@PathVariable Long id, @RequestBody SolicitudDTO solicitudDTO) {
        SolicitudDTO updatedSolicitud = solicitudService.updateSolicitud(id, solicitudDTO);
        if (updatedSolicitud != null) {
            return ResponseEntity.ok(updatedSolicitud);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        solicitudService.deleteSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}