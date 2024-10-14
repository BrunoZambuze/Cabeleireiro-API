package com.cabeleireiro.agendamentroApi.api.representationmodel.input;

import com.cabeleireiro.agendamentroApi.domain.model.FuncaoProfissional;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProfissionalDtoInput {

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
