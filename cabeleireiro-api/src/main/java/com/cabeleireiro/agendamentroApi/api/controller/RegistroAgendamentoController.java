package com.cabeleireiro.agendamentroApi.api.controller;

import com.cabeleireiro.agendamentroApi.api.assembler.AgendamentoAssembler;
import com.cabeleireiro.agendamentroApi.api.representationmodel.input.AgendamentoDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.input.AgendamentoId;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.AgendamentoDtoOutput;
import com.cabeleireiro.agendamentroApi.domain.model.Cliente;
import com.cabeleireiro.agendamentroApi.domain.service.AgendamentoService;
import com.cabeleireiro.agendamentroApi.domain.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("profissional/")
public class RegistroAgendamentoController {

    private final AgendamentoService agendamentoService;
    private final AgendamentoAssembler agendamentoAssembler;
    private final ClienteService clienteService;

    @PostMapping("{profissionalId}/clientes/agendamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoDtoOutput inserirAgendamento(@PathVariable Long profissionalId,
                                                   @Valid @RequestBody AgendamentoDtoInput agendamentoInput){
        return agendamentoService.inserirAgendamento(profissionalId, agendamentoInput);
    }

    @GetMapping("/clientes/{clienteId}/agendamentos")
    public List<AgendamentoDtoOutput> listarAgendamentos(@PathVariable Long clienteId){
        Cliente cliente = clienteService.buscar(clienteId);
        return agendamentoAssembler.toCollectionAgendamentoOutput(cliente.getAgendamentos());
    }

    @DeleteMapping("/clientes/{clienteId}/agendamentos")
    public ResponseEntity<Void> removerAgendamento(@PathVariable Long clienteId,
                                                   @RequestBody @Valid AgendamentoId agendamentoId){
        agendamentoService.removerAgendamento(clienteId, agendamentoId.getId());
        return ResponseEntity.noContent().build();
    }

}
