package com.joaogabrielramos.dao.impl;

import com.joaogabrielramos.dao.InscricaoDAO;
import com.joaogabrielramos.model.Inscricao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class InscricaoDaoImpl extends DAOGenericoImpl<Inscricao> implements InscricaoDAO {

    public List<Inscricao> recuperarTodosAsInscricoesDeUmAluno(int id) {
        return map.values()
                .stream()
                .filter((inscricao) -> inscricao.getAluno().getId() == id)
                .toList();
    }

    public List<Inscricao> recuperarTodosAsInscricoesDeUmaTurma(int id) {
        return map.values()
                .stream()
                .filter((inscricao) -> inscricao.getTurma().getId() == id)
                .toList();
    }

    public List<Inscricao> recuperarTodosAsInscricoesDeUmaTurmaAprovadas(int id) {
        return map.values()
                .stream()
                .filter((inscricao) -> inscricao.getNota() >= 6)
                .filter((inscricao -> inscricao.getPresenca().equals("Suficiente")))
                .filter((inscricao) -> inscricao.getTurma().getId() == id)
                .toList();
    }
}
