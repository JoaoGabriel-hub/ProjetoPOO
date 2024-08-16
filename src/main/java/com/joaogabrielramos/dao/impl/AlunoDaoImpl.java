package com.joaogabrielramos.dao.impl;

import com.joaogabrielramos.dao.AlunoDAO;
import com.joaogabrielramos.model.Aluno;

import java.util.List;

public class AlunoDaoImpl extends DAOGenericoImpl<Aluno> implements AlunoDAO {

    public List<Aluno> recuperarAlunosOrdenadosPorNome() {
        return map.values()
                .stream()
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .toList();
    }
}