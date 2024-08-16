package com.joaogabrielramos.model;

import java.util.ArrayList;
import java.util.List;

import com.joaogabrielramos.util.Id;

public class Disciplina {
    private int id;
    private String nome;
    private int cargaHoraria;
    private List<Turma> turmas;
    private List<Disciplina> preRequisitos;

    public Disciplina(String nome, int cargaHoraria, List<Disciplina> preRequisitos) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.turmas = new ArrayList<>();
        this.preRequisitos = preRequisitos;
    }

    public String toString() {
        return "Nome = " + nome +
                "  Carga Horaria = " + cargaHoraria;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {  return nome;  }
    public int getCargaHoraria() {  return cargaHoraria;  }
    public List<Turma> getTurmas() {  return turmas;  }

    public void setNome(String nome) {  this.nome = nome;  }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public void setTurmas(List<Turma> turmas) {  this.turmas = turmas;   }


    public List<Disciplina> getPreRequisitos() {
        return preRequisitos;
    }

    public void setPreRequisitos(List<Disciplina> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }


}
