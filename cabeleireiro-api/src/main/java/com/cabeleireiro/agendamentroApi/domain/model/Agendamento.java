package com.cabeleireiro.agendamentroApi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /*
    Verificar a validação dos atributos estrangeiro em relação aos ID's que são obrigatórios
     */

    @OneToOne
    @JoinColumn(name = "fk_id_cliente")
    public Cliente cliente;

    @OneToOne
    @JoinColumn(name = "fk_id_profissional")
    public Profissional profissional;

    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataAgendamento;

    @Column
    private OffsetDateTime dataPrevista;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusAgendamento statusAgendamento;

}
