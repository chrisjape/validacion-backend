package mx.uacj.cifi.validacion_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "validacion")
public class Validacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idParticipante;

    @Enumerated(EnumType.STRING)
    private EstadoValidacion estado = EstadoValidacion.pendiente;

    private String creadoEn;
    private String actualizadoEn;

    public enum EstadoValidacion {
        pendiente, aprobado, rechazado
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdParticipante() { return idParticipante; }
    public void setIdParticipante(Long idParticipante) { this.idParticipante = idParticipante; }

    public EstadoValidacion getEstado() { return estado; }
    public void setEstado(EstadoValidacion estado) { this.estado = estado; }

    public String getCreadoEn() { return creadoEn; }
    public void setCreadoEn(String creadoEn) { this.creadoEn = creadoEn; }

    public String getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(String actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}