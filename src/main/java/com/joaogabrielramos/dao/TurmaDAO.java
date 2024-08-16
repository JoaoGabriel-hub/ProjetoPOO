package com.joaogabrielramos.dao;

import com.joaogabrielramos.model.Turma;

import java.util.List;

public interface TurmaDAO extends DAOGenerico <Turma>{
    List<Turma> recuperarTodosAsTurmasDeUmProfessor(int id);
    List<Turma> recuperarTodosAsTurmasDeUmaDisciplina(int id);
}
