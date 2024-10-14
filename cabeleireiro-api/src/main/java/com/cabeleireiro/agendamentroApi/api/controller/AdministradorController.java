package com.cabeleireiro.agendamentroApi.api.controller;

import com.cabeleireiro.agendamentroApi.api.assembler.AdministradorAssembler;
import com.cabeleireiro.agendamentroApi.api.assembler.ProfissionalAssembler;
import com.cabeleireiro.agendamentroApi.api.representationmodel.input.AdministradorDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.input.ProfissionalDtoInput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.AdministradorDtoOutput;
import com.cabeleireiro.agendamentroApi.api.representationmodel.output.ProfissionalDtoOutput;
import com.cabeleireiro.agendamentroApi.domain.exception.ControllerException;
import com.cabeleireiro.agendamentroApi.domain.model.Administrador;
import com.cabeleireiro.agendamentroApi.domain.model.Profissional;
import com.cabeleireiro.agendamentroApi.domain.repository.AdministradorRepository;
import com.cabeleireiro.agendamentroApi.domain.repository.ProfissionalRepository;
import com.cabeleireiro.agendamentroApi.domain.service.AdministradorService;
import com.cabeleireiro.agendamentroApi.domain.service.ProfissionalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/administrador")
public class AdministradorController {

    private final AdministradorRepository administradorRepository;
    private final AdministradorService administradorService;
    private final ProfissionalService profissionalService;
    private final AdministradorAssembler administradorAssembler;
    private final ProfissionalAssembler profissionalAssembler;

    @GetMapping
    public List<AdministradorDtoOutput> listar(){
        return administradorAssembler.toColletionAdministradorOutput(administradorRepository.findAll());
    }

    @GetMapping("/{administradorId}")
    public ResponseEntity<AdministradorDtoOutput> buscar(@PathVariable Long administradorId){
        return administradorRepository.findById(administradorId)
                                      .map(administradorAssembler::toAdministradorOutput)
                                      .map(a -> ResponseEntity.ok(a))
                                      .orElseThrow(() -> new ControllerException("Nenhum administrador encontrado!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdministradorDtoOutput inserir(@Valid @RequestBody AdministradorDtoInput administradorDtoInput){
        Administrador novoAdministrador = administradorAssembler.toEntity(administradorDtoInput);
        return administradorAssembler.toAdministradorOutput(administradorService.cadastrar(novoAdministrador));
    }

    @PutMapping("/{administradorId}")
    public ResponseEntity<Administrador> atualizar(@PathVariable Long administradorId,
                                                   @RequestBody AdministradorDtoInput administradorDtoInput){
        Administrador administradorAtualizado = administradorService.atualizar(administradorId, administradorAssembler.toEntity(administradorDtoInput));
        return ResponseEntity.ok(administradorAtualizado);
    }

    @DeleteMapping("/{administradorId}")
    public ResponseEntity<Void> deletar(@PathVariable Long administradorId){
        return administradorService.deletar(administradorId);
    }

    @PostMapping("/profissional")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalDtoOutput cadastrar(@Valid @RequestBody ProfissionalDtoInput profissionalDtoInput){
        Profissional novoProfissional = profissionalAssembler.toEntity(profissionalDtoInput);
        return profissionalAssembler.toProffisionalOutput(profissionalService.cadastrarProfissional(novoProfissional));
    }

    @GetMapping("/profissional")
    public List<ProfissionalDtoOutput> listarProfissional(){
        return profissionalAssembler.toCollectionProfissionalOutput(profissionalService.listar());
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<ProfissionalDtoOutput> buscarProfissional(@PathVariable Long profissionalId){
        return ResponseEntity.ok(profissionalAssembler.toProffisionalOutput(profissionalService.buscar(profissionalId)));
    }

    @PutMapping("/profissional/{profissinalId}")
    public ResponseEntity<ProfissionalDtoOutput> atualizar(@PathVariable Long profissinalId,
                                          @Valid @RequestBody ProfissionalDtoInput profissionalInput){
        Profissional profissionalAtualizado = profissionalService.atualizar(profissinalId, profissionalAssembler.toEntity(profissionalInput));
        return ResponseEntity.ok(profissionalAssembler.toProffisionalOutput(profissionalAtualizado));
    }

    @DeleteMapping("/profissional/{profissinalId}")
    public ResponseEntity<Void> remover(@PathVariable Long profissinalId){
        profissionalService.remover(profissinalId);
        return ResponseEntity.noContent().build();
    }

}
