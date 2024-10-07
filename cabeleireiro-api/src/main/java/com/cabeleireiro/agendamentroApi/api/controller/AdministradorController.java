package com.cabeleireiro.agendamentroApi.api.controller;

import com.cabeleireiro.agendamentroApi.domain.exception.ControllerException;
import com.cabeleireiro.agendamentroApi.domain.model.Administrador;
import com.cabeleireiro.agendamentroApi.domain.repository.AdministradorRepository;
import com.cabeleireiro.agendamentroApi.domain.repository.ProfissionalRepository;
import com.cabeleireiro.agendamentroApi.domain.service.AdministradorService;
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

    private AdministradorRepository administradorRepository;
    private AdministradorService administradorService;

    @GetMapping
    public List<Administrador> listar(){
        return administradorRepository.findAll();
    }

    @GetMapping("/{administradorId}")
    public ResponseEntity<Administrador> buscar(@PathVariable Long administradorId){
        return administradorRepository.findById(administradorId)
                                      .map(a -> ResponseEntity.ok(a))
                                      .orElseThrow(() -> new ControllerException("Nenhum administrador encontrado!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Administrador inserir(@Valid @RequestBody Administrador administrador){
        return administradorService.cadastrar(administrador);
    }

    @PutMapping("/{administradorId}")
    public ResponseEntity<Administrador> atualizar(@PathVariable Long administradorId,
                                                   @RequestBody Administrador administrador){
        Administrador adm = administradorService.atualizar(administradorId, administrador);
        return ResponseEntity.ok(adm);
    }

    @DeleteMapping("/{administradorId}")
    public ResponseEntity<Void> deletar(@PathVariable Long administradorId){
        return administradorService.deletar(administradorId);
    }

    @PostMapping("/profissional")
    /*
    DESENVOLVER LÓGICA PARA O ADMNISTRADOR PODER ADICIONAR UM PROFISSIONAL
     */

}
