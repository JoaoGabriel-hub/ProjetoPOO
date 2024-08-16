package com.joaogabrielramos.dao;

import com.joaogabrielramos.model.Disciplina;

import java.util.List;

public interface DisciplinaDAO extends DAOGenerico <Disciplina>{
    List<Disciplina> recuperarDisciplinasOrdenadosPorNome();
}
