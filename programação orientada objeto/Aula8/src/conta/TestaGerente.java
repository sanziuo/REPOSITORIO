/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaGerente {
    public static void main(String[] args) {
        Gerente gerente = new Gerente();
        
        gerente.setSalario(5000);
        System.out.println(gerente.getBonificacao());
        // podemos chamar métodos do Funcionario:
        // gerente.setNome("João da Silva");

        // e também métodos do Gerente!
        // gerente.setSenha(4231);

        // (extra) para ver o resultado no console:
        // System.out.println("Nome: " + gerente.getNome());
         //gerente.autentica(4231);
        //gerente.autentica(1111);
    }
}
