package com.cabeleireiro.agendamentroApi.domain.model;

import com.cabeleireiro.agendamentroApi.domain.exception.ListaVazioException;
import com.cabeleireiro.agendamentroApi.domain.repository.AdministradorRepository;
import com.cabeleireiro.agendamentroApi.domain.service.AgendamentoService;
import com.cabeleireiro.agendamentroApi.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NotNull(groups = ValidationGroups.clienteId.class)
    private Long id;

    @Column
    @NotBlank
    private String nome;

    @Column
    @NotBlank
    @NotBlank
    private String email;

    @Column
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Agendamento realizarAgendamento(Agendamento agendamento){
        agendamento.setCliente(this);
        agendamento.setDataAgendamento(OffsetDateTime.now());
        agendamento.setStatusAgendamento(StatusAgendamento.AGENDADO);
        this.getAgendamentos().add(agendamento);
        return agendamento;
    }

    public void removerAgendamento(Agendamento agendamento){
        this.getAgendamentos().remove(agendamento); //Passei o objeto completo pois a comparação é feita através do equals/hashcode, que eu configurei
                                                   //para comprar através do Id. Caso fosse necessário comparar somente pelo Id do tipo Long, teríamos que
                                                  //utilizar esse código: this.getAgendamentos().removeIf(agendamento -> agendamento.getId().equals(id));
    }



}
