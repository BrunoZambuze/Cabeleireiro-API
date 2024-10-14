package com.cabeleireiro.agendamentroApi.domain.model;

import com.cabeleireiro.agendamentroApi.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "fk_id_cliente")
    @NotNull
    public Cliente cliente;

    @OneToOne
    @JoinColumn(name = "fk_id_profissional")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Profissional profissional;

    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime dataAgendamento;

    @Column
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime dataPrevista;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusAgendamento statusAgendamento;

}
