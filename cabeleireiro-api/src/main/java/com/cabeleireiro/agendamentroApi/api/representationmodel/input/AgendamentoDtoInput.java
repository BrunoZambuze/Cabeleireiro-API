package com.cabeleireiro.agendamentroApi.api.representationmodel.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AgendamentoDtoInput {

    @NotNull
    @Valid
    private ClienteIdInput cliente;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "America/Sao_Paulo")
    private OffsetDateTime dataPrevista;

}
