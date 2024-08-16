package com.joaogabrielramos.dao.impl;

import com.joaogabrielramos.dao.ProfessorDAO;
import com.joaogabrielramos.model.Professor;

import java.util.List;

public class ProfessorDaoImpl extends DAOGenericoImpl<Professor> implements ProfessorDAO {

    public List<Professor> recuperarProfessoresOrdenadosPorNome() {
        return map.values()
                .stream()
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .toList();
    }
}
