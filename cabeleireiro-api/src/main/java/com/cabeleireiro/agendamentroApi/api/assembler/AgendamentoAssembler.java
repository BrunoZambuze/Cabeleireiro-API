package com.cabeleireiro.agendamentroApi.api.assembler;

import com.cabeleireiro.agendamentroApi.api.representationmodel.input.AgendamentoDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.input.AgendamentoId;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.AgendamentoDtoOutput;
import com.cabeleireiro.agendamentroApi.domain.model.Agendamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AgendamentoAssembler {

    private final ModelMapper modelMapper;

    public Agendamento toEntity(AgendamentoDtoInput agendamentoInput){
        return modelMapper.map(agendamentoInput, Agendamento.class);
    }

    public AgendamentoDtoOutput toAgendamentoOutput(Agendamento agendamento){
        return modelMapper.map(agendamento, AgendamentoDtoOutput.class);
    }

    public List<AgendamentoDtoOutput> toCollectionAgendamentoOutput(List<Agendamento> agendamentos){
        return agendamentos.stream()
                .map(this::toAgendamentoOutput)
                .toList();
    }
}
