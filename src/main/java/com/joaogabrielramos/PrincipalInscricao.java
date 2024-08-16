package com.joaogabrielramos;

import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.model.Disciplina;
import com.joaogabrielramos.model.Inscricao;
import com.joaogabrielramos.model.Aluno;
import com.joaogabrielramos.service.InscricaoService;
import com.joaogabrielramos.service.AlunoService;
import com.joaogabrielramos.model.Turma;
import com.joaogabrielramos.service.TurmaService;
import corejava.Console;

import java.util.List;

public class PrincipalInscricao {

    private final InscricaoService inscricaoService = new InscricaoService();
    private final AlunoService alunoService = new AlunoService();
    private final TurmaService turmaService = new TurmaService();

    public void principal() {

        int numero;
        int nota;
        Inscricao umInscricao;
        Aluno umAluno;
        Turma umTurma;
        int idAluno, idTurma;
        String presenca;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma inscrição");
            System.out.println("2. Remover uma inscrição");
            System.out.println("3. Listar todas as incrições");
            System.out.println("4. Listar todas inscrições de um aluno");
            System.out.println("5. Listar todas inscrições de uma turma");
            System.out.println("6. Listar todos aprovados de uma turma");
            System.out.println("7. Alterar nota de uma inscrição");
            System.out.println("8. Alterar presença de uma inscrição");
            System.out.println("9. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 9:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    numero = Console.readInt("Informe o numero de inscrição: ");
                    idAluno = Console.readInt("Informe o número do aluno: ");
                    nota = Console.readInt("Informe a nota da inscrição: ");
                    presenca = Console.readLine("Informe a presença do aluno (Suficiente/Insuficiente): ");
                    idTurma = Console.readInt("Informe o número da turma: ");
                    try {
                        umAluno = alunoService.recuperarAlunoPorId(idAluno);
                        umTurma = turmaService.recuperarTurmaPorId(idTurma);
                    }
                    catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    umInscricao = new Inscricao(numero, nota, presenca, umAluno, umTurma);

                    inscricaoService.incluir(umInscricao);
                    System.out.println("\nInscrição número " + umInscricao.getId() + " do aluno " + umInscricao.getAluno().getNome() +
                            " na turma de " + umInscricao.getTurma().getDisciplina().getNome() + " com presença "
                            + umInscricao.getPresenca() + " e nota " + umInscricao.getNota() +
                            " ministrada por " + umInscricao.getTurma().getProfessor().getNome() + " cadastrada com sucesso!");


                }
                case 2 ->    // Remover
                {
                    int id = Console.readInt("Informe o número da inscrição que você deseja remover: ");

                    try {
                        inscricaoService.remover(id);
                        System.out.println('\n' + "Inscrição removida com sucesso!");
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }

                case 3 -> {    // Listar todas as inscriçõess
                    List<Inscricao> inscricoes = inscricaoService.recuperarInscricoes();
                    for (Inscricao inscricao : inscricoes) {
                        System.out.println(inscricao);
                    }
                }

                case 4 -> {    // Listar todos as inscrições de um aluno
                    int id = Console.readInt("Informe o número do aluno: ");
                    List<Inscricao> inscricoes = inscricaoService.recuperarTodosAsInscricoesDeUmAluno(id);
                    for (Inscricao inscricao : inscricoes) {
                        System.out.println(inscricao);
                    }
                }

                case 5 -> {    // Listar todos as inscrições de uma turma
                    int id = Console.readInt("Informe o número da turma: ");
                    List<Inscricao> inscricoes = inscricaoService.recuperarTodosAsInscricoesDeUmaTurma(id);
                    for (Inscricao inscricao : inscricoes) {
                        System.out.println(inscricao);
                    }
                }

                case 6 -> {    // Listar todos as inscrições de uma turma aprovadas
                    int id = Console.readInt("Informe o número da turma: ");
                    List<Inscricao> inscricoes = inscricaoService.recuperarTodosAsInscricoesDeUmaTurmaAprovadas(id);
                    for (Inscricao inscricao : inscricoes) {
                        System.out.println(inscricao);
                    }
                }

                case 7 ->    // Alterar nota
                {
                    int id = Console.readInt("Informe o número da inscrição que você deseja alterar: ");

                    try {
                        umInscricao = inscricaoService.recuperarInscricaoPorId(id);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    int novaNota = Console.readInt("Informe a nova nota: ");
                    inscricaoService.alterarNota(umInscricao, novaNota);
                    System.out.println('\n' + "Alteração de nota efetuada com sucesso!");
                }

                case 8 ->    // Alterar presença
                {
                    int id = Console.readInt("Informe o número da inscrição que você deseja alterar: ");

                    try {
                        umInscricao = inscricaoService.recuperarInscricaoPorId(id);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    String novaPresenca = Console.readLine("Informe a situação da presença do aluno: ");
                    inscricaoService.alterarPresenca(umInscricao, novaPresenca);
                    System.out.println('\n' + "Alteração de presença efetuada com sucesso!");
                }

                case 9 ->    // Sair
                        continua = false;

                default -> System.out.println('\n' + "Opção inválida!");

                }
            }
        }
    }

