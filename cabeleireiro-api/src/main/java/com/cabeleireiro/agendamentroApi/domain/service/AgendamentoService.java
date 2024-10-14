package com.cabeleireiro.agendamentroApi.domain.service;

import com.cabeleireiro.agendamentroApi.api.assembler.AgendamentoAssembler;
import com.cabeleireiro.agendamentroApi.api.representationmodel.input.AgendamentoDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.AgendamentoDtoOutput;
import com.cabeleireiro.agendamentroApi.domain.exception.ListaVazioException;
import com.cabeleireiro.agendamentroApi.domain.model.Agendamento;
import com.cabeleireiro.agendamentroApi.domain.model.Cliente;
import com.cabeleireiro.agendamentroApi.domain.model.Profissional;
import com.cabeleireiro.agendamentroApi.domain.repository.AgendamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AgendamentoService {

    private final AgendamentoAssembler agendamentoAssembler;
    private final ClienteService clienteService;
    private final ProfissionalService profissionalService;
    private final AgendamentoRepository agendamentoRepository;

    @Transactional
    public AgendamentoDtoOutput inserirAgendamento(Long profissionalId, AgendamentoDtoInput agendamentoInput){
        Agendamento agendamento = agendamentoAssembler.toEntity(agendamentoInput);
        Profissional profissional = profissionalService.buscar(profissionalId);
        agendamento.setProfissional(profissional);
        Cliente cliente = clienteService.buscar(agendamento.getCliente().getId());
        return agendamentoAssembler.toAgendamentoOutput(cliente.realizarAgendamento(agendamento));

    }

    @Transactional
    public void removerAgendamento(Long clienteId, Long agendamentoId) {
        Cliente cliente = clienteService.buscar(clienteId);
        cliente.removerAgendamento(agendamentoId);
    }

    public Boolean isAgendamentoNaoExiste(Long agendamentoId){
        return !agendamentoRepository.existsById(agendamentoId);
    }

}
