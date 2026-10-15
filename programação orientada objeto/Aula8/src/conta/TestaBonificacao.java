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
        // Funcionario f = new Funcionario(); // NÃO COMPILA: classe abstrata

        // Mas criar REFERÊNCIA do tipo Funcionario pode:
        Funcionario gerente = new Gerente();
        gerente.setNome("João");
        gerente.setSalario(5000.0);

        Funcionario diretor = new Diretor();
        diretor.setNome("Ana");
        diretor.setSalario(6000.0);

        Funcionario presidente = new Presidente();
        presidente.setNome("Carlos");
        presidente.setSalario(10000.0);

        ControleDeBonificacoes controle = new ControleDeBonificacoes();
        controle.registra(gerente);     // 5000 * 1.4 + 1000 = 8000
        controle.registra(diretor);     // 6000 * 1.4 + 1000 = 9400
        controle.registra(presidente);  // 10000 * 2        = 20000

        System.out.println("Total: " + controle.getTotalDeBonificacoes());
    }
}

