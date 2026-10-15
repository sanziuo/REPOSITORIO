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
    double saldo; //private double saldo;
    double salario;
    
    // ... outros atributos ...
    public void saca(double valor) {
        //posso sacar até saldo
        if (valor > this.saldo){
            System.out.println("Não POsso Sacar um valor maior do que o Saldo!");
        } else {
            this.saldo = this.saldo - valor;
        }
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
