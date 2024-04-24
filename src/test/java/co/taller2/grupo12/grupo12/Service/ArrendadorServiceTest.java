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
import static org.mockito.Mockito.mock;


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Grupo12Application.class
) 
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)


class ArrendadorServiceTest {

    @Mock
    private ArrendadorRepository arrendadorRepository;

    @InjectMocks
    private ArrendadorService arrendadorService;

    @Test
    void testGetArrendadores() {
        ArrendadorRepository arrendadorRepository = mock(ArrendadorRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ArrendadorService arrendadorService = new ArrendadorService(arrendadorRepository, modelMapper);
        List<Arrendador> arrendadores = new ArrayList<>();
        arrendadores.add(new Arrendador());
        arrendadores.add(new Arrendador());
        when(arrendadorRepository.findAll()).thenReturn(arrendadores);
        List<ArrendadorDTO> result = arrendadorService.getArrendadores();
        assertNotNull(result);
        assertEquals(arrendadores.size(), result.size());
    }

    @Test
    void testObtenerArrendadorPorIdExistente() {
        // Arrange
        Long id = 1L;
        ArrendadorRepository arrendadorRepository = mock(ArrendadorRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ArrendadorService arrendadorService = new ArrendadorService(arrendadorRepository, modelMapper);

        Arrendador arrendador = new Arrendador();
        arrendador.setId_arrendador(id);

        when(arrendadorRepository.findById(id)).thenReturn(Optional.of(arrendador));

        // Act
        Optional<Arrendador> result = arrendadorService.obtenerArrendadorPorId(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId_arrendador());
    }

    @Test
    void testActualizarArrendadorExistente() {
        // Arrange
        Long id = 1L;
        ArrendadorDTO arrendadorDTO = new ArrendadorDTO();
        arrendadorDTO.setId_arrendador(id);
        ArrendadorRepository arrendadorRepository = mock(ArrendadorRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ArrendadorService arrendadorService = new ArrendadorService(arrendadorRepository, modelMapper);

        Arrendador arrendador = new Arrendador();
        arrendador.setId_arrendador(id);

        when(arrendadorRepository.findById(id)).thenReturn(Optional.of(arrendador));
        when(arrendadorRepository.save(any(Arrendador.class))).thenReturn(arrendador);

        // Act
        Arrendador result = arrendadorService.actualizarArrendador(id, arrendadorDTO);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_arrendador());
    }


    @Test
    void testGuardarArrendador() {
        // Arrange
        ArrendadorDTO arrendadorDTO = new ArrendadorDTO();
        arrendadorDTO.setNombre("Nombre");
        arrendadorDTO.setApellido("Apellido");
        ArrendadorRepository arrendadorRepository = mock(ArrendadorRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ArrendadorService arrendadorService = new ArrendadorService(arrendadorRepository, modelMapper);

        Arrendador arrendador = new Arrendador();
        arrendador.setNombre(arrendadorDTO.getNombre());
        arrendador.setApellido(arrendadorDTO.getApellido());

        when(arrendadorRepository.save(any(Arrendador.class))).thenReturn(arrendador);

        // Act
        Arrendador result = arrendadorService.guardarArrendador(arrendadorDTO);

        // Assert
        assertNotNull(result);
        assertEquals(arrendadorDTO.getNombre(), result.getNombre());
        assertEquals(arrendadorDTO.getApellido(), result.getApellido());
    }


    @Test
    void testDeleteArrendador() {
        // Arrange
        Long id = 1L;
        ArrendadorRepository arrendadorRepository = mock(ArrendadorRepository.class);
        ModelMapper modelMapper = new ModelMapper();
        ArrendadorService arrendadorService = new ArrendadorService(arrendadorRepository, modelMapper);

        // Act
        arrendadorService.deleteArrendador(id);

        // Assert
        verify(arrendadorRepository).deleteById(id);
    }
}