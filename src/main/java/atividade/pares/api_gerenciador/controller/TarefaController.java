package atividade.pares.api_gerenciador.controller;


import atividade.pares.api_gerenciador.entity.Status;
import atividade.pares.api_gerenciador.entity.Tarefa;
import atividade.pares.api_gerenciador.repository.TarefaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Tag(
    name = "Tarefas",
    description = "Endpoints para cadastro, consulta, atualização e exclusão de tarefas"
)

/**
 * Controller responsável pelos endpoints relacionados as tarefas.
 *
 * <p>Esta classe disponibiliza operações HTTP para listar, consultar,
 * cadastrar, atualizar e remover tarefas.</p>
 *
 * @author Lucas
 * @since 1.0
 */

@RestController
@RequestMapping("api/v1/tarefa")
public class TarefaController {

    private final TarefaRepository tarefas = new TarefaRepository();
    private final AtomicInteger sequencialId = new AtomicInteger(1);

    /**
     * Lista Tarefas cadastradas
     *
     * <p>Lista todas as tarefas </p>
     * @return
     */


    @Operation(
            summary = "Lista de tarefas",
            description = "Retorna todas as tarefas criadas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarefa encontrada"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada"
    )
    @GetMapping("/lista")
    public ResponseEntity<Map<Integer, Tarefa>> listar(){
    return ResponseEntity.ok(tarefas.getTarefas());
    }

    /**
     * Lista Tarefas cadastradas
     *
     * <p>Quando a categoria Id é informada, somente os produtos pertencentes
     * à categoria são retornados.</p>
     *
     * @return resposta HTTP contendo a entidade do id encontrado
     */

    @Operation(
            summary = "Lista tarefa por id",
            description = "Retorna a tarefa criada pelo id buscado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarefa encontrada"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa>buscarPorId(@PathVariable int id){
        for( Tarefa tarefa : tarefas.getTarefas().values()){
            if(tarefa.getId().equals(id)){
                return ResponseEntity.ok(tarefa);
            }
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Lista Tarefas cadastradas
     *
     * <p>Quando a categoria de status é informada, somente os produtos pertencentes
     * à categoria são retornados.</p>
     *
     * @param status categoria utilizada como filtro.
     * @return resposta HTTP contendo a lista das entidades com o status solicitado
     */

    @Operation(
            summary = "Lista tarefa por id",
            description = "Retorna a tarefa criada pelo id buscado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarefa encontrada"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada"
    )

    @GetMapping
    public ResponseEntity<List<Tarefa>> buscarPorStatus(@RequestParam(required = false) Status status){

       List <Tarefa> listaStatus = new ArrayList<>();

        for( Tarefa tarefa : tarefas.getTarefas().values()){
            if(tarefa.getStatus().equals(status)){
              listaStatus.add(tarefa);
            }
        }


        return ResponseEntity.ok().body(listaStatus);
    }

    /**
     * cadastra tarefas
     *
     * <p>Cadastra uma tarefa nova</p>
     *
     * @return Retorna o objeto que foi criado
     */

    @Operation(
            summary = "Cria uma tarefa",
            description = "Cria uma tarefa e gera automaticamente o id"
    )
    @ApiResponses({
    @ApiResponse(
            responseCode = "201",
            description = "Tarefa criada com sucesso"
    ),
    @ApiResponse(
            responseCode = "404",
            description = "Dados Inválidos"
    )
})

    @PostMapping
    public ResponseEntity<Tarefa> cadastrar(@RequestBody Tarefa tarefa){
    tarefa.setId(sequencialId.getAndIncrement());
    tarefas.postTarefa(tarefa);
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(tarefa);
    }

    /**
     * atuliza tarefas
     *
     * <p>Atualiza uma tarefa</p>
     *
     * @return Retorna o objeto atualizado
     */

    @Operation(
            summary = "Atualiza uma tarefa",
            description = "Atualiza completamente os de uma tarefa existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Tarefa atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarefa não encontrada"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(
            @PathVariable int id,@RequestBody Tarefa tarefaAtualizada
    ){

        return ResponseEntity.ok().body(tarefas.updateTarefa(id, tarefaAtualizada));
    }

    /**
     * atuliza status
     *
     * <p>Atualiza o status de uma tarefa</p>
     *
     * @return Retorna o objeto com o status atualizado
     */

    @Operation(
            summary = "Atualiza uma tarefa",
            description = "Atualiza somente os atributos informados na requisição"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Tarefa atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarefa não encontrada"
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarStatus(
            @PathVariable int id
){
        return ResponseEntity.ok().body(tarefas.patchTarefa(id));
}

    /**
     * deleta tarefas
     *
     * <p>Deleta uma tarefa</p>
     *
     */

    @Operation(
            summary = "Remove uma tarefa",
            description = "Remove uma tarefa utilizando seu id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Tarefa removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarefa não encontrada"
            )
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id){
        boolean removido =tarefas.deleteTarefa(id);

        return ResponseEntity.ok("removido " + id);
    }

}
