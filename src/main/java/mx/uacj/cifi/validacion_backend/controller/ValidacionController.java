package mx.uacj.cifi.validacion_backend.controller;

import mx.uacj.cifi.validacion_backend.model.Validacion;
import mx.uacj.cifi.validacion_backend.repository.ValidacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/validacion")
public class ValidacionController {

    @Autowired
    private ValidacionRepository validacionRepository;

    // Listar todas
    @GetMapping
    public List<Validacion> listar() {
        return validacionRepository.findAll();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Validacion> buscarPorId(@PathVariable Long id) {
        Optional<Validacion> validacion = validacionRepository.findById(id);
        return validacion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nueva validacion
    @PostMapping
    public Validacion guardar(@RequestBody Validacion validacion) {
        return validacionRepository.save(validacion);
    }

    // Aprobar participante
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Validacion> aprobar(@PathVariable Long id) {
        Optional<Validacion> optional = validacionRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Validacion validacion = optional.get();
        validacion.setEstado(Validacion.EstadoValidacion.aprobado);
        validacion.setActualizadoEn(java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(validacionRepository.save(validacion));
    }

    // Rechazar participante
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Validacion> rechazar(@PathVariable Long id) {
        Optional<Validacion> optional = validacionRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Validacion validacion = optional.get();
        validacion.setEstado(Validacion.EstadoValidacion.rechazado);
        validacion.setActualizadoEn(java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(validacionRepository.save(validacion));
    }

    // Regresar a pendiente
    @PutMapping("/{id}/pendiente")
    public ResponseEntity<Validacion> pendiente(@PathVariable Long id) {
        Optional<Validacion> optional = validacionRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Validacion validacion = optional.get();
        validacion.setEstado(Validacion.EstadoValidacion.pendiente);
        validacion.setActualizadoEn(java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(validacionRepository.save(validacion));
    }
}