package co.taller2.grupo12.grupo12.Service;

import org.apache.tomcat.util.http.parser.MediaType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import co.taller2.grupo12.grupo12.Grupo12Application;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.ComentarioRepository;
import co.taller2.grupo12.grupo12.Controller.ArrendadorController;
import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.DTOS.ComentarioDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Comentario;
import co.taller2.grupo12.grupo12.services.ArrendadorService;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;
import co.taller2.grupo12.grupo12.services.ComentarioService;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Grupo12Application.class
) 
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)


class ComentarioServiceTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private ArrendatarioRepository arrendatarioRepository;

    @Mock
    private ArrendadorRepository arrendadorRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ComentarioService comentarioService;

    @Test
    void testGetAllComentarios() {
        // Arrange
        List<Comentario> comentarios = new ArrayList<>();
        comentarios.add(new Comentario());
        comentarios.add(new Comentario());

        when(comentarioRepository.findAll()).thenReturn(comentarios);
        when(modelMapper.map(any(Comentario.class), eq(ComentarioDTO.class))).thenReturn(new ComentarioDTO());

        // Act
        List<ComentarioDTO> result = comentarioService.getAllComentarios();

        // Assert
        assertNotNull(result);
        assertEquals(comentarios.size(), result.size());
    }

    @Test
    void testGetComentarioByIdNoExistente() {
        // Arrange
        Long id = 1L;

        when(comentarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ComentarioDTO result = comentarioService.getComentarioById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void testCreateComentarioSinArrendadorOArrendatario() {
        // Arrange
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> comentarioService.createComentario(comentarioDTO));
    }

    @Test
    void testUpdateComentarioExistente() {
        Date fechaSql = Date.valueOf("2022-04-10");
        // Arrange
        Long id = 1L;
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setFecha(fechaSql);
        Comentario comentario = new Comentario();

        when(comentarioRepository.findById(id)).thenReturn(Optional.of(comentario));
        when(comentarioRepository.save(any())).thenReturn(comentario);
        when(modelMapper.map(any(Comentario.class), eq(ComentarioDTO.class))).thenReturn(comentarioDTO);

        // Act
        ComentarioDTO result = comentarioService.updateComentario(id, comentarioDTO);

        // Assert
        assertNotNull(result);
        assertEquals(comentarioDTO.getFecha(), result.getFecha());
    }

    @Test
    void testUpdateComentarioNoExistente() {
        // Arrange
        Long id = 1L;
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        when(comentarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ComentarioDTO result = comentarioService.updateComentario(id, comentarioDTO);

        // Assert
        assertNull(result);
    }

    @Test
    void testDeleteComentario() {
        // Arrange
        Long id = 1L;

        // Act
        comentarioService.deleteComentario(id);

        // Assert
        verify(comentarioRepository).deleteById(id);
    }

}