/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conta;

/**
 *
 * @author sanzi
 */
public class Conta {

    int numero;
    String titular;
    double saldo;
    double salario;
    
    // ... outros atributos ...
    void saca(double quantidade) {
        double novoSaldo = this.saldo - quantidade;
        this.saldo = novoSaldo;
    }
    
    // ... outros atributos e métodos ...
    void deposita(double quantidade) {
        this.saldo += quantidade;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
