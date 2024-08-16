package com.joaogabrielramos;

import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.model.Disciplina;
import com.joaogabrielramos.model.Turma;
import com.joaogabrielramos.model.Professor;
import com.joaogabrielramos.model.Aluno;
import com.joaogabrielramos.model.Inscricao;
import com.joaogabrielramos.service.*;
import corejava.Console;

import java.util.List;

public class PrincipalTurma {
    private final TurmaService turmaService = new TurmaService();
    private final ProfessorService professorService = new ProfessorService();
    private final DisciplinaService disciplinaService = new DisciplinaService();
    private final AlunoService alunoService = new AlunoService();

    private final InscricaoService inscricaoService = new InscricaoService();

    public void principal() {

        int ano;
        int periodo;
        Turma umTurma;
        Professor umProfessor;
        Disciplina umDisciplina;
        Aluno umAluno;
        int idProfessor, idDisciplina, idAluno;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma turma");
            System.out.println("2. Remover uma turma");
            System.out.println("3. Listar todas as turmas");
            System.out.println("4. Listar todas turmas de um professor");
            System.out.println("5. Listar todas turmas de uma disciplina");
            System.out.println("6. Verifica se aluno pode ou não ser inscrito em uma turma");
            System.out.println("7. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 7:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    ano = Console.readInt("Informe o ano da turma: ");
                    periodo = Console.readInt("Informe o periodo da turma: ");
                    idProfessor = Console.readInt("Informe o número do professor: ");
                    idDisciplina = Console.readInt("Informe o número da disciplina: ");
                    try {
                        umProfessor = professorService.recuperarProfessorPorId(idProfessor);
                        umDisciplina = disciplinaService.recuperarDisciplinaPorId(idDisciplina);
                    }
                    catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    umTurma = new Turma(ano, periodo, umProfessor, umDisciplina);
                    turmaService.incluir(umTurma);
                    System.out.println("\nTurma numero " + umTurma.getId() + " de " + umTurma.getDisciplina().getNome()  + " do ano " + umTurma.getAno() + " e período " + umTurma.getPeriodo() + " cadastrada com sucesso!");

                }
                case 2 ->    // Remover
                {
                    int id = Console.readInt("Informe o número da turma que você deseja remover: ");

                    try {
                        turmaService.remover(id);
                        System.out.println('\n' + "Turma removida com sucesso!");
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }

                case 3 -> {    // Listar todas as inscrições
                    List<Turma> turmas = turmaService.recuperarTurmas();
                    for (Turma turma : turmas) {
                        System.out.println(turma);
                    }
                }

                case 4 -> {    // Listar todos as turmas de um professor
                    int id = Console.readInt("Informe o número do professor: ");
                    List<Turma> turmas = turmaService.recuperarTodosAsTurmasDeUmProfessor(id);
                    for (Turma turma : turmas) {
                        System.out.println(turma);
                    }
                }

                case 5 -> {    // Listar todos as turmas de uma disciplina
                    int id = Console.readInt("Informe o número da disciplina: ");
                    List<Turma> turmas = turmaService.recuperarTodosAsTurmasDeUmaDisciplina(id);
                    for (Turma turma : turmas) {
                        System.out.println(turma);
                    }
                }

                case 6 -> {    // Inscrever aluno em uma turma
                    int idTurma = Console.readInt("Informe o número da turma: ");
                    idAluno = Console.readInt("Informe o número do aluno: ");
                    try {
                        umTurma = turmaService.recuperarTurmaPorId(idTurma);
                        umAluno = alunoService.recuperarAlunoPorId(idAluno);

                        if (turmaService.inscreverAlunoNaTurma(umAluno, umTurma)) {
                            System.out.println("Aluno cursou os pré-requisitos e poderá ser inscrito na turma " + umTurma.getId() + '\n' +
                                    "Para inscrever um aluno em uma turma, acesse suas inscrições. ");

                        } else {
                            System.out.println("Não será possível inscrever o aluno na turma " + umTurma.getId());
                        }
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 7 ->    // Sair
                        continua = false;

                default -> System.out.println('\n' + "Opção inválida!");

            }

        }
    }
}



