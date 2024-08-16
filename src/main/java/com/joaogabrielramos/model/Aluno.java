package com.joaogabrielramos.model;

import com.joaogabrielramos.util.Id;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private Integer id;
    private String nome;
    private String email;
    private List<Inscricao> inscricoes;
    private List<Turma> turmasCursadas = new ArrayList<>();

    public Aluno(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.inscricoes = new ArrayList<>();
    }

    public String toString() {
        return "Número = " + id +
                "  Nome = " + nome +
                "  Email = " + email;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {  return nome;  }
    public String getEmail() {  return email;  }
    public List<Inscricao> getInscricoes() {  return inscricoes;  }

    public void setNome(String nome) {  this.nome = nome;  }
    public void setEmail(String email) { this.email = email; }
    public void setInscricoes(List<Inscricao> inscricoes) {  this.inscricoes = inscricoes;  }

    public List<Turma> getTurmasCursadas() {
        return turmasCursadas;
    }

    public void addTurmaCursada(Turma turma) {
        this.turmasCursadas.add(turma);
    }

}
