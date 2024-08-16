package com.joaogabrielramos.dao.impl;

import com.joaogabrielramos.dao.DisciplinaDAO;
import com.joaogabrielramos.model.Disciplina;

import java.util.List;

public class DisciplinaDaoImpl extends DAOGenericoImpl<Disciplina> implements DisciplinaDAO {

    public List<Disciplina> recuperarDisciplinasOrdenadosPorNome() {
        return map.values()
                .stream()
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .toList();
    }
}
