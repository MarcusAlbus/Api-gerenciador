package atividade.pares.api_gerenciador.repository;

import atividade.pares.api_gerenciador.entity.Status;
import atividade.pares.api_gerenciador.entity.Tarefa;

import java.util.HashMap;
import java.util.Map;

public class TarefaRepository{

    private Map<Integer,Tarefa> tarefas;

    public TarefaRepository(){

        tarefas = new HashMap<Integer, Tarefa>();
    }

    public Map<Integer ,Tarefa> getTarefas() {
        return tarefas;
    }

    public Tarefa getTarefabyId(int id){
        for(Tarefa tarefa : tarefas.values()){
            if(tarefa.getId() == id){
                return tarefa;
            }
        }
        throw new RuntimeException();
    }

    public void postTarefa(Tarefa tarefa){
        tarefas.put(tarefa.getId(), tarefa);
    }

    public Tarefa updateTarefa(int id, Tarefa tarefaAtualizada){
        Tarefa tarefa = getTarefabyId(id);
        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
        tarefa.setStatus(tarefaAtualizada.getStatus());
        return  tarefa;
    }

    public Tarefa patchTarefa(int id){
        Tarefa tarefa = getTarefabyId(id);
        tarefa.setStatus(Status.CONCLUIDA);
        return tarefa;
    }

    public boolean deleteTarefa(int id){
        tarefas.remove(id);
        return false;
    }
}
