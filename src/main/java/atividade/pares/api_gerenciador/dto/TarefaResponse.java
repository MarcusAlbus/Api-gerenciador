package atividade.pares.api_gerenciador.dto;

import atividade.pares.api_gerenciador.entity.Prioridade;
import atividade.pares.api_gerenciador.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "TarefaResponse",
        description = "Usado para passar os dados das respostas"
)

public record TarefaResponse (

        @Schema(
                description = "ID",
                example = "Exemplo 1"
        )
         Integer id,
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
         Prioridade prioridade,
        @Schema(
                description = "Status",
                example = "Exemplo CONCLUIDA"
        )
         Status status

){
}
