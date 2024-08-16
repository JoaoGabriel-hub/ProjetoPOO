package com.joaogabrielramos;

import com.joaogabrielramos.exception.EntidadeNaoEncontradaException;
import com.joaogabrielramos.exception.DisciplinaComTurmaException;
import com.joaogabrielramos.model.Disciplina;
import com.joaogabrielramos.service.DisciplinaService;
import corejava.Console;

import java.util.List;
import java.util.ArrayList;

public class PrincipalDisciplina {
    private final DisciplinaService disciplinaService = new DisciplinaService();

    public void principal() {

        String nome;
        int cargaHoraria;
        Disciplina umDisciplina;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma disciplina");
            System.out.println("2. Alterar uma discilpina");
            System.out.println("3. Remover uma disciplina");
            System.out.println("4. Listar todas disciplinas");
            System.out.println("5. Listar todas disciplinas ordenadas por nome");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine("Informe o nome da disciplina: ");
                    cargaHoraria = Console.readInt("Informe a carga horária da disciplina: ");

                    List<Disciplina> preRequisitos = new ArrayList<>();
                    List<Disciplina> todasDisciplinas = disciplinaService.recuperarDisciplinas();

                    if (!todasDisciplinas.isEmpty()) {
                        // Capturar os pré-requisitos se não for a primeira disciplina
                        int opcaoPreReq;
                        do {
                            opcaoPreReq = Console.readInt("Informe o número da disciplina pré-requisito ou 0 para sair:");
                            if (opcaoPreReq != 0) {
                                try {
                                    Disciplina preRequisito = disciplinaService.recuperarDisciplinaPorId(opcaoPreReq);
                                    preRequisitos.add(preRequisito);
                                } catch (EntidadeNaoEncontradaException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        } while (opcaoPreReq != 0);
                    }

                    // Criar a disciplina com os dados coletados
                    umDisciplina = new Disciplina(nome, cargaHoraria, preRequisitos);
                    disciplinaService.incluir(umDisciplina);
                    System.out.println("\nDisciplina " + umDisciplina.getId() + " nome " + umDisciplina.getNome() + " e carga horária " + umDisciplina.getCargaHoraria() + " cadastrada com sucesso!" + '\n' +
                            "Pré-requisitos: " + umDisciplina.getPreRequisitos());
                }


                case 2 ->    // Alterar
                {
                    int id = Console.readInt("Informe o número da disciplina que você deseja alterar: ");

                    try {
                        umDisciplina = disciplinaService.recuperarDisciplinaPorId(id);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    String novoNome = Console.readLine("Informe o novo nome: ");
                    disciplinaService.alterar(umDisciplina, novoNome);
                    System.out.println('\n' + "Alteração de nome efetuada com sucesso!");


                }
                case 3 ->    // Remover
                {
                    int id = Console.readInt("Informe o número da disciplina que você deseja remover: ");

                    try {
                        disciplinaService.remover(id);
                        System.out.println('\n' + "Disciplina removida com sucesso!");
                    } catch (EntidadeNaoEncontradaException | DisciplinaComTurmaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 4 -> {    // Listar tudo
                    List<Disciplina> disciplinas = disciplinaService.recuperarDisciplinas();
                    for (Disciplina disciplina : disciplinas) {
                        System.out.println("Disciplina: " + disciplina.getNome() + "  Carga Horária: " + disciplina.getCargaHoraria());
                        System.out.print("Pré-requisitos: ");
                        if (disciplina.getPreRequisitos().isEmpty()) {
                            System.out.println("Nenhum");
                        } else {
                            for (Disciplina preReq : disciplina.getPreRequisitos()) {
                                System.out.print(preReq.getNome() + " ");
                            }
                            System.out.println();
                        }
                    }
                }
                case 5 -> {    // Listar tudo
                    List<Disciplina> disciplinas = disciplinaService.recuperarDisciplinasOrdenadosPorNome();
                    for (Disciplina disciplina : disciplinas) {
                        System.out.println(disciplina);
                    }
                }
                case 6 ->    // Sair
                        continua = false;

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
