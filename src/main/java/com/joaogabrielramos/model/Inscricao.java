package com.joaogabrielramos.model;

import com.joaogabrielramos.util.Id;

public class Inscricao {
    private int id;
    private int numero;
    private int nota;
    private String presenca;

    private Aluno aluno;
    private Turma turma;

    public Inscricao(int numero, int nota, String presenca, Aluno aluno, Turma turma) {
        this.nota = nota;
        this.presenca = presenca;
        this.numero = numero;
        this.aluno = aluno;
        this.turma = turma;
    }

    public String toString() {
        return "Número = " + numero +
                "   Nome = " + getAluno().getNome() +
                "   Nota = " + nota +
                "   Presença = " + presenca +
                "   Turma ano = " + getTurma().getAno() +
                "   Turma período = " + getTurma().getPeriodo() +
                "   Disciplima  = " + getTurma().getDisciplina().getNome();
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getPresenca() {
        return presenca;
    }

    public void setPresenca(String presenca) {
        this.presenca = presenca;
    }
}
