package com.cabeleireiro.agendamentroApi.domain.service;

import com.cabeleireiro.agendamentroApi.domain.model.Agendamento;
import com.cabeleireiro.agendamentroApi.domain.model.Cliente;
import com.cabeleireiro.agendamentroApi.domain.model.Profissional;
import com.cabeleireiro.agendamentroApi.domain.model.StatusAgendamento;
import com.cabeleireiro.agendamentroApi.domain.repository.AgendamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ProfissionalService profissionalService;
    private final ClienteService clienteService;

    @Transactional
    public Agendamento inserirAgendamento(Long profissionalId, Agendamento agendamento){
        Profissional profissional = profissionalService.buscar(profissionalId);
        Cliente cliente = clienteService.buscar(agendamento.getCliente().getId());

        agendamento.setProfissional(profissional);
        agendamento.setCliente(cliente);
        agendamento.setStatusAgendamento(StatusAgendamento.AGENDADO);
        agendamento.setDataAgendamento(OffsetDateTime.now());
        return agendamentoRepository.save(agendamento);
    }

}
