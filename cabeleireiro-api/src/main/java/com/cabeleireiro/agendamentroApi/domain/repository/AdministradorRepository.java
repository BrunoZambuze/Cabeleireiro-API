package com.cabeleireiro.agendamentroApi.domain.repository;

import com.cabeleireiro.agendamentroApi.domain.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByNome(String nome);

}
