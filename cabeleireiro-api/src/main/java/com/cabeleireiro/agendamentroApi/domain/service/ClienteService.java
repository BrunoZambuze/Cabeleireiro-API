package com.cabeleireiro.agendamentroApi.domain.service;

import com.cabeleireiro.agendamentroApi.domain.exception.RegraNegocioException;
import com.cabeleireiro.agendamentroApi.domain.model.Cliente;
import com.cabeleireiro.agendamentroApi.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private Cliente buscar(Long id){
        return clienteRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException("Nenhum cliente encontrado com esse Id"));
    }

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente){
        if(cliente.getId() != null){
            throw new RegraNegocioException("O cliente cadastrado não pode ter Id");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizarCliente(Long id, Cliente cliente){
        this.buscar(id);
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void removerCliente(Long id){
        this.buscar(id);
        clienteRepository.deleteById(id);
    }

}
