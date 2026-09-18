package atividade.pares.api_gerenciador.dto;

import atividade.pares.api_gerenciador.entity.Prioridade;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "TarefaUpdate",
        description = "Usado para passar os dados usados nas criações"
)

public record TarefaCreateRequest(
    @Schema(
            description = "Titulo",
            example = "Exemplo titulo fazer arroz"
    )
     String titulo,

    @Schema(
            description = "Descrição",
            example = "Exemplo fazendo arroz"
    )
     String descricao,
    @Schema(
            description = "Prioridade",
            example = "Exemplo Urgente"
    )
     Prioridade prioridade

){

}
