/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaBonificacao {
    public static void main(String[] args) {
        ControleDeBonificacoes controle = new ControleDeBonificacoes();

        Gerente funcionario1 = new Gerente();
        funcionario1.setSalario(5000.0);
        controle.registra(funcionario1);   // 750
        System.out.println("Bonificação 1: "+ funcionario1.getBonificacao() ); // 850.0

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setSalario(1000.0);
        controle.registra(funcionario2);   // 100
        System.out.println("Bonificação 2: "+ funcionario2.getBonificacao()); // 850.0

        System.out.println("Total de bonificações: "+ controle.getTotalDeBonificacoes()); // 850.0
    }
}

