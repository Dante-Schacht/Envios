package com.Envios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class EnviosDTO {

    private Integer id;

    @NotBlank(message = "La dirección de destino es obligatoria")
    private String direccionDestino;

    @NotBlank(message = "El estado del envío es obligatorio")
    private String estado;

    @NotNull(message = "La fecha de envío es obligatoria")
    private LocalDateTime fechaEnvio;
}