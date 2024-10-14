package com.cabeleireiro.agendamentroApi.api.controller;

import com.cabeleireiro.agendamentroApi.api.assembler.ClienteAssembler;
import com.cabeleireiro.agendamentroApi.api.assembler.ProfissionalAssembler;
import com.cabeleireiro.agendamentroApi.api.representationmodel.input.ClienteDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.ClienteDtoOutput;
import com.cabeleireiro.agendamentroApi.domain.model.Agendamento;
import com.cabeleireiro.agendamentroApi.domain.model.Cliente;
import com.cabeleireiro.agendamentroApi.domain.repository.ClienteRepository;
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
@RequestMapping("/profissional/clientes")
public class ProfissionalController {

    private final ClienteRepository clietClienteRepository;
    private final ClienteService clienteService;
    private final AgendamentoService agendamentoService;
    private final ClienteAssembler clienteAssembler;

    @GetMapping
    public List<ClienteDtoOutput> listar(){
        return clienteAssembler.toCollectionClienteOutput(clietClienteRepository.findAll());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDtoOutput> buscar(@PathVariable Long clienteId){
        return clietClienteRepository.findById(clienteId)
                .map(clienteAssembler::toClienteOutput)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDtoOutput cadastrar(@Valid @RequestBody ClienteDtoInput clienteInput){
        Cliente novoCliente = clienteAssembler.toEntity(clienteInput);
        return clienteAssembler.toClienteOutput(clienteService.cadastrarCliente(novoCliente));
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDtoOutput> atualizar(@PathVariable Long clienteId,
                                             @Valid @RequestBody ClienteDtoInput clienteInput){
        Cliente clienteAtualizado = clienteService.atualizarCliente(clienteId, clienteAssembler.toEntity(clienteInput));
        return ResponseEntity.ok().body(clienteAssembler.toClienteOutput(clienteAtualizado));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){
        clienteService.removerCliente(clienteId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/agendamentos/{profisionalId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Agendamento inserirAgendamento(@PathVariable Long profisionalId,
                                          @Valid @RequestBody Agendamento agendamento){
        return agendamentoService.inserirAgendamento(profisionalId, agendamento);
    }
}
