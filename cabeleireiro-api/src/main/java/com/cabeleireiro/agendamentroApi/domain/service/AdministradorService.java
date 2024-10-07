package com.cabeleireiro.agendamentroApi.domain.service;

import com.cabeleireiro.agendamentroApi.domain.exception.RegraNegocioException;
import com.cabeleireiro.agendamentroApi.domain.model.Administrador;
import com.cabeleireiro.agendamentroApi.domain.repository.AdministradorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdministradorService {

    private AdministradorRepository administradorRepository;

    @Transactional
    public Administrador cadastrar(Administrador administrador){
        if(administradorRepository.findByNome(administrador.getNome()).isPresent()){
           throw new RegraNegocioException("Nome já em uso por um administrador");
        }
        return administradorRepository.save(administrador);
    }

    @Transactional
    public Administrador atualizar(Long id, Administrador administrador){
        if(!administradorRepository.findById(id).isPresent()){
            throw new RegraNegocioException("Administrador não cadastrado. Não foi possível atualizar os dados!");
        }
        administrador.setId(id);
        return administradorRepository.save(administrador);
    }

    @Transactional
    public ResponseEntity<Void> deletar(Long id){
        if(!administradorRepository.findById(id).isPresent()){
            throw new RegraNegocioException("Id não existente!");
        }
        administradorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
