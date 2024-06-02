package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Checklist {
    private List<Tarefa> tarefas;


    public Checklist() {
        this.tarefas = new ArrayList<>();
    }

    public void addTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public List<Tarefa> getTodasAsTarefas() {
        return new ArrayList<>(tarefas);
    }

    public List<Tarefa> getTodasAsTarefasConcluidas() {
        return tarefas.stream()
                .filter(Tarefa::isConcluida)
                .collect(Collectors.toList());
    }

    public List<Tarefa> getTodasAsTarefasPendentes() {
        return tarefas.stream()
                .filter(tarefa -> !tarefa.isConcluida())
                .collect(Collectors.toList());
    }

    public void alterarStatusTarefa(int id, boolean concluida) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setConcluida(concluida);
                break;
            }
        }
    }

    public List<String> getTodasAsDescricoes() {
        return tarefas.stream()
                .map(Tarefa::getDescricao)
                .collect(Collectors.toList());
    }

    public List<Tarefa> getTodasAsTarefasOrdenadasPorConclusao() {
        return tarefas.stream()
                .sorted((x1, x2) -> {
                    if (x1.isConcluida() == x2.isConcluida()) {
                        return x1.getDescricao().compareTo(x2.getDescricao());
                    } else {
                        return Boolean.compare(x2.isConcluida(), x1.isConcluida());
                    }
                })
                .collect(Collectors.toList());
    }
}

