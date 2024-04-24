package co.taller2.grupo12.grupo12.Controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.taller2.grupo12.grupo12.Grupo12Application;
import co.taller2.grupo12.grupo12.Controller.ComentarioController;
import co.taller2.grupo12.grupo12.DTOS.ComentarioDTO;
import co.taller2.grupo12.grupo12.services.ComentarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Grupo12Application.class
) 
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)

class ComentarioControllerTest {

    @Mock
    ComentarioService comentarioService;

    @InjectMocks
    ComentarioController comentarioController;

    @Test
    void testGetComentarioById() {
        // Arrange
        Long id = 1L;
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId_solicitud(id);
        // Mockear el comportamiento del servicio
        when(comentarioService.getComentarioById(id)).thenReturn(comentarioDTO);

        // Act
        ResponseEntity<ComentarioDTO> response = comentarioController.getComentarioById(id);

        // Assert
        if (response.getStatusCode() == HttpStatus.OK) {
            assertNotNull(response.getBody());
            assertEquals(id, response.getBody().getId_solicitud());
        } else {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
        // Verificar que el método del servicio fue invocado correctamente con el ID correcto
        verify(comentarioService).getComentarioById(id);
    }

    @Test
    void testGetAllComentarios() {
        // Arrange
        List<ComentarioDTO> comentarios = new ArrayList<>();
        // Agregar algunos comentarios simulados a la lista esperada
        comentarios.add(new ComentarioDTO());
        comentarios.add(new ComentarioDTO());
        // Mockear el comportamiento del servicio
        when(comentarioService.getAllComentarios()).thenReturn(comentarios);
    
        // Act
        ResponseEntity<List<ComentarioDTO>> response = comentarioController.getAllComentarios();
        List<ComentarioDTO> result = response.getBody();
    
        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comentarios.size(), result.size());
        // Verificar que el método del servicio fue invocado correctamente
        verify(comentarioService).getAllComentarios();
    }

    @Test
    void testCreateComentario() {
        // Arrange
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId_solicitud(1L);
        // Mockear el comportamiento del servicio
        when(comentarioService.createComentario(any(ComentarioDTO.class))).thenReturn(comentarioDTO);
    
        // Act
        ResponseEntity<ComentarioDTO> response = comentarioController.createComentario(comentarioDTO);
    
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(comentarioDTO.getId_solicitud(), response.getBody().getId_solicitud());
        // Verificar que el método del servicio fue invocado correctamente con el DTO de comentario correcto
        verify(comentarioService).createComentario(eq(comentarioDTO));
    }


    @Test
    void testUpdateComentario() {
        // Arrange
        Long id = 1L;
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId_solicitud(id);
        // Mockear el comportamiento del servicio
        when(comentarioService.updateComentario(eq(id), any(ComentarioDTO.class))).thenReturn(comentarioDTO);
    
        // Act
        ResponseEntity<ComentarioDTO> response = comentarioController.updateComentario(id, comentarioDTO);
    
        // Assert
        assertNotNull(response);
        if (response.getStatusCode() == HttpStatus.OK) {
            assertNotNull(response.getBody());
            assertEquals(id, response.getBody().getId_solicitud());
        } else {
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
        // Verificar que el método del servicio fue invocado correctamente con el ID y DTO de comentario correctos
        verify(comentarioService).updateComentario(eq(id), eq(comentarioDTO));
    }

    @Test
    void testDeleteComentario() {
        // Arrange
        Long id = 1L;
    
        // Act
        ResponseEntity<Void> response = comentarioController.deleteComentario(id);
    
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // Verificar que el método del servicio fue invocado correctamente con el ID de comentario a eliminar
        verify(comentarioService).deleteComentario(eq(id));
    }

}