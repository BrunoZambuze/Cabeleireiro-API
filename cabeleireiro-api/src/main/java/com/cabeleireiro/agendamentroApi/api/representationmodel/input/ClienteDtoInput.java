package com.cabeleireiro.agendamentroApi.api.representationmodel.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClienteDtoInput {

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

}
