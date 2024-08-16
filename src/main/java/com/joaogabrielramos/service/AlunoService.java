package com.joaogabrielramos.service;

import com.joaogabrielramos.dao.AlunoDAO;
import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.exception.AlunoComInscricaoException;
import com.joaogabrielramos.model.Aluno;
import com.joaogabrielramos.util.FabricaDeDaos;

import java.util.List;

public class AlunoService {

    private final AlunoDAO alunoDAO = FabricaDeDaos.getDAO(AlunoDAO.class);

    public Aluno incluir(Aluno aluno) {
        return alunoDAO.incluir(aluno);
    }


    // O nome do produto pode ser alterado a qq momento
    public Aluno alterar(Aluno aluno, String novoNome) {
        aluno.setNome(novoNome);
        return aluno;
    }


    // Verificar se o aluno existe
    // Só permitir a remoção do aluno caso ele não tenha inscriçoes
    public Aluno remover(int id) {
        Aluno aluno = recuperarAlunoPorId(id);
        if (aluno == null) {
            throw new EntidadeNaoEncontradaException("Aluno inexistente.");
        }
        if (aluno.getInscricoes().isEmpty()) {
            alunoDAO.remover(aluno.getId());
        } else {
            throw new AlunoComInscricaoException(
                    "Este aluno possui inscrições e não pode ser removido.");
        }
        return aluno;
    }

    public Aluno recuperarAlunoPorId(int id) {
        Aluno aluno = alunoDAO.recuperarPorId(id);
        if (aluno == null)
            throw new EntidadeNaoEncontradaException("Aluno inexistente.");
        return aluno;
    }

    public List<Aluno> recuperarAlunos() {
        return alunoDAO.recuperarTodos();
    }

    public List<Aluno> recuperarAlunosOrdenadosPorNome() {
        return alunoDAO.recuperarAlunosOrdenadosPorNome();
    }
}
