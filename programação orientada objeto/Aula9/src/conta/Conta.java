/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class Conta {

    // ENCAPSULAMENTO: atributos privados
    private int numero;
    private String titular;
    private double saldo;

    // CONSTRUTOR sem argumentos (permite new Conta())
    public Conta() {
        System.out.println("Construindo uma conta.");
    }

    // CONSTRUTOR com titular (sobrecarga de construtor)
    public Conta(String titular) {
        this.titular = titular;
        System.out.println("Construindo uma conta.");
    }

    // MÉTODOS DE NEGÓCIO
    public void saca(double valor) {
        if (valor > this.saldo) {
            System.out.println("Não posso sacar um valor maior do que o saldo!");
        } else {
            this.saldo -= valor;
        }
    }

    public void deposita(double quantidade) {
        this.saldo += quantidade;
    }

    public void transfere(Conta destino, double valor) {
        if (valor > this.saldo) {
            System.out.println("Saldo insuficiente para transferência!");
        } else {
            this.saldo -= valor;
            destino.deposita(valor);
        }
    }

    // GETTERS E SETTERS
    public double getSaldo() {
        return this.saldo;
    }

    // mesmo papel do getSaldo, com o nome usado nos primeiros slides
    public double pegaSaldo() {
        return this.saldo;
    }

    // Sem setSaldo: o saldo só muda por saca(), deposita() e transfere()

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

        public static void main(String[] args) {

        }
}