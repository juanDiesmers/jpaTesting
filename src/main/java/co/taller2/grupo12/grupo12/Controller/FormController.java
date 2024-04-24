package co.taller2.grupo12.grupo12.Controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.services.ArrendadorService;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/submit")
public class FormController {

    private final ArrendadorService arrendadorService;

    public FormController(ArrendadorService arrendadorService) {
        this.arrendadorService = arrendadorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArrendadorDTO> getArrendadores() {
        return arrendadorService.getArrendadores();
    }

    @PostMapping
    public ArrendadorDTO guardarArrendador(@RequestBody ArrendadorDTO arrendadorDTO) {
        return arrendadorService.createArrendador(arrendadorDTO);
    }
}
