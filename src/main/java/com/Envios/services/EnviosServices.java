package com.Envios.services;

import com.Envios.dto.EnviosDTO;
import com.Envios.models.Envios;
import com.Envios.repository.EnviosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnviosServices {

    @Autowired
    private EnviosRepository envioRepository;

    public List<EnviosDTO> listarTodos() {
        return envioRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<EnviosDTO> obtenerPorId(Long id) {
        return envioRepository.findById(id).map(this::toDTO);
    }

    public EnviosDTO guardar(EnviosDTO dto) {
        Envios envio = toEntity(dto);
        return toDTO(envioRepository.save(envio));
    }

    public void eliminar(Long id) {
        envioRepository.deleteById(id);
    }

    private EnviosDTO toDTO(Envios envio) {
        EnviosDTO dto = new EnviosDTO();
        dto.setId(envio.getId());
        dto.setDireccionDestino(envio.getDireccionDestino());
        dto.setEstado(envio.getEstado());
        dto.setFechaEnvio(envio.getFechaEnvio());
        return dto;
    }

    private Envios toEntity(EnviosDTO dto) {
        Envios envio = new Envios();
        envio.setId(dto.getId());
        envio.setDireccionDestino(dto.getDireccionDestino());
        envio.setEstado(dto.getEstado());
        envio.setFechaEnvio(dto.getFechaEnvio());
        return envio;
    }
}
