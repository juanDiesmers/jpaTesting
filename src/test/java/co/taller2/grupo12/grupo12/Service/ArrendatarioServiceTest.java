package co.taller2.grupo12.grupo12.Service;

import org.apache.tomcat.util.http.parser.MediaType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
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
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import co.taller2.grupo12.grupo12.Controller.ArrendadorController;
import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendadorService;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Grupo12Application.class
) 
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)


class ArrendatarioServiceTest {

    @Mock
    private ArrendatarioRepository arrendatarioRepository;

    @InjectMocks
    private ArrendatarioService arrendatarioService;

    @Test
    void testCrearArrendatario() {
        // Arrange
        ArrendatarioDTO arrendatarioDTO = new ArrendatarioDTO();
        arrendatarioDTO.setNombre("John");
        arrendatarioDTO.setApellido("Doe");
        arrendatarioDTO.setCorreo("john.doe@example.com");
        arrendatarioDTO.setContrasena("password");

        Arrendatario arrendatario = new Arrendatario();
        arrendatario.setId_arrendatario(1L);
        arrendatario.setNombre(arrendatarioDTO.getNombre());
        arrendatario.setApellido(arrendatarioDTO.getApellido());
        arrendatario.setCorreo(arrendatarioDTO.getCorreo());
        arrendatario.setContrasena(arrendatarioDTO.getContrasena());

        when(arrendatarioRepository.save(any(Arrendatario.class))).thenReturn(arrendatario);

        // Act
        Arrendatario result = arrendatarioService.crearArrendatario(arrendatarioDTO);

        // Assert
        assertNotNull(result);
        assertEquals(arrendatarioDTO.getNombre(), result.getNombre());
        assertEquals(arrendatarioDTO.getApellido(), result.getApellido());
        assertEquals(arrendatarioDTO.getCorreo(), result.getCorreo());
        assertEquals(arrendatarioDTO.getContrasena(), result.getContrasena());
    }

    @Test
    void testObtenerTodosLosArrendatarios() {
        // Arrange
        List<Arrendatario> arrendatarios = new ArrayList<>();
        arrendatarios.add(new Arrendatario(1L, "John", "Doe", "john.doe@example.com", "password"));
        arrendatarios.add(new Arrendatario(2L, "Jane", "Smith", "jane.smith@example.com", "password123"));

        when(arrendatarioRepository.findAll()).thenReturn(arrendatarios);

        // Act
        List<Arrendatario> result = arrendatarioService.obtenerTodosLosArrendatarios();

        // Assert
        assertNotNull(result);
        assertEquals(arrendatarios.size(), result.size());
        assertEquals(arrendatarios.get(0).getId_arrendatario(), result.get(0).getId_arrendatario());
        assertEquals(arrendatarios.get(0).getNombre(), result.get(0).getNombre());
        assertEquals(arrendatarios.get(0).getApellido(), result.get(0).getApellido());
        assertEquals(arrendatarios.get(0).getCorreo(), result.get(0).getCorreo());
        assertEquals(arrendatarios.get(0).getContrasena(), result.get(0).getContrasena());
        assertEquals(arrendatarios.get(1).getId_arrendatario(), result.get(1).getId_arrendatario());
        assertEquals(arrendatarios.get(1).getNombre(), result.get(1).getNombre());
        assertEquals(arrendatarios.get(1).getApellido(), result.get(1).getApellido());
        assertEquals(arrendatarios.get(1).getCorreo(), result.get(1).getCorreo());
        assertEquals(arrendatarios.get(1).getContrasena(), result.get(1).getContrasena());
    }

    @Test
    void testObtenerArrendatarioPorIdExistente() {
        // Arrange
        Long id = 1L;
        Arrendatario arrendatario = new Arrendatario(id, "John", "Doe", "john.doe@example.com", "password");

        when(arrendatarioRepository.findById(id)).thenReturn(Optional.of(arrendatario));

        // Act
        Optional<Arrendatario> result = arrendatarioService.obtenerArrendatarioPorId(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(arrendatario, result.get());
    }

    @Test
    void testActualizarArrendatarioExistente() {
        // Arrange
        Long id = 1L;
        ArrendatarioDTO arrendatarioDTO = new ArrendatarioDTO();
        arrendatarioDTO.setNombre("John");
        arrendatarioDTO.setApellido("Doe");
        arrendatarioDTO.setCorreo("john.doe@example.com");
        arrendatarioDTO.setContrasena("password");

        Arrendatario arrendatario = new Arrendatario(id, "Old", "Arrendatario", "old.arrendatario@example.com", "oldpassword");

        when(arrendatarioRepository.findById(id)).thenReturn(Optional.of(arrendatario));
        when(arrendatarioRepository.save(any(Arrendatario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Arrendatario result = arrendatarioService.actualizarArrendatario(id, arrendatarioDTO);

        // Assert
        assertNotNull(result);
        assertEquals(arrendatarioDTO.getNombre(), result.getNombre());
        assertEquals(arrendatarioDTO.getApellido(), result.getApellido());
        assertEquals(arrendatarioDTO.getCorreo(), result.getCorreo());
        assertEquals(arrendatarioDTO.getContrasena(), result.getContrasena());
    }

    @Test
    void testEliminarArrendatarioPorId() {
        // Arrange
        Long id = 1L;

        // Act
        arrendatarioService.eliminarArrendatarioPorId(id);

        // Assert
        // Verificar que el método deleteById del repositorio se llamó correctamente con el ID del arrendatario a eliminar
        verify(arrendatarioRepository).deleteById(eq(id));
    }

}