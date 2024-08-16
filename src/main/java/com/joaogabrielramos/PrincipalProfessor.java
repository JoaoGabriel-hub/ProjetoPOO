package com.joaogabrielramos;

import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.exception.ProfessorComTurmaException;
import com.joaogabrielramos.model.Professor;
import com.joaogabrielramos.service.ProfessorService;
import corejava.Console;

import java.util.List;

public class PrincipalProfessor {
    private final ProfessorService professorService = new ProfessorService();

    public void principal() {

        String nome;
        String email;
        Professor umProfessor;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um professor");
            System.out.println("2. Alterar um professor");
            System.out.println("3. Remover um professor");
            System.out.println("4. Listar todos professores");
            System.out.println("5. Listar todos professores ordenados por nome");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine("Informe o nome do professor: ");
                    email = Console.readLine("Informe o email do professor: ");
                    umProfessor = new Professor(nome, email);
                    professorService.incluir(umProfessor);
                    System.out.println("\nProfessor número " + umProfessor.getId() + " e nome " + umProfessor.getNome() + " cadastrado com sucesso!");
                }

                case 2 ->    // Alterar
                {
                    int id = Console.readInt("Informe o número do professor que você deseja alterar: ");

                    try {
                        umProfessor = professorService.recuperarProfessorPorId(id);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    String novoNome = Console.readLine("Informe o novo nome: ");
                    professorService.alterar(umProfessor, novoNome);
                    System.out.println('\n' + "Alteração de nome efetuada com sucesso!"); }

                case 3 ->    // Remover
                {
                    int id = Console.readInt("Informe o número do professor que você deseja remover: ");

                    try {
                        professorService.remover(id);
                        System.out.println('\n' + "Professor removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException | ProfessorComTurmaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }

                case 4 -> {    // Listar tudo
                    List<Professor> professores = professorService.recuperarProfessores();
                    for (Professor professor : professores) {
                        System.out.println(professor);
                    }
                }

                case 5 -> {    // Listar tudo
                    List<Professor> professores = professorService.recuperarProfessoresOrdenadosPorNome();
                    for (Professor professor : professores) {
                        System.out.println(professor);
                    }
                }
                case 6 ->    // Sair
                        continua = false;

                default -> System.out.println('\n' + "Opção inválida!");

                }
            }
        }
    }
