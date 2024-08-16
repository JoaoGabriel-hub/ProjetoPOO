package com.joaogabrielramos.service;

import com.joaogabrielramos.dao.DisciplinaDAO;
import com.joaogabrielramos.exception.DisciplinaComTurmaException;
import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.model.Disciplina;
import com.joaogabrielramos.model.Inscricao;
import com.joaogabrielramos.model.Aluno;
import com.joaogabrielramos.util.FabricaDeDaos;

import java.util.List;
public class DisciplinaService {
    private final DisciplinaDAO disciplinaDAO = FabricaDeDaos.getDAO(DisciplinaDAO.class);
    private final InscricaoService inscricaoService = new InscricaoService();

    public Disciplina incluir(Disciplina disciplina) {
        return disciplinaDAO.incluir(disciplina);
    }

    // O nome da disciplina pode ser alterado a qq momento

    public Disciplina alterar(Disciplina disciplina, String novoNome) {
        disciplina.setNome(novoNome);
        return disciplina;
    }

    // Verificar se a disciplina existe
    // Só permitir a remoção da disciplina caso ele não tenha turmas

    public Disciplina remover(int id) {
        Disciplina disciplina = recuperarDisciplinaPorId(id);
        if (disciplina == null) {
            throw new EntidadeNaoEncontradaException("Disciplina inexistente.");
        }
        if (disciplina.getTurmas().isEmpty()) {
            disciplinaDAO.remover(disciplina.getId());
        } else {
            throw new DisciplinaComTurmaException("Esta disciplina possui turmas e não pode ser removida.");
        }
        return disciplina;
    }

    public Disciplina recuperarDisciplinaPorId(int id) {
        Disciplina disciplina = disciplinaDAO.recuperarPorId(id);
        if (disciplina == null)
            throw new EntidadeNaoEncontradaException("Disciplina inexistente.");
        return disciplina;
    }

    public List<Disciplina> recuperarDisciplinas() {
        return disciplinaDAO.recuperarTodos();
    }

    public List<Disciplina> recuperarDisciplinasOrdenadosPorNome() {
        return disciplinaDAO.recuperarDisciplinasOrdenadosPorNome();
    }

    public boolean alunoCumprePreRequisitos(Aluno aluno, Disciplina disciplina) {
        List<Disciplina> preRequisitos = disciplina.getPreRequisitos();
        for (Disciplina preRequisito : preRequisitos) {
            boolean cursou = aluno.getTurmasCursadas().stream()
                    .anyMatch(turma -> turma.getDisciplina().equals(preRequisito));
            if (!cursou) {
                return false;
            }
        }
        return true;
    }
}
