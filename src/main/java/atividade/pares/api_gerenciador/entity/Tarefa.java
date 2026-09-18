package atividade.pares.api_gerenciador.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity responsavel por representar uma tabela do banco
 *
 * <p>Esta classe representa os atributos de uma tabela do banco de dados,
 * onde cada variavel seria um atributo da tabela.</p>
 *
 * @author Lucas
 * @since 1.0
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    private Integer id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private Status status;

}
