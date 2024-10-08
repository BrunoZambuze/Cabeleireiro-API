package com.cabeleireiro.agendamentroApi.domain.service;

import com.cabeleireiro.agendamentroApi.domain.exception.RegraNegocioException;
import com.cabeleireiro.agendamentroApi.domain.model.Profissional;
import com.cabeleireiro.agendamentroApi.domain.repository.ProfissionalRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfissionalService {

    private ProfissionalRepository profissionalRepository;

    public List<Profissional> listar(){
        return profissionalRepository.findAll();
    }

    public Profissional buscar(Long id){
        return profissionalRepository.findById(id)
                                     .orElseThrow(() -> new RegraNegocioException("Nenhum profssional encontrado com esse Id!"));
    }

    @Transactional
    public Profissional cadastrarProfissional(Profissional profissional){
        if(profissional.getId() != null){
            throw new RegraNegocioException("O profissional cadastrado não pode ter Id");
        }
        return profissionalRepository.save(profissional);
    }

    @Transactional
    public Profissional atualizar(Long id, Profissional profissional){
        this.buscar(id);
        profissional.setId(id);
        return profissionalRepository.save(profissional);
    }

    @Transactional
    public void remover(Long id){
        this.buscar(id);
        profissionalRepository.deleteById(id);
    }

}
