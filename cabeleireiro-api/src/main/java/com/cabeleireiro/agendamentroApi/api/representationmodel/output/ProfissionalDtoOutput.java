package com.cabeleireiro.agendamentroApi.api.representationmodel.output;

import com.cabeleireiro.agendamentroApi.domain.model.FuncaoProfissional;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProfissionalDtoOutput {

    private Long id;
    private String nome;
    private FuncaoProfissional funcao_profissional;

}
