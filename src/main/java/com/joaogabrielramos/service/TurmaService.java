package com.joaogabrielramos.service;

import com.joaogabrielramos.dao.TurmaDAO;
import com.joaogabrielramos.dao.impl.TurmaDaoImpl;
import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.model.Turma;
import com.joaogabrielramos.model.Aluno;
import com.joaogabrielramos.model.Inscricao;
import com.joaogabrielramos.util.FabricaDeDaos;

import java.util.List;

public class TurmaService {
    private final TurmaDAO turmaDAO = FabricaDeDaos.getDAO(TurmaDAO.class);
    private final DisciplinaService disciplinaService = new DisciplinaService();
    private final InscricaoService inscricaoService = new InscricaoService();

    public Turma incluir(Turma turma) {
        turmaDAO.incluir(turma);
        turma.getProfessor().getTurmas().add(turma);
        turma.getDisciplina().getTurmas().add(turma);
        return turma;
    }

    public Turma remover(int id) {
        Turma turma = this.recuperarTurmaPorId(id);

        List<Inscricao> inscricoes = inscricaoService.recuperarTodosAsInscricoesDeUmaTurma(turma.getId());
        for (Inscricao inscricao : inscricoes) {
            inscricaoService.remover(inscricao.getId());
        }

        turma.getProfessor().getTurmas().remove(turma);
        turma.getDisciplina().getTurmas().remove(turma);
        turmaDAO.remover(turma.getId());
        return turma;
    }

    // Deve retornar um erro caso a inscrição não seja encontrada.
    public Turma recuperarTurmaPorId(int id) {
        Turma turma = turmaDAO.recuperarPorId(id);
        if (turma == null) {
            throw new EntidadeNaoEncontradaException("Turma inexistente."); }
        return turma;
    }

    public List<Turma> recuperarTurmas() {
        return turmaDAO.recuperarTodos();
    }

    public List<Turma> recuperarTodosAsTurmasDeUmProfessor(int id) {
        return turmaDAO.recuperarTodosAsTurmasDeUmProfessor(id);
    }

    public List<Turma> recuperarTodosAsTurmasDeUmaDisciplina(int id) {
        return turmaDAO.recuperarTodosAsTurmasDeUmaDisciplina(id);
    }

    public boolean inscreverAlunoNaTurma(Aluno aluno, Turma turma) {
        if (disciplinaService.alunoCumprePreRequisitos(aluno, turma.getDisciplina())) {
            turma.addAluno(aluno);
            aluno.addTurmaCursada(turma);
            /*turmaDAO.incluir(turma);*/
            return true;
        } else {
            System.out.println("Aluno não cumpre os pré-requisitos para se inscrever nesta turma.");
            return false;
        }
    }
}

