package co.taller2.grupo12.grupo12.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.taller2.grupo12.grupo12.DTOS.ComentarioDTO;
import co.taller2.grupo12.grupo12.services.ComentarioService;

@RestController
@CrossOrigin
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> getAllComentarios() {
        List<ComentarioDTO> comentarios = comentarioService.getAllComentarios();
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> getComentarioById(@PathVariable Long id) {
        ComentarioDTO comentario = comentarioService.getComentarioById(id);
        if (comentario != null) {
            return ResponseEntity.ok(comentario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> createComentario(@RequestBody ComentarioDTO comentarioDTO) {
        ComentarioDTO createdComentario = comentarioService.createComentario(comentarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> updateComentario(@PathVariable Long id,
            @RequestBody ComentarioDTO comentarioDTO) {
        ComentarioDTO updatedComentario = comentarioService.updateComentario(id, comentarioDTO);
        if (updatedComentario != null) {
            return ResponseEntity.ok(updatedComentario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteComentario(id);
        return ResponseEntity.noContent().build();
    }
}
