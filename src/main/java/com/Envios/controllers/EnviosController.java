package com.Envios.controllers;

import com.Envios.dto.EnviosDTO;
import com.Envios.services.EnviosServices;
import com.Envios.assembler.EnviosAssembler;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/envios")
public class EnviosController {

    @Autowired
    private EnviosServices EnviosServices;

    @Autowired
    private EnviosAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<EnviosDTO>> listar() {
        List<EntityModel<EnviosDTO>> envios = EnviosServices.listarTodos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnviosController.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        return EnviosServices.obtenerPorId(id)
                .map(envio -> ResponseEntity.ok(assembler.toModel(envio)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<EnviosDTO>> crear(@Valid @RequestBody EnviosDTO dto) {
        EnviosDTO creado = EnviosServices.guardar(dto);
        return ResponseEntity.ok(assembler.toModel(creado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        EnviosServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
