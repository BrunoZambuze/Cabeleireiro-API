package com.cabeleireiro.agendamentroApi.domain.model;

import com.cabeleireiro.agendamentroApi.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Profissional {

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
    @Email
    private String email;

    @Column
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date data_nascimento;

    @Enumerated(EnumType.STRING)
    @NotNull
    private FuncaoProfissional funcao_profissional;

}
