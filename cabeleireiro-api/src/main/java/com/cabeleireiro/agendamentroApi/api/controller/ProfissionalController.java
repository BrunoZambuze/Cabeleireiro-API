package com.cabeleireiro.agendamentroApi.api.controller;

import com.cabeleireiro.agendamentroApi.domain.model.Cliente;
import com.cabeleireiro.agendamentroApi.domain.repository.ClienteRepository;
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

    @GetMapping
    public List<Cliente> listar(){
        return clietClienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        return clietClienteRepository.findById(clienteId)
                .map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastrar(@Valid @RequestBody Cliente cliente){
        return clienteService.cadastrarCliente(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,
                                             @Valid @RequestBody Cliente cliente){
        Cliente clienteAtualizado = clienteService.atualizarCliente(clienteId, cliente);
        return ResponseEntity.ok().body(clienteAtualizado);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){
        clienteService.removerCliente(clienteId);
        return ResponseEntity.noContent().build();
    }

}
