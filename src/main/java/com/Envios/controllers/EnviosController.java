
package com.Envios.controllers;

import com.Envios.dto.EnviosDTO;
import com.Envios.services.EnviosServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnviosController {

    @Autowired
    private EnviosServices EnviosServices;

    @GetMapping
    public List<EnviosDTO> listar() {
        return EnviosServices.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnviosDTO> obtener(@PathVariable Long id) {
        return EnviosServices.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EnviosDTO> crear(@Valid @RequestBody EnviosDTO dto) {
        return ResponseEntity.ok(EnviosServices.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        EnviosServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
