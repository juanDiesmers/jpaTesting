package co.taller2.grupo12.grupo12.Controller;

import org.junit.jupiter.api.BeforeEach;
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
import co.taller2.grupo12.grupo12.DTOS.PagoDTO;
import co.taller2.grupo12.grupo12.DTOS.SolicitudDTO;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;
import co.taller2.grupo12.grupo12.services.PagoService;
import co.taller2.grupo12.grupo12.services.SolicitudService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Grupo12Application.class
) 
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)

public class SolicitudControllerTest {
    @Mock
    private SolicitudService solicitudService;

    @InjectMocks
    private SolicitudController solicitudController;

   

    @Test
    void testGetAllSolicitudes() {
        // Datos de prueba
        List<SolicitudDTO> solicitudesMock = new ArrayList<>();
        SolicitudDTO solicitudDTO1 = new SolicitudDTO();
        solicitudDTO1.setIdSolicitud(1L);
        SolicitudDTO solicitudDTO2 = new SolicitudDTO();
        solicitudDTO2.setIdSolicitud(2L);
        solicitudesMock.add(solicitudDTO1);
        solicitudesMock.add(solicitudDTO2);
        
        // Configurar el comportamiento esperado del servicio
        when(solicitudService.getAllSolicitudes()).thenReturn(solicitudesMock);
        
        // Llamar al método del controlador
        ResponseEntity<List<SolicitudDTO>> response = solicitudController.getAllSolicitudes();
        
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size()); // Verificar que se devuelvan 2 solicitudes
        verify(solicitudService).getAllSolicitudes();
    }

    @Test
    void testCreateSolicitud() {
        // Datos de prueba
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setIdSolicitud(1L);
        
        // Configurar el comportamiento esperado del servicio
        when(solicitudService.createSolicitud(any(SolicitudDTO.class))).thenReturn(solicitudDTO);
        
        // Llamar al método del controlador
        ResponseEntity<SolicitudDTO> response = solicitudController.createSolicitud(solicitudDTO);
        
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(solicitudDTO.getIdSolicitud(), response.getBody().getIdSolicitud());
        verify(solicitudService).createSolicitud(any(SolicitudDTO.class));
    }

    @Test
    void testUpdateSolicitud() {
        // Datos de prueba
        Long solicitudId = 1L;
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setIdSolicitud(solicitudId);
        
        // Configurar el comportamiento esperado del servicio
        when(solicitudService.updateSolicitud(eq(solicitudId), any(SolicitudDTO.class))).thenReturn(solicitudDTO);
        
        // Llamar al método del controlador
        ResponseEntity<SolicitudDTO> response = solicitudController.updateSolicitud(solicitudId, solicitudDTO);
        
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(solicitudId, response.getBody().getIdSolicitud());
        verify(solicitudService).updateSolicitud(eq(solicitudId), any(SolicitudDTO.class));
    }
    @Test
    void testDeleteSolicitud() {
        // Datos de prueba
        Long solicitudId = 1L;
        
        // Llamar al método del controlador
        ResponseEntity<Void> response = solicitudController.deleteSolicitud(solicitudId);
        
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(solicitudService).deleteSolicitud(solicitudId);
    }
    @Test
void testGetAllSolicitudes_EmptyList() {
    // Configurar el comportamiento esperado del servicio para devolver una lista vacía
    when(solicitudService.getAllSolicitudes()).thenReturn(new ArrayList<>());
    
    // Llamar al método del controlador
    ResponseEntity<List<SolicitudDTO>> response = solicitudController.getAllSolicitudes();
    
    // Verificar el resultado
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(response.getBody().isEmpty()); // Verificar que la lista esté vacía
    verify(solicitudService).getAllSolicitudes();
}

    @Test
    void testGetSolicitudById_NotFound() {
        // Datos de prueba
        Long solicitudId = 1L;
        
        // Configurar el comportamiento esperado del servicio para devolver null (solicitud no encontrada)
        when(solicitudService.getSolicitudById(solicitudId)).thenReturn(null);
        
        // Llamar al método del controlador
        ResponseEntity<SolicitudDTO> response = solicitudController.getSolicitudById(solicitudId);
        
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody()); // Verificar que el cuerpo de la respuesta sea nulo
        verify(solicitudService).getSolicitudById(solicitudId);
    }

 

    @Test
    void testUpdateSolicitud_NotFound() {
        // Datos de prueba
        Long solicitudId = 1L;
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setIdSolicitud(solicitudId);
        
        // Configurar el comportamiento esperado del servicio para devolver null (solicitud no encontrada)
        when(solicitudService.updateSolicitud(eq(solicitudId), any(SolicitudDTO.class))).thenReturn(null);
        
        // Llamar al método del controlador
        ResponseEntity<SolicitudDTO> response = solicitudController.updateSolicitud(solicitudId, solicitudDTO);
        
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody()); // Verificar que el cuerpo de la respuesta sea nulo
        verify(solicitudService).updateSolicitud(eq(solicitudId), any(SolicitudDTO.class));
    }

    

}
