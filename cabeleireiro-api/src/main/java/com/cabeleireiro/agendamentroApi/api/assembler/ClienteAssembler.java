package com.cabeleireiro.agendamentroApi.api.assembler;

import com.cabeleireiro.agendamentroApi.api.representationmodel.input.ClienteDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.ClienteDtoOutput;
import com.cabeleireiro.agendamentroApi.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ClienteAssembler {

    private final ModelMapper modelMapper;

    public Cliente toEntity(ClienteDtoInput clienteDtoInput){
        return modelMapper.map(clienteDtoInput, Cliente.class);
    }

    public ClienteDtoOutput toClienteOutput(Cliente cliente){
        return modelMapper.map(cliente, ClienteDtoOutput.class);
    }

    public List<ClienteDtoOutput> toCollectionClienteOutput(List<Cliente> clientes){
        return clientes.stream()
                       .map(this::toClienteOutput)
                       .toList();
    }

}
