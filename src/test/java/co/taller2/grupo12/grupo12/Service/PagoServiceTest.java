package co.taller2.grupo12.grupo12.Service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import co.taller2.grupo12.grupo12.Grupo12Application;

import co.taller2.grupo12.grupo12.ApplicationRepository.PagoRepository;

import co.taller2.grupo12.grupo12.DTOS.PagoDTO;

import co.taller2.grupo12.grupo12.entity.Pago;

import co.taller2.grupo12.grupo12.services.PagoService;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Grupo12Application.class)
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)

public class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    void testGetAllPagos() {
        // Datos de prueba
        List<Pago> pagos = new ArrayList<>();
        pagos.add(new Pago(1L, Date.valueOf("2024-04-03"), 100.0, null, null)); // Suponiendo que tienes un constructor
                                                                                // en Pago
        pagos.add(new Pago(2L, Date.valueOf("2024-04-04"), 150.0, null, null));
        when(pagoRepository.findAll()).thenReturn(pagos);

        // Llamar al método del servicio
        List<PagoDTO> result = pagoService.getAllPagos();

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetPagoById_ExistingId() {
        // Datos de prueba
        Pago pago = new Pago(1L, Date.valueOf("2024-04-03"), 100.0, null, null);
        when(pagoRepository.findById(1L)).thenReturn(java.util.Optional.of(pago));

        // Llamar al método del servicio
        PagoDTO result = pagoService.getPagoById(1L);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(1L, result.getId_pago());
        assertEquals(Date.valueOf("2024-04-03"), result.getFecha());
        assertEquals(100.0, result.getValor());
    }

    @Test
    void testCreatePago() {
        // Datos de prueba
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setFecha(Date.valueOf("2024-04-03"));
        pagoDTO.setValor(100.0);

        Pago pago = new Pago(); // Fix: Update constructor call
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        // Llamar al método del servicio
        PagoDTO result = pagoService.createPago(pagoDTO);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(pagoDTO.getFecha(), result.getFecha());
        assertEquals(pagoDTO.getValor(), result.getValor());
    }

    @Test
    void testDeletePago() {
        // Llamar al método del servicio
        pagoService.deletePago(1L);

        // Verificar que se llame al método deleteById del repositorio con el ID
        // correcto
        verify(pagoRepository).deleteById(1L);
    }

    @Test
    void testUpdatePago_NullInput() {
        // Llamar al método del servicio con un ID y un objeto PagoDTO nulos
        PagoDTO result = pagoService.updatePago(null, null);

        // Verificar que el resultado sea nulo
        assertNull(result);
    }
}