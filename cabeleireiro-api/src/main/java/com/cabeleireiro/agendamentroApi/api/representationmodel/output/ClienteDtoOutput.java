package com.cabeleireiro.agendamentroApi.api.representationmodel.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClienteDtoOutput {

    private Long id;
    private String nome;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataNascimento;

}
