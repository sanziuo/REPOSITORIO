/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaacessocompegaSaldo {
    public static void main(String[] args) {
        // Construtor com argumento: a conta já nasce com titular
        String carlos = "Carlos";
        Conta1 minhaConta = new Conta1(carlos);
        minhaConta.deposita(1000);
        System.out.println("Saldo: " + minhaConta.getSaldo());
    }
}
