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
import co.taller2.grupo12.grupo12.ApplicationRepository.FincaRepository;
import co.taller2.grupo12.grupo12.Controller.ArrendadorController;
import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.DTOS.FincaDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Finca;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import co.taller2.grupo12.grupo12.services.ArrendadorService;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;
import co.taller2.grupo12.grupo12.services.FincaService;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
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


class FincaServiceTest {

    @Mock
    private FincaRepository fincaRepository;

    @Mock
    private ArrendatarioRepository arrendatarioRepository;

    @Mock
    private ArrendadorRepository arrendadorRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FincaService fincaService;

    @Test
    public void testGetAllFincas() {
        // Preparar datos de prueba
        List<Finca> fincas = new ArrayList<>();
        fincas.add(new Finca());
        fincas.add(new Finca());
        when(fincaRepository.findAll()).thenReturn(fincas);
        
        // Ejecutar método a probar
        List<FincaDTO> result = fincaService.getAllFincas();
        
        // Verificar resultado
        assertEquals(2, result.size());
    }


    @Test
    public void testUpdateFinca() {
        // Preparar datos de prueba
        Long id = 1L;
        FincaDTO fincaDTO = new FincaDTO();
        fincaDTO.setNombre("Nombre actualizado");
        Finca finca = new Finca();
        when(fincaRepository.findById(id)).thenReturn(Optional.of(finca));
        when(fincaRepository.save(any(Finca.class))).thenReturn(finca);

        // Ejecutar método a probar
        FincaDTO result = fincaService.updateFinca(id, fincaDTO);

        // Verificar resultado
        assertEquals(null, result);
        assertEquals("Nombre actualizado", finca.getNombre());
    }

    @Test
    public void testDeleteFinca() {
        // Preparar datos de prueba
        Long id = 1L;

        // Ejecutar método a probar
        fincaService.deleteFinca(id);

        // Verificar que se llame al método deleteById del repositorio
        verify(fincaRepository, times(1)).deleteById(id);
    }
}