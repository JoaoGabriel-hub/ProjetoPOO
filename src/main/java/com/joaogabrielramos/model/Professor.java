package com.joaogabrielramos.model;

import com.joaogabrielramos.util.Id;

import java.util.ArrayList;
import java.util.List;

public class Professor {
    private Integer id;
    private String nome;
    private String email;
    private List<Turma> turmas;

    public Professor(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.turmas = new ArrayList<>();
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
    public List<Turma> getTurmas() {  return turmas;  }
    public void setNome(String nome) {  this.nome = nome;  }
    public void setEmail(String email) { this.email = email; }
    public void setTurmas(List<Turma> turmas) {  this.turmas = turmas;  }
}
