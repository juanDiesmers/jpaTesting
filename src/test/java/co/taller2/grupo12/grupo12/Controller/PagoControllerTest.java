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
import co.taller2.grupo12.grupo12.DTOS.PagoDTO;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;
import co.taller2.grupo12.grupo12.services.PagoService;

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

class PagoControllerTest {

    @Mock
    PagoService pagoService;

    @InjectMocks
    PagoController pagoController;

    @Test
    void testGetAllPagos() {
        // Arrange
        List<PagoDTO> pagos = new ArrayList<>();
        pagos.add(new PagoDTO());
        pagos.add(new PagoDTO());
        when(pagoService.getAllPagos()).thenReturn(pagos);

        // Act
        ResponseEntity<List<PagoDTO>> response = pagoController.getAllPagos();
        List<PagoDTO> result = response.getBody();

        // Assert
        assertNotNull(result);
        assertEquals(pagos.size(), result.size());
        verify(pagoService).getAllPagos();
    }

    @Test
    void testGetPagoByIdExistente() {
        // Arrange
        Long id = 1L;
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setId_pago(id);
        when(pagoService.getPagoById(id)).thenReturn(pagoDTO);

        // Act
        ResponseEntity<PagoDTO> response = pagoController.getPagoById(id);
        PagoDTO result = response.getBody();

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_pago());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(pagoService).getPagoById(id);
    }

    @Test
    void testCreatePago() {
        // Arrange
        PagoDTO pagoDTO = new PagoDTO();
        when(pagoService.createPago(pagoDTO)).thenReturn(pagoDTO);

        // Act
        ResponseEntity<PagoDTO> response = pagoController.createPago(pagoDTO);
        PagoDTO result = response.getBody();

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pagoDTO, result);
        verify(pagoService).createPago(pagoDTO);
    }


    @Test
    void testUpdatePagoExistente() {
        // Arrange
        Long id = 1L;
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setId_pago(id);
        when(pagoService.updatePago(id, pagoDTO)).thenReturn(pagoDTO);

        // Act
        ResponseEntity<PagoDTO> response = pagoController.updatePago(id, pagoDTO);
        PagoDTO result = response.getBody();

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_pago());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(pagoService).updatePago(id, pagoDTO);
    }


    @Test
    void testDeletePago() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> response = pagoController.deletePago(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pagoService).deletePago(id);
    }
}