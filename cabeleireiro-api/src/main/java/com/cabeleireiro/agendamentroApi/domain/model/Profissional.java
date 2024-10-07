package com.cabeleireiro.agendamentroApi.domain.model;

import jakarta.persistence.*;
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
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    private FuncaoProfissional funcaoProfissional;

}
