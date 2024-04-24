package co.taller2.grupo12.grupo12.Service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;


import co.taller2.grupo12.grupo12.Grupo12Application;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.FincaRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.PagoRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.SolicitudRepository;
import co.taller2.grupo12.grupo12.DTOS.PagoDTO;
import co.taller2.grupo12.grupo12.DTOS.SolicitudDTO;
import co.taller2.grupo12.grupo12.entity.Pago;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import co.taller2.grupo12.grupo12.services.PagoService;
import co.taller2.grupo12.grupo12.services.SolicitudService;

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


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Grupo12Application.class
) 
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)


public class SolicitudServiceTest {

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private ArrendatarioRepository arrendatarioRepository;

    @Mock
    private FincaRepository fincaRepository;

    @InjectMocks
    private SolicitudService solicitudService;

    @Test
    void testDeleteSolicitud_ExistingId() {
        // Datos de prueba
        Solicitud solicitudExistente = new Solicitud(Date.valueOf("2024-04-03"), "Pendiente");
        when(solicitudRepository.findById(1L)).thenReturn(java.util.Optional.of(solicitudExistente));

        // Llamar al método del servicio
        solicitudService.deleteSolicitud(1L);

        // Verificar que el método deleteById haya sido invocado
        verify(solicitudRepository).deleteById(1L);
    }

    
    


}
