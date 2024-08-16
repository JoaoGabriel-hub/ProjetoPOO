package com.joaogabrielramos.dao;

import com.joaogabrielramos.model.Professor;

import java.util.List;

public interface ProfessorDAO extends DAOGenerico <Professor>{
    List<Professor> recuperarProfessoresOrdenadosPorNome();
}