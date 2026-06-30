package mx.uacj.cifi.validacion_backend.controller;

import mx.uacj.cifi.validacion_backend.model.ValidacionPago;
import mx.uacj.cifi.validacion_backend.repository.ValidacionPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/validacion-pago")
public class ValidacionPagoController {

    @Autowired
    private ValidacionPagoRepository validacionPagoRepository;

    // Listar todos
    @GetMapping
    public List<ValidacionPago> listar() {
        return validacionPagoRepository.findAll();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public Optional<ValidacionPago> buscarPorId(@PathVariable Long id) {
        return validacionPagoRepository.findById(id);
    }

    // Buscar por id de validacion (el que usa el frontend)
    @GetMapping("/validacion/{idValidacion}")
    public ValidacionPago buscarPorValidacion(@PathVariable Long idValidacion) {
        return validacionPagoRepository.findByIdValidacion(idValidacion);
    }

    // Guardar nuevo
    @PostMapping
    public ValidacionPago guardar(@RequestBody ValidacionPago validacionPago) {
        return validacionPagoRepository.save(validacionPago);
    }

    // Actualizar estado
    @PutMapping("/{id}")
    public ValidacionPago actualizar(@PathVariable Long id, @RequestBody ValidacionPago datos) {
        datos.setId(id);
        return validacionPagoRepository.save(datos);
    }
}