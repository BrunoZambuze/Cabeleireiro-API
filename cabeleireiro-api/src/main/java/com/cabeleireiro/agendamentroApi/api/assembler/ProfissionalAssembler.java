package com.cabeleireiro.agendamentroApi.api.assembler;

import com.cabeleireiro.agendamentroApi.api.representationmodel.input.ProfissionalDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.ProfissionalDtoOutput;
import com.cabeleireiro.agendamentroApi.domain.model.Profissional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProfissionalAssembler {

    private final ModelMapper modelMapper;

    public Profissional toEntity(ProfissionalDtoInput profissionalDtoInput){
        return modelMapper.map(profissionalDtoInput, Profissional.class);
    }

    public ProfissionalDtoOutput toProffisionalOutput(Profissional profissional){
        return modelMapper.map(profissional, ProfissionalDtoOutput.class);
    }

    public List<ProfissionalDtoOutput> toCollectionProfissionalOutput(List<Profissional> profissionais){
        return profissionais.stream()
                            .map(this::toProffisionalOutput)
                            .toList();
    }

}
