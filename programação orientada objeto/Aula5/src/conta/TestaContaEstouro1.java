/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaContaEstouro1 {
    public static void main(String [] args){
        Conta minhaConta = new Conta();
        minhaConta.saldo = 1000.0;
        minhaConta.saca(5000); // Saldo é só 1000!
        
        System.out.println("Salddo atual: " + minhaConta.saldo);
        
    }
}
