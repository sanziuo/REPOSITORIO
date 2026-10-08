/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author sanzi
 */
public class TestaAlgunsMetodos {

    public static void main(String[] args) {
        // criando a conta
        Conta minhaConta;
        minhaConta = new Conta();

        // alterando os valores de minhaConta
        minhaConta.titular = "Duke";
        minhaConta.saldo = 1000;
        System.out.println("Saldo da conta: " + minhaConta.saldo);
        // saca 200 reais
        minhaConta.saca(200);
        System.out.println("Saldo da conta: " + minhaConta.saldo);
        // deposita 500 reais
        minhaConta.deposita(500);
        System.out.println("Saldo da conta: " + minhaConta.saldo);
    }
}
