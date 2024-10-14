package com.cabeleireiro.agendamentroApi.api.representationmodel.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoId {

    @NotNull
    private Long id;

}
