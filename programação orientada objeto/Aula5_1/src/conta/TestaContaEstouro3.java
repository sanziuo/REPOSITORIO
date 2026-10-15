/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaContaEstouro3 {

    public static void main(String[] args) {
        // a Conta
        Conta minhaConta1 = new Conta();
        minhaConta1.saldo = 100;
        System.out.println("Salddo atual: " + minhaConta1.saldo);
        
        //quero mudar o saldo para -200
        double novoSaldo = -200;
        
        //testa se o novoSaldo é Válido
        if (novoSaldo < 0){ //
            System.out.println("Não Posso mudar para esse saldo");
        } else {
            minhaConta1.saldo = novoSaldo;
        }
        System.out.println("Salddo atual: " + minhaConta1.saldo);
    }
}
