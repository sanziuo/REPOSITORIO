/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class test {
    
    public static void main(String[] args) {

    Conta c1;
    
    c1 = new Conta();
    
    c1.deposita(100);

    Conta c2;
    c2 = new Conta();
    c2.deposita(200);
    
    System.out.println("Saldo atual: " + c1.saldo);
    System.out.println("Saldo atual: " + c2.saldo);
    }
}
    
