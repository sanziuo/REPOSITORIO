/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author sanzi
 */
public class programa {

    public static void main(String[] args) {
        Conta minhaConta;
        minhaConta = new Conta();
        minhaConta.titular = "Duke";
        minhaConta.saldo = 1000.0;
        System.out.println("Saldo atual: " + minhaConta.saldo);
    }
}
