package com.Envios.assembler;

import com.Envios.controllers.EnviosController;
import com.Envios.dto.EnviosDTO;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EnviosAssembler implements RepresentationModelAssembler<EnviosDTO, EntityModel<EnviosDTO>> {

    @Override
    public EntityModel<EnviosDTO> toModel(EnviosDTO envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnviosController.class).obtener(envio.getId().longValue())).withSelfRel(),
                linkTo(methodOn(EnviosController.class).listar()).withRel("envios"));
    }
}
