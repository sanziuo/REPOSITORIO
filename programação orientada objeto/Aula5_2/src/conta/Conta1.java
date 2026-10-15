/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conta;

/**
 *
 * @author sanzi
 */
public class Conta1 {

    // ENCAPSULAMENTO: atributos privados, só acessíveis pelos métodos
    private int numero;
    private String titular;
    private double saldo;
    
    //CONTRUINDO CONTA
    
    Conta1(String titular){
        this.titular = titular;
    }
 
    // GETTERS E SETTERS
    public double getSaldo() {
        return this.saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    // Get e Set Titular
    
    public String getTitular() {
        return this.titular;
    }
    
    public void setTitular(String titular) {
        this.titular = titular;
    }
    
    // NUMERO
    
    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    // MÉTODOS PÚBLICOS (a "interface" da classe)
    public void saca(double valor) {
        // posso sacar até o saldo
        if (valor > this.saldo) {
            System.out.println("Não posso sacar um valor maior do que o saldo!");
        } else {
            this.saldo = this.saldo - valor;
        }
    }
    
    // 
    
    public void deposita(double quantidade) {
        this.saldo += quantidade;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
