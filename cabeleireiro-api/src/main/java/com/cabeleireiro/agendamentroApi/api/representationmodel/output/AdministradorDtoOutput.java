package com.cabeleireiro.agendamentroApi.api.representationmodel.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdministradorDtoOutput {

    private Long id;
    private String nome;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataNascimento;

}
