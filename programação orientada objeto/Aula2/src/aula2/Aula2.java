/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aula2;

/**
 *
 * @author gabrielsanzio
 */
public class Aula2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // declara a idade
        int idade;
        idade = 15;

        // imprime a idade
        System.out.println(idade);

        int quatro = 2 + 2;
        int tres = 5 - 2;

        int oito = 4 * 2;
        int dezesseis = 64 / 4;

        int um = 5 % 2; // 5 dividido por 2 dá 2, e tem resto 1;
        // o operador % pega o resto da divisão inteira.

        double pi = 3.14;
        double x = 5 * 10;

        boolean verdade = true;

        int i = 5; // i recebe uma cópia do valor 5;
        int j = i; // j recebe uma cópia do valor de i;
        i = i + 1; // i vira 6, j continua 5.

        double d = 3.1415;
        // int k = d;       // não compila: double não cabe em int sem conversão
        // int k = 3.14;    // também não compila

        double d3 = 3.14;
        int i3 = (int) d3;  // casting: força a conversão, i3 vira 3 (perde a parte decimal)

        // Estrutura do if:
        // if (condicaoBooleana) {
        //     codigo;
        // }
        
        idade = 15; // reaproveita a variável já declarada
        if (idade < 18) {
            System.out.println("Não pode entrar");
        }
        
        if (idade < 18) {
            System.out.println("Não pode entrar");
        } else {
            System.out.println("Pode entrar");
        }
        
        
        idade = 15; // reaproveita a variável já declarada
        boolean amigoDoDono = true;
        if (idade < 18) {
            System.out.println("Não pode entrar");
        }
        
        if (idade < 18 && amigoDoDono == false) {
            System.out.println("Não pode entrar");
        } else {
            System.out.println("Pode entrar");
        }
        
        
        while(idade<18){
            System.out.println(idade);
            idade = idade+1;
        }
        
        int i1 = 0;
        while(i1<10){
            System.out.println(i1);
            i1 = i1 + 1;
        }
        
        for(i=0; i<10; i=i+1){
            System.out.println("olá!");
        }
        
        i = 0;
        while(i<10){
            System.out.println("olá! 2.0");
            i=i+1;
        }
        
        i = 5;
        
        System.out.println("i = " +i);
        int x1 = i++;
        System.out.println("x = " +x1);
        System.out.println("i = " +i);
        x1 = i;
        System.out.println("x = " +x1);
    }
}