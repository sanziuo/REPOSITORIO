package ProgramaDesktop.Unidade2; // Pacote onde a classe está

import java.util.InputMismatchException; // Exceção para entrada de tipo errado
import java.util.Scanner;                // Classe para ler dados do teclado

public class atividade4 {

    // Scanner único usado no programa todo para ler o teclado
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Lê quantos estudantes e quantas disciplinas serão cadastrados
        int qtdAlunos = lerInteiro("Quantos estudantes tem na sala? ");
        int qtdDisciplinas = lerInteiro("Quantas disciplinas cada estudante cursa? ");

        // Vetores paralelos: o mesmo índice representa o mesmo estudante
        String[] nome = new String[qtdAlunos];      // Guarda os nomes
        String[] matricula = new String[qtdAlunos]; // Guarda as matrículas
        String[] curso = new String[qtdAlunos];     // Guarda os cursos
        String[] turma = new String[qtdAlunos];     // Guarda as turmas

        // Vetor com o nome de cada disciplina
        String[] disciplinas = new String[qtdDisciplinas];

        // Matriz 3D de notas: [estudante][disciplina][nota de 1 a 4]
        double[][][] notas = new double[qtdAlunos][qtdDisciplinas][4];

        // Cadastra o nome de cada disciplina
        for (int d = 0; d < qtdDisciplinas; d++) {
            System.out.print("Nome da disciplina " + (d + 1) + ": ");
            disciplinas[d] = sc.nextLine();
        }

        // Percorre cada estudante para cadastrar seus dados e notas
        for (int i = 0; i < qtdAlunos; i++) {
            System.out.println("\n--- Estudante " + (i + 1) + " ---");

            // Lê os dados pessoais do estudante
            System.out.print("Nome: ");
            nome[i] = sc.nextLine();
            System.out.print("Matrícula: ");
            matricula[i] = sc.nextLine();
            System.out.print("Curso: ");
            curso[i] = sc.nextLine();
            System.out.print("Turma: ");
            turma[i] = sc.nextLine();

            // Para cada disciplina, lê as 4 notas do estudante
            for (int d = 0; d < qtdDisciplinas; d++) {
                System.out.println("Notas de " + disciplinas[d] + ":");
                for (int n = 0; n < 4; n++) {
                    notas[i][d][n] = lerNota("  Nota " + (n + 1) + ": ");
                }
            }
        }

        // Exibe o relatório final com dados, notas, média e situação
        System.out.println("\n========== RELATÓRIO DA SALA ==========");
        for (int i = 0; i < qtdAlunos; i++) {

            // Mostra os dados do estudante
            System.out.println("\nNome: " + nome[i] + " | Matrícula: " + matricula[i]
                    + " | Curso: " + curso[i] + " | Turma: " + turma[i]);

            // Mostra as notas e calcula a média de cada disciplina
            for (int d = 0; d < qtdDisciplinas; d++) {
                double soma = 0; // Acumula a soma das 4 notas
                System.out.print("  " + disciplinas[d] + " -> Notas: ");
                for (int n = 0; n < 4; n++) {
                    System.out.print(notas[i][d][n] + " ");
                    soma += notas[i][d][n];
                }
                double media = soma / 4; // Média aritmética das 4 notas

                // Define a situação: aprovado se média >= 6
                String situacao = (media >= 6) ? "Aprovado" : "Reprovado";
                System.out.printf("| Média: %.2f | %s%n", media, situacao);
            }
        }

        sc.close(); // Fecha o Scanner ao final do programa
    }

    // Método que lê um número inteiro positivo, tratando entrada inválida
    static int lerInteiro(String msg) {
        while (true) { // Repete até receber um valor válido
            try {
                System.out.print(msg);
                int valor = sc.nextInt();
                sc.nextLine(); // Limpa o "Enter" que sobra no buffer
                if (valor <= 0) { // Não aceita zero nem negativo
                    System.out.println("Digite um número maior que zero.");
                    continue;
                }
                return valor; // Retorna o valor válido
            } catch (InputMismatchException e) { // Usuário digitou letra/símbolo
                System.out.println("Entrada inválida! Digite um número inteiro.");
                sc.nextLine(); // Descarta a entrada errada
            }
        }
    }

    // Método que lê uma nota entre 0 e 10, tratando entrada inválida
    static double lerNota(String msg) {
        while (true) { // Repete até receber uma nota válida
            try {
                System.out.print(msg);
                double nota = sc.nextDouble();
                sc.nextLine(); // Limpa o "Enter" que sobra no buffer
                if (nota < 0 || nota > 10) { // Valida o intervalo da nota
                    System.out.println("A nota deve estar entre 0 e 10.");
                    continue;
                }
                return nota; // Retorna a nota válida
            } catch (InputMismatchException e) { // Usuário digitou algo que não é número
                System.out.println("Entrada inválida! Use números (ex: 7,5).");
                sc.nextLine(); // Descarta a entrada errada
            }
        }
    }
}