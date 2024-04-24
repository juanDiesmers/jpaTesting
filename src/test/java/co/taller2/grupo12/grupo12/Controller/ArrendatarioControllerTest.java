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
import co.taller2.grupo12.grupo12.Controller.ArrendatarioController;
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

class ArrendatarioControllerTest {

    @Mock
    ArrendatarioService arrendatarioService;

    @InjectMocks
    ArrendatarioController arrendatarioController;

    @Test
    void testObtenerArrendatarioPorId() {
        // Configurar objeto mock
        Arrendatario arrendatarioMock = new Arrendatario();
        // Configurar el comportamiento esperado del método obtenerArrendatarioPorId() del servicio
        when(arrendatarioService.obtenerArrendatarioPorId(Mockito.anyLong())).thenReturn(Optional.of(arrendatarioMock));
        // Llamar al método del controlador
        ResponseEntity<Arrendatario> response = arrendatarioController.obtenerArrendatarioPorId(1L);
        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testObtenerTodosLosArrendatarios() {
        // Configurar objeto mock para el servicio
        List<Arrendatario> arrendatariosMock = new ArrayList<>();
        arrendatariosMock.add(new Arrendatario());
        arrendatariosMock.add(new Arrendatario());
        // Configurar el comportamiento esperado del método obtenerTodosLosArrendatarios() del servicio
        when(arrendatarioService.obtenerTodosLosArrendatarios()).thenReturn(arrendatariosMock);
        // Llamar al método del controlador
        List<Arrendatario> resultado = arrendatarioController.obtenerTodosLosArrendatarios();
        // Verificar el resultadooo
        assertNotNull(resultado);
        assertEquals(2, resultado.size()); // Verificar que se devuelvan todos los arrendatarios esperados
    }

    @Test
    void testCrearArrendatario() {
        // Datos de prueba
        ArrendatarioDTO arrendatarioDTO = new ArrendatarioDTO();
        // Configurar objeto mock para el servicio
        Arrendatario arrendatarioMock = new Arrendatario();
        // Configurar el comportamiento esperado del método crearArrendatario() del servicio
        when(arrendatarioService.crearArrendatario(any(ArrendatarioDTO.class))).thenReturn(arrendatarioMock);
        // Llamar al método del controlador
        Arrendatario resultado = arrendatarioController.crearArrendatario(arrendatarioDTO);
        // Verificar el resultado
        assertNotNull(resultado);
        // Si se espera algún procesamiento adicional o validaciones en el servicio, se pueden agregar más pruebas aquí
    }

    @Test
    void testActualizarArrendatarioExistente() {
        // Datos de prueba
        Long id = 1L;
        ArrendatarioDTO arrendatarioDTO = new ArrendatarioDTO();
        // Configurar objeto mock para el servicio
        Arrendatario arrendatarioMock = new Arrendatario();
        // Configurar el comportamiento esperado del método actualizarArrendatario() del servicio
        when(arrendatarioService.actualizarArrendatario(eq(id), any(ArrendatarioDTO.class))).thenReturn(arrendatarioMock);
        // Llamar al método del controlador
        ResponseEntity<Arrendatario> response = arrendatarioController.actualizarArrendatario(id, arrendatarioDTO);
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
        ResponseEntity<Void> response = arrendatarioController.eliminarArrendatario(id);
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // Verificar que el método eliminarArrendatarioPorId() del servicio haya sido llamado con el id adecuado
        verify(arrendatarioService).eliminarArrendatarioPorId(id);
    }
}