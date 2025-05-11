package com.municipalidad.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroTicket;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoServicio tipoServicio;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column
    private LocalDateTime fechaAtencion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTicket estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "atendido_por")
    private Usuario atendidoPor;

    public enum TipoServicio {
        IUSI,
        AGUA,
        CATASTRO,
        LICENCIA_CONSTRUCCION
    }

    public enum EstadoTicket {
        PENDIENTE,
        EN_ATENCION,
        ATENDIDO,
        CANCELADO
    }
} 