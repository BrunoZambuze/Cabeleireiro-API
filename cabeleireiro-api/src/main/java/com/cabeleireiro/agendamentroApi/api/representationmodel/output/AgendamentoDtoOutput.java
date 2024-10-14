package com.cabeleireiro.agendamentroApi.api.representationmodel.output;

import com.cabeleireiro.agendamentroApi.domain.model.StatusAgendamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AgendamentoDtoOutput {

    private ProfissionalDtoOutput profissional;
    private ClienteDtoOutput cliente;
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime dataAgendamento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "America/Sao_Paulo")
    private OffsetDateTime dataPrevista;
    private StatusAgendamento statusAgendamento;

}
