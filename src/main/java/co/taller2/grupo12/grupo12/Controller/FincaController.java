package co.taller2.grupo12.grupo12.Controller;

import co.taller2.grupo12.grupo12.DTOS.FincaDTO;
import co.taller2.grupo12.grupo12.services.FincaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fincas")
public class FincaController {

    private final FincaService fincaService;

    @Autowired
    public FincaController(FincaService fincaService) {
        this.fincaService = fincaService;
    }

    @GetMapping
    public ResponseEntity<List<FincaDTO>> getAllFincas() {
        List<FincaDTO> fincas = fincaService.getAllFincas();
        return ResponseEntity.ok(fincas);
    }

    @PostMapping
    public FincaDTO guardarFinca(@RequestBody FincaDTO fincaDTO) {
        return fincaService.createFinca(fincaDTO);
    }

    @GetMapping("/{id}")
    public FincaDTO getFincaById(@PathVariable Long id) {
        return fincaService.getFincaById(id);
    }

    @PutMapping("/{id}")
    public FincaDTO actualizarFinca(@PathVariable Long id, @RequestBody FincaDTO fincaDTO) {
        return fincaService.updateFinca(id, fincaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarFinca(@PathVariable Long id) {
        fincaService.deleteFinca(id);
    }

}