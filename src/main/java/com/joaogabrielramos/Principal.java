package com.joaogabrielramos;

import corejava.Console;

public class Principal {
    public static void main(String[] args) {

        PrincipalAluno principalAluno = new PrincipalAluno();
        PrincipalInscricao principalInscricao = new PrincipalInscricao();
        PrincipalProfessor principalProfessor = new PrincipalProfessor();
        PrincipalTurma principalTurma = new PrincipalTurma();
        PrincipalDisciplina principalDisciplina = new PrincipalDisciplina();

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Alunos");
            System.out.println('\n' + "2. Tratar Professores");
            System.out.println('\n' + "3. Tratar Disciplinas");
            System.out.println('\n' + "4. Tratar Turmas");
            System.out.println('\n' + "5. Tratar Inscrições");
            System.out.println('\n' + "6. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    principalAluno.principal();
                }

                case 2 -> {
                    principalProfessor.principal();
                }

                case 3 -> {
                    principalDisciplina.principal();
                }

                case 4 -> {
                    principalTurma.principal();
                }

                case 5 -> {
                    principalInscricao.principal();
                }


                case 6 -> {
                    continua = false;
                }

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
