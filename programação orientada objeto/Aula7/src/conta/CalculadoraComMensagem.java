/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class CalculadoraComMensagem extends Calculadora {
    // Mesma assinatura e mesmo retorno de Soma.somar(int, int)
    @Override
    public int somar(int a, int b) {
        int resultado = super.somar(a, b); // reaproveita o da classe mãe
        System.out.println("Somando " + a + " + " + b + " = " + resultado);
        return resultado;
    }
}
