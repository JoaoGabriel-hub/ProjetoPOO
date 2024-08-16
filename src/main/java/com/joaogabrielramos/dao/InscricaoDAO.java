package com.joaogabrielramos.dao;

import com.joaogabrielramos.model.Inscricao;
import com.joaogabrielramos.model.Aluno;
import com.joaogabrielramos.model.Turma;

import java.util.List;

public interface InscricaoDAO  extends DAOGenerico<Inscricao> {
    List<Inscricao> recuperarTodosAsInscricoesDeUmAluno(int id);
    List<Inscricao> recuperarTodosAsInscricoesDeUmaTurma(int id);
    List<Inscricao> recuperarTodosAsInscricoesDeUmaTurmaAprovadas(int id);
}
