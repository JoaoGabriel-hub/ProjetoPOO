package com.joaogabrielramos.service;

import com.joaogabrielramos.dao.InscricaoDAO;
import com.joaogabrielramos.dao.impl.InscricaoDaoImpl;
import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.model.Disciplina;
import com.joaogabrielramos.model.Inscricao;
import com.joaogabrielramos.util.FabricaDeDaos;

import java.util.List;

public class InscricaoService {

    private final InscricaoDAO inscricaoDAO = FabricaDeDaos.getDAO(InscricaoDAO.class);

    public Inscricao incluir(Inscricao inscricao) {
        inscricaoDAO.incluir(inscricao);
        inscricao.getAluno().getInscricoes().add(inscricao);
        return inscricao;
    }

    public Inscricao alterarNota(Inscricao inscricao, int novaNota) {
        inscricao.setNota(novaNota);
        return inscricao;
    }

    public Inscricao alterarPresenca(Inscricao inscricao, String novaPresenca) {
        inscricao.setPresenca(novaPresenca);
        return inscricao;
    }

    public Inscricao remover(int id) {
        Inscricao inscricao = this.recuperarInscricaoPorId(id);
        inscricao.getAluno().getInscricoes().remove(inscricao);
        inscricaoDAO.remover(id);
        return inscricao;
    }

    // Deve retornar um erro caso a inscrição não seja encontrada.
    public Inscricao recuperarInscricaoPorId(int id) {
        Inscricao inscricao = inscricaoDAO.recuperarPorId(id);
        if (inscricao == null)
            throw new EntidadeNaoEncontradaException("Inscrição inexistente.");
        return inscricao;
    }

    public List<Inscricao> recuperarInscricoes() {
        return inscricaoDAO.recuperarTodos();
    }

    public List<Inscricao> recuperarTodosAsInscricoesDeUmAluno(int id) {
        return inscricaoDAO.recuperarTodosAsInscricoesDeUmAluno(id);
    }

    public List<Inscricao> recuperarTodosAsInscricoesDeUmaTurma(int id) {
        return inscricaoDAO.recuperarTodosAsInscricoesDeUmaTurma(id);
    }

    public List<Inscricao> recuperarTodosAsInscricoesDeUmaTurmaAprovadas(int id) {
        return inscricaoDAO.recuperarTodosAsInscricoesDeUmaTurmaAprovadas(id);
    }
}


