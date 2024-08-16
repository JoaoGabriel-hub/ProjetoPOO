package com.joaogabrielramos.dao;

import com.joaogabrielramos.model.Aluno;

import java.util.List;

public interface AlunoDAO extends DAOGenerico <Aluno>{
    List<Aluno> recuperarAlunosOrdenadosPorNome();
}
