package co.taller2.grupo12.grupo12.Controller;
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
import co.taller2.grupo12.grupo12.Controller.ArrendadorController;
import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendadorService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Grupo12Application.class
) 
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)


class ArrendadorControllerTest {

    @Mock
    ArrendadorService arrendadorService;

    

    @InjectMocks
    ArrendadorController arrendadorController;

   

    @Test
    void testObtenerArrendadoroPorId() {
        // Configurar objeto mock
        Arrendador arrendadorMock = new Arrendador();
        // Configurar el comportamiento esperado del método obtenerArrendatarioPorId() del servicio
        when(arrendadorService.obtenerArrendadorPorId(Mockito.anyLong())).thenReturn(Optional.of(arrendadorMock));
        // Llamar al método del controlador
        ResponseEntity<Arrendador> response = arrendadorController.obtenerArrendadorPorId(1L);
        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    @Test
    void testCrearArrendadorConDTO() {
        // Datos de prueba
        ArrendadorDTO arrendadorDTO = new ArrendadorDTO();
        arrendadorDTO.setNombre("Juan");
        arrendadorDTO.setApellido("Perez");

        //Crear mock
        ArrendadorDTO arrendadorDTOMock = new ArrendadorDTO();
        arrendadorDTOMock.setId_arrendador(1L);
        arrendadorDTOMock.setNombre(arrendadorDTO.getNombre());
        arrendadorDTOMock.setApellido(arrendadorDTO.getApellido());
        
        // Configurar el comportamiento esperado del método createArrendador() del servicio
        when(arrendadorService.createArrendador(any(ArrendadorDTO.class))).thenReturn(arrendadorDTOMock);
        
        // Llamar al método del controlador
        ResponseEntity<ArrendadorDTO> response = arrendadorController.createArrendador(arrendadorDTO);
        
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(arrendadorDTOMock.getId_arrendador(), response.getBody().getId_arrendador());
        assertEquals(arrendadorDTOMock.getNombre(), response.getBody().getNombre());
        assertEquals(arrendadorDTOMock.getApellido(), response.getBody().getApellido());
    }

    @Test
    void testCrearArrendador() {
        // Datos de prueba
        ArrendadorDTO arrendadorDTO = new ArrendadorDTO();
        // Configurar objeto mock para el servicio
        Arrendador arrendadorMock = new Arrendador();
        // Configurar el comportamiento esperado del método crearArrendatario() del servicio
        when(arrendadorService.crearArrendadorSIN(any(ArrendadorDTO.class))).thenReturn(arrendadorMock);
        // Llamar al método del controlador
        Arrendador resultado = arrendadorController.crearArrendador(arrendadorDTO);
        // Verificar el resultado
        assertNotNull(resultado);
        // Si se espera algún procesamiento adicional o validaciones en el servicio, se pueden agregar más pruebas aquí
    }
    
    @Test
    void testActualizarArrendatarioExistente() {
        // Datos de prueba
        Long id = 1L;
        ArrendadorDTO arrendadorDTO = new ArrendadorDTO();
        // Configurar objeto mock para el servicio
        Arrendador arrendatarioMock = new Arrendador();
        // Configurar el comportamiento esperado del método actualizarArrendatario() del servicio
        when(arrendadorService.actualizarArrendador(eq(id), any(ArrendadorDTO.class))).thenReturn(arrendatarioMock);
        // Llamar al método del controlador
        ResponseEntity<Arrendador> response = arrendadorController.actualizarArrendador(id, arrendadorDTO);
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(arrendatarioMock, response.getBody());
    }

    @Test
    void testEliminarArrendatarioExistente() {
        // Datos de prueba
        Long id = 1L;
        // Llamar al método del controlador
        ResponseEntity<Void> response = arrendadorController.deleteArrendador(id);
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // Verificar que el método eliminarArrendatarioPorId() del servicio haya sido llamado con el id adecuado
        verify(arrendadorService).deleteArrendador(id);
    }
   
}
