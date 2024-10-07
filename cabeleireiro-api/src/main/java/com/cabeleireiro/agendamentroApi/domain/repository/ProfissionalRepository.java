package com.cabeleireiro.agendamentroApi.domain.repository;

import com.cabeleireiro.agendamentroApi.domain.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
