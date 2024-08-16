package com.joaogabrielramos.dao.impl;

import com.joaogabrielramos.dao.TurmaDAO;
import com.joaogabrielramos.model.Turma;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TurmaDaoImpl extends DAOGenericoImpl<Turma> implements TurmaDAO {

    public List<Turma> recuperarTodosAsTurmasDeUmProfessor(int id){
        return map.values()
                .stream()
                .filter((turma) -> turma.getProfessor().getId() == id)
                .toList();
    }

    public List<Turma> recuperarTodosAsTurmasDeUmaDisciplina(int id){
        return map.values()
                .stream()
                .filter((turma) -> turma.getDisciplina().getId() == id)
                .toList();
    }
}