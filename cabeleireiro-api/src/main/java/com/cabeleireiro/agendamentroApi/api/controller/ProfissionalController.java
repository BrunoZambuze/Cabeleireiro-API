package com.cabeleireiro.agendamentroApi.api.controller;

import com.cabeleireiro.agendamentroApi.domain.exception.ControllerException;
import com.cabeleireiro.agendamentroApi.domain.model.Profissional;
import com.cabeleireiro.agendamentroApi.domain.repository.ProfissionalRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/profissional")
public class ProfissionalController {

    ProfissionalRepository profissionalRepository;

    @GetMapping
    public List<Profissional> listarProfissional(){
        return profissionalRepository.findAll();
    }

    @GetMapping({"/profissionalId"})
    public ResponseEntity<Profissional> buscarProfissional(@PathVariable Long profissionalId){
        return profissionalRepository.findById(profissionalId)
                .map(p -> ResponseEntity.ok(p))
                .orElseThrow(() -> new ControllerException("Profissinal não encontrado!"));
    }



}
