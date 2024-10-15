package com.cabeleireiro.agendamentroApi.domain.service;

import com.cabeleireiro.agendamentroApi.api.assembler.AgendamentoAssembler;
import com.cabeleireiro.agendamentroApi.api.representationmodel.input.AgendamentoDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.AgendamentoDtoOutput;
import com.cabeleireiro.agendamentroApi.domain.exception.ListaVazioException;
import com.cabeleireiro.agendamentroApi.domain.exception.RegraNegocioException;
import com.cabeleireiro.agendamentroApi.domain.model.Agendamento;
import com.cabeleireiro.agendamentroApi.domain.model.Cliente;
import com.cabeleireiro.agendamentroApi.domain.model.Profissional;
import com.cabeleireiro.agendamentroApi.domain.repository.AgendamentoRepository;
import lombok.AllArgsConstructor;
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
        if (cliente.getAgendamentos().isEmpty()) {
            throw new ListaVazioException("Cliente não possui nenhum agendamento!");
        } else if(isAgendamentoNaoExiste(agendamentoId)){
            throw new ListaVazioException("Não foi possível encontrar o agendamento!");
        } else if(agendamentoId == null || agendamentoId < 0){
            throw new NullPointerException("Agendamento Id não pode ser nulo ou menor que 0!");
        }
        Agendamento agendamento = this.buscarAgendamento(agendamentoId);
        cliente.removerAgendamento(agendamento);
    }

    public Boolean isAgendamentoNaoExiste(Long agendamentoId){
        return !agendamentoRepository.existsById(agendamentoId);
    }

    private Agendamento buscarAgendamento(Long agendamentoId){
        return agendamentoRepository.findById(agendamentoId).orElseThrow(() -> new RegraNegocioException("Nenhum agendamento encontrado com esse Id"));
    }

}
