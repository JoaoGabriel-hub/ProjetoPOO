package com.joaogabrielramos.service;

import com.joaogabrielramos.dao.ProfessorDAO;
import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.exception.ProfessorComTurmaException;
import com.joaogabrielramos.model.Professor;
import com.joaogabrielramos.util.FabricaDeDaos;

import java.util.List;

public class ProfessorService {
    private final ProfessorDAO professorDAO = FabricaDeDaos.getDAO(ProfessorDAO.class);

    public Professor incluir(Professor professor) {
        return professorDAO.incluir(professor);
    }

    // O nome do professor pode ser alterado a qq momento
    public Professor alterar(Professor professor, String novoNome) {
        professor.setNome(novoNome);
        return professor;
    }

    // Verificar se o professor existe
    // Só permitir a remoção do professor caso ele não tenha turmas

    public Professor remover(int id) {
        Professor professor = recuperarProfessorPorId(id);
        if (professor == null) {
            throw new EntidadeNaoEncontradaException("Professor inexistente.");
        }
        if (professor.getTurmas().isEmpty()) {
            professorDAO.remover(professor.getId());
        } else {
            throw new ProfessorComTurmaException("Este professor possui turmas e não pode ser removido.");
        }
        return professor;
    }

    public Professor recuperarProfessorPorId(int id) {
        Professor professor = professorDAO.recuperarPorId(id);
        if (professor == null)
            throw new EntidadeNaoEncontradaException("Professor inexistente.");
        return professor;
    }

    public List<Professor> recuperarProfessores() {
        return professorDAO.recuperarTodos();
    }

    public List<Professor> recuperarProfessoresOrdenadosPorNome() {
        return professorDAO.recuperarProfessoresOrdenadosPorNome();
    }
}

