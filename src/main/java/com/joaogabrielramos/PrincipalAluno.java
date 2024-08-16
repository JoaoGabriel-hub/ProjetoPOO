package com.joaogabrielramos;

import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.exception.AlunoComInscricaoException;
import com.joaogabrielramos.model.Aluno;
import com.joaogabrielramos.service.AlunoService;
import corejava.Console;

import java.util.List;

public class PrincipalAluno {

    private final AlunoService alunoService = new AlunoService();

    public void principal() {

        String nome;
        String email;
        Aluno umAluno;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um aluno");
            System.out.println("2. Alterar um aluno");
            System.out.println("3. Remover um aluno");
            System.out.println("4. Listar todos alunos");
            System.out.println("5. Listar todos alunos ordenados por nome");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine("Informe o nome do aluno: ");
                    email = Console.readLine("Informe o email do aluno: ");
                    umAluno = new Aluno(nome, email);
                    alunoService.incluir(umAluno);
                    System.out.println("\nAluno número " + umAluno.getId() + " e nome " + umAluno.getNome() + " cadastrado com sucesso!");
                }
                case 2 ->    // Alterar
                {
                    int id = Console.readInt("Informe o número do aluno que você deseja alterar: ");

                    try {
                        umAluno = alunoService.recuperarAlunoPorId(id);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    String novoNome = Console.readLine("Informe o novo nome: ");
                    alunoService.alterar(umAluno, novoNome);
                    System.out.println('\n' + "Alteração de nome efetuada com sucesso!");


                }
                case 3 ->    // Remover
                {
                    int id = Console.readInt("Informe o número do aluno que você deseja remover: ");

                    try {
                        alunoService.remover(id);
                        System.out.println('\n' + "Aluno removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException | AlunoComInscricaoException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 4 -> {    // Listar tudo
                    List<Aluno> alunos = alunoService.recuperarAlunos();
                    for (Aluno aluno : alunos) {
                        System.out.println(aluno);
                    }
                }
                case 5 -> {    // Listar tudo ordenados por nome
                    List<Aluno> alunos = alunoService.recuperarAlunosOrdenadosPorNome();
                    for (Aluno aluno : alunos) {
                        System.out.println(aluno);
                    }
                }
                case 6 ->    // Sair
                        continua = false;

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}