package com.cabeleireiro.agendamentroApi.api.assembler;

import com.cabeleireiro.agendamentroApi.api.representationmodel.input.AdministradorDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.AdministradorDtoOutput;
import com.cabeleireiro.agendamentroApi.domain.model.Administrador;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AdministradorAssembler {

    private final ModelMapper modelMapper;

    public Administrador toEntity(AdministradorDtoInput administradorDtoInput){
        return modelMapper.map(administradorDtoInput, Administrador.class);
    }

    public AdministradorDtoOutput toAdministradorOutput(Administrador administrador){
        return modelMapper.map(administrador, AdministradorDtoOutput.class);
    }

    public List<AdministradorDtoOutput> toColletionAdministradorOutput(List<Administrador> administradores){
        return administradores.stream()
                              .map(this::toAdministradorOutput)
                              .toList();
    }

}
