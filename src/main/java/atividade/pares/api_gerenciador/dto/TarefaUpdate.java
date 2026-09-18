package atividade.pares.api_gerenciador.dto;

import atividade.pares.api_gerenciador.entity.Prioridade;
import atividade.pares.api_gerenciador.entity.Status;

public record TarefaUpdate (

        String titulo,
        String descricao,
        Prioridade prioridade,
        Status status
){
}
