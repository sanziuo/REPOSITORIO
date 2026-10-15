/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class Polimorfismo {
    public static void main(String[] args) {
        Gerente gerente = new Gerente();
        Funcionario funcionario = gerente;
        funcionario.setSalario(5000);
        System.out.println(funcionario.getBonificacao());
    }
}
