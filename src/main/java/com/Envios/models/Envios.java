package com.Envios.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "envios")
public class Envios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "direccion_destino", nullable = false)
    private String direccionDestino;

    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;
}