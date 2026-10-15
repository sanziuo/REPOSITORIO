/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaAceddoDireto {
    public static void main(String[] args) {
        // Construtor com argumento: a conta já nasce com titular
        String carlos = "Carlos";
        Conta minhaConta = new Conta(carlos);
        minhaConta.setNumero(123);

        minhaConta.deposita(1000);
        System.out.println("Titular: " + minhaConta.getTitular());
        System.out.println("Saldo: " + minhaConta.getSaldo());

        minhaConta.saca(50000); // bloqueado pelo if
        minhaConta.saca(200);
        System.out.println("Saldo após saque: " + minhaConta.getSaldo());
    }
}
