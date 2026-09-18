package atividade.pares.api_gerenciador.dto;

import atividade.pares.api_gerenciador.entity.Prioridade;

public record TarefaCreateRequest(

     String titulo,
     String descricao,
     Prioridade prioridade

){

}
