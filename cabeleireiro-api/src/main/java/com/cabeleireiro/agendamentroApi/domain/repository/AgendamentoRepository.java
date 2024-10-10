package com.cabeleireiro.agendamentroApi.domain.repository;

import com.cabeleireiro.agendamentroApi.domain.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
