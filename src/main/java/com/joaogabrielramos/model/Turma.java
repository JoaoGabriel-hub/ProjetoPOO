package com.joaogabrielramos.model;

import java.util.ArrayList;
import java.util.List;

import com.joaogabrielramos.util.Id;

public class Turma {
    private int id;
    private int ano;
    private int periodo;
    private List<Inscricao> inscricoes;
    private Professor professor;
    private Disciplina disciplina;
    private List<Aluno> alunos = new ArrayList<>();

    public Turma(int ano, int periodo, Professor professor, Disciplina disciplina) {
        this.ano = ano;
        this.periodo = periodo;
        this.professor = professor;
        this.disciplina = disciplina;
        this.inscricoes = new ArrayList<>();
    }

    public String toString() {
        return "Turma = " + id +
                "    Ano = " + ano +
                "    Periodo = " + periodo +
                "    Professor = " + getProfessor().getNome() +
                "    Disciplina = " + getDisciplina().getNome();
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public int getAno() { return ano; }
    public int getPeriodo() { return periodo; }
    public List<Inscricao> getInscricoes() {  return inscricoes;  }
    public Professor getProfessor() {
        return professor;
    }
    public void setAno(int ano) { this.ano = ano; }
    public void setPeriodo(int periodo) { this.periodo = periodo; }
    public void setInscricoes(List<Inscricao> inscricoes) {  this.inscricoes = inscricoes;  }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Aluno> getAlunos() { return alunos; }

    public void addAluno(Aluno aluno) { this.alunos.add(aluno); }

}
