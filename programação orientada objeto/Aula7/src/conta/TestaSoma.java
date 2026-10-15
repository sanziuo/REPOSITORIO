/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conta;

/**
 *
 * @author gabrielsanzio
 */
public class TestaSoma {
    public static void main(String[] args) {
        Calculadora s = new Calculadora();

        System.out.println("=== SOBRECARGA ===");
        System.out.println(s.somar(2, 3));        // somar(int, int)         -> 5
        System.out.println(s.somar(2, 3, 4));     // somar(int, int, int)    -> 9
        System.out.println(s.somar(2.5f, 1.5f));  // somar(float, float)     -> 4.0
        System.out.println(s.somar(2.5, 1.5));    // somar(double, double)   -> 4.0

        System.out.println("=== SOBRESCRITA ===");
        Calculadora s2 = new CalculadoraComMensagem();  // referência Soma, objeto SomaComMensagem
        System.out.println(s2.somar(2, 3));       // usa a versão sobrescrita
        System.out.println(s2.somar(2, 3, 4));    // não foi sobrescrito: usa o herdado
    }
}
