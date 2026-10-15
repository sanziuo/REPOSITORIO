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

       // ENCAPSULAMENTO: atributos privados, só acessíveis pelos métodos
    private int numero;
    private String titular;
    private double saldo;
    private double salario;

    // CONSTRUTOR: obriga toda conta a ser criada já com um titular
    public Conta(String titular) {
        this.titular = titular;
        System.out.println("Construindo uma conta.");
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

    public void deposita(double quantidade) {
        this.saldo += quantidade;
    }

    // GETTERS E SETTERS
    public double getSaldo() {
        return this.saldo;
    }
    // Sem setSaldo: o saldo só muda por saca() e deposita(),
    // senão a regra do saque poderia ser burlada.

    public String getTitular() {
        return this.titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
