/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaAcessoDireto {
    public static void main(String[] args) {
       Conta minhaConta = new Conta();
       
       // N]ao compila! você não pode acessar o atributo privado de outra classe.
       minhaConta.saldo = 1000;
    }
}
